package com.eshop.webapp.m.util;
/*****
 * 用于记录和保存当前请求Id
 * 
 * @author ZhichengHan
 *
 */
public class HttpRequestIdHolder {
	
	protected HttpRequestIdHolder(){}
	
	private static final ThreadLocal<String> _localContext = new ThreadLocal<String>();
	
	/*****
	 * 获取Http请求Id
	 * @return
	 */
	public static String getHttpRequestId(){
		return _localContext.get();
	}
	/****
	 * 保存请求Id
	 * @param requestId
	 */
	public static void setHttpRequestId(String requestId){
		_localContext.set(requestId);
	}
}
