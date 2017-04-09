package com.eshop.webapp.m.enums;
/** 
 * @author yangmeng
 * @version 创建时间：2017年2月8日 上午11:25:21 
 * 
 */
public enum ShippingWayEnum {
	ONE_TIME("onetime", "一次送"),
	MULTIPLE("multiple", "分批送")
	;
		
	public String way;
	public String name;
		
	ShippingWayEnum(String way,String name){
		this.way= way;
		this.name= name;
			
	}	
}
