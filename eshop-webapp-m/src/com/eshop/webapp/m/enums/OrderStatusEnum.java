package com.eshop.webapp.m.enums;
/** 
 * 订单状态
 * @author yangmeng
 * @version 创建时间：2017年2月8日 上午10:47:25 
 * 
 */
public enum OrderStatusEnum {
	CHUSHI(10, "初始"),
	SUCCESS(30, "订单已完成"),
	CANCEL(-99, "订单已取消")
	;
		
	public int status;
	public String name;
		
	OrderStatusEnum(int status,String name){
		this.status= status;
		this.name= name;
			
	}	
}
