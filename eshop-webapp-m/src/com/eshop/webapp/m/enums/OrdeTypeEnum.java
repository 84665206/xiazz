package com.eshop.webapp.m.enums;
/** 
 * 订单类型
 * @author yangmeng
 * @version 创建时间：2017年2月8日 上午10:47:25 
 * 
 */
public enum OrdeTypeEnum {
	NOMAL(1, "普通订单"),
	PRE_SELL(2, "预售订单")
	;
		
	public int type;
	public String name;
		
	OrdeTypeEnum(int type,String name){
		this.type= type;
		this.name= name;
			
	}	
}
