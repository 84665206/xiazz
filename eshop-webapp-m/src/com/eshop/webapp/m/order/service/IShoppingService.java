package com.eshop.webapp.m.order.service;

import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.model.Cart;
import com.eshop.webapp.m.model.CartItem;
import com.eshop.webapp.m.model.SessionCustomer;
import com.eshop.webapp.m.order.model.OrderInfo;

/** 
 * 购物
 * @author yangmeng
 * @version 创建时间：2017年2月1日 上午10:53:14 
 * 
 */
public interface IShoppingService {
	
	Cart addGoodsToCart(Cart cart, CartItem item);
	
	OrderInfo cartToOrder(Cart cart, OrderInfo orderInfo);
	
	OrderInfo createOrder(Cart cart, OrderInfo orderInfo);
	
	@SuppressWarnings("rawtypes")
	ResponseResult setDefaultDelivery(OrderInfo orderInfo, Integer delivery_id, SessionCustomer customer);
}
