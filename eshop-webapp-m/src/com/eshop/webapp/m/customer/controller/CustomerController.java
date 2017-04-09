package com.eshop.webapp.m.customer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.m.base.BaseController;
import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.customer.model.Customer;
import com.eshop.webapp.m.customer.model.CustomerDelivery;
import com.eshop.webapp.m.customer.service.ICustomerService;
import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.order.model.AreaInfo;
import com.eshop.webapp.m.order.model.OrderInfo;
import com.eshop.webapp.m.order.service.IOrderService;

/** 
 * @author yangmeng
 * @version 创建时间：2017年2月15日 上午9:43:34 
 * 
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{
	
	@Autowired
	private IOrderService orderService;
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping(value="/index")
    public String index(
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		try {
			if(getCurrentCustomer()==null){
				LOGIN(request, response);
				return null;
			}
			
			model.addAttribute("customer_name", getCurrentCustomerName());
			model.addAttribute("customer_true_name", getCurrentCustomer().getCustomer_true_name());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "customer/index";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param order_tt 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/order/list")
    public String orderList(
    		HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value="order_tt", required=false)Integer order_tt,
    		Model model){
		try {
			if(getCurrentCustomer()==null){
				LOGIN(request, response);
				return null;
			}
			
			if(order_tt==null){
				
			}
			
			List<OrderInfo> orderList=orderService.myOrderInfoList(getCurrentCustomerId(), order_tt);
			
			model.addAttribute("customer_name", getCurrentCustomerName());
			model.addAttribute("orderList", orderList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "customer/myorder";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param order_tt 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/order/detail")
    public String orderDetail(
    		HttpServletRequest request, HttpServletResponse response,
    		Integer order_id,
    		Model model){
		try {
			if(getCurrentCustomer()==null){
				LOGIN(request, response);
				return null;
			}
			
			if(order_id==null){
				return null;
			}
			
			OrderInfo orderInfo = orderService.getOrderDetailByOrderId(order_id);
			
			model.addAttribute("orderInfo", orderInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "customer/myorderdetail";
	}
	
	/**
	 * 客户信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/info")
    public String info(
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		try {
			if(getCurrentCustomer()==null){
				LOGIN(request, response);
				return null;
			}
			
			Customer customer=customerService.getCustomerInfoById(getCurrentCustomerId());
			
			model.addAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "customer/info";
	}
	
	/**
	 * 客户收货地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delivery")
    public String customerDelivery(
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		try {
			if(getCurrentCustomer()==null){
				LOGIN(request, response);
				return null;
			}
			
			List<CustomerDelivery> customerDeliveries=customerService.getCustomerDeliveryList(getCurrentCustomerId());
			model.addAttribute("customerDeliveries", customerDeliveries);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "customer/mydelivery";
	}
	
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/delivery/default",method=RequestMethod.POST)
    public ResponseResult defaultDelivery(@RequestParam("delivery_id")Integer delivery_id, 
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		ResponseResult responseResult = new ResponseResult();
		if(delivery_id==null||delivery_id==0){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("设置的默认地址不能为空");
			return responseResult;
		}
		
		Integer utotal=customerService.setDefaultDelivery(delivery_id, getCurrentCustomer());
		if(utotal!=null&&utotal>0){
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
			responseResult.setMsg("设置默认地址成功");
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("设置默认地址失败");
			return responseResult;
		}
		
		return responseResult;
	}
	
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/delivery/delete",method=RequestMethod.POST)
    public ResponseResult deleteDelivery(@RequestParam("delivery_id")Integer delivery_id, 
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		ResponseResult responseResult = new ResponseResult();
		if(delivery_id==null||delivery_id==0){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("删除的地址不能为空");
			return responseResult;
		}
		
		Integer utotal=customerService.deleteCustomerDeliveryById(delivery_id, getCurrentCustomer());
		if(utotal!=null&&utotal>0){
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
			responseResult.setMsg("删除成功");
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("删除失败");
			return responseResult;
		}
		
		return responseResult;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/delivery/add",method=RequestMethod.POST)
	public ResponseResult delivery(Model model,
			HttpServletRequest request, HttpServletResponse response,
			String  consignee,
			String province,
			String city,
			String district,
			String address, 
			@RequestParam(value="tel", required=false)String tel, 
			@RequestParam(value="mobile", required=false)String mobile
			){
		ResponseResult responseResult = new ResponseResult();
		if(StringUtils.isBlank(consignee)){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("收件人不能 为空");
			return responseResult;
		}
		if(StringUtils.isBlank(province)){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("省份不能 为空");
			return responseResult;
		}
		if(StringUtils.isBlank(city)){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("城市不能 为空");
			return responseResult;
		}
		if(StringUtils.isBlank(district)){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("区县不能 为空");
			return responseResult;
		}
		if(StringUtils.isBlank(address)){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("详细地址不能 为空");
			return responseResult;
		}
		if(StringUtils.isBlank(tel)&&StringUtils.isBlank(mobile)){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("电话和手机号码不能同时 为空");
			return responseResult;
		}
		
		try {
			CustomerDelivery customerDelivery= new CustomerDelivery();
			customerDelivery.setCustomer_id(getCurrentCustomerId());
			customerDelivery.setConsignee(consignee);
			customerDelivery.setProvince(province);
			customerDelivery.setCity(city);
			customerDelivery.setDistrict(district);
			customerDelivery.setAddress(address);
			customerDelivery.setTel(tel);
			customerDelivery.setMobile(mobile);
			customerDelivery.setInsert_user(getCurrentCustomerName());
			
			responseResult=customerService.addCustomerDelivery(customerDelivery);
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.EXCEPTION.code);
			responseResult.setMsg("添加地址异常");
		}
		
		return responseResult;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="/delivery/area",method=RequestMethod.POST)
	public ResponseResult area(Model model,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="parent_id", required=false)Integer parent_id, 
			Integer area_type){
		ResponseResult responseResult = new ResponseResult();
		
		try {
			List<AreaInfo> areaInfos=customerService.getAreaInfoList(parent_id, area_type);
			if(areaInfos==null||areaInfos.size()==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("没有查到地区");
			}else{
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setData(areaInfos);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.EXCEPTION.code);
			responseResult.setMsg("查询地区异常");
		}
		
		return responseResult;
	}
}
