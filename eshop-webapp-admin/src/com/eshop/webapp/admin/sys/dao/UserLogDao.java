package com.eshop.webapp.admin.sys.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.sys.model.UserLog;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 * 用户登陆日志
 *
 * @author liuyi
 */
@Repository
public class UserLogDao extends BaseDao<UserLog> {

	/**
	 * 记录登陆日志
	 *
	 * @param id
	 * @return
	 */
	public Integer insertUserLog(UserLog userLog) {

		return this.insert("UserLogMapper.insertUserLog", userLog);
	}


	/**
	 * 分页查询登陆日志
	 * @param page
	 * @param username
	 * @return
	 */
	public PageResponse<UserLog> listUserLogPage(PageRequest pageRequest,
			Map<String, Object> condition){
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "UserLogMapper.searchUserLogCount",
				"UserLogMapper.searchUserLogWithPage", map);
	}

}
