package com.eshop.webapp.m.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.base.ServiceException;
import com.eshop.webapp.m.customer.dao.CustomerDeliveryDao;
import com.eshop.webapp.m.customer.model.CustomerDelivery;
import com.eshop.webapp.m.enums.OrdeTypeEnum;
import com.eshop.webapp.m.enums.OrderStatusEnum;
import com.eshop.webapp.m.enums.PayStatusEnum;
import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.enums.ShippingStatusEnum;
import com.eshop.webapp.m.goods.model.GoodsInfoVo;
import com.eshop.webapp.m.goods.model.GoodsSku;
import com.eshop.webapp.m.goods.service.impl.GoodsService;
import com.eshop.webapp.m.model.Cart;
import com.eshop.webapp.m.model.CartItem;
import com.eshop.webapp.m.model.SessionCustomer;
import com.eshop.webapp.m.order.model.OrderInfo;
import com.eshop.webapp.m.order.service.IShoppingService;
import com.eshop.webapp.m.pay.dao.PayMethodDao;
import com.eshop.webapp.m.pay.model.PayMethod;
import com.eshop.webapp.m.util.CartUtil;
import com.eshop.webapp.m.util.ShoppingUtil;

/** 
 * @author yangmeng
 * @version 创建时间：2017年2月1日 上午10:53:30 
 * 
 */
@Service
public class ShoppingService implements IShoppingService{
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CustomerDeliveryDao customerDeliveryDao;
	@Autowired
	private PayMethodDao payMethodDao;
	@Autowired
	private CoreOrderService coreOrderService;
	
	
	@Override
	public Cart addGoodsToCart(Cart cart, CartItem item) {
		List<String> messages=new ArrayList<String>();
		if (cart == null || item == null
				|| item.getSku_id()==null) {
			messages.add("加入购物车失败(0001)");
			cart.setMessages(messages);
			return cart;
		}
		
		GoodsInfoVo goodsInfoVo=goodsService.getGoodsInfoVo(item.getSku_id());
		if(goodsInfoVo!=null){
			GoodsSku goodsSku=goodsInfoVo.getMain_goods();
			CartUtil.fillGoodsToItem(item, goodsSku);
			if(StringUtils.isBlank(item.getMessage())){
				cart.putItemIntoCart(item);
			}
		}
		
		return cart;
	}

	@Override
	public OrderInfo cartToOrder(Cart cart, OrderInfo orderInfo) {
		if(orderInfo==null){
			throw new ServiceException("订单信息不能为空(0001)");
		}
		
		List<CustomerDelivery> customerDeliveries=customerDeliveryDao.selectCustomerDeliveryByCustomerId(cart.getSessionCustomer().getCustomer_id());
		
		if (customerDeliveries != null && customerDeliveries.size() > 0) {
			orderInfo.setCustomer_deliveries(customerDeliveries);
		}
		
		CustomerDelivery defaultDelivery=orderInfo.getCustomer_delivery();
		if(defaultDelivery==null||!customerDeliveries.contains(defaultDelivery)){
			if (orderInfo.getCustomer_deliveries() != null && orderInfo.getCustomer_deliveries().size() > 0) {
				defaultDelivery=orderInfo.getCustomer_deliveries().get(0);
			}
		}
		
		ShoppingUtil.setOrderFromCart(orderInfo, cart);
		
		/**
		 * 计算运费、支付方式
		 */
		if(defaultDelivery!=null){
			setOrderPayMethod(orderInfo, defaultDelivery);
		}
		
		/**
		 * 计算订单金额
		 */
		calOrderMoney(orderInfo);
		
		return orderInfo;
	}
	
	/**
	 * 设置订单支付方式、运费
	 * @param orderInfo
	 * @param customerDelivery
	 */
	private void setOrderPayMethod(OrderInfo orderInfo, CustomerDelivery customerDelivery){
		if(ShoppingUtil.checkOrderDelivery(customerDelivery)){
			orderInfo.setCustomer_delivery(customerDelivery);
			orderInfo.setOrder_shipping_fee(0.0);
			orderInfo.setPay_way(null);
			orderInfo.setPay_method(null);
			orderInfo.setPay_methods(null);
			
			
			List<PayMethod> payMethods=payMethodDao.selectPayMethodList();
			orderInfo.setPay_methods(payMethods);
		}
	}
	
	/**
	 * 计算订单金额
	 * @param orderInfo
	 */
	private void calOrderMoney(OrderInfo orderInfo){
		BigDecimal goods_amount=new BigDecimal(orderInfo.getGoods_amount());
		BigDecimal order_shipping_fee=new BigDecimal(orderInfo.getOrder_shipping_fee());
		BigDecimal order_amount=goods_amount.add(order_shipping_fee);
		BigDecimal pay_amount=BigDecimal.ZERO;
		
		BigDecimal need_pay_amount=order_amount.subtract(pay_amount);
		orderInfo.setOrder_amount(order_amount.doubleValue());
		orderInfo.setNeed_pay_amount(need_pay_amount.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		orderInfo.setDing_amount(BigDecimal.valueOf(orderInfo.getNeed_pay_amount()).multiply(BigDecimal.valueOf(OrderInfo.DING_RATE)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
	}

	@Override
	public OrderInfo createOrder(Cart cart, OrderInfo orderInfo) {
		if(cart==null||orderInfo==null||cart.getItems().size()==0){
			throw new ServiceException("参数错误(0001)");
		}
		if(orderInfo.getCustomer_id()==null){
			throw new ServiceException("参数错误(0002)");
		}
		
		orderInfo.setReceiver_name(orderInfo.getCustomer_delivery().getConsignee());
		orderInfo.setReceiver_province(orderInfo.getCustomer_delivery().getProvince());
		orderInfo.setReceiver_city(orderInfo.getCustomer_delivery().getCity());
		orderInfo.setReceiver_district(orderInfo.getCustomer_delivery().getDistrict());
		orderInfo.setReceiver_addr(orderInfo.getCustomer_delivery().getAddress());
		orderInfo.setReceiver_mobile(orderInfo.getCustomer_delivery().getMobile());
		orderInfo.setReceiver_phone(orderInfo.getCustomer_delivery().getTel());
		orderInfo.setShipping_status(ShippingStatusEnum.NO_SHIPPING.status);
		
		ShoppingUtil.setOrderFromCart(orderInfo, cart);
		
		if(orderInfo.getPay_way()==null||
				orderInfo.getPay_methods().size()==0){
			throw new ServiceException("参数错误(0003)");
		}
		
		PayMethod payMethod=payMethodDao.selectPayMethodById(orderInfo.getPay_way());
		if(payMethod!=null){
			orderInfo.setPay_method(payMethod);
		}else{
			throw new ServiceException("参数错误(0004)");
		}
		
		if(orderInfo.getNeed_pay_amount()<=0){
			throw new ServiceException("参数错误(0005)");
		}
		
		BigDecimal need_pay_amount=BigDecimal.valueOf(orderInfo.getNeed_pay_amount());
		
		calOrderMoney(orderInfo);
		
		if(need_pay_amount.compareTo(BigDecimal.valueOf(orderInfo.getNeed_pay_amount()))!=0){
			throw new ServiceException("参数错误(0006)");
		}
		
		orderInfo.setPay_status(PayStatusEnum.NO_PAY.status);
		orderInfo.setOrder_status(OrderStatusEnum.CHUSHI.status);
		orderInfo.setOrder_type(OrdeTypeEnum.NOMAL.type);
		
		coreOrderService.createOrder(orderInfo);
		
		return orderInfo;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseResult setDefaultDelivery(OrderInfo orderInfo, Integer delivery_id,
			SessionCustomer customer) {
		ResponseResult responseResult = new ResponseResult();
		if(orderInfo.getCustomer_deliveries()==null||orderInfo.getCustomer_deliveries().size()==0){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("设置默认地址失败(00001)");
			return responseResult;
		}
		
		CustomerDelivery selectDelivery=null;
		for (CustomerDelivery customerDelivery : orderInfo.getCustomer_deliveries()) {
			if(customerDelivery.getId().equals(delivery_id)){
				selectDelivery=customerDelivery;
				selectDelivery.setIs_default(1);
				break;
			}
		}
		
		if(selectDelivery==null){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("设置默认地址失败(00002)");
			return responseResult;
		}
		
		selectDelivery.setCustomer_id(customer.getCustomer_id());
		int stotal=customerDeliveryDao.cancelAllDefaultDelivery(customer.getCustomer_id());
		if(stotal==0){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("设置默认地址失败(00003)");
			return responseResult;
		}
		
		int dtotal=customerDeliveryDao.setDefaultDelivery(delivery_id, customer.getCustomer_name(), customer.getCustomer_id());
		if(dtotal==0){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("设置默认地址失败(00004)");
			return responseResult;
		}
		
		setOrderPayMethod(orderInfo, selectDelivery);
		calOrderMoney(orderInfo);
		
		responseResult.setCode(ResponseResultEnum.SUCCESS.code);
		responseResult.setMsg("操作成功");
		
		return responseResult;
	}

}
