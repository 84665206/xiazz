package com.eshop.webapp.m.enums;

public enum WeixinPayTypeEnum {
	/*
	JSAPI网页支付即前文说的公众号支付，
	可在微信公众号、朋友圈、聊天会话中点击页面链接，或者用微信“扫一扫”
	扫描页面地址二维码在微信中打开商户HTML5页面，在页面内下单完成支付
	*/
	JSAPI("JSAPI","JSAPI网页支付"),
	/*Native原生支付即前文说的扫码支付，商户根据微信支付协议格式生成的二维码，
	 用户通过微信“扫一扫”扫描二维码后即进入付款确认界面，输入密码即完成支付
	 */
	NATIVE("NATIVE","Native原生支付"),
	
	//手机app唤起支付
	APP("APP","APP支付");
	
	public final String type; //类型
	public final String desc; //描述场景
	
	private WeixinPayTypeEnum(String type,String desc) {
		this.desc=desc;
		this.type=type;
	}
}
