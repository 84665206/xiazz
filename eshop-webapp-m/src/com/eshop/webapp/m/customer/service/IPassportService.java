package com.eshop.webapp.m.customer.service;

import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.customer.model.Customer;

/** 
 * 登录、注册
 * @author yangmeng
 * @version 创建时间：2017年2月2日 下午1:27:33 
 * 
 */
public interface IPassportService {
	/**
	 * 去登录
	 * @param customer_name
	 * @param password
	 * @return
	 */
	Customer login(String customer_name, String password);
	
	/**
	 * 去注册
	 * @param customer_name
	 * @param password
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	ResponseResult regist(Customer customer);
}
