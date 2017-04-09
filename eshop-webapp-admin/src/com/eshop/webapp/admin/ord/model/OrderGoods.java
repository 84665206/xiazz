package com.eshop.webapp.admin.ord.model;

import java.math.BigDecimal;

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
 * @since 2017年2月10日
 * @version V1.0
 * @author luowen
 */
public class OrderGoods extends BaseModel {
	private static final long serialVersionUID = 1;
	
	private Integer id;
	private Integer orderId;
	private String orderSn;
	private Integer skuId;
	private String skuName;
	private Integer skuNum;
	private BigDecimal salePrice;
	private BigDecimal sharePrice;
	private String skuPic;

	private String goodsVarietiesName;
	private String goodsSpecName;
	
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
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}
	
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * @return the orderSn
	 */
	public String getOrderSn() {
		return orderSn;
	}
	
	/**
	 * @param orderSn the orderSn to set
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
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
	 * @return the skuName
	 */
	public String getSkuName() {
		return skuName;
	}
	
	/**
	 * @param skuName the skuName to set
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	/**
	 * @return the skuNum
	 */
	public Integer getSkuNum() {
		return skuNum;
	}
	
	/**
	 * @param skuNum the skuNum to set
	 */
	public void setSkuNum(Integer skuNum) {
		this.skuNum = skuNum;
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
	 * @return the sharePrice
	 */
	public BigDecimal getSharePrice() {
		return sharePrice;
	}
	
	/**
	 * @param sharePrice the sharePrice to set
	 */
	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}
	
	/**
	 * @return the skuPic
	 */
	public String getSkuPic() {
		return skuPic;
	}
	
	/**
	 * @param skuPic the skuPic to set
	 */
	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	
	/**
	 * @return the goodsVarietiesName
	 */
	public String getGoodsVarietiesName() {
		return goodsVarietiesName;
	}

	
	/**
	 * @param goodsVarietiesName the goodsVarietiesName to set
	 */
	public void setGoodsVarietiesName(String goodsVarietiesName) {
		this.goodsVarietiesName = goodsVarietiesName;
	}

	
	/**
	 * @return the goodsSpecName
	 */
	public String getGoodsSpecName() {
		return goodsSpecName;
	}

	
	/**
	 * @param goodsSpecName the goodsSpecName to set
	 */
	public void setGoodsSpecName(String goodsSpecName) {
		this.goodsSpecName = goodsSpecName;
	}
	
	
}
