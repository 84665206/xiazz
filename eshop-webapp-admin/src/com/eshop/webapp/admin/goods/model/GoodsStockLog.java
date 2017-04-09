package com.eshop.webapp.admin.goods.model;

import java.util.Date;

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
public class GoodsStockLog extends BaseModel {
	private static final long serialVersionUID = 1;

	private Integer id;
	private Integer skuId;
	private String skuCode;
	private String goodsName;
	private String goodsVarieties;
	private String goodsSpec;
	private String goodsOrigin;
	private String goodsUnit;
	private String shippingLevel;
	private Date createTime;
	private String createUser;
	private String linkOrderType;
	private String linkOrder;
	private String stockChangeType;
	private Integer stockChangeNum;
	private Integer changeBeforNum;
	private Integer changeAfterNum;
	
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
	 * @return the skuCode
	 */
	public String getSkuCode() {
		return skuCode;
	}
	
	/**
	 * @param skuCode the skuCode to set
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	
	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	/**
	 * @return the goodsVarieties
	 */
	public String getGoodsVarieties() {
		return goodsVarieties;
	}
	
	/**
	 * @param goodsVarieties the goodsVarieties to set
	 */
	public void setGoodsVarieties(String goodsVarieties) {
		this.goodsVarieties = goodsVarieties;
	}
	
	/**
	 * @return the goodsSpec
	 */
	public String getGoodsSpec() {
		return goodsSpec;
	}
	
	/**
	 * @param goodsSpec the goodsSpec to set
	 */
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	
	/**
	 * @return the goodsOrigin
	 */
	public String getGoodsOrigin() {
		return goodsOrigin;
	}
	
	/**
	 * @param goodsOrigin the goodsOrigin to set
	 */
	public void setGoodsOrigin(String goodsOrigin) {
		this.goodsOrigin = goodsOrigin;
	}
	
	/**
	 * @return the goodsUnit
	 */
	public String getGoodsUnit() {
		return goodsUnit;
	}
	
	/**
	 * @param goodsUnit the goodsUnit to set
	 */
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}
	
	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	/**
	 * @return the linkOrderType
	 */
	public String getLinkOrderType() {
		return linkOrderType;
	}
	
	/**
	 * @param linkOrderType the linkOrderType to set
	 */
	public void setLinkOrderType(String linkOrderType) {
		this.linkOrderType = linkOrderType;
	}
	
	/**
	 * @return the linkOrder
	 */
	public String getLinkOrder() {
		return linkOrder;
	}
	
	/**
	 * @param linkOrder the linkOrder to set
	 */
	public void setLinkOrder(String linkOrder) {
		this.linkOrder = linkOrder;
	}
	
	/**
	 * @return the stockChangeType
	 */
	public String getStockChangeType() {
		return stockChangeType;
	}
	
	/**
	 * @param stockChangeType the stockChangeType to set
	 */
	public void setStockChangeType(String stockChangeType) {
		this.stockChangeType = stockChangeType;
	}
	
	/**
	 * @return the stockChangeNum
	 */
	public Integer getStockChangeNum() {
		return stockChangeNum;
	}
	
	/**
	 * @param stockChangeNum the stockChangeNum to set
	 */
	public void setStockChangeNum(Integer stockChangeNum) {
		this.stockChangeNum = stockChangeNum;
	}
	
	/**
	 * @return the changeBeforNum
	 */
	public Integer getChangeBeforNum() {
		return changeBeforNum;
	}
	
	/**
	 * @param changeBeforNum the changeBeforNum to set
	 */
	public void setChangeBeforNum(Integer changeBeforNum) {
		this.changeBeforNum = changeBeforNum;
	}
	
	/**
	 * @return the changeAfterNum
	 */
	public Integer getChangeAfterNum() {
		return changeAfterNum;
	}
	
	/**
	 * @param changeAfterNum the changeAfterNum to set
	 */
	public void setChangeAfterNum(Integer changeAfterNum) {
		this.changeAfterNum = changeAfterNum;
	}
	
}
