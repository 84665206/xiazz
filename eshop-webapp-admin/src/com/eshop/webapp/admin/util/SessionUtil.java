package com.eshop.webapp.admin.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

/**
 * IP 工具类
 * 
 * @author william
 * 
 */
public class SessionUtil {

 
	/**
	 * 性能稍好request.getSession(false),尽量使用此方法,减少对memcache的交互
	 * @param request
	 * @param name 
	 * @return
	 */
	public static Object getSessionAttribute(HttpServletRequest request, String name) {
		return WebUtils.getSessionAttribute(request, name);
	}

 
	/**
	 *  将对象保存至session
	 * @param request
	 * @param name
	 * @param value 
	 */
	public static void setSessionAttribute(HttpServletRequest request, String name,
			Object value) {
		WebUtils.setSessionAttribute(request, name, value);
	}

 
	/**
	 * 将对象从session移除
	 * @param request
	 * @param name 
	 */
	public static void removeSessionAttribute(HttpServletRequest request, String name) {
		WebUtils.setSessionAttribute(request, name, null);
	}
}
