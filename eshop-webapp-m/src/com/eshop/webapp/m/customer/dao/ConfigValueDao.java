package com.eshop.webapp.m.customer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.model.ConfigValue;

/** 
 * @author yangmeng
 * @version 创建时间：2016年5月4日 下午3:07:54 
 * 
 */
@Repository
public class ConfigValueDao extends BaseDao<ConfigValue>{
	
	public Map<String, ConfigValue> selectAllConfigValue(){
		
		return (Map<String, ConfigValue>)this.selectMap("ConfigValueMapper.selectAllConfigValue", "key");
	}
	
}
