package com.eshop.webapp.m.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.m.customer.dao.ConfigValueDao;
import com.eshop.webapp.m.model.ConfigValue;
import com.eshop.webapp.m.sys.service.ISysService;

/** 
 * @author yangmeng
 * @version 创建时间：2017年2月23日 下午6:10:18 
 * 
 */
@Service
public class SysService implements ISysService{
	
	@Autowired
	private ConfigValueDao configValueDao;

	@Override
	public Map<String, ConfigValue> getAllConfig() {
		
		return configValueDao.selectAllConfigValue();
	}
}
