package com.eshop.webapp.admin.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

public class JSONUtils {
	
	/**
	 * json格式的字符串转换成json对象
	 * @param parameter
	 * @return 返回null时说明转换失败
	 */
	public static JSONObject parseString2Obj(String parameter){
		JSONObject jsonObject = null;
		if(StringUtils.isNotEmpty(parameter)){
			try {
				jsonObject = JSONObject.fromObject(parameter);
				if(jsonObject != null){
					return jsonObject;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
