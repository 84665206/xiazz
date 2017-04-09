package com.eshop.webapp.m.util.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author liuyi,ZhichengHan
 * @version ${V1.0}
 */
public class HttpClientUtils {
	/****
	 */
	public static String DEFAULT_CHARSET = "UTF-8";

	/** 连接超时时间，由bean factory设置，缺省为8秒钟 */
	private int defaultConnectionTimeout = 8000;

	/** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
	private int defaultSoTimeout = 30000;

	/** 闲置连接超时时间, 由bean factory设置，缺省为60秒钟 */
	private int defaultIdleConnTimeout = 60000;

	private int defaultMaxConnPerHost = 30;

	private int defaultMaxTotalConn = 80;

	/** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒 */
	private static final long defaultHttpConnectionManagerTimeout = 3 * 1000;

	/**
	 * HTTP连接管理器，该连接管理器必须是线程安全的.
	 */
	private HttpConnectionManager connectionManager;

	private static HttpClientUtils singleton = new HttpClientUtils();

	/**
	 * logger
	 */
	protected static Logger logger = LoggerFactory
			.getLogger(HttpClientUtils.class);

	/**
	 * 工厂方法
	 *
	 * @return
	 */
	public static HttpClientUtils getInstance() {
		return singleton;
	}

	/**
	 * 私有的构造方法
	 */
	private HttpClientUtils() {
		// 创建一个线程安全的HTTP连接池
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(
				defaultMaxConnPerHost);
		connectionManager.getParams().setMaxTotalConnections(
				defaultMaxTotalConn);

		IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
		ict.addConnectionManager(connectionManager);
		ict.setConnectionTimeout(defaultIdleConnTimeout);

		ict.start();
	}

	/**
	 * 执行Http请求
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public HttpResponse execute(HttpRequest request) throws Exception {

		HttpClient httpclient = new HttpClient(connectionManager);

		// 设置连接超时
		int connectionTimeout = defaultConnectionTimeout;
		if (request.getConnectionTimeout() > 0) {
			connectionTimeout = request.getConnectionTimeout();
		}
		httpclient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(connectionTimeout);

		// 设置回应超时
		int soTimeout = defaultSoTimeout;
		if (request.getTimeout() > 0) {
			soTimeout = request.getTimeout();
		}
		httpclient.getHttpConnectionManager().getParams()
				.setSoTimeout(soTimeout);

		// 设置等待ConnectionManager释放connection的时间
		httpclient.getParams().setConnectionManagerTimeout(
				defaultHttpConnectionManagerTimeout);
		// 编码控制
		String charset = request.getCharset();
		charset = charset == null ? DEFAULT_CHARSET : charset;
		HttpMethod method = null;
		if (HttpRequest.METHOD_GET.equals(request.getMethod())) {
			method = new GetMethod(request.getUrl());
			method.getParams().setCredentialCharset(charset);
			method.setQueryString(request.getNameValuePairs());
			
		} else if (HttpRequest.METHOD_POST.equals(request.getMethod())) {
			method = new PostMethod(request.getUrl());
			((PostMethod) method).getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
			((PostMethod) method).addParameters(request.getNameValuePairs());
		}
		List<NameValuePair> headers = request.getHttpHeaders();
		for(NameValuePair nvp:headers){
			method.addRequestHeader(nvp.getName(),nvp.getValue());
		}
		try {
			int responseCode = httpclient.executeMethod(method);
			HttpResponse response = new HttpResponse(responseCode);
			if (HttpStatus.SC_OK == responseCode) {
				if (HttpResultType.BYTES == request.getResultType()) {
					response.setByteResponse(method.getResponseBody());
				} else {
					// 接收内容过大时容易内存溢出,采用流的形式
					BufferedReader bf = new BufferedReader(
							new InputStreamReader(
									method.getResponseBodyAsStream(), charset));
					StringBuffer sb = new StringBuffer();
					String contents = null;
					while ((contents = bf.readLine()) != null) {
						sb.append(contents);
					}
					response.setResponseText(sb.toString());
				}
			}
			return response;

		} catch (UnknownHostException ex) {
			logger.warn("", ex);
			throw new UnknownHostException("UnknownHostException! "+ex.getMessage());
		} catch (IOException ex) {
			logger.warn("HTTP请求执行失败，请求参数为："+request,ex);
			throw new IOException("IOException"+ex.getMessage());
		} catch (Exception ex) {
			logger.warn("", ex);
			throw ex;
		} finally {
			method.releaseConnection();
		}
	}
}
