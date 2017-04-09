package com.eshop.webapp.m.customer.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.customer.dao.CustomerDao;
import com.eshop.webapp.m.customer.model.Customer;
import com.eshop.webapp.m.customer.service.IPassportService;
import com.eshop.webapp.m.enums.ResponseResultEnum;

/** 
 * @author yangmeng
 * @version 创建时间：2017年2月2日 下午1:33:18 
 * 
 */
@Service
public class PassportService implements IPassportService{
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public Customer login(String customer_name, String password) {
		String pwd = DigestUtils.md5Hex(password).substring(8, 24);
		Customer customer=customerDao.selectCustomerLoginInfo(customer_name, pwd);
		
		if(customer!=null){
			//记录登录日志
		}
		
		return customer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseResult regist(Customer customer) {
		ResponseResult responseResult = new ResponseResult();
		int total=customerDao.selectCustomerCount(customer.getCustomer_name());
		if(total>0){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("用户名已经存在，请重新输入用户名");
			return responseResult;
		}
		
		String pwd = DigestUtils.md5Hex(customer.getCustomer_pwd()).substring(8, 24);
		customer.setCustomer_pwd(pwd);
		customerDao.insertCustomerInfo(customer);
		if(customer.getId()==null){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("用户创建失败");
			return responseResult;
		}
		
		responseResult.setData(customer);
		return responseResult;
	}

}
