package com.eshop.webapp.m.order.controller;

import java.util.ArrayList;
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
import com.eshop.webapp.m.customer.model.CustomerDelivery;
import com.eshop.webapp.m.customer.service.ICustomerService;
import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.enums.ShippingWayEnum;
import com.eshop.webapp.m.model.Cart;
import com.eshop.webapp.m.model.SelectValue;
import com.eshop.webapp.m.order.model.OrderInfo;
import com.eshop.webapp.m.order.service.IShoppingService;
import com.eshop.webapp.m.util.CartUtil;
import com.eshop.webapp.m.util.IPUtil;
import com.eshop.webapp.m.util.SessionUtil;
import com.eshop.webapp.m.util.session.SessionConstant;


/** 
 * @author yangmeng
 * @version 创建时间：2017年2月10日 下午2:54:32 
 * 
 */
@Controller
@RequestMapping("/checkout")
public class ShoppingController extends BaseController{
	
	@Autowired
	private IShoppingService shoppingService;
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping(value="/view")
	public String view(Model model,
			HttpServletRequest request, HttpServletResponse response){
		
		Integer customer_id = getCurrentCustomerId();
		if(customer_id==null){
			LOGIN(request, response);
			return null;
		}
		OrderInfo orderInfo = new OrderInfo();
		try {
			
			Object orderS = SessionUtil.getSessionAttribute(request,
					SessionConstant.SESSION_ORDER + "_" + customer_id);
			if(orderS!=null){
				orderInfo=(OrderInfo)orderS;
			}
			
			Cart cart = CartUtil.getSessionCart(request);
			if (cart == null || cart.isEmpty()) {
				INDEX(request, response);
				return null;
			}
			orderInfo.setOrder_ip(IPUtil.getIpAddr(request));
			
			orderInfo=shoppingService.cartToOrder(cart, orderInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			SessionUtil.removeSessionAttribute(request, SessionConstant.SESSION_ORDER + "_" + customer_id);
			orderInfo=null;
		}
		
		if(orderInfo!=null){
			model.addAttribute("orderInfo", orderInfo);
			SessionUtil.setSessionAttribute(request, SessionConstant.SESSION_ORDER + "_" + customer_id, orderInfo);
		}else {
			INDEX(request, response);
			return null;
		}
		
		//地址库为空，先去添加地址库
		if(orderInfo.getCustomer_deliveries()==null||orderInfo.getCustomer_deliveries().size()==0){
			return "checkout/orderdelivery";
		}
		
		ShippingWayEnum shippingWayEnum[]=ShippingWayEnum.values();
		List<SelectValue> selectValues=new ArrayList<SelectValue>();
		for (ShippingWayEnum w : shippingWayEnum) {
			SelectValue selectValue=new SelectValue();
			selectValue.setName(w.name);
			selectValue.setValue(w.way);
			selectValues.add(selectValue);
		}
		model.addAttribute("selectValues", selectValues);
		
		return "checkout/view";
	}
	
	@RequestMapping(value="/submit")
	public String submit(Model model,
			HttpServletRequest request, HttpServletResponse response,
			Integer pay_way,
			String shipping_way,
			String customer_memo){
		Integer customer_id = getCurrentCustomerId();
		if(customer_id==null){
			LOGIN(request, response);
			return null;
		}
		
		if(pay_way==null){
			INDEX(request, response);
			return null;
		}
		
		if(StringUtils.isBlank(shipping_way)){
			INDEX(request, response);
			return null;
		}
		OrderInfo orderInfo = new OrderInfo();
		try {
			
			Object orderS = SessionUtil.getSessionAttribute(request,
					SessionConstant.SESSION_ORDER + "_" + customer_id);
			if(orderS!=null){
				orderInfo=(OrderInfo)orderS;
			}
			
			Cart cart=CartUtil.getSessionCart(request);
			
			orderInfo.setPay_way(pay_way);
			orderInfo.setCustomer_memo(customer_memo);
			orderInfo.setCustomer_id(getCurrentCustomerId());
			orderInfo.setCustomer_name(getCurrentCustomerName());
			orderInfo.setShipping_way(shipping_way);
			
			if (orderS==null ||cart == null || cart.isEmpty()) {
				INDEX(request, response);
				return null;
			}
			shoppingService.createOrder(cart, orderInfo);
			
			model.addAttribute("orderInfo", orderInfo);
			
			SessionUtil.removeSessionAttribute(request, SessionConstant.SESSION_ORDER + "_" + customer_id);
			CartUtil.clearSessionCart(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
		}
		
		return "checkout/submit";
	}

	/**
	 * 客户收货地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delivery/page")
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
		
		return "checkout/orderdelivery";
	}
	
	/**
	 * 设置订单默认地址
	 * @param delivery_id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/delivery/default",method=RequestMethod.POST)
    public ResponseResult defaultDelivery(@RequestParam("delivery_id")Integer delivery_id, 
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		ResponseResult responseResult = new ResponseResult();
		try {
			if(delivery_id==null||delivery_id==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("设置的默认地址不能为空");
				return responseResult;
			}
			OrderInfo orderInfo =null;
			Object orderS = SessionUtil.getSessionAttribute(request,
					SessionConstant.SESSION_ORDER + "_" + getCurrentCustomerId());
			if(orderS==null){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("订单信息不能为空");
				return responseResult;
			}
			orderInfo=(OrderInfo)orderS;
			
			responseResult = shoppingService.setDefaultDelivery(orderInfo, delivery_id, getCurrentCustomer());
			if(!responseResult.getCode().equals(ResponseResultEnum.SUCCESS.code)){
				return responseResult;
			}
			SessionUtil.setSessionAttribute(request, SessionConstant.SESSION_ORDER + "_" + getCurrentCustomerId(), orderInfo);
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("操作异常");
		}
		
		
		return responseResult;
	}
	
	/**
	 * 清空购物车、订单信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/order/clear",method=RequestMethod.POST)
    public ResponseResult clear(
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		ResponseResult responseResult = new ResponseResult();
		try {
			SessionUtil.removeSessionAttribute(request, SessionConstant.SESSION_ORDER + "_" + getCurrentCustomerId());
			CartUtil.clearSessionCart(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("操作异常");
		}
		
		return responseResult;
	}
}
