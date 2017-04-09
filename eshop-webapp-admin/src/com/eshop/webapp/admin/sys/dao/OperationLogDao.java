package com.eshop.webapp.admin.sys.dao;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.sys.model.OperationLog;

/****
 * 
 * <p>
 * 操作日志
 * </p>
 * @since 2014-3-28
 * @version V1.0
 * @author yangmeng
 */
@Repository
public class OperationLogDao extends BaseDao<OperationLog> {
	
	/**
	 * 记录日志
	 * @param operationLog
	 * @return
	 */
	public Integer insertOperationLog(OperationLog operationLog){
		
		return this.sqlSession.insert("OperationLogMapper.insertOperationLog", operationLog);
	}
}