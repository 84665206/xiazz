package com.eshop.webapp.admin.sys.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.webapp.admin.sys.dao.UserLogDao;
import com.eshop.webapp.admin.sys.model.UserLog;
import com.eshop.webapp.admin.sys.service.IUserLogService;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;
import com.eshop.webapp.admin.util.XStreamWrapper;

@Service
@Transactional
public class UserLogService implements IUserLogService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserLogDao userLogDao;


	@Transactional(readOnly = false)
	@Override
	public int insertUserLog(UserLog userLog) {
		if(this.logger.isDebugEnabled()){
			this.logger.debug(XStreamWrapper.toXML(userLog));
		}
		return this.userLogDao.insertUserLog(userLog);
	}


	@Transactional(readOnly = true)
	@Override
	public PageResponse<UserLog> listUserLogPage(PageRequest pageRequest,
			Map<String, Object> condition){
		this.logger.debug(XStreamWrapper.toXML(condition));
		return this.userLogDao.listUserLogPage(pageRequest, condition);
	}
}
