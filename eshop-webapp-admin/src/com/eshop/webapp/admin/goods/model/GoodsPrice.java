package com.eshop.webapp.admin.goods.model;

import java.math.BigDecimal;

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
public class GoodsPrice extends GoodsSku {
	private static final long serialVersionUID = 1;

	private Integer skuId;
	private String shippingLevel;
	private BigDecimal salePrice;
	private BigDecimal labelPrice;
	private BigDecimal costPrice;
	
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
	 * @return the salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	
	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	/**
	 * @return the labelPrice
	 */
	public BigDecimal getLabelPrice() {
		return labelPrice;
	}
	
	/**
	 * @param labelPrice the labelPrice to set
	 */
	public void setLabelPrice(BigDecimal labelPrice) {
		this.labelPrice = labelPrice;
	}
	
	/**
	 * @return the costPrice
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	
	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	
}
