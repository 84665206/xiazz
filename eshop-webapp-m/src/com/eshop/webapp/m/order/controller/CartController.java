package com.eshop.webapp.m.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.m.base.BaseController;
import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.model.Cart;
import com.eshop.webapp.m.model.CartItem;
import com.eshop.webapp.m.order.service.IShoppingService;
import com.eshop.webapp.m.util.CartUtil;


/** 
 * @author yangmeng
 * @version 创建时间：2017年2月10日 下午2:55:09 
 * 
 */
@Controller
@RequestMapping("/cart")
public class CartController extends BaseController{
	
	@Autowired
	private IShoppingService shoppingService;
	private Cart cart;
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseResult add(@RequestParam("sku_id")Integer sku_id, 
			@RequestParam("sku_num")Integer sku_num,
			HttpServletRequest request, HttpServletResponse response){
		ResponseResult responseResult = new ResponseResult();
		
		try {
			if(sku_id==null||sku_num==null||sku_num==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("参数错误(0001)");
				return responseResult;
			}
			CartUtil.clearSessionCart(request, response);
			
			preAddCartOprate(request);
			
			CartItem item = new CartItem();
			item.setSku_id(sku_id);
			item.setBuy_num(sku_num);
			
			cart=shoppingService.addGoodsToCart(cart, item);
			
			if (cart.getMessages()!=null&&cart.getMessages().size() != 0) {
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg(cart.getMessages().get(0));
				return responseResult;
			}
			
			CartUtil.setSessionCart(cart, request, response);
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
			responseResult.setMsg("加入购车成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(ResponseResultEnum.EXCEPTION.code);
			responseResult.setMsg("加入购物车失败");
		}
		
		return responseResult;
	}
	
	private void preAddCartOprate(HttpServletRequest request) {
		cart = CartUtil.getSessionCart(request);
		cart.setSessionCustomer(getCurrentCustomer());
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
