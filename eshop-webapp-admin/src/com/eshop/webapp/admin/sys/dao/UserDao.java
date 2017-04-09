package com.eshop.webapp.admin.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 * 用户管理dao
 *
 * @author alex, liuyi
 */
@Repository
public class UserDao extends BaseDao<User> {

	/**
	 * 分页查询用户
	 * @param page
	 * @param username
	 * @return
	 */
	public PageResponse<User> listUserPage(PageRequest pageRequest, Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "UserMapper.searchUserCount",
				"UserMapper.searchUserWithPage", map);
	}

	/**
	 * 查询所有非删除的用户
	 * @return
	 */
	public List<User> selectUserList(Map<String, Object> condition){
		
		return this.selectList("UserMapper.selectUserList", condition);
	}

	/**
	 * 根据名字取得用户
	 * @param userName
	 * @return
	 */
	public User getUserByUserName(String user_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", user_name);
		return (User) this.selectOne("UserMapper.getUserByUserName", map);
	}

	/**
	 * 根据用户ID取得用户
	 * @param userName
	 * @return
	 */
	public User getUserById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (User) this.selectOne("UserMapper.getUserById", map);
	}


	public List<User> getUsersWithUserGroupRela(Integer group_id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("group_id", group_id);
		return (List<User>)this.selectList("UserMapper.getUsersWithUserGroupRela", map);
	}

	/**
	 * 更新登陆次数
	 * @param id
	 * @return
	 */
	public Integer updateLoginSum(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		Integer login_sum = 1;
		if(user.getLogin_sum()!= null){
			login_sum = user.getLogin_sum()+1;
		}
		map.put("login_sum", login_sum);
		return this.sqlSession.update("UserMapper.updateLoginSum", map);
	}


	/**
	 * 新增用户信息
	 * @param user
	 * @return
	 */
	public Integer insertUserInfo(User user){
		return this.sqlSession.insert("UserMapper.insert", user);
	}


	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public Integer updateUserById(User user){
		return this.sqlSession.update("UserMapper.updateUserById", user);
	}

	/**
	 * 删除一般是逻辑删除，调用 updateUserById
	 * @param user
	 * @return
	 */
	public Integer deleteUserById(User user){
		return this.sqlSession.delete("UserMapper.deleteUserById", user);
	}
	
	/**
	 * 查询角色用户
	 * @param roleId
	 * @return
	 */
	public List<User> selectRoleUser(int role_id){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("role_id", role_id);
		
		return this.sqlSession.selectList("UserMapper.selectRoleUser", condition);
	}
	
	/**
	 * 根据用户名更改密码
	 * @param user
	 * @return
	 */
	public Integer updateUserPassByUserName(User user){
		return this.update("UserMapper.updateUserPassByUserName", user);
	}
}
