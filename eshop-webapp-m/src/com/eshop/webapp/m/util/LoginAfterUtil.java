package com.eshop.webapp.m.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshop.webapp.m.customer.model.Customer;
import com.eshop.webapp.m.model.SessionCustomer;
import com.eshop.webapp.m.util.session.CookieConstant;
import com.eshop.webapp.m.util.session.CookieWrapper;
import com.eshop.webapp.m.util.session.SessionConstant;

public class LoginAfterUtil {
	/****
	 * 登录后设置
	 * @param user 用户
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param quick_login_chanel 快捷登录渠道 包括：alipay 支付宝。
	 */
	public static void afterLogin(Customer customer,HttpServletRequest request,HttpServletResponse response){
		if(SessionUtil.getSessionAttribute(request, SessionConstant.SESSION_CUSTOMER)!=null){//如果之前有登陆用户信息清除购物车
			  SessionCustomer olduser=(SessionCustomer)SessionUtil.getSessionAttribute(request, SessionConstant.SESSION_CUSTOMER);
			  if(!olduser.getCustomer_name().equals(customer.getCustomer_name())){
			     //CartUtil.clearSessionCart(request, response);
			  }
		}
		request.getSession().invalidate(); //清除所有session
		
		CookieWrapper cookiewrapper = new CookieWrapper(request,response);
		
		// 登录数据正确，将userName 、userId保存到session中
		SessionCustomer sessionCustomer = new SessionCustomer(); 
		sessionCustomer.setCustomer_id(customer.getId());
		sessionCustomer.setCustomer_name(customer.getCustomer_name());
		sessionCustomer.setOpen_id(customer.getOpen_id());
		sessionCustomer.setCustomer_true_name(customer.getCustomer_true_name());
		sessionCustomer.setShop_name(customer.getShop_name());
		sessionCustomer.setShop_address(customer.getShop_address());
		sessionCustomer.setRecruit_code(customer.getRecruit_code());
		
		//保存用户信息 session
		SessionUtil.setSessionAttribute(request, SessionConstant.SESSION_CUSTOMER, sessionCustomer);
		
		cookiewrapper.addCookie(CookieConstant.CUSTOMER_NAME, sessionCustomer.getCustomer_name(),24 * 3600);
	}
	
}
