package com.eshop.webapp.m.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.m.order.dao.OrderGoodsDao;
import com.eshop.webapp.m.order.dao.OrderInfoDao;
import com.eshop.webapp.m.order.model.OrderGoods;
import com.eshop.webapp.m.order.model.OrderInfo;
import com.eshop.webapp.m.order.service.IOrderService;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderGoodsDao orderGoodsDao;

	@Override
	public List<OrderInfo> myOrderInfoList(Integer customer_id, Integer order_tt) {
		
		return orderInfoDao.myOrderInfoList(customer_id, order_tt);
	}

	@Override
	public OrderInfo getOrderInfoByOrderSn(String order_sn) {
		
		return orderInfoDao.selectOrderInfoByOrderSn(order_sn);
	}

	@Override
	public OrderInfo getOrderDetailByOrderId(Integer order_id) {
		
		OrderInfo orderInfo = orderInfoDao.selectOrderInfoByOrderId(order_id);
		if(orderInfo==null||orderInfo.getId()==null){
			return orderInfo;
		}
		
		List<OrderGoods> orderGoodsList = orderGoodsDao.selectOrderGoodsList(orderInfo.getId());
		if(orderGoodsList!=null&&orderGoodsList.size()>0){
			orderInfo.setOrder_goodsList(orderGoodsList);
		}
		
		return orderInfo;
	}
	
	

}
