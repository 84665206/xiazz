package com.eshop.webapp.admin.goods.model;

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
public class GoodsSku extends BaseModel {
	private static final long serialVersionUID = 1;
	
	private Integer id;
	private String skuCode;
	private String goodsName;
	private String goodsVarieties;
	private String goodsSpec;
	private String goodsOrigin;
	private String goodsUnit;
	private String otherExplain;
	private Integer isValid;
	private String goodsShipping;
	
	private String goodsVarietiesDesc;
	private String goodsSpecDesc;
	private String goodsOriginDesc;
	private String goodsShippingDesc;
	
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
	 * @return the otherExplain
	 */
	public String getOtherExplain() {
		return otherExplain;
	}
	
	/**
	 * @param otherExplain the otherExplain to set
	 */
	public void setOtherExplain(String otherExplain) {
		this.otherExplain = otherExplain;
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
	 * @return the goodsShipping
	 */
	public String getGoodsShipping() {
		return goodsShipping;
	}

	
	/**
	 * @param goodsShipping the goodsShipping to set
	 */
	public void setGoodsShipping(String goodsShipping) {
		this.goodsShipping = goodsShipping;
	}

	
	/**
	 * @return the goodsVarietiesDesc
	 */
	public String getGoodsVarietiesDesc() {
		return goodsVarietiesDesc;
	}

	
	/**
	 * @param goodsVarietiesDesc the goodsVarietiesDesc to set
	 */
	public void setGoodsVarietiesDesc(String goodsVarietiesDesc) {
		this.goodsVarietiesDesc = goodsVarietiesDesc;
	}

	
	/**
	 * @return the goodsSpecDesc
	 */
	public String getGoodsSpecDesc() {
		return goodsSpecDesc;
	}

	
	/**
	 * @param goodsSpecDesc the goodsSpecDesc to set
	 */
	public void setGoodsSpecDesc(String goodsSpecDesc) {
		this.goodsSpecDesc = goodsSpecDesc;
	}

	
	/**
	 * @return the goodsOriginDesc
	 */
	public String getGoodsOriginDesc() {
		return goodsOriginDesc;
	}

	
	/**
	 * @param goodsOriginDesc the goodsOriginDesc to set
	 */
	public void setGoodsOriginDesc(String goodsOriginDesc) {
		this.goodsOriginDesc = goodsOriginDesc;
	}

	
	/**
	 * @return the goodsShippingDesc
	 */
	public String getGoodsShippingDesc() {
		return goodsShippingDesc;
	}

	
	/**
	 * @param goodsShippingDesc the goodsShippingDesc to set
	 */
	public void setGoodsShippingDesc(String goodsShippingDesc) {
		this.goodsShippingDesc = goodsShippingDesc;
	}
	
}
