package com.eshop.webapp.admin.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.admin.base.BaseController;
import com.eshop.webapp.admin.base.ResponseResult;
import com.eshop.webapp.admin.crm.model.Customer;
import com.eshop.webapp.admin.crm.service.ICustomerService;
import com.eshop.webapp.admin.enums.ResponseResultEnum;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;
import com.eshop.webapp.admin.util.XStreamWrapper;

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
@RequestMapping("/crm")
@Controller
public class CustomerController extends BaseController {

	@Autowired
	private ICustomerService customerService;
	

	/**
	 * 进入商品SKU信息界面
	 *
	 * @return
	 */
	@RequestMapping("customer/page")
	public String customerPage() {
		return "/crm/customerInfo";
	}

	/**
	 * 查询商品SKU信息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("customer/list")
	public PageResponse<Customer> customerList(@ModelAttribute PageRequest pageRequest, 
			@RequestParam(value="customerName", required=false)String customerName,
			@RequestParam(value="customerTrueName", required=false)String customerTrueName
			) {
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(customerName)){
			condition.put("customerName", customerName);
		}
		if(StringUtils.isNotBlank(customerTrueName)){
			condition.put("customerTrueName", customerTrueName);
		}
		PageResponse<Customer> pageResponse = this.customerService.listCustomerPage(pageRequest, condition);
		
        return pageResponse;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("customer/create")
	public ResponseResult customerCreate(
			@RequestParam(value="customerName", required=false)String customerName,
			@RequestParam(value="customerPwd", required=false)String customerPwd,
			@RequestParam(value="customerTrueName", required=false)String customerTrueName,
			@RequestParam(value="shopName", required=false)String shopName,
			@RequestParam(value="shopAddress", required=false)String shopAddress,
			@RequestParam(value="isValid", required=false)Integer isValid,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();
		Customer customer = new Customer();
		customer.setIsDelete(0);

		if(StringUtils.isNotBlank(customerName)){
			customer.setCustomerName(customerName);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("客户名称不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(customerPwd)){
			customer.setCustomerPwd(customerPwd);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("客户密码不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(customerTrueName)){
			customer.setCustomerTrueName(customerTrueName);
		}
		if(StringUtils.isNotBlank(shopName)){
			customer.setShopName(shopName);
		}
		if(StringUtils.isNotBlank(shopAddress)){
			customer.setShopAddress(shopAddress);
		}
		if(isValid != null){
			customer.setIsValid(isValid);
		}
		
		try {
			this.logger.debug(XStreamWrapper.toXML(customer));
			Integer itotal=this.customerService.insertCustomerInfo(customer, getCurrentUser());
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("客户新增失败");
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("客户新增成功");
			}
		}
		catch (Exception e) {
			logger.error("客户新增-发生系统异常", e);
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg(e.getMessage());
			return responseResult;
		}
		
		return responseResult;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("customer/edit")
	public ResponseResult customerEdit(
			@RequestParam(value="id", required=false)Integer id,
			@RequestParam(value="customerName", required=false)String customerName,
			@RequestParam(value="customerPwd", required=false)String customerPwd,
			@RequestParam(value="customerTrueName", required=false)String customerTrueName,
			@RequestParam(value="shopName", required=false)String shopName,
			@RequestParam(value="shopAddress", required=false)String shopAddress,
			@RequestParam(value="isValid", required=false)Integer isValid,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();
		Customer customer = new Customer();

		if(id != null){
			customer.setId(id);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("ID不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(customerName)){
			customer.setCustomerName(customerName);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("客户编码不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(customerTrueName)){
			customer.setCustomerTrueName(customerTrueName);
		}
		if(StringUtils.isNotBlank(shopName)){
			customer.setShopName(shopName);
		}
		if(StringUtils.isNotBlank(shopAddress)){
			customer.setShopAddress(shopAddress);
		}
		if(isValid != null){
			customer.setIsValid(isValid);
		}
		
		try {
			this.logger.debug(XStreamWrapper.toXML(customer));
			Integer itotal=this.customerService.updateCustomerById(customer, getCurrentUser());
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("客户修改失败");
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("客户修改成功");
			}
		}
		catch (Exception e) {
			logger.error("客户修改-发生系统异常", e);
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg(e.getMessage());
			return responseResult;
		}
		
		return responseResult;
	}
	
}
