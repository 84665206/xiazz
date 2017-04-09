package com.eshop.webapp.admin.enums;

public enum SimpleStatusEnum {

	All(-1,"所有"),
	SUCCESS(1, "成功"), FAILED(0, "失败"), 
	TRUE(1, "真"), FALSE(0, "假"),
	YES(1, "是"), NO(0, "否"),
	NO_DELETE(0,"未删除"),DELETE(1,"删除");

	public final int value;

	public final String description;

	SimpleStatusEnum(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public static Integer getSimpleStatusValue(String description){
		for(SimpleStatusEnum statusEnum:SimpleStatusEnum.values()){
			if(statusEnum.description.equals(description)){
				return statusEnum.value;
			}
		}
		return null;
	}

}
