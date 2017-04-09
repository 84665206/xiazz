package com.eshop.webapp.m.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.order.model.OrderInfo;

/** 
 * 订单
 * @author yangmeng
 * @version 创建时间：2017年2月9日 下午7:01:06 
 * 
 */
@Repository
public class OrderInfoDao extends BaseDao<OrderInfo>{
	
	public Integer insertOrderInfo(OrderInfo orderInfo){
		
		return this.insert("OrderInfoMapper.insertOrderInfo", orderInfo);
	}
	
	public Integer updateOrderInfoOrderSn(String order_sn, Integer id){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("order_sn", order_sn);
		condition.put("id", id);
		
		return this.update("OrderInfoMapper.updateOrderInfoOrderSn", condition);
	}
	
	public OrderInfo selectOrderInfoByOrderSn(String order_sn){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("order_sn", order_sn);
		
		return (OrderInfo)this.selectOne("OrderInfoMapper.selectOrderInfoByOrderSn", condition);
	}
	
	public OrderInfo selectOrderInfoByOrderId(Integer order_id){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("order_id", order_id);
		
		return (OrderInfo)this.selectOne("OrderInfoMapper.selectOrderInfoByOrderId", condition);
	}
	
	public List<OrderInfo> myOrderInfoList(Integer customer_id, Integer order_tt){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("customer_id", customer_id);
		condition.put("order_tt", order_tt);
		
		return this.selectList("OrderInfoMapper.myOrderInfoList", condition);
	}
}
