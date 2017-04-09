package com.eshop.webapp.admin.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 密码加密
 *
 * @author shangxuejin
 * @date Nov 21, 2011 4:10:53 PM
 */
public class PasswordHash {

	public static String defaultPassword = "123456";

	/**
	 * MD5盐值加密
	 *
	 * @param username 用户名
	 * @param password 密码
	 */
	public static String md5HashWithUsername(String username, String password) {

		String text = password + "{" + username + "}";

		return DigestUtils.md5Hex(text);
	}

	public static void main(String[] args) {
		System.out.println(PasswordHash.md5HashWithUsername("super", defaultPassword));

	}
}
