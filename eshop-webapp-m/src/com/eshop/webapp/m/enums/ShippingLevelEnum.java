package com.eshop.webapp.m.enums;
/** 
 * 配送级别
 * @author yangmeng
 * @version 创建时间：2017年2月8日 上午10:47:25 
 * 
 */
public enum ShippingLevelEnum {
	TODAY("4002", "次日送"),
	TOMORROW("4001", "今日送")
	;
		
	public String level;
	public String name;
		
	ShippingLevelEnum(String level,String name){
		this.level= level;
		this.name= name;
			
	}	
}
