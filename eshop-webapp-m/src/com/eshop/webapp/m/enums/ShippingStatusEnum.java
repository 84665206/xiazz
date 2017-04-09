package com.eshop.webapp.m.enums;
/** 
 * @author yangmeng
 * @version 创建时间：2017年2月8日 上午11:25:21 
 * 
 */
public enum ShippingStatusEnum {
	NO_SHIPPING(0, "未配送"),
	PART_SHIPPING(1, "部分配送"),
	ALL_SHIPPING(2, "全部配送"),
	;
		
	public int status;
	public String name;
		
	ShippingStatusEnum(int status,String name){
		this.status= status;
		this.name= name;
	}	
}
