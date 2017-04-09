package com.eshop.webapp.admin.sys.service;

import java.util.List;
import java.util.Map;

import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 *
 * @author liuyi
 *
 */
public interface IUserService {

	public PageResponse<User> listUserPage(PageRequest pageRequest, Map<String, Object> condition);

	public List<User> getUserList(Map<String, Object> condition);

	public User getUserByUserName(String userName);

	public User getUserById(Integer id);

	public Integer updateUserById(User user);

	public Integer updateUserLoginSum(User user);

	public Integer insertUserInfo(User user, User currentUser);

	public Integer deleteUserById(User user);

	public List<User> getUsersWithUserGroupRela(Integer groupId);
	
	public Integer insertUserForSSO(User user);
	
	public Integer updateUserPassByUserName(User user);
}
