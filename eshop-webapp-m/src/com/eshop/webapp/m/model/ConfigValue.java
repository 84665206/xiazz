package com.eshop.webapp.m.model;

import com.eshop.webapp.m.base.BaseModel;

/** 
 * @author yangmeng
 * @version 创建时间：2016年5月31日 下午1:26:18 
 * 
 */
public class ConfigValue extends BaseModel{

	private static final long serialVersionUID = -3776384437390825155L;
	
	
	private String key;
	
	private String value;
	
	private String desc;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
