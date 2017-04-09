package com.eshop.webapp.m.util;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;



public class Md5CyptoUtil {
	
	public static final String CHARSET_UTF_8 = "UTF-8";
	
	public static final String MD5_SIGN_KEY = "8370JLFJS471Jsl81kvzf3fcf3c63";

	public static String getRandomMd5String(int length) {
		String randomString = getRandomString(length);
	    return md5(randomString); 
	}
	
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
	    StringBuffer sb = new StringBuffer(); 
	    Random r = new Random(); 
	    int range = buffer.length(); 
	    for (int i = 0; i < length; i ++) { 
	        sb.append(buffer.charAt(r.nextInt(range))); 
	    } 
	    return sb.toString();
	}

	public static String md5(String text) {

		return DigestUtils.md5Hex(getContentBytes(text, CHARSET_UTF_8));

	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}

		try {
			return content.getBytes(charset);
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}
}
