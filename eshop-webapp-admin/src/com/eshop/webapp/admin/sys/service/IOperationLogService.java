package com.eshop.webapp.admin.sys.service;

import com.eshop.webapp.admin.sys.model.OperationLog;

/****
 * <p>
 * 操作日志
 * </p>
 * @since 2014-3-28
 * @version V1.0
 * @author yangmeng
 */
public interface IOperationLogService {

	/**
	 * 保存操作日志
	 */
	int insert(OperationLog opLog);

}
