package com.eshop.webapp.m.customer.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.customer.model.CustomerDelivery;

/** 
 * @author yangmeng
 * @version 创建时间：2017年2月10日 下午6:20:52 
 * 
 */
@Repository
public class CustomerDeliveryDao extends BaseDao<CustomerDelivery>{
	
	public List<CustomerDelivery> selectCustomerDeliveryByCustomerId(Integer customer_id){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("customer_id", customer_id);
		
		return this.selectList("CustomerDeliveryMapper.selectCustomerDeliveryByCustomerId", condition);
	}
	
	public Integer insertCustomerDelivery(CustomerDelivery customerDelivery){
		
		return this.insert("CustomerDeliveryMapper.insertCustomerDelivery", customerDelivery);
	}
	
	public Integer selectCustomerDeliveryCount(Integer customer_id){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("customer_id", customer_id);
		
		return (Integer)this.selectOne("CustomerDeliveryMapper.selectCustomerDeliveryCount", condition);
	}
	
	public Integer setDefaultDelivery(Integer delivery_id, String customer_name, Integer customer_id){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("delivery_id", delivery_id);
		condition.put("customer_name", customer_name);
		condition.put("customer_id", customer_id);
		
		return this.update("CustomerDeliveryMapper.setDefaultDelivery", condition);
	}
	
	public Integer cancelAllDefaultDelivery(Integer customer_id){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("customer_id", customer_id);
		
		return this.update("CustomerDeliveryMapper.cancelAllDefaultDelivery", condition);
	}
	
	public Integer deleteCustomerDeliveryById(Integer delivery_id, String customer_name, Integer customer_id){
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("delivery_id", delivery_id);
		condition.put("customer_name", customer_name);
		condition.put("customer_id", customer_id);
		
		return this.update("CustomerDeliveryMapper.deleteCustomerDeliveryById", condition);
	}
}
