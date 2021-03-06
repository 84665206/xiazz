package com.eshop.webapp.admin.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HessianRequestFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String httpRequestId = httpRequest.getHeader("httpRequestId");
		if(httpRequestId!=null){
			HttpRequestIdHolder.setHttpRequestId(httpRequestId);
		}
		chain.doFilter(httpRequest, response);
	}

	@Override
	public void destroy() {
		
	}

}
