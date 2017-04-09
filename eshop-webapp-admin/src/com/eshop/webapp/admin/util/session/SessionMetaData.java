package com.eshop.webapp.admin.util.session;

import java.io.Serializable;

/**
 * 
 * @author shangxuejin
 * @date Nov 30, 2011 10:24:23 AM
 */
public class SessionMetaData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	/** session的创建时间 单位 毫秒 */

	private Long createTm;

	/** session的最大空闲时间 单位秒 */

	private int maxIdle;

	/** session的最后一次访问时间 单位 毫秒 */

	private Long lastAccessTm;

	/** 是否可用 */

	private Boolean validate = false;

	/** 当前版本 */

	private int version = 0;

	/**
	 * 
	 * 构造方法
	 */

	public SessionMetaData(String sessionId, int maxIdle) {

		this.id = sessionId;

		this.createTm = System.currentTimeMillis();

		this.lastAccessTm = this.createTm;

		this.validate = true;

		this.maxIdle = maxIdle;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Long createTm) {
		this.createTm = createTm;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Long getLastAccessTm() {
		return lastAccessTm;
	}

	public void setLastAccessTm(Long lastAccessTm) {
		this.lastAccessTm = lastAccessTm;
	}

	public Boolean getValidate() {
		Long now = System.currentTimeMillis(); // 当前时间
		// 检查是否过期
		Long timeout = getLastAccessTm() + getMaxIdle() * 1000; // 空闲时间
		// 如果空闲时间小于当前时间，则表示Session超时
		if (timeout < now) {
			return false;
		}
		return validate;
	}

	// public void setValidate(Boolean validate) {
	// this.validate = validate;
	// }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return String.format("{id=%s, createTm=%s, maxIdle=%s, lastAccessTm=%s, validate=%s, version=%s}", id,
			createTm, maxIdle, lastAccessTm, validate, version);
	}

}
