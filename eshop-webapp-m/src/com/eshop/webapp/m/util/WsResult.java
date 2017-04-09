package com.eshop.webapp.m.util;

/**
 * <p>
 * TODO类概述
 * </p>
 * <p>
 * 提供如下功能：<br>
 * <1>.TODO<br>
 * <2>.TODO<br>
 * </p>
 * @since 2012-10-15
 * @version V1.0
 * @author luowen
 */
public class WsResult {
	public static final String STATUS_TYPE_OK = "success";
	public static final String STATUS_TYPE_ERR = "error";
	
	private String wsid;
	private String status;
	private String msg;
	private Object request;
	private Object response;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getWsid() {
		return wsid;
	}
	
	public void setWsId(String wsid) {
		this.wsid = wsid;
	}
	
	public Object getRsquest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}
	
	public Object getResponse() {
		return response;
	}
	
	public void setResponse(Object response) {
		this.response = response;
	}
	
}
