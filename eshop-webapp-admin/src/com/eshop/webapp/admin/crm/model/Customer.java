package com.eshop.webapp.admin.crm.model;

import com.eshop.webapp.admin.base.BaseModel;

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
public class Customer extends BaseModel {
	private static final long serialVersionUID = 1;

	private Integer id;
	private String customerName;
	private String customerPwd;
	private String customerTrueName;
	private String shopName;
	private String shopAddress;
	private String recruitCode;
	private Integer isValid;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the customerPwd
	 */
	public String getCustomerPwd() {
		return customerPwd;
	}
	
	/**
	 * @param customerPwd the customerPwd to set
	 */
	public void setCustomerPwd(String customerPwd) {
		this.customerPwd = customerPwd;
	}
	
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}
	
	/**
	 * @param shopName the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	/**
	 * @return the shopAddress
	 */
	public String getShopAddress() {
		return shopAddress;
	}
	
	/**
	 * @param shopAddress the shopAddress to set
	 */
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	
	/**
	 * @return the isValid
	 */
	public Integer getIsValid() {
		return isValid;
	}
	
	/**
	 * @param isValid the isValid to set
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	
	/**
	 * @return the customerTrueName
	 */
	public String getCustomerTrueName() {
		return customerTrueName;
	}

	
	/**
	 * @param customerTrueName the customerTrueName to set
	 */
	public void setCustomerTrueName(String customerTrueName) {
		this.customerTrueName = customerTrueName;
	}

	
	/**
	 * @return the recruitCode
	 */
	public String getRecruitCode() {
		return recruitCode;
	}

	
	/**
	 * @param recruitCode the recruitCode to set
	 */
	public void setRecruitCode(String recruitCode) {
		this.recruitCode = recruitCode;
	}
	
}
