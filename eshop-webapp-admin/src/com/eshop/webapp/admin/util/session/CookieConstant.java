package com.eshop.webapp.admin.util.session;

/***
 * 
 * @author Administrator
 * 
 */
public class CookieConstant {

	
	private CookieConstant() {
		// do nothing
	}

	/***
	 * Session对应的cookie name。
	 */
	public static final String SESSION_ID = "BEID";
	
	
	/**
	 * 登录成功后，用户需要返回的url
	 */
	public static final String LOGIN_RETURN_URL = "returnUrl";
	
	
	/**
	 * 用户登录时，选中记住用户名，保存在LOGIN_REMEMBER_ME中
	 */
	public static final String LOGIN_REMEMBER_ME= "rememberMe";
	
	

	public static final String CART_COOKIE= "cc";
	
	public static final String CART_PRONUM_COOKIE= "cpnc";
	
	
    /**
     * 存储edm链接页面的webid
     */
	public static final String WEB_ID_EDM="webidedm";
	
	
	/**
	 * 粉丝团的groupId
	 */
	public static final String USER_FANS_TUAN="fanstuan";


	/**
	 * 找回密码时，发送的手机验证码
	 */
	public static final String FIND_PASSWORD_CODE = "randomCode";
}
