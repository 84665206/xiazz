package com.eshop.webapp.admin.util.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieWrapper {

	private Logger logger = LoggerFactory.getLogger(CookieWrapper.class);

	private String domain = null;// 备用，为了以后定根域名
	private HttpServletResponse response = null;
	private HttpServletRequest request = null;

	public CookieWrapper(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	private Cookie getCookie(String cookieName) {

		try {
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals(cookieName)) {
						return cookie;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得cookie值
	 * 
	 * @param cookieName
	 * @return
	 */
	public String getCookieValue(String cookieName) {

		try {
			Cookie cookie = getCookie(cookieName);
			if (cookie != null) {
				return cookie.getValue();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置普通cookie
	 * 
	 * @param name
	 * @param value
	 * @param expire
	 *            大于0
	 * 
	 */
	public boolean addCookie(String name, String value, int expire) {
		if (expire > 0 && name != null && value != null) {
			Cookie cookie = new Cookie(name, value);
			if (domain != null) {
				cookie.setDomain(domain);
			}
			cookie.setPath("/");
			cookie.setMaxAge(expire);
			response.addCookie(cookie);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * add会话级cookie
	 * 
	 * @param name
	 * @param value
	 * @param httpOnly
	 * @return
	 */
	public boolean addSessionCookie(String name, String value, boolean httpOnly) {
		if (name != null && value != null) {
			Cookie cookie = new Cookie(name, value);
			if (domain != null) {
				cookie.setDomain(domain);
			}
			cookie.setPath("/");
			cookie.setMaxAge(-1);
			if (httpOnly) {
				// todo:httponly貌似没有作用
				// cookie.setPath("/; HTTPOnly");
				// servlet-api3.0方法
				// cookie.setHttpOnly(httpOnly);
				response.addCookie(cookie);
			} else {
				response.addCookie(cookie);
			}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新cookie
	 * 
	 * @param name
	 * @param value
	 * @param expire
	 * @return
	 */
	public boolean updateCookie(String name, String value, int expire) {
		if (expire > 0 && name != null && value != null) {
			Cookie cookie = getCookie(name);
			if (cookie != null) {
				if (domain != null) {
					cookie.setDomain(domain);
				}
				cookie.setValue(value);
				cookie.setPath("/");
				cookie.setMaxAge(expire);
				response.addCookie(cookie);
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * 清楚cookie
	 * 
	 * @param cookieName
	 * @return
	 */
	public boolean deleteCookie(String cookieName) {
		if (cookieName != null) {
			Cookie cookie = getCookie(cookieName);
			if (cookie != null) {
				cookie.setMaxAge(0);// 如果0，就说明立即删除
				cookie.setPath("/");// 不要漏掉
				response.addCookie(cookie);
				return true;
			}
		}
		return false;
	}

}
