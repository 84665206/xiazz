package com.eshop.webapp.m.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eshop.webapp.m.model.ConfigValue;
import com.eshop.webapp.m.model.SessionCustomer;
import com.eshop.webapp.m.sys.service.ISysService;
import com.eshop.webapp.m.util.session.CookieWrapper;
import com.eshop.webapp.m.util.session.SessionConstant;

/** 
 * 基础controller
 * @author yangmeng
 * @version 
 * 
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISysService sysService;
	
	private static Map<String, ConfigValue> configInfoMap = new HashMap<String, ConfigValue>();
	
	/**
	 * 获取当前已登录用户id
	 */
	protected Integer getCurrentCustomerId() {
		SessionCustomer c = this.getCurrentCustomer();

		if (c == null)
			return null;

		return c.getCustomer_id();
	}
	
	/**
	 * 获取当前已登录用户名
	 */
	protected String getCurrentCustomerName() {
		SessionCustomer c = this.getCurrentCustomer();

		if (c == null)
			return null;

		return c.getCustomer_name();
	}
	
	/**
	 * 获取当前已登录用户
	 */
	protected SessionCustomer getCurrentCustomer() {
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		return (SessionCustomer) request.getSession().getAttribute(SessionConstant.SESSION_CUSTOMER);
	}
	
	/**
	 * request
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 获得当前线程的cookie信息
	 *
	 * @return
	 */
	protected CookieWrapper getCookies(HttpServletRequest request , HttpServletResponse response) {
		return new CookieWrapper(request, response);
	}
	
	/**
	 * 跳转至首页
	 * @param request
	 * @param response
	 */
	protected void INDEX(HttpServletRequest request, HttpServletResponse response){
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 跳转至登录页
	 * @param request
	 * @param response
	 */
	protected void LOGIN(HttpServletRequest request, HttpServletResponse response){
		try {
			response.sendRedirect("/passport/login.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询公共参数
	 * @param key
	 * @return
	 */
	protected ConfigValue getConfigValue(String key){
		if(configInfoMap.isEmpty()){
			configInfoMap=sysService.getAllConfig();
		}
		
		ConfigValue configValue=configInfoMap.get(key);
		
		return configValue;
	}
}
