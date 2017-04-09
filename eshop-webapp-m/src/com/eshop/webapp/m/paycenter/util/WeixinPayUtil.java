package com.eshop.webapp.m.paycenter.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @ClassName: WxpayUtil 
 * @Description: 微信支付接口工具类 
 * @author Jun.Zhang 
 * @date 2015-4-3 下午3:20:59 
 *
 */
public class WeixinPayUtil {
	
	protected static Logger logger = LoggerFactory.getLogger(WeixinPayUtil.class);
	
	public static String baseChar="abcdefghijklmnopqrstuvwxyz0123456789";
	
	/** 连接超时时间 缺省为30秒 */
	private static final int DEFAULT_CONNECTION_TIME_OUT = 30000;
	/** 响应超时时间 缺省为30秒 */
	private static final int SO_TIMEOUT=30000; 
	
	/**
	 * 为空判断
	 * @param value
	 * @return
	 */
	public static String trimString(String value){
		if (StringUtils.isBlank(value)) {
			value=null;
		}
		return value;
	}
	
	public static String getCurrentTimeStamp(){
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 根据uuid生成32位随机串
	 * @return
	 */
	public static String generateRandom32ByUUID(){
		
		UUID uuid = UUID.randomUUID(); 
		
		return uuid.toString().replace("-", "").toUpperCase();
		
	}
	
	/**
	 * 根据当前时间生成32位随机字符
	 * @return
	 */
	public static String generateRandom32(){
		
		String currTime=getCurrTime(); //14位
		
		String noncestr=createNoncestr(18);//18位
		
		return currTime+noncestr;
	}
	
	
	
	/**
	 * 获取当前时间 yyyyMMddHHmmss(14位)
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 作用：产生随机字符串
	 * @param length
	 * @return
	 */
	public static String createNoncestr(int length){
			Random random = new Random();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(baseChar.length());
				sb.append(baseChar.charAt(number));
			}
			return sb.toString();
	}
	
	
	/** 对字符串进行URL编码 */
	public static String UrlEncode(String instr, String charset) {
		if (StringUtils.isBlank(instr)) {
			return "";
		} else {
			String res="";
			try {
				res = URLEncoder.encode(instr, charset);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return res;
		}
	}
	/**
	 * 微信http request请求公共方法 POST请求(不使用证书)
	 * @param requestUrl
	 * @param requestXML
	 * @return
	 */
	public static String requestWeixinPay(String requestUrl,String requestXML){
		
		String responseContext=null;
		
		HttpClient client = new HttpClient();
	
		HttpClientParams params = new HttpClientParams();
		
		//设置超时时间 30秒
		params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, DEFAULT_CONNECTION_TIME_OUT);
		params.setParameter(HttpClientParams.SO_TIMEOUT, SO_TIMEOUT);
		
		client.setParams(params);
		
        PostMethod authpost = new PostMethod(requestUrl);
        
        try {
        	//设置提交的post xml内容
        	RequestEntity requestEntity=new StringRequestEntity
            		(requestXML, "application/xml", "UTF-8");
        	
            authpost.setRequestEntity(requestEntity);
            
            // 执行Post请求
        	int status=client.executeMethod(authpost);
        	
        	if (status!=200) {
        		logger.error("微支付网络异常:"+requestUrl+"请求响应码:"+status);
			}else{
				responseContext=authpost.getResponseBodyAsString();	
			}
        	
		} catch (HttpException e) {
			logger.error("微信支付接口"+requestUrl+"HTTP异常:"+e.getMessage());
		} catch (IOException e) {
			logger.error("微信支付接口"+requestUrl+"IO异常:"+e.getMessage());
		}finally{
			
			if (authpost!=null) {
				authpost.releaseConnection(); //释放链接
			}
		}
        return responseContext;
	}


	
	 	/**
		 * 根据参数和url生成requesturl
		 * @param requestUrl
		 * @param parameters
		 * @return
		 */
		public static String createRequestUrl(String requestUrl,Map<String,String> parameters) {

			StringBuilder sb = new StringBuilder();
			ArrayList akeys = new ArrayList(parameters.keySet());
			Collections.sort(akeys);
			for (Object key : akeys) {
				String v = (String) parameters.get(key);
				if (null != v && !"key".equals(key)) {
					sb.append(key + "=" + v
							+ "&");
				}
			}
			String sbs = sb.toString();
			// 去掉最后一个&
			if (sb.length() > 0) {
				sbs = sb.substring(0, sb.length() - 1);
			}

			return requestUrl + "?" + sbs;
		}

}
