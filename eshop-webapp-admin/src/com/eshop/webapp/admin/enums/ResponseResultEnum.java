package com.eshop.webapp.admin.enums;


/** 
 * 响应状态
 * @author yangmeng
 * @version 创建时间：2016年4月19日 下午5:04:53 
 * 
 */
public enum ResponseResultEnum {
	
	SUCCESS("200","操作成功"),
	FAIL("999","操作失败"),
	EXCEPTION("404","操作异常")
	;
	
	public String code;
	public String msg;
	
	ResponseResultEnum(String code,String msg){
		this.code= code;
		this.msg= msg;
		
	}
	
	 
}
