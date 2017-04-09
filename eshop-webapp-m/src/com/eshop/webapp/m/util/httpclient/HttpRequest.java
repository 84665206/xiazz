package com.eshop.webapp.m.util.httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
/****
 * <p>HttpRequest,用于封装一次HTTP请求所需的所有信息。</p>
 * <p>此对象初始化后，可以使用链式的设置参数方式</p>
 * @author LiuYi,ZhichengHan
 *
 */
public class HttpRequest implements java.io.Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/** HTTP POST method */
	public static final String METHOD_POST = "POST";
	/** HTTP POST method */
	public static final String METHOD_GET="GET";
	
	/**
	 * 待请求的URL
	 */
	private String url = null;

	/**
	 * HTTP METHOD 默认为POST请求
	 */
	private String method = METHOD_POST;
	/*****
	 * 响应等待时间。单位秒
	 */
	private int timeout = 0;
	/***
	 * 连接超时时间。单位秒
	 */
	private int connectionTimeout = 0;

	/**
	 * Post方式请求时组装好的参数值对
	 */
	private List<NameValuePair> parameters = null;
	
	/****
	 * HTTP Header
	 */
	private List<NameValuePair> headers = null;

	/**
	 * 默认的请求编码方式。此参数只对POST请求有用。
	 * GET请求的请求参数，始终使用UTF-8编码
	 */
	private String charset = "UTF-8";
	
	/**
     * 请求返回的方式
     */
    private HttpResultType resultType = HttpResultType.STRING;
	
	/****
	 * 受保护的无参构造器
	 */
	protected HttpRequest() {
		super();
		this.parameters = new ArrayList<NameValuePair>();
		this.headers = new ArrayList<NameValuePair>();
	}
	/****
	 * 新创建一个HTTP GET 请求
	 * @param url
	 * @return
	 */
	public static HttpRequest createHttpGetRequest(String url){
		HttpRequest request = new HttpRequest();
		request.url = url;
		request.method = METHOD_GET;
		return request;
	}
	/****
	 * 新创建一个HTTP POST 请求
	 * @param url
	 * @return
	 */
	public static HttpRequest createHttpPostRequest(String url){
		HttpRequest request = new HttpRequest();
		request.url = url;
		return request;
	}
	/****
	 * 增加一个参数
	 * @param name
	 * @param value
	 * @return
	 */
	public HttpRequest addHttpParameter(String name,String value){
		this.parameters.add(new NameValuePair(name,value));
		return this;
	}
	/****
	 * 添加一个Http Header 参数
	 * @param name
	 * @param value
	 * @return
	 */
	public HttpRequest addHttpHeader(String name,String value){
		this.headers.add(new NameValuePair(name,value));
		return this;
	}
	
	
	/****
	 * 以Map构造请求参数
	 * @param headers
	 * @return
	 */
	public HttpRequest addHttpHeaders(Map<String, String> headers){
		for(String key : headers.keySet()){
			this.headers.add(new NameValuePair(key,headers.get(key)));
		}
		return this;
	}
	
	/****
	 * 以Map构造请求参数
	 * @param parameters
	 * @return
	 */
	public HttpRequest addHttpParameters(Map<String, String> parameters){
		for(String key : parameters.keySet()){
			this.parameters.add(new NameValuePair(key,parameters.get(key)));
		}
		return this;
	}
	
	/*****
	 * 将参数转换为NameValuePair
	 * @return
	 */
	public NameValuePair[] getNameValuePairs() {
		NameValuePair[] pairs = new NameValuePair[this.parameters.size()];
		pairs = this.parameters.toArray(pairs);
		return pairs;
	}
	
	/****
	 * 获取当前HTTP 请求的HTTP 请求参数键值对形式。
	 * &name1=value1&name2=value2 ........。
	 * 主要用于程序中记录请求Log
	 * @return
	 */
	public String getHttpNameValuePairs(){
		if(this.parameters.size() == 0){
			return StringUtils.EMPTY;
		}
		StringBuilder sb = new StringBuilder();
		for(NameValuePair parameter:this.parameters){
			sb.append("&"+parameter.getName()+"="+parameter.getValue());
		}
		return sb.toString();
	}
	
	
	public List<NameValuePair> getHttpHeaders(){
		return this.headers;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getMethod() {
		return method;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	
	
	public HttpRequest setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}

	public int getTimeout() {
		return timeout;
	}

	public HttpRequest setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}

	/**
	 * @return Returns the charset.
	 */
	public String getCharset() {
		return charset;
	}
	
	
	
	public HttpResultType getResultType() {
		return resultType;
	}
	/**
	 * @param charset The charset to set.
	 */
	public HttpRequest setCharset(String charset) {
		this.charset = charset;
		return this;
	}
	public HttpRequest setResultType(HttpResultType resultType) {
		this.resultType = resultType;
		return this;
	}
	@Override
	public String toString() {
		return "HttpRequest [url=" + url + ", method=" + method + ", timeout="
				+ timeout + ", connectionTimeout=" + connectionTimeout
				+ ", parameters=" + parameters + ", charset=" + charset
				+ ", resultType=" + resultType + "]";
	}
	
	
	
}
