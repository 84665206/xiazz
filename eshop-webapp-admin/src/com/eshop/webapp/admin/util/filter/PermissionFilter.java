package com.eshop.webapp.admin.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.sys.service.IMenuService;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@Component
public class PermissionFilter implements Filter {
	static Logger logger = LoggerFactory.getLogger(PermissionFilter.class);

	protected FilterConfig filterConfig;

	@Autowired
	private IMenuService menuService;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String contextPath = request.getContextPath();
		String requestUri = request.getRequestURI().replaceFirst(contextPath, "");
		request.setAttribute("basePath", contextPath);

		//白名单 URL
		String ignoreRequestUri[] = {
									"/sys/registerPassword.do",//激活密码
									"/getVerificationCode.do", //验证码
									"/sys/enterModifyPasswordPage.do", //进入修改密码
									"/sys/modifyUserPassword.do", //保存修改密码
									"/submit.do"          //提交登录
									};
		for(String uri: ignoreRequestUri){
			if(requestUri.endsWith(uri)){
				chain.doFilter(request, response);
				return;
			}
		};

		if (requestUri.equals("/") || requestUri.equals("/login.do") //
			|| requestUri.endsWith("/unauthorized.do") || requestUri.endsWith("/logout.do")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(User.CURRENT_USER_KEY_IN_SESSION);
		if (currentUser==null || currentUser.getId()==null) {
			logger.info("not login");
			response.sendRedirect(contextPath + "/login.do");
			return;
		}

		if (requestUri.equals("/index.do")) {
			chain.doFilter(request, response);
			return;
		}

		if (menuService.authorizate(currentUser.getId(), requestUri.substring(1, requestUri.length()))) {
			chain.doFilter(request, response);
			return;
		}

		String params = "requestUri=" + requestUri;
		String xRequestedWith = request.getHeader("X-Requested-With");
		params += "&requestedWith=" + (xRequestedWith == null ? "" : xRequestedWith);

		String accept = request.getHeader("Accept");
		params += "&ccept=" + (accept == null ? "" : accept);

		String redirectUri = contextPath + "/unauthorized.do?" + params;
		logger.debug("跳转到{}", redirectUri);

		response.sendRedirect(redirectUri);
	}

	public void init(FilterConfig config) throws ServletException {
		logger.info("init(), targetFilterLifecycle setted in web.xml for securityFilter, {}{}",
			"the servlet container will control the lifecycle of the target Filter, ",//
			"with this proxy delegating the corresponding calls");

		this.filterConfig = config;
	}

	public void destroy() {
		logger.info(" destoy(), targetFilterLifecycle setted in web.xml for securityFilter");
	}

}
