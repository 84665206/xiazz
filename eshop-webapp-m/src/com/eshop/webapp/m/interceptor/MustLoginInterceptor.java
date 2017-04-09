package com.eshop.webapp.m.interceptor;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.util.SessionUtil;
import com.eshop.webapp.m.util.session.CookieConstant;
import com.eshop.webapp.m.util.session.CookieWrapper;
import com.eshop.webapp.m.util.session.SessionConstant;

/** 
 * 登录拦截器
 * @author yangmeng
 * @version 创建时间：2016年4月9日 下午4:46:24 
 * 
 */
public class MustLoginInterceptor implements HandlerInterceptor {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		try {
			Object customer = SessionUtil.getSessionAttribute(request,
					SessionConstant.SESSION_CUSTOMER);
			if(customer==null){
				String requestType = request.getHeader("X-Requested-With"); 
				if (requestType != null && ("XMLHttpRequest".equals(requestType)||"MOBHttpRequest".equals(requestType))) {
					this.getRefererUrl(request,response);
					response.setHeader("Content-Type", "application/json; charset=utf-8");
					response.setCharacterEncoding("utf-8");
					PrintWriter out = response.getWriter();
					out.print("{\"needlogin\":true}");
					out.close();
				} else {
					this.getCurrentUrl(request,response);
					response.sendRedirect("/passport/login.do");
				}
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("..请求服务出现错误");
			response.setHeader("Content-Type", "application/json; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print("{\"code\":\""+ResponseResultEnum.EXCEPTION.code+"\", \"msg\":\""+ResponseResultEnum.EXCEPTION.msg+"\"}");
			out.close();
		}
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	private void getCurrentUrl(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		CookieWrapper cookie = new CookieWrapper(request, response);
		Map map = request.getParameterMap();
		Iterator it = map.entrySet().iterator();
		String params = "";
		while (it.hasNext()) {
			if (!"".equals(params))
				params += "&";
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			String[] value = (String[]) entry.getValue();
			for (int i = 0; i < value.length; i++) {
				if (i > 0)
					params += "&";
				params += key + "=" + value[i];
			}
		}
		String returnUrl = /*
							 * request.getScheme() + "://" +
							 * request.getServerName() + ":" +
							 * request.getServerPort()+
							 */
		request.getServletPath();
		if (!"".equals(params))
			returnUrl = returnUrl + "?" + params;
		String url = URLEncoder.encode(returnUrl, "UTF-8");
		cookie.addCookie(CookieConstant.LOGIN_RETURN_URL, url, 3600);
	}
	
	private void getRefererUrl(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		CookieWrapper cookie = new CookieWrapper(request, response); 
		String returnUrl =request.getHeader("Referer"); 
		if(StringUtils.isNotBlank(returnUrl)){
			String url = URLEncoder.encode(returnUrl, "UTF-8");
			if(StringUtils.isNotBlank(returnUrl)){
			 cookie.addCookie(CookieConstant.LOGIN_RETURN_URL, url, 3600);
			}
		}
	}
}
