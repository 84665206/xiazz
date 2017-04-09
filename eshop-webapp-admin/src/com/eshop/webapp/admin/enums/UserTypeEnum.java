
package com.eshop.webapp.admin.enums;

/**
 * <p>
 * 地区类型
 * </p>
 * @since 2013-6-21
 * @version V1.0
 * @author yangmeng
 */

public enum UserTypeEnum {
	SYSTEM_USER(1,"管理员",2);
	
	public int type;
	public String name;
	public int roleId;
	
	UserTypeEnum(int type,String name,int roleId){
		this.name= name;
		this.type= type;
		this.roleId= roleId;
	}
}
