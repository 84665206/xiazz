package com.eshop.webapp.m.model;
/** 
 * 客户session信息
 * @author yangmeng
 * @version 创建时间：2017年2月3日 上午11:10:04 
 * 
 */
public class SessionCustomer {
	
	private Integer customer_id;
	
	private String customer_name;
	
	private String customer_true_name;
	
	private String shop_name;

    private String shop_address;

    private String recruit_code; 
	
	private String open_id;

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getCustomer_true_name() {
		return customer_true_name;
	}

	public void setCustomer_true_name(String customer_true_name) {
		this.customer_true_name = customer_true_name;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public String getRecruit_code() {
		return recruit_code;
	}

	public void setRecruit_code(String recruit_code) {
		this.recruit_code = recruit_code;
	}
}
