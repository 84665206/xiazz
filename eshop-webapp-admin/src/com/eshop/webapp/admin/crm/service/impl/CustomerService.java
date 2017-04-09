package com.eshop.webapp.admin.crm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.admin.crm.dao.CustomerDao;
import com.eshop.webapp.admin.crm.model.Customer;
import com.eshop.webapp.admin.crm.service.ICustomerService;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;


/**
 * <p>
 * TODO类概述
 * </p>
 * <p>
 * 提供如下功能：<br>
 * <1>.TODO<br>
 * <2>.TODO<br>
 * </p>
 * @since 2017年1月26日
 * @version V1.0
 * @author luowen
 */
@Service
public class CustomerService implements ICustomerService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerDao customerDao;
	
	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.ICustomerService#insertCustomerInfo(com.eshop.webapp.admin.goods.model.Customer, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer insertCustomerInfo(Customer customer, User currentUser) {
		if(customer!=null && currentUser!=null){
			customer.setInsertUser(currentUser.getUser_name());
			customer.setUpdateUser(currentUser.getUser_name());
		}
		return customerDao.insertCustomerInfo(customer);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.ICustomerService#deleteCustomerById(com.eshop.webapp.admin.goods.model.Customer, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer deleteCustomerById(Integer id) {
		// 使用逻辑删除，物理删除暂时为空
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.ICustomerService#updateCustomerById(com.eshop.webapp.admin.goods.model.Customer, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer updateCustomerById(Customer customer, User currentUser) {
		if(customer!=null && currentUser!=null){
			customer.setUpdateUser(currentUser.getUser_name());
			customer.setUpdateTime(new Date());
		}
		return customerDao.updateCustomerById(customer);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.ICustomerService#getCustomerById(java.lang.Integer)
	 */
	@Override
	public Customer getCustomerById(Integer id) {
		return customerDao.getCustomerById(id);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.ICustomerService#getCustomerList(java.util.Map)
	 */
	@Override
	public List<Customer> getCustomerList(Map<String, Object> condition) {
		return customerDao.selectCustomerList(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.ICustomerService#listCustomerPage(com.eshop.webapp.admin.util.PageRequest, java.util.Map)
	 */
	@Override
	public PageResponse<Customer> listCustomerPage(PageRequest pageRequest, Map<String, Object> condition) {
		return customerDao.listCustomerPage(pageRequest, condition);
	}

}
