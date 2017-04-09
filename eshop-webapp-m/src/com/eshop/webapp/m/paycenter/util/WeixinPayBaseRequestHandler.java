package com.eshop.webapp.m.paycenter.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;


public class WeixinPayBaseRequestHandler {
	public WeixinPayBaseRequestHandler() {
		parameters = new Hashtable();

		this.setGateUrl("https://api.mch.weixin.qq.com/pay/unifiedorder");
	}

	/** 网关url地址 */
	private String gateUrl;

	/** 密钥 */
	private String key;

	/** 请求的参数 */
	protected Hashtable parameters;

	/** debug信息 */
	private String debugInfo;

	private String charset;

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	/** 初始化函数。 */
	public void init() {
		// nothing to do
	}

	/** 获取入口地址,不包含参数值 */
	public String getGateUrl() {
		return gateUrl;
	}

	/** 设置入口地址,不包含参数值 */
	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}

	/** 获取密钥 */
	public String getKey() {
		return key;
	}

	/** 设置密钥 */
	public void setKey(String key) {
		this.key = key;
	}

	/** 获取带参数的请求URL @return String */
	public String getRequestURL() {
		this.createSign();

		StringBuilder sb = new StringBuilder();
		ArrayList akeys = new ArrayList(parameters.keySet());
		Collections.sort(akeys);
		for (Object key : akeys) {
			String v = (String) parameters.get(key);
			if (null != v && !"key".equals(key)) {
				sb.append(key + "=" + WeixinPayUtil.UrlEncode(v, getCharset())
						+ "&");
			}
		}
		String sbs = sb.toString();
		// 去掉最后一个&
		if (sb.length() > 0) {
			sbs = sb.substring(0, sb.length() - 1);
		}

		return this.getGateUrl() + "?" + sbs;
	}
	
	/**
	 * 获取预生成订单的后参数，加密后，
	 * 传给app
	 * @return
	 */
	public Map<String, Object> getPreOrderData() {
		this.createSign();
		
		return parameters;
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	protected void createSign() {
		StringBuilder sb = new StringBuilder();

		ArrayList akeys = new ArrayList(parameters.keySet());
		Collections.sort(akeys);
		 
		for (Object key : akeys) {
			String v = (String) parameters.get(key);
			if (StringUtils.isNotBlank(v) &&!"sign".equals(key)&& !"key".equals(key)) {
				sb.append(key + "=" +v  + "&");
			}
		} 
		sb.append("key=" + this.getKey());
		String sign =DigestUtils.md5Hex(sb.toString()).toUpperCase();

		this.setParameter("sign", sign);

		// debug信息
		this.setDebugInfo(sb.toString() + " => sign:" + sign);
	}

	/** 获取参数值 */
	public String getParameter(String parameter) {
		String s = (String) parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	/** 设置参数值 */
	public void setParameter(String parameter, String parameterValue) {
		if (StringUtils.isNotBlank(parameter)) {
			parameters.put(parameter, parameterValue);
		}
	}



	/** 获取debug信息 */
	public String getDebugInfo() {
		return debugInfo;
	}

	/** 设置debug信息 */
	public void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}

	public Hashtable getAllParameters() {
		return this.parameters;
	}
	
	/**根据请求参数生成xml**/
	public String getRequestXML(){
		this.createSign();
		
		ArrayList akeys = new ArrayList(parameters.keySet());
		Collections.sort(akeys);
		
		StringBuffer xml=new StringBuffer();
		xml.append("<xml>");
		for (Object key:akeys) {
			String value=(String)parameters.get(key);
			if(StringUtils.isNotBlank(value)){
				xml.append("<"+key+">"+value+"</"+key+">");	
			}
		}
		xml.append("</xml>");
		return xml.toString();
	}
	
	public String createPaySign() {
		StringBuilder sb = new StringBuilder();

		ArrayList akeys = new ArrayList(parameters.keySet());
		Collections.sort(akeys);
		 
		for (Object key : akeys) {
			String v = (String) parameters.get(key);
			if (StringUtils.isNotBlank(v) &&!"sign".equals(key)&& !"key".equals(key)) {
				sb.append(key + "=" +v  + "&");
			}
		} 
		sb.append("key=" + this.getKey());

		String sign =DigestUtils.md5Hex(sb.toString()).toUpperCase();

		// debug信息
		this.setDebugInfo(sb.toString() + " => sign:" + sign);
		
		return sign;
	}

}