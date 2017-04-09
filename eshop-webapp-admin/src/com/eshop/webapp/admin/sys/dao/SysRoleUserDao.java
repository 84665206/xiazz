package com.eshop.webapp.admin.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.sys.model.RoleUser;

@Repository
public class SysRoleUserDao extends BaseDao<RoleUser> {
	
	public List<RoleUser> listUserRole(Integer userId){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		return this.selectList("SysRoleUserMapper.listUsersRoles", condition);
	}
	
	/**
	 * 创建用户角色关联
	 * @return
	 */
	public Integer createUserRole(int userId,List<Integer> roleId){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userId", userId);
		condition.put("roleId", roleId);
		return this.insert("SysRoleUserMapper.grantRoleForUser", condition);
	}
	
	
	public Integer deleteRoleByUserId(Integer userId){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userId", userId);
		return this.delete("SysRoleUserMapper.deleteRoleByUserId", condition);
	}
	
	
}
