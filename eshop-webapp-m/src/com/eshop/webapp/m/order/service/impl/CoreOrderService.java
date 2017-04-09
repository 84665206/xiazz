package com.eshop.webapp.m.order.service.impl;



import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.base.ServiceException;
import com.eshop.webapp.m.enums.PayStatusEnum;
import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.enums.ShippingLevelEnum;
import com.eshop.webapp.m.enums.ShippingWayEnum;
import com.eshop.webapp.m.order.dao.OrderBatchSendDao;
import com.eshop.webapp.m.order.dao.OrderGoodsDao;
import com.eshop.webapp.m.order.dao.OrderInfoDao;
import com.eshop.webapp.m.order.model.OrderBatchSend;
import com.eshop.webapp.m.order.model.OrderGoods;
import com.eshop.webapp.m.order.model.OrderInfo;
import com.eshop.webapp.m.util.DateUtils;
import com.eshop.webapp.m.util.ShoppingUtil;

/** 
 * 下单
 * @author yangmeng
 * @version 创建时间：2017年2月8日 下午6:48:05 
 * 
 */
@Service
@Transactional(readOnly = false)
public class CoreOrderService {
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	@Autowired
	private OrderBatchSendDao orderBatchSendDao;
	
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=false)
	public ResponseResult createOrder(OrderInfo orderInfo){
		
		ResponseResult responseResult =new ResponseResult();
		
		orderInfoDao.insertOrderInfo(orderInfo);
		if(orderInfo.getId()==null){
			throw new ServiceException("下单失败了(0001)");
		}
		String order_sn = ShoppingUtil.genOrderCode(orderInfo.getId());
		orderInfo.setOrder_sn(order_sn);
		
		int uto=orderInfoDao.updateOrderInfoOrderSn(order_sn, orderInfo.getId());
		if(uto==0){
			throw new ServiceException("下单失败了(0002)");
		}
		
		// 2. save orderPro
		Collections.sort(orderInfo.getOrder_goodsList(), new Comparator<OrderGoods>() { // 排序防止死锁
					public int compare(OrderGoods o1, OrderGoods o2) {
						return Integer.valueOf(o1.getSku_id()).compareTo(
								Integer.valueOf(o2.getSku_id()));
					}
				});
		for (OrderGoods orderGoods : orderInfo.getOrder_goodsList()) {
			orderGoods.setOrder_sn(orderInfo.getOrder_sn());
			orderGoods.setOrder_id(orderInfo.getId());
			orderGoodsDao.insertOrderGoods(orderGoods);			// 下单减掉库存
			
			int updateRow= orderGoodsDao.reduceStock(orderGoods.getSku_num(), orderGoods.getSku_id());
			
			if (updateRow <= 0) { // 库存不足
				throw new ServiceException(orderGoods.getSku_name()+"库存不足(0003)");
			}
			
			//3 pici
			if(orderInfo.getShipping_way().equals(ShippingWayEnum.MULTIPLE.way)){
				createBatchSendThird(orderGoods, orderInfo);
			}else {
				createBatchSendOne(orderGoods, orderInfo);
			}
		}
		
		responseResult.setCode(ResponseResultEnum.SUCCESS.code);
		return responseResult;
	}
	
	/**
	 * 一次性配送
	 * @param orderGoods
	 */
	private void createBatchSendOne(OrderGoods orderGoods, OrderInfo orderInfo){
		OrderBatchSend batchSend_first= new OrderBatchSend();
		batchSend_first.setSku_id(orderGoods.getSku_id());
		batchSend_first.setSku_name(orderGoods.getSku_name());
		batchSend_first.setSku_num(orderGoods.getSku_num());
		batchSend_first.setGoods_code(orderGoods.getGoods_code());
		batchSend_first.setGoods_origin(orderGoods.getGoods_origin());
		batchSend_first.setGoods_origin_name(orderGoods.getGoods_origin_name());
		batchSend_first.setGoods_shipping(orderGoods.getGoods_shipping());
		batchSend_first.setGoods_shipping_name(orderGoods.getGoods_shipping_name());
		batchSend_first.setGoods_spec(orderGoods.getGoods_spec());
		batchSend_first.setGoods_spec_name(orderGoods.getGoods_spec_name());
		batchSend_first.setGoods_varieties(orderGoods.getGoods_varieties());
		batchSend_first.setGoods_varieties_name(orderGoods.getGoods_varieties_name());
		batchSend_first.setBatch_sn(ShoppingUtil.genSendCode(System.currentTimeMillis()));
		batchSend_first.setSend_no(1);
		batchSend_first.setPay_status(PayStatusEnum.NO_PAY.status);
		batchSend_first.setSale_price(orderGoods.getSale_price());
		batchSend_first.setShare_price(orderGoods.getShare_price());
		batchSend_first.setOg_id(orderGoods.getId());
		batchSend_first.setNeed_pay_amount(BigDecimal.valueOf(batchSend_first.getSale_price()).multiply(BigDecimal.valueOf(batchSend_first.getSku_num())).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		if(ShippingLevelEnum.TODAY.level.equals(orderInfo.getShipping_level())){
			batchSend_first.setNeed_send_time(DateUtils.DateToString(new Date(), DateUtils.YYYY_MM_DD));
		}else{
			batchSend_first.setNeed_send_time(DateUtils.DateToString(DateUtils.DateAdd(new Date(), 1), DateUtils.YYYY_MM_DD));
		}
		orderBatchSendDao.insertOrderBatchSend(batchSend_first);
		if(batchSend_first.getId()==null){
			throw new ServiceException("生成发货单失败(0007)");
		}
	}
	
	/**
	 * 3次配送
	 * @param orderGoods
	 */
	private void createBatchSendThird(OrderGoods orderGoods, OrderInfo orderInfo){
		int num = orderGoods.getSku_num();
		int first=orderGoods.getSku_num()/3;
		int secend=orderGoods.getSku_num()/3;
		int third=num-first-secend;
		
		OrderBatchSend batchSend_first= new OrderBatchSend();
		batchSend_first.setSku_id(orderGoods.getSku_id());
		batchSend_first.setSku_name(orderGoods.getSku_name());
		batchSend_first.setSku_num(first);
		batchSend_first.setGoods_code(orderGoods.getGoods_code());
		batchSend_first.setGoods_origin(orderGoods.getGoods_origin());
		batchSend_first.setGoods_origin_name(orderGoods.getGoods_origin_name());
		batchSend_first.setGoods_shipping(orderGoods.getGoods_shipping());
		batchSend_first.setGoods_shipping_name(orderGoods.getGoods_shipping_name());
		batchSend_first.setGoods_spec(orderGoods.getGoods_spec());
		batchSend_first.setGoods_spec_name(orderGoods.getGoods_spec_name());
		batchSend_first.setGoods_varieties(orderGoods.getGoods_varieties());
		batchSend_first.setGoods_varieties_name(orderGoods.getGoods_varieties_name());
		batchSend_first.setBatch_sn(ShoppingUtil.genSendCode(System.currentTimeMillis()));
		batchSend_first.setSend_no(1);
		batchSend_first.setPay_status(PayStatusEnum.NO_PAY.status);
		batchSend_first.setSale_price(orderGoods.getSale_price());
		batchSend_first.setShare_price(orderGoods.getShare_price());
		batchSend_first.setOg_id(orderGoods.getId());
		batchSend_first.setNeed_pay_amount(BigDecimal.valueOf(batchSend_first.getSale_price()).multiply(BigDecimal.valueOf(batchSend_first.getSku_num())).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		if(ShippingLevelEnum.TODAY.level.equals(orderInfo.getShipping_level())){
			batchSend_first.setNeed_send_time(DateUtils.DateToString(new Date(), DateUtils.YYYY_MM_DD));
		}else{
			batchSend_first.setNeed_send_time(DateUtils.DateToString(DateUtils.DateAdd(new Date(), 1), DateUtils.YYYY_MM_DD));
		}
		orderBatchSendDao.insertOrderBatchSend(batchSend_first);
		if(batchSend_first.getId()==null){
			throw new ServiceException("生成发货单失败(0004)");
		}
		
		OrderBatchSend batchSend_secend= new OrderBatchSend();
		batchSend_secend.setSku_id(orderGoods.getSku_id());
		batchSend_secend.setSku_name(orderGoods.getSku_name());
		batchSend_secend.setSku_num(secend);
		batchSend_secend.setGoods_code(orderGoods.getGoods_code());
		batchSend_secend.setGoods_origin(orderGoods.getGoods_origin());
		batchSend_secend.setGoods_origin_name(orderGoods.getGoods_origin_name());
		batchSend_secend.setGoods_shipping(orderGoods.getGoods_shipping());
		batchSend_secend.setGoods_shipping_name(orderGoods.getGoods_shipping_name());
		batchSend_secend.setGoods_spec(orderGoods.getGoods_spec());
		batchSend_secend.setGoods_spec_name(orderGoods.getGoods_spec_name());
		batchSend_secend.setGoods_varieties(orderGoods.getGoods_varieties());
		batchSend_secend.setGoods_varieties_name(orderGoods.getGoods_varieties_name());
		batchSend_secend.setBatch_sn(ShoppingUtil.genSendCode(System.currentTimeMillis()));
		batchSend_secend.setSend_no(2);
		batchSend_secend.setPay_status(PayStatusEnum.NO_PAY.status);
		batchSend_secend.setSale_price(orderGoods.getSale_price());
		batchSend_secend.setShare_price(orderGoods.getShare_price());
		batchSend_secend.setOg_id(orderGoods.getId());
		batchSend_secend.setNeed_pay_amount(BigDecimal.valueOf(batchSend_secend.getSale_price()).multiply(BigDecimal.valueOf(batchSend_secend.getSku_num())).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		if(ShippingLevelEnum.TODAY.level.equals(orderInfo.getShipping_level())){
			batchSend_first.setNeed_send_time(DateUtils.DateToString(new Date(), DateUtils.YYYY_MM_DD));
		}else{
			batchSend_first.setNeed_send_time(DateUtils.DateToString(DateUtils.DateAdd(new Date(), 1), DateUtils.YYYY_MM_DD));
		}
		orderBatchSendDao.insertOrderBatchSend(batchSend_secend);
		if(batchSend_first.getId()==null){
			throw new ServiceException("生成发货单失败(0005)");
		}
		
		OrderBatchSend batchSend_third= new OrderBatchSend();
		batchSend_third.setSku_id(orderGoods.getSku_id());
		batchSend_third.setSku_name(orderGoods.getSku_name());
		batchSend_third.setSku_num(third);
		batchSend_third.setGoods_code(orderGoods.getGoods_code());
		batchSend_third.setGoods_origin(orderGoods.getGoods_origin());
		batchSend_third.setGoods_origin_name(orderGoods.getGoods_origin_name());
		batchSend_third.setGoods_shipping(orderGoods.getGoods_shipping());
		batchSend_third.setGoods_shipping_name(orderGoods.getGoods_shipping_name());
		batchSend_third.setGoods_spec(orderGoods.getGoods_spec());
		batchSend_third.setGoods_spec_name(orderGoods.getGoods_spec_name());
		batchSend_third.setGoods_varieties(orderGoods.getGoods_varieties());
		batchSend_third.setGoods_varieties_name(orderGoods.getGoods_varieties_name());
		batchSend_third.setBatch_sn(ShoppingUtil.genSendCode(System.currentTimeMillis()));
		batchSend_third.setSend_no(3);
		batchSend_third.setPay_status(PayStatusEnum.NO_PAY.status);
		batchSend_third.setSale_price(orderGoods.getSale_price());
		batchSend_third.setShare_price(orderGoods.getShare_price());
		batchSend_third.setOg_id(orderGoods.getId());
		batchSend_third.setNeed_pay_amount(BigDecimal.valueOf(batchSend_third.getSale_price()).multiply(BigDecimal.valueOf(batchSend_third.getSku_num())).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		if(ShippingLevelEnum.TODAY.level.equals(orderInfo.getShipping_level())){
			batchSend_first.setNeed_send_time(DateUtils.DateToString(new Date(), DateUtils.YYYY_MM_DD));
		}else{
			batchSend_first.setNeed_send_time(DateUtils.DateToString(DateUtils.DateAdd(new Date(), 1), DateUtils.YYYY_MM_DD));
		}
		orderBatchSendDao.insertOrderBatchSend(batchSend_third);
		if(batchSend_first.getId()==null){
			throw new ServiceException("生成发货单失败(0006)");
		}
	}
}
