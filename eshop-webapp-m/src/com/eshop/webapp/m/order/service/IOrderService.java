package com.eshop.webapp.m.order.service;

import java.util.List;

import com.eshop.webapp.m.order.model.OrderInfo;

/**
 * 订单服务
 * @author Administrator
 *
 */
public interface IOrderService {
	
	List<OrderInfo> myOrderInfoList(Integer customer_id, Integer order_tt);
	
	OrderInfo getOrderInfoByOrderSn(String order_sn);
	
	OrderInfo getOrderDetailByOrderId(Integer order_id);
}
