package com.eshop.webapp.m.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.customer.dao.CustomerDao;
import com.eshop.webapp.m.customer.dao.CustomerDeliveryDao;
import com.eshop.webapp.m.customer.model.Customer;
import com.eshop.webapp.m.customer.model.CustomerDelivery;
import com.eshop.webapp.m.customer.service.ICustomerService;
import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.model.SessionCustomer;
import com.eshop.webapp.m.order.dao.AreaInfoDao;
import com.eshop.webapp.m.order.model.AreaInfo;

/**
 * 客户服务
 * @author yangmeng
 *
 */
@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerDeliveryDao customerDeliveryDao;
	@Autowired
	private AreaInfoDao areaInfoDao;
	
	
	@Override
	public Customer getCustomerInfoById(Integer customer_id) {
	
		return customerDao.selectCustomerInfoById(customer_id);
	}

	@Override
	public List<CustomerDelivery> getCustomerDeliveryList(Integer customer_id) {
		
		return customerDeliveryDao.selectCustomerDeliveryByCustomerId(customer_id);
	}

	@Override
	public Integer setDefaultDelivery(Integer delivery_id, SessionCustomer customer) {
		int stotal=customerDeliveryDao.cancelAllDefaultDelivery(customer.getCustomer_id());
		if(stotal==0){
			return 0;
		}
		
		return customerDeliveryDao.setDefaultDelivery(delivery_id, customer.getCustomer_name(), customer.getCustomer_id());
	}

	@Override
	public Integer deleteCustomerDeliveryById(Integer delivery_id,
			SessionCustomer customer) {
		
		return customerDeliveryDao.deleteCustomerDeliveryById(delivery_id, customer.getCustomer_name(), customer.getCustomer_id());
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseResult addCustomerDelivery(CustomerDelivery customerDelivery) {
		ResponseResult responseResult = new ResponseResult();
		
		int stotal=customerDeliveryDao.selectCustomerDeliveryCount(customerDelivery.getCustomer_id());
		if(stotal>10){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("最多添加10个地址");
			return responseResult;
		}
		
		int ito=customerDeliveryDao.insertCustomerDelivery(customerDelivery);
		if(ito==0){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("添加地址失败");
			return responseResult;
		}
		
		responseResult.setCode(ResponseResultEnum.SUCCESS.code);
		responseResult.setMsg("添加成功");
		
		return responseResult;
	}
	

	@Override
	public List<AreaInfo> getAreaInfoList(Integer parent_id, Integer area_type) {
		
		return areaInfoDao.selectAreaInfoList(parent_id, area_type);
	}

}
