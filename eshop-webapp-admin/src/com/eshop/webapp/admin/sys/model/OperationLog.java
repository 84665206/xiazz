package com.eshop.webapp.admin.sys.model;

import java.util.Date;

import com.eshop.webapp.admin.base.BaseModel;


public class OperationLog extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	public static final int OPRESULT_SUCCESSFUL = 0;
	public static final int OPRESULT_FAILURE = 1;

	private Integer id;
	private Integer userId;
	private String ipAddr;
	private Date createTime;
	private String opModule;
	private String opPage;
	private String operation;
	private Integer opResult;

	private User user;

	public OperationLog() {}

	/**
	 * @param userId 用户ID
	 * @param ipAddr 所在IP
	 * @param opModule 所在模块
	 * @param opPage 所在页面
	 * @param operation 具体操作（具体操作，必选 + 操作对象ID，可选，如订单ID）
	 */
	public OperationLog(Integer userId, String ipAddr, String opModule, String opPage, String operation,
		Integer opResult) {
		this.userId = userId;
		this.ipAddr = ipAddr;
		this.opModule = opModule;
		this.opPage = opPage;
		this.operation = operation;
		this.opResult = opResult;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOpModule() {
		return this.opModule;
	}

	public void setOpModule(String opModule) {
		this.opModule = opModule;
	}

	public String getOpPage() {
		return this.opPage;
	}

	public void setOpPage(String opPage) {
		this.opPage = opPage;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	/** (getter for opResult) */
	public Integer getOpResult() {
		return opResult;
	}

	/** (setter for opResult) */
	public void setOpResult(Integer opResult) {
		this.opResult = opResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.createTime == null) ? 0 : this.createTime.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.ipAddr == null) ? 0 : this.ipAddr.hashCode());
		result = (prime * result) + ((this.opModule == null) ? 0 : this.opModule.hashCode());
		result = (prime * result) + ((this.opPage == null) ? 0 : this.opPage.hashCode());
		result = (prime * result) + ((this.operation == null) ? 0 : this.operation.hashCode());
		result = (prime * result) + ((this.userId == null) ? 0 : this.userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		OperationLog other = (OperationLog) obj;
		if (this.createTime == null) {
			if (other.createTime != null) return false;
		}
		else if (!this.createTime.equals(other.createTime)) return false;
		if (this.id == null) {
			if (other.id != null) return false;
		}
		else if (!this.id.equals(other.id)) return false;
		if (this.ipAddr == null) {
			if (other.ipAddr != null) return false;
		}
		else if (!this.ipAddr.equals(other.ipAddr)) return false;
		if (this.opModule == null) {
			if (other.opModule != null) return false;
		}
		else if (!this.opModule.equals(other.opModule)) return false;
		if (this.opPage == null) {
			if (other.opPage != null) return false;
		}
		else if (!this.opPage.equals(other.opPage)) return false;
		if (this.operation == null) {
			if (other.operation != null) return false;
		}
		else if (!this.operation.equals(other.operation)) return false;
		if (this.userId == null) {
			if (other.userId != null) return false;
		}
		else if (!this.userId.equals(other.userId)) return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
			"{id=%s, userId=%s, ipAddr=%s, createTime=%s, opModule=%s, opPage=%s, operation=%s}", this.id,
			this.userId, this.ipAddr, this.createTime, this.opModule, this.opPage, this.operation);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}