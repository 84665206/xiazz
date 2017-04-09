package com.eshop.webapp.admin.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.webapp.admin.base.ServiceException;
import com.eshop.webapp.admin.sys.dao.UserDao;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.sys.service.IUserService;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;
import com.eshop.webapp.admin.util.PasswordHash;
import com.eshop.webapp.admin.util.XStreamWrapper;

@Service
public class UserService implements IUserService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleService roleService;

	@Override
	public PageResponse<User> listUserPage(PageRequest pageRequest,  Map<String, Object> condition) {
		PageResponse<User> pageuser=userDao.listUserPage(pageRequest, condition);
		
		return pageuser;
	}

	@Override
	public List<User> getUserList(Map<String, Object> condition){
		
		List<User> userList = this.userDao.selectUserList(condition);
		if(this.logger.isDebugEnabled()){
			this.logger.debug(XStreamWrapper.toXML(userList));
		}
		return userList;
	}

	@Override
	public User getUserByUserName(String userName) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("getUserByUserName:userName{}", userName);
		}
		User user = this.userDao.getUserByUserName(userName);
		if (user != null && this.logger.isDebugEnabled()) {
			this.logger.debug(XStreamWrapper.toXML(user));
		}
		return user;

	}
	
	@Override
	public User getUserById(Integer id) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("getUserById:id{}", id);
		}
		User user = this.userDao.getUserById(id);
		if (user != null && this.logger.isDebugEnabled()) {
			this.logger.debug(XStreamWrapper.toXML(user));
		}
		return user;

	}

	@Override
	public List<User> getUsersWithUserGroupRela(Integer groupId) {

		return this.userDao.getUsersWithUserGroupRela(groupId);

	}

	@Override
	public Integer updateUserById(User user){
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(XStreamWrapper.toXML(user));
		}
		return this.userDao.updateUserById(user);
	}


	@Transactional(readOnly = false)
	@Override
	public Integer updateUserLoginSum(User user){
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(XStreamWrapper.toXML(user));
		}
		return this.userDao.updateLoginSum(user);
	}


	@Transactional(readOnly = false)
	@Override
	public Integer insertUserInfo(User user, User currentUser){
		
		user.setPassword(PasswordHash.md5HashWithUsername(user.getUser_name(), user.getUser_name()+PasswordHash.defaultPassword));
		user.setCreate_user(user.getUser_name());
		user.setCreate_date(new Date());
		user.setPwd_terminal_time(new Date());
		user.setIs_delete(0);
		
		//创建用户
		int total=this.userDao.insertUserInfo(user);
		if(total==0) throw new ServiceException("创建用户失败(User)，请稍后再试");
		
		if(user.getId()==null) throw new ServiceException("创建用户失败(id=null)，请稍后再试");
//		
//		List<Integer> roleIds=new ArrayList<Integer>();
//		//默认角色
//		roleIds.add(1);
//		
//		//添加角色
//		UserTypeEnum userTypeEnum[]=UserTypeEnum.values();
//		for(int i=0;i<userTypeEnum.length;i++){
//			if(user.getUser_type().equals(userTypeEnum[i].type)){
//				roleIds.add(userTypeEnum[i].roleId);
//			}
//		}
//		
//		//为用户添加权限
//		total=roleService.createUserRole(user.getId(), roleIds);
//		if(total==0) throw new ServiceException("创建用户失败(Role)，请稍后再试");
		
		return total;
	}

	@Transactional(readOnly = false)
	@Override
	public Integer deleteUserById(User user){
		if (this.logger.isDebugEnabled()) {
			this.logger.debug(XStreamWrapper.toXML(user));
		}
		return this.userDao.deleteUserById(user);
	}

	@Override
	public Integer insertUserForSSO(User user) {
		
		return userDao.insertUserInfo(user);
	}

	@Override
	public Integer updateUserPassByUserName(User user) {
		
		return userDao.updateUserPassByUserName(user);
	}
}
