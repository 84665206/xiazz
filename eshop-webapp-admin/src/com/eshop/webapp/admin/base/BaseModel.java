package com.eshop.webapp.admin.base;

import java.util.Date;

public class BaseModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private String memo;
	private Date insertTime;
	private String insertUser;
	private Date updateTime;
	private String updateUser;
	private Integer isDelete;
	
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * @return the insertTime
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	
	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	/**
	 * @return the insertUser
	 */
	public String getInsertUser() {
		return insertUser;
	}
	
	/**
	 * @param insertUser the insertUser to set
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/**
	 * @return the isDelete
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
}
