package com.eshop.webapp.m.util.httpclient;

import java.io.Serializable;
/****
 * 
 * @author ZhichengHan
 *
 */
public class HttpResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/****
	 * responseCode
	 */
	private int responseCode = -1;
	/****
	 * 
	 */
	private String responseText;
	/****
	 * 
	 */
	private byte[] byteResponse;

	public HttpResponse() {
		super();
	}




	public HttpResponse(int responseCode) {
		super();
		this.responseCode = responseCode;
	}
	
	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseText() {
		return responseText;
	}




	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}




	public byte[] getByteResponse() {
		return byteResponse;
	}




	public void setByteResponse(byte[] byteResponse) {
		this.byteResponse = byteResponse;
	}
	
	
	

}
