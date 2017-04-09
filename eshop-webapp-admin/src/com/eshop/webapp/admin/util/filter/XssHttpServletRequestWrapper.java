package com.eshop.webapp.admin.util.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/****
 * HTML 编码过滤器
 * 
 * @author ZhichengHan
 * 
 */
public final class XssHttpServletRequestWrapper extends
		HttpServletRequestWrapper {


	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getQueryString() {
		String value = super.getQueryString();
		if(value!=null){
			value = xssEncode(value);
		}
		return value;
	}



	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String[]> getParameterMap() {
		Map<String,String[]> map = super.getParameterMap();
		if(map.size()>0){
			for(String key:map.keySet()){
				String[] arr = map.get(key);
				if(arr!=null && arr.length>0){
					for(int i=0;i<arr.length;i++){
						arr[i]=xssEncode(arr[i]);
					}
				}
			}
		}
		return map;
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
	 * getHeaderNames 也可能需要覆盖
	 */
	@Override
	public String getHeader(String name) {

		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
	 * 
	 * @param s
	 * @return
	 */
	private static String xssEncode(String value) {
		//You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
	}

}
