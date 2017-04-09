package com.eshop.webapp.m.passport.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.m.base.BaseController;
import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.customer.model.Customer;
import com.eshop.webapp.m.customer.service.IPassportService;
import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.util.AuthCode;
import com.eshop.webapp.m.util.CartUtil;
import com.eshop.webapp.m.util.LoginAfterUtil;
import com.eshop.webapp.m.util.SessionUtil;
import com.eshop.webapp.m.util.session.CookieConstant;
import com.eshop.webapp.m.util.session.SessionConstant;

/**
 * 登录、注册
 * @author yangmeng
 *
 */
@Controller
@RequestMapping("/passport")
public class PassportController extends BaseController {
	
	@Autowired
	private IPassportService passportService;
	
	@RequestMapping(value="/login")
    public String login(@RequestParam(value="going_url", required=false)String going_url,
    		HttpServletRequest request, HttpServletResponse response){
		if(getCurrentCustomer()!=null){
			INDEX(request, response);
			return null;
		}
		
		if(StringUtils.isNotBlank(going_url)&&going_url.indexOf("passport")<0){
			String url="/";
			try {
				url = URLEncoder.encode(going_url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			getCookies(request, response).addCookie(CookieConstant.LOGIN_RETURN_URL, url, 3600);
		}
		
		return "passport/login";
	}
	
	@RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
		
		CartUtil.clearSessionCart(request, response);
		request.getSession().invalidate();
		
		INDEX(request, response);
		return null;
	}
	
	@RequestMapping(value="/regist")
    public String regist(HttpServletRequest request, HttpServletResponse response){
		if(getCurrentCustomer()!=null){
			INDEX(request, response);
			return null;
		}
		return "passport/regist";
	}
	
	@RequestMapping(value="/regist/authcode")
    public void registAuthCode(HttpServletRequest request, HttpServletResponse response){
		try {
			AuthCode authCode = new AuthCode();
			String code=authCode.getCode();
			SessionUtil.setSessionAttribute(request, SessionConstant.REGIST_IMAGE_CODE, code);
			authCode.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/tologin",method=RequestMethod.POST)
    public ResponseResult tologin(@RequestParam("customer_name")String customer_name, 
    		@RequestParam("password")String password, 
    		@RequestParam(value="goto_url", required=false)String goto_url, 
    		HttpServletRequest request, HttpServletResponse response){
		ResponseResult responseResult=new ResponseResult();
		try {
			if(getCurrentCustomer()!=null){
				INDEX(request, response);
				return null;
			}
			if(StringUtils.isBlank(customer_name)){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("请输入登录用户名");
				return responseResult;
			}
			if(StringUtils.isBlank(password)){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("请输入登录密码");
				return responseResult;
			}
			
			if(StringUtils.isNotBlank(goto_url)&&goto_url.indexOf("passport")<0){
				String url="/";
				try {
					url = URLEncoder.encode(goto_url, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				getCookies(request,response).addCookie(CookieConstant.LOGIN_RETURN_URL, url, 3600);
			}
			
			Customer customer=passportService.login(customer_name, password);
			if (customer == null) {
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("用户名或密码错误，请重新输入");
				return responseResult;
			} else {
				LoginAfterUtil.afterLogin(customer, request, response);
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("登录成功");
				return responseResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.EXCEPTION.code);
			responseResult.setMsg(ResponseResultEnum.EXCEPTION.msg);
		}
		
		return responseResult;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/toregist",method=RequestMethod.POST)
    public ResponseResult toregist(String customer_name, 
    		String customer_pwd,
    		String regist_auth_code,
    		@RequestParam(value="customer_true_name", required=false)String customer_true_name,
    		@RequestParam(value="shop_name", required=false)String shop_name,
    		@RequestParam(value="shop_address", required=false)String shop_address,
    		@RequestParam(value="recruit_code", required=false)String recruit_code,
    		HttpServletRequest request, 
    		HttpServletResponse response){
		ResponseResult responseResult=new ResponseResult();
		try {
			if(StringUtils.isBlank(customer_name)){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("用户名不能为空");
				return responseResult;
			}
			if(StringUtils.isBlank(customer_pwd)){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("密码不能为空");
				return responseResult;
			}
			if(StringUtils.isBlank(regist_auth_code)){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("验证码不能为空");
				return responseResult;
			}
			
			String sess_regist_code=(String)SessionUtil.getSessionAttribute(request, SessionConstant.REGIST_IMAGE_CODE);
			if(sess_regist_code==null){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("验证码不能为空");
				return responseResult;
			}
			SessionUtil.removeSessionAttribute(request, SessionConstant.REGIST_IMAGE_CODE);
			
			if(!sess_regist_code.equals(regist_auth_code)){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("验证码错误，请重新输入");
				return responseResult;
			}
			
			Customer customer= new Customer();
			customer.setCustomer_name(customer_name);
			customer.setCustomer_pwd(customer_pwd);
			if(StringUtils.isNotBlank(recruit_code)){
				customer.setRecruit_code(recruit_code);
			}
			if(StringUtils.isNotBlank(customer_true_name)){
				customer.setCustomer_true_name(customer_true_name);
			}
			if(StringUtils.isNotBlank(shop_name)){
				customer.setShop_name(shop_name);
			}
			if(StringUtils.isNotBlank(shop_address)){
				customer.setShop_address(shop_address);
			}
			customer.setInsert_user(getCurrentCustomerName());
			responseResult=passportService.regist(customer);
			if(responseResult.getCode().equals(ResponseResultEnum.SUCCESS.code)){
				LoginAfterUtil.afterLogin(customer, request, response);
			}
			
			return responseResult;
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.EXCEPTION.code);
			responseResult.setMsg(ResponseResultEnum.EXCEPTION.msg);
		}
		
		return responseResult;
	}
	
	/**
	 * 登录成功,跳转回登录前页面
	 *
	 * @return
	 * 
	 */
	@RequestMapping(value="/returnurl")
	public String return_url(HttpServletRequest request,HttpServletResponse response) {
		
		final String url = this.getCookies(request, response).getCookieValue(
				CookieConstant.LOGIN_RETURN_URL);
		logger.debug("return URL = {}", url);
		String goingToURL="";
		if (this.getCurrentCustomer()!=null&&StringUtils.isNotBlank(url)) {
			try {
				goingToURL = URLDecoder.decode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
		}
		
		if(StringUtils.isBlank(goingToURL)){
			goingToURL="/";
		}
		
		try {
			response.sendRedirect(goingToURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
