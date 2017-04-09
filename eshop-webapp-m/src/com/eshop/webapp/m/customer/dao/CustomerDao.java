package com.eshop.webapp.m.customer.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.customer.model.Customer;

/**
 * 用户信息
 *
 * @author yangmeng
 */
@Repository
public class CustomerDao extends BaseDao<Customer> {
	
	/**
	 * 查询用户登录信息
	 */
	public Customer selectCustomerLoginInfo(String customer_name, String customer_pwd){
		Map<String, Object> condition= new HashMap<String, Object>();
		condition.put("customer_name", customer_name);
		condition.put("customer_pwd", customer_pwd);
		
		return (Customer)this.selectOne("CustomerMapper.selectCustomerLoginInfo", condition);
	}
	
	/**
	 * 查询用户信息 by id
	 */
	public Customer selectCustomerInfoById(Integer customer_id){
		Map<String, Object> condition= new HashMap<String, Object>();
		condition.put("customer_id", customer_id);
		
		return (Customer)this.selectOne("CustomerMapper.selectCustomerInfoById", condition);
	}
	
	/**
	 * 查询用户是否存在
	 * @param customer_name
	 * @return
	 */
	public Integer selectCustomerCount(String customer_name){
		Map<String, Object> condition= new HashMap<String, Object>();
		condition.put("customer_name", customer_name);
		
		return (Integer)this.selectOne("CustomerMapper.selectCustomerCount", condition);
	}
	
	public Integer insertCustomerInfo(Customer customer){
		
		return this.insert("CustomerMapper.insertCustomerInfo", customer);
	}
}