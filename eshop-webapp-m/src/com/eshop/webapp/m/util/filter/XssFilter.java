package com.eshop.webapp.m.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/****
 * 防止Xss漏洞和SQL注入过滤器。
 * @author ZhichengHan
 *
 */
public class XssFilter implements Filter {
	//private FilterConfig filterConfig;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//this.filterConfig = filterConfig;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);

	}

	@Override
	public void destroy() {
		//this.filterConfig = null;

	}

}