package com.eshop.webapp.m.paycenter.config;


/**
 * 微信支付
 * 基础参数
 * @author Administrator
 *
 */
public class WeixinpayConfig {
	
	// 商户号
	public static String mch_id;
	// 密钥
	public static String key;
	//appid
	public static String app_id;
	//异步回调地址
	public static String notify_url;
	//同步跳转地址
	public static String return_url;
	
	private static String app_secret;
	
	public static String charset = "UTF-8";
	
	/**
	 *JS API相关系统参数 
	 */
	public final static String AUTH_GRANT_TYPE="authorization_code";//授权类型
	public final static String AUTHORIZE_URL="https://open.weixin.qq.com/connect/oauth2/authorize";//授权url
	public final static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token"; //获取openid的url
	public final static String RESPONSE_TYPE="code";
	/**
	 * 应用授权作用域:
	 * 1.snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
	 * 2.snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 */
	public final static String SCOPE="snsapi_base";
	public final static String STATE="#wechat_redirect";
	
	public static String getMch_id() {
		return mch_id;
	}
	public static void setMch_id(String mch_id) {
		WeixinpayConfig.mch_id = mch_id;
	}
	public static String getKey() {
		return key;
	}
	public static void setKey(String key) {
		WeixinpayConfig.key = key;
	}
	public static String getApp_id() {
		return app_id;
	}
	public static void setApp_id(String app_id) {
		WeixinpayConfig.app_id = app_id;
	}
	public static String getNotify_url() {
		return notify_url;
	}
	public static void setNotify_url(String notify_url) {
		WeixinpayConfig.notify_url = notify_url;
	}
	public static String getApp_secret() {
		return app_secret;
	}
	public static void setApp_secret(String app_secret) {
		WeixinpayConfig.app_secret = app_secret;
	}
	public static String getReturn_url() {
		return return_url;
	}
	public static void setReturn_url(String return_url) {
		WeixinpayConfig.return_url = return_url;
	}
	
	
}
