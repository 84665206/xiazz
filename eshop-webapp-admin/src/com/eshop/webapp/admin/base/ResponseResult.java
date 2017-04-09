package com.eshop.webapp.admin.base;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.eshop.webapp.admin.enums.ResponseResultEnum;
import com.eshop.webapp.admin.util.DateUtils;


/**
 * 基本返回对象
 * 
 * @author IT
 *
 */
public class ResponseResult<T> extends BaseModel {


	private static final long serialVersionUID = 8756299451390209689L;
	
	private String code= "200";
	private String msg= "success";

	private String response_time;
	private T data;

	private String error;

	public String getError() {
		if (!"200".equals(code)) {
			error = msg;
		}
		return error;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		if(StringUtils.isBlank(msg)){
			return ResponseResultEnum.valueOf(code).msg;
		}
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResponse_time() {
		response_time = DateUtils.DateToString(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS);
		return response_time;
	}

	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
