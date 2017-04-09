package com.eshop.webapp.m.model;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.eshop.webapp.m.base.BaseModel;

/** 
 * 购物车 
 * @author yangmeng
 * @version 创建时间：2017年2月8日 下午3:02:34 
 * 
 */
public class Cart extends BaseModel{

	private static final long serialVersionUID = -2788854007825295637L;
	
	private SessionCustomer sessionCustomer;
	
	private LinkedHashMap<String, CartItem> items = new LinkedHashMap<String, CartItem>(16, 0.75F);
	
	private List<String> messages;

	public SessionCustomer getSessionCustomer() {
		return sessionCustomer;
	}

	public void setSessionCustomer(SessionCustomer sessionCustomer) {
		this.sessionCustomer = sessionCustomer;
	}

	public LinkedHashMap<String, CartItem> getItems() {
		return items;
	}

	public void setItems(LinkedHashMap<String, CartItem> items) {
		this.items = items;
	}
	
	/**
	 * 将一个商品放入购物车<BR/>
	 * 供Service调用
	 * @see CartItem
	 * @param item	欲放入购物车的产品信息
	 */
	public void putItemIntoCart(CartItem item){
		if(item == null || item.getPK() == null || StringUtils.isBlank(item.getPK())){
			return;
		}
		if(items.containsKey(item.getPK())){
			items.get(item.getPK()).setBuy_num(items.get(item.getPK()).getBuy_num() + item.getBuy_num());
		}else{
			//如果一个商品购买数量为0，则不放入购物车中
			if(item.getBuy_num() == 0){
				
			}else{
				items.put(item.getPK(), item);
			}
		}
	}
	
	/**
	 * 判断购物车是否为空
	 * 
	 */
	public boolean isEmpty() {
		if (this.items == null || this.items.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
