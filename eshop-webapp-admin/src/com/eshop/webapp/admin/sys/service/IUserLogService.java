package com.eshop.webapp.admin.sys.service;

import java.util.Map;

import com.eshop.webapp.admin.sys.model.UserLog;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 *
 * @author liuyi
 *
 */
public interface IUserLogService {


	public int insertUserLog(UserLog userLog);

	public PageResponse<UserLog> listUserLogPage(PageRequest pageRequest,
			Map<String, Object> condition);
}
