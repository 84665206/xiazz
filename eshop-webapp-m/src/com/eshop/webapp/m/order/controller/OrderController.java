package com.eshop.webapp.m.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.m.base.BaseController;
import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.order.model.OrderInfo;
import com.eshop.webapp.m.order.service.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("/list")
	public String list(Model model, 
			Integer order_tt,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			List<OrderInfo> orderInfos=orderService.myOrderInfoList(getCurrentCustomerId(), order_tt);
			
			model.addAttribute("orderInfos", orderInfos);
			//model.addAttribute("userName", getCurrentUser().getUser_name());
			return "order/list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/order/area")
	public ResponseResult area(Model model, 
			HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult responseResult = new ResponseResult();
		try {
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseResult;
	} 
}
