package com.eshop.webapp.m.customer.service;

import java.util.List;

import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.customer.model.Customer;
import com.eshop.webapp.m.customer.model.CustomerDelivery;
import com.eshop.webapp.m.model.SessionCustomer;
import com.eshop.webapp.m.order.model.AreaInfo;



/**
 * 用户信息
 * @author 
 * yangmeng
 * 
 * */
public interface ICustomerService {
	
	Customer getCustomerInfoById(Integer customer_id);
	
	List<CustomerDelivery> getCustomerDeliveryList(Integer customer_id);
	
	Integer setDefaultDelivery(Integer delivery_id, SessionCustomer customer);
	
	Integer deleteCustomerDeliveryById(Integer delivery_id, SessionCustomer customer);
	
	@SuppressWarnings("rawtypes")
	ResponseResult addCustomerDelivery(CustomerDelivery customerDelivery);
	
	List<AreaInfo> getAreaInfoList(Integer parent_id, Integer area_type);
}
