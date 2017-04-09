package com.eshop.webapp.admin.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.enums.SimpleStatusEnum;
import com.eshop.webapp.admin.sys.model.Role;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

@Repository
public class RoleDao extends BaseDao<Role> {

	
	/**
	 * 根据Id 获取 Role 对象
	 * @param id
	 * @return
	 */
	public Role getRoleById(int id) {
		return (Role) this.selectOne("RoleMapper.searchRoleById", id);
	}

	/**
	 * 保存角色对象
	 * @param role 角色对象
	 * @return
	 */
	public int insertRole(Role role) {
		return this.insert("RoleMapper.insertRole", role);
	}

	/**
	 * 修改角色对象
	 * @param role
	 * @return
	 */
	public int updateRole(Role role) {
		return this.update("RoleMapper.updateRole", role);
	}

	/**
	 * 逻辑删除角色,设置 is_delete = 1
	 * @param roleIds 需要删除的角色Id
	 * @return 修改数据库记录条数
	 */
	public int deleteRoles(int[] roleIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleIds", roleIds);
		return this.delete("RoleMapper.deleteRoles", map);
	}

	/**
	 * 获取角色分页对象
	 * @param page 分页对象
	 * @param role_name 角色名称
	 * @return 分页对象 
	 */
	public PageResponse<Role> listRolePage(PageRequest pageRequest, String role_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_name", role_name);
		map.put("is_delete", SimpleStatusEnum.FALSE.value);
		return this.findPage(pageRequest, "RoleMapper.searchRoleCount", "RoleMapper.searchRoleWithPage", map);
	}
	
	/**
	 * 列出所有的角色
	 * @return
	 */
	public List<Role> listAllRoles(){
		return this.selectList("RoleMapper.listAllRoles");
	}
	

	public List<Role> listLeftRole(List<Integer> userIdList){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userIdList", userIdList);
		return this.selectList("RoleMapper.listLeftRole", condition);
	}
	
	public List<Role> listRightRole(List<Integer> userIdList){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userIdList", userIdList);
		return this.selectList("RoleMapper.listRightRole", condition);
	}
}
