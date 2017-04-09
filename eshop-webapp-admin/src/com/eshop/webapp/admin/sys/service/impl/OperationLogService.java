package com.eshop.webapp.admin.sys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.admin.sys.dao.OperationLogDao;
import com.eshop.webapp.admin.sys.model.OperationLog;
import com.eshop.webapp.admin.sys.service.IOperationLogService;

/**
 * 
 * <p>
 * 操作日志
 * </p>
 * @since 2014-3-28
 * @version V1.0
 * @author yangmeng
 */
@Service
public class OperationLogService implements IOperationLogService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OperationLogDao operationLogDao;

	@Override
	public int insert(OperationLog opLog) {

		int effect = this.operationLogDao.insertOperationLog(opLog);

		return effect;
	}

}
