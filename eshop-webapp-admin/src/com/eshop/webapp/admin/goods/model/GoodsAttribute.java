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
public class GoodsAttribute extends BaseModel {
	private static final long serialVersionUID = 1;
	
	private Integer id;
	private String attrTypeCode;
	private String attrTypeName;
	private String attrCode;
	private String attrValue;
	private String attrDesc;
	private Integer attrIndex;
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
	 * @return the attrTypeCode
	 */
	public String getAttrTypeCode() {
		return attrTypeCode;
	}
	
	/**
	 * @param attrTypeCode the attrTypeCode to set
	 */
	public void setAttrTypeCode(String attrTypeCode) {
		this.attrTypeCode = attrTypeCode;
	}
	
	/**
	 * @return the attrTypeName
	 */
	public String getAttrTypeName() {
		return attrTypeName;
	}
	
	/**
	 * @param attrTypeName the attrTypeName to set
	 */
	public void setAttrTypeName(String attrTypeName) {
		this.attrTypeName = attrTypeName;
	}
	
	/**
	 * @return the attrCode
	 */
	public String getAttrCode() {
		return attrCode;
	}
	
	/**
	 * @param attrCode the attrCode to set
	 */
	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}
	
	/**
	 * @return the attrValue
	 */
	public String getAttrValue() {
		return attrValue;
	}
	
	/**
	 * @param attrValue the attrValue to set
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	
	/**
	 * @return the attrDesc
	 */
	public String getAttrDesc() {
		return attrDesc;
	}
	
	/**
	 * @param attrDesc the attrDesc to set
	 */
	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}
	
	/**
	 * @return the attrIndex
	 */
	public Integer getAttrIndex() {
		return attrIndex;
	}
	
	/**
	 * @param attrIndex the attrIndex to set
	 */
	public void setAttrIndex(Integer attrIndex) {
		this.attrIndex = attrIndex;
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
	
}
