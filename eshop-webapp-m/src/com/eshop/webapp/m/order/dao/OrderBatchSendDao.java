package com.eshop.webapp.m.order.dao;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.order.model.OrderBatchSend;

/** 
 * @author yangmeng
 * @version 创建时间：2017年2月23日 下午6:51:37 
 * 
 */
@Repository
public class OrderBatchSendDao extends BaseDao<OrderBatchSend>{
	
	public Integer insertOrderBatchSend(OrderBatchSend orderBatchSend){
		
		return this.insert("OrderBatchSendMapper.insertOrderBatchSend", orderBatchSend);
	}
}
