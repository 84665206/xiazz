package com.eshop.webapp.admin.crm.service;

import java.util.List;
import java.util.Map;

import com.eshop.webapp.admin.crm.model.Customer;
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
 * @since 2017年2月8日
 * @version V1.0
 * @author luowen
 */
public interface ICustomerService {

	public Integer insertCustomerInfo(Customer customer, User currentUser);

	public Integer deleteCustomerById(Integer id);

	public Integer updateCustomerById(Customer customer, User currentUser);

	public Customer getCustomerById(Integer id);

	public List<Customer> getCustomerList(Map<String, Object> condition);

	public PageResponse<Customer> listCustomerPage(PageRequest pageRequest, Map<String, Object> condition);

}
