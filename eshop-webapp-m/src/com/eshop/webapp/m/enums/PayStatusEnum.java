package com.eshop.webapp.m.enums;
/** 
 * 支付状态
 * @author yangmeng
 * @version 创建时间：2017年2月8日 上午10:47:25 
 * 
 */
public enum PayStatusEnum {
	NO_PAY(0, "未支付"),
	PART_PAY(5, "部分支付"),
	ALL_PAY(10, "全部支付")
	;
		
	public int status;
	public String name;
		
	PayStatusEnum(int status,String name){
		this.status= status;
		this.name= name;
			
	}	
}
