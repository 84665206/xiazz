package com.eshop.webapp.admin.sys.service;

import java.util.List;

import com.eshop.webapp.admin.sys.model.Role;
import com.eshop.webapp.admin.sys.model.RoleMenu;
import com.eshop.webapp.admin.sys.model.RoleUser;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

public interface IRoleService {
	/**
	 * 根据角色ID获取角色对象
	 * @param id 角色ID
	 * @return 角色对象
	 */
	public Role getRoleById(int id);

	/**
	 * 列出角色分页对象
	 * @param page 
	 * @param roleName
	 * @return
	 */
	public PageResponse<Role> listRolePage(PageRequest pageRequest, String roleName);

	/**
	 * 新增角色
	 * @param role 角色对象
	 */
	public void addRole(Role role);

	/**
	 * 修改角色
	 * @param role 角色对象
	 */
	public void modifyRole(Role role);

	/**
	 * 删除角色
	 * @param roleIds 角色Id
	 */
	public void deleteRoles(int[] roleIds);

	/**
	 * 给角色分配权限
	 * @param roleId
	 * @param menuList
	 */
	public void grantMenusForRol(int roleId,List<Integer> menuList);
	
	/**
	 * 根据roleid获取该角色的所有菜单
	 * @param roleId
	 * @return
	 */
	public List<RoleMenu> listRolesMenus(Integer roleId);

	/**
	 * 根据menuId获取该菜单所属的角色列表
	 * @param menuId
	 * @return
	 */
	public List<RoleMenu> listRolesMenusByMenuId(Integer menuId);
	
	/**
	 * 获取所有角色
	 * 没有分页
	 * @return
	 */
	public List<Role> listAllRoles();
	
	/**
	 * 创建用户角色关联
	 * @return
	 */
	public Integer createUserRole(int userId,List<Integer> roleId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> listLeftRole(List<Integer> userIdList);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> listRightRole(List<Integer> userIdList);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<RoleUser> listUsersRole(Integer userId);
	
	/**
	 * 创建菜单的时候给角色分配权限
	 * @param menuId 新增菜单ID
	 * @param roleIdList 角色ID集合
	 * @return
	 */
	public Integer grantMenuForRole(Integer menuId,String roleIds);
	
	/**
	 * 查询角色用户
	 * @param roleId
	 * @return
	 */
	public List<User> selectRoleUser(int roleId);
	
	
	
	
}
