package com.eshop.webapp.admin.ord.service;

import java.util.List;
import java.util.Map;

import com.eshop.webapp.admin.ord.model.OrderBatchSend;
import com.eshop.webapp.admin.ord.model.OrderGoods;
import com.eshop.webapp.admin.ord.model.OrderInfo;
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
 * @since 2017年2月8日
 * @version V1.0
 * @author luowen
 */
public interface IOrderInfoService {

	public Integer insertOrderInfoInfo(OrderInfo orderInfo, User currentUser);

	public Integer deleteOrderInfoById(Integer id);

	public Integer updateOrderInfoById(OrderInfo orderInfo, User currentUser);

	public OrderInfo getOrderInfoById(Integer id);

	public List<OrderInfo> getOrderInfoList(Map<String, Object> condition);

	public PageResponse<OrderInfo> listOrderInfoPage(PageRequest pageRequest, Map<String, Object> condition);
	
	public OrderInfo searchOrderSumData(Map<String, Object> condition);

	public List<OrderGoods> getOrderGoodsList(Integer orderId);

	public List<OrderBatchSend> getOrderBatchSendList(Integer orderId);

	public PageResponse<OrderBatchSend> listOrderBatchSendPage(PageRequest pageRequest, Map<String, Object> condition);

	public OrderBatchSend searchOrderSendSumData(Map<String, Object> condition);

	public OrderBatchSend getOrderBatchSendMoreById(Integer batchId);

	public Integer approveOrderInfoById(Integer orderId, User currentUser);

	public Integer cancelOrderInfoById(Integer orderId, User currentUser);

	public Integer completeOrderInfoById(Integer orderId, User currentUser);
	
	public Integer updateOrderSendById(OrderBatchSend orderBatchSend, User currentUser);
	
	public Integer saveSendResult(OrderBatchSend orderBatchSend, User currentUser);

}
