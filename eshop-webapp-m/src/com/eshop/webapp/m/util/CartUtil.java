package com.eshop.webapp.m.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.eshop.webapp.m.goods.model.GoodsSku;
import com.eshop.webapp.m.model.Cart;
import com.eshop.webapp.m.model.CartItem;
import com.eshop.webapp.m.model.SessionCustomer;
import com.eshop.webapp.m.util.session.SessionConstant;



/**
 * 
 * 购物车操作工具类
 * 
 * @author william
 * @since 2011.11.29
 * @version 1.1 修改了部分方法的实现 By Lixz
 * 
 * 
 */
public class CartUtil {

	private static SessionCustomer getCurrentCustomer(HttpServletRequest request) {
		Object o = SessionUtil.getSessionAttribute(request,
				SessionConstant.SESSION_CUSTOMER);
		if (o != null) {
			SessionCustomer customer = (SessionCustomer) o;
			return customer;
		} else {
			return null;
		}
	}

	
	/**
	 * 获得session中的购物车 没有拿到则返回cookie中cart
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 购物车，不会为null
	 */
	public static Cart getSessionCart(HttpServletRequest request) {
		Cart result = null;
		Object obj = SessionUtil.getSessionAttribute(request,
				SessionConstant.SESSION_CART);
		if (obj != null) {
			result = (Cart) obj;
		} else {
			result = new Cart();
			SessionUtil.setSessionAttribute(request,
					SessionConstant.SESSION_CART, result);
		}
		if(result!=null){
			result.setSessionCustomer(null); 
			SessionCustomer customer = getCurrentCustomer(request);
			if (customer != null && customer.getCustomer_id() != null
					&& StringUtils.isNotBlank(customer.getCustomer_id().toString())) {
				result.setSessionCustomer(customer);
			} 
		}
		return result;
	}

	/**
	 * 将购物车写入到Session中<BR/>
	 * 同时会将购物车写入到Cookie中
	 * 
	 * @param cart
	 * @param request
	 * @param response
	 */
	public static void setSessionCart(Cart cart, HttpServletRequest request,
			HttpServletResponse response) {
		if (cart != null) {
			SessionUtil.setSessionAttribute(request,
					SessionConstant.SESSION_CART, cart);
		}
	}

	/**
	 * 清空购物车
	 */
	public static void clearSessionCart(HttpServletRequest request,
			HttpServletResponse response) {
		SessionUtil.removeSessionAttribute(request,
				SessionConstant.SESSION_CART);

	}
	
	/**
	 * 填充购物车中item的基本信息
	 */
	public static void fillGoodsToItem(CartItem item,
			GoodsSku goodsSku) {
		if (item.getGoods_name() == null) {
			item.fillGoodsInfoToItem(goodsSku);
		} else {
			item.setBuy_num(0);
			item.setMessage("商品信息错误(0004)");
		}
	}
}
