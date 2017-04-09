package com.eshop.webapp.admin.sys.model;

import java.util.Date;

import com.eshop.webapp.admin.base.BaseModel;


public class UserLog extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String user_name;

	private String user_ip;

	private Date login_time;

	private Integer login_result;

	private String memo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public Integer getLogin_result() {
		return login_result;
	}

	public void setLogin_result(Integer login_result) {
		this.login_result = login_result;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}