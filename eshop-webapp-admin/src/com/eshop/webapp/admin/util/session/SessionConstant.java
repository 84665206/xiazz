package com.eshop.webapp.admin.util.session;

/***
 *
 * @author Administrator
 * @modify shangxuejin
 */
public class SessionConstant {

	private SessionConstant() {
		// do nothing
	}

	/***
	 * 放在Session中的UserId。
	 */
	public static final String SESSION_USER_ID_KEY = "LOGIN_USER_ID";
	/***
	 * 放在Session中的用户名。
	 */
	public static final String SESSION_USER_NAME_KEY = "LOGIN_USER_NAME";

	/**
	 * 放在Session中的登录页面的图片验证码
	 */
	public static final String SESSION_IMAGE_CODE = "LOGIN_IMAGE_CODE";

}
