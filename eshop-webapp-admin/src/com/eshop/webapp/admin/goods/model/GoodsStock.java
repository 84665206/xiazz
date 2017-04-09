package com.eshop.webapp.admin.goods.model;

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
public class GoodsStock extends GoodsSku {
	private static final long serialVersionUID = 1;
	
	private Integer skuId;
	private String shippingLevel;
	private Integer stockNum;
	private Integer lockNum;
	private Integer validNum;
	
	/**
	 * @return the skuId
	 */
	public Integer getSkuId() {
		return skuId;
	}
	
	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	
	/**
	 * @return the shippingLevel
	 */
	public String getShippingLevel() {
		return shippingLevel;
	}
	
	/**
	 * @param shippingLevel the shippingLevel to set
	 */
	public void setShippingLevel(String shippingLevel) {
		this.shippingLevel = shippingLevel;
	}
	
	/**
	 * @return the stockNum
	 */
	public Integer getStockNum() {
		return stockNum;
	}
	
	/**
	 * @param stockNum the stockNum to set
	 */
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	
	/**
	 * @return the lockNum
	 */
	public Integer getLockNum() {
		return lockNum;
	}
	
	/**
	 * @param lockNum the lockNum to set
	 */
	public void setLockNum(Integer lockNum) {
		this.lockNum = lockNum;
	}

	
	/**
	 * @return the validNum
	 */
	public Integer getValidNum() {
		return validNum;
	}

	
	/**
	 * @param validNum the validNum to set
	 */
	public void setValidNum(Integer validNum) {
		this.validNum = validNum;
	}
	
}
