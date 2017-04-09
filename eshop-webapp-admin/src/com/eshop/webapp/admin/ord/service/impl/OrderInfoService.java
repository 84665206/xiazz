package com.eshop.webapp.admin.ord.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.admin.ord.dao.OrderBatchSendDao;
import com.eshop.webapp.admin.ord.dao.OrderGoodsDao;
import com.eshop.webapp.admin.ord.dao.OrderInfoDao;
import com.eshop.webapp.admin.ord.model.OrderBatchSend;
import com.eshop.webapp.admin.ord.model.OrderGoods;
import com.eshop.webapp.admin.ord.model.OrderInfo;
import com.eshop.webapp.admin.ord.service.IOrderInfoService;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;


/**
 * <p>
 * TODO类概述
 * </p>
 * <p>
 * 提供如下功能：<br>
 * <1>.TODO<br>
 * <2>.TODO<br>
 * </p>
 * @since 2017年1月26日
 * @version V1.0
 * @author luowen
 */
@Service
public class OrderInfoService implements IOrderInfoService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderInfoDao orderInfoDao;
	
	@Autowired
	private OrderGoodsDao orderGoodsDao;

	@Autowired
	private OrderBatchSendDao orderBatchSendDao;
	
	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IOrderInfoService#insertOrderInfoInfo(com.eshop.webapp.admin.goods.model.OrderInfo, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer insertOrderInfoInfo(OrderInfo orderInfo, User currentUser) {
		if(orderInfo!=null && currentUser!=null){
			orderInfo.setInsertUser(currentUser.getUser_name());
			orderInfo.setUpdateUser(currentUser.getUser_name());
		}
		return orderInfoDao.insertOrderInfoInfo(orderInfo);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IOrderInfoService#deleteOrderInfoById(com.eshop.webapp.admin.goods.model.OrderInfo, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer deleteOrderInfoById(Integer id) {
		// 使用逻辑删除，物理删除暂时为空
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IOrderInfoService#updateOrderInfoById(com.eshop.webapp.admin.goods.model.OrderInfo, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer updateOrderInfoById(OrderInfo orderInfo, User currentUser) {
		if(orderInfo!=null && currentUser!=null){
			orderInfo.setUpdateUser(currentUser.getUser_name());
			orderInfo.setUpdateTime(new Date());
		}
		return orderInfoDao.updateOrderInfoById(orderInfo);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IOrderInfoService#getOrderInfoById(java.lang.Integer)
	 */
	@Override
	public OrderInfo getOrderInfoById(Integer id) {
		return orderInfoDao.getOrderInfoById(id);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IOrderInfoService#getOrderInfoList(java.util.Map)
	 */
	@Override
	public List<OrderInfo> getOrderInfoList(Map<String, Object> condition) {
		return orderInfoDao.selectOrderInfoList(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IOrderInfoService#listOrderInfoPage(com.eshop.webapp.admin.util.PageRequest, java.util.Map)
	 */
	@Override
	public PageResponse<OrderInfo> listOrderInfoPage(PageRequest pageRequest, Map<String, Object> condition) {
		return orderInfoDao.listOrderInfoPage(pageRequest, condition);
	}

	@Override
	public OrderInfo searchOrderSumData(Map<String, Object> condition){
		return orderInfoDao.searchOrderSumData(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#getOrderGoodsList(java.util.Map)
	 */
	@Override
	public List<OrderGoods> getOrderGoodsList(Integer orderId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("orderId", orderId);
		return orderGoodsDao.selectOrderGoodsList(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#getOrderBatchSendList(java.util.Map)
	 */
	@Override
	public List<OrderBatchSend> getOrderBatchSendList(Integer orderId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("orderId", orderId);
		return orderBatchSendDao.selectOrderBatchSendList(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#listOrderBatchSendPage(com.eshop.webapp.admin.util.PageRequest, java.util.Map)
	 */
	@Override
	public PageResponse<OrderBatchSend> listOrderBatchSendPage(PageRequest pageRequest, Map<String, Object> condition) {
		return orderBatchSendDao.listOrderBatchSendPage(pageRequest, condition);
	}

	@Override
	public OrderBatchSend searchOrderSendSumData(Map<String, Object> condition){
		return orderBatchSendDao.searchOrderSendSumData(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#getOrderBatchSendMoreById(java.lang.Integer)
	 */
	@Override
	public OrderBatchSend getOrderBatchSendMoreById(Integer batchId) {
		return orderBatchSendDao.getOrderBatchSendMoreById(batchId);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#approveOrderInfoById(com.eshop.webapp.admin.ord.model.OrderInfo, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer approveOrderInfoById(Integer orderId, User currentUser) {
		if(orderId==null || currentUser==null){
			return 0;
		}
		Integer result = 0;
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(orderId);
		orderInfo.setUpdateUser(currentUser.getUser_name());
		orderInfo.setUpdateTime(new Date());
		
		//1、更新订单主表信息
		result = orderInfoDao.approveOrderInfoById(orderInfo);
		
		if(result>0){
			OrderBatchSend orderBatchSend = new OrderBatchSend();
			orderBatchSend.setOrderId(orderId);
			orderBatchSend.setUpdateUser(currentUser.getUser_name());
			orderBatchSend.setUpdateTime(new Date());
			
			//2、更新定配送表
			orderBatchSendDao.approveOrderBatchSendById(orderBatchSend);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#cancelOrderInfoById(com.eshop.webapp.admin.ord.model.OrderInfo, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer cancelOrderInfoById(Integer orderId, User currentUser) {
		if(orderId==null || currentUser==null){
			return 0;
		}
		Integer result = 0;
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(orderId);
		orderInfo.setUpdateUser(currentUser.getUser_name());
		orderInfo.setUpdateTime(new Date());
		
		//1、更新订单主表信息
		result = orderInfoDao.cancelOrderInfoById(orderInfo);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#completeOrderInfoById(java.lang.Integer, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer completeOrderInfoById(Integer orderId, User currentUser) {
		if(orderId==null || currentUser==null){
			return 0;
		}
		Integer result = 0;
		
		//检查一下该订单下是否存在 已配送 但未完成配送的 配送单，存在则报错
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("orderId", orderId);
		List<OrderBatchSend> batchSendList = orderBatchSendDao.selectOrderBatchSendList(condition);
		for (OrderBatchSend ele : batchSendList) {
			if(ele.getShippingStatus()!=null && ele.getShippingStatus()==2){
				//存在 已配送 的配送单，直接退出
				return result;
			}
		}
		
		//1、更新订单配送表，将未完成配送的配送单进行作废
		for (OrderBatchSend ele : batchSendList) {
			if(ele.getShippingStatus()!=null && ele.getShippingStatus()==1){
				//存在 待配送 的配送单，更新成 卖家作废
				OrderBatchSend obj = new OrderBatchSend();
				obj.setId(ele.getId());
				obj.setShippingStatus(8);//卖家作废 状态
				obj.setFromShippingStatus(1);//前状态为 待配送
				obj.setUpdateUser(currentUser.getUser_name());
				obj.setUpdateTime(new Date());
				
				//更新数据库
				orderBatchSendDao.updateOrderBatchSendById(obj);
			}
		}

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(orderId);
		orderInfo.setUpdateUser(currentUser.getUser_name());
		orderInfo.setUpdateTime(new Date());
		
		//2、更新订单主表 订单状态
		result = orderInfoDao.completeOrderInfoById(orderInfo);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#updateOrderSendById(com.eshop.webapp.admin.ord.model.OrderBatchSend, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer updateOrderSendById(OrderBatchSend orderBatchSend, User currentUser) {
		if(orderBatchSend!=null && currentUser!=null){
			orderBatchSend.setUpdateUser(currentUser.getUser_name());
			orderBatchSend.setUpdateTime(new Date());
		}
		return orderBatchSendDao.updateOrderBatchSendById(orderBatchSend);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.ord.service.IOrderInfoService#saveSendResult(com.eshop.webapp.admin.ord.model.OrderBatchSend, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer saveSendResult(OrderBatchSend orderBatchSend, User currentUser) {
		//1、先更新本 配送单数据
		Integer result = updateOrderSendById(orderBatchSend, currentUser);
		
		//2、再更新订单主表相关信息
		List<OrderBatchSend> list = orderBatchSendDao.searchPayInfoByBatchId(orderBatchSend.getId());
		if(list!=null && list.size()>0){
			int sendPayStatusCnt = 0;
			int sendShippingStatusCnt = 0;
			BigDecimal orderNeedPayAmount = new BigDecimal("0");
			BigDecimal orderPayAmount = new BigDecimal("0");
			
			for (OrderBatchSend ele : list) {
				if(ele.getPayStatus()!=null && ele.getPayStatus()==1){
					sendPayStatusCnt ++;
				}
				if(ele.getShippingStatus()!=null && ele.getShippingStatus()>2){
					sendShippingStatusCnt ++;
				}
				if(ele.getNeedPayAmount()!=null){
					orderNeedPayAmount = orderNeedPayAmount.add(ele.getNeedPayAmount());
				}
				if(ele.getPayAmount()!=null){
					orderPayAmount = orderPayAmount.add(ele.getPayAmount());
				}
			}

			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setId(list.get(0).getOrderId());
			orderInfo.setUpdateUser(currentUser.getUser_name());
			orderInfo.setUpdateTime(new Date());
			orderInfo.setPayTime(new Date());
			orderInfo.setPayAmount(orderPayAmount);
			orderInfo.setNeedPayAmount(orderNeedPayAmount);
			
			if(sendPayStatusCnt == list.size()){
				orderInfo.setPayStatus(2);	//全部完成支付
			}else if(sendPayStatusCnt > 0){
				orderInfo.setPayStatus(1);	//部分完成支付
			}
			if(sendShippingStatusCnt == list.size()){
				orderInfo.setShippingStatus(2);	//全部完成配送
			}else if(sendShippingStatusCnt > 0){
				orderInfo.setShippingStatus(1);	//部分完成配送
			}
			
			//更新订单主表信息
			orderInfoDao.updateOrderInfoById(orderInfo);
		}
		
		return result;
	}

}
