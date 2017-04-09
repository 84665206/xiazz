package com.eshop.webapp.m.sys.service;

import java.util.Map;

import com.eshop.webapp.m.model.ConfigValue;

/** 
 * @author yangmeng
 * @version 创建时间：2017年2月23日 下午6:09:35 
 * 
 */
public interface ISysService {
	Map<String, ConfigValue> getAllConfig();
}
