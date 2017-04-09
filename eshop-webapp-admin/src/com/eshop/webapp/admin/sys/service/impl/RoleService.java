package com.eshop.webapp.admin.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.webapp.admin.base.ServiceException;
import com.eshop.webapp.admin.sys.dao.RoleDao;
import com.eshop.webapp.admin.sys.dao.RoleMenuDao;
import com.eshop.webapp.admin.sys.dao.SysRoleUserDao;
import com.eshop.webapp.admin.sys.dao.UserDao;
import com.eshop.webapp.admin.sys.model.Role;
import com.eshop.webapp.admin.sys.model.RoleMenu;
import com.eshop.webapp.admin.sys.model.RoleUser;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.sys.service.IRoleService;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

@Service
@Transactional
public class RoleService implements IRoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleMenuDao sysRoleMenuDao;
	
	@Autowired
	private SysRoleUserDao sysRoleUserDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<RoleMenu> listRolesMenus(Integer roleId){
		return sysRoleMenuDao.listRolesMenus(roleId,null);
	}

	@Override
	public List<RoleMenu> listRolesMenusByMenuId(Integer menuId){
		return sysRoleMenuDao.listRolesMenus(null,menuId);
	}
	
	@Override
	public List<Role> listLeftRole(List<Integer> userIdList){
		if(userIdList != null && userIdList.size() > 0){
			return this.roleDao.listLeftRole(userIdList);
		}
		else{
			return this.roleDao.listAllRoles();
		}
	}
	
	@Override
	public List<RoleUser> listUsersRole(Integer userId){
		return this.sysRoleUserDao.listUserRole(userId);
	}
	
	
	@Override
	public List<Role> listRightRole(List<Integer> userIdList){
		if(userIdList != null && userIdList.size() > 0){
			return this.roleDao.listRightRole(userIdList);
		}else{
			return null;
		}
		
	}
	
	@Override
	public List<Role> listAllRoles(){
		return roleDao.listAllRoles();
	}
	
	@Override
	public Integer createUserRole(int userId,List<Integer> roleId){
		List<RoleUser> sysRoleUserList = sysRoleUserDao.listUserRole(userId);
		if(sysRoleUserList != null && sysRoleUserList.size() > 0){
			sysRoleUserDao.deleteRoleByUserId(userId);
		}
		return roleId.size()==0?0:sysRoleUserDao.createUserRole(userId, roleId);
	}
	
	/**
	 * 给角色分配菜单,分配菜单前先删除该角色的所有菜单
	 */
	@Override
	public void grantMenusForRol(int roleId,List<Integer> menuList){
		if(menuList != null && menuList.size() > 0){
			sysRoleMenuDao.deleteMenuByRoleId(roleId);
			sysRoleMenuDao.grantMenusForRol(roleId, menuList);
		}
	}
	
	
	/**
	 * 根据角色ID获取角色对象
	 * @param id 角色ID
	 * @return 角色对象
	 */
	public Role getRoleById(int id) {
		return roleDao.getRoleById(id);
	}

	/**
	 * 列出角色分页对象
	 * @param page 
	 * @param roleName
	 * @return
	 */
	public PageResponse<Role> listRolePage(PageRequest pageRequest, String roleName) {
		return roleDao.listRolePage(pageRequest, roleName);
	}

	/**
	 * 新增角色
	 * @param role 角色对象
	 */
	public void addRole(Role role) {
		roleDao.insertRole(role);
	}

	/**
	 * 修改角色
	 * @param role 角色对象
	 */
	public void modifyRole(Role role) {
		roleDao.updateRole(role);
	}

	/**
	 * 删除角色
	 * @param roleIds 角色Id
	 */
	public void deleteRoles(int[] roleIds) {
		int result = roleDao.deleteRoles(roleIds);
		if (result != roleIds.length) {
			throw new ServiceException("可删除数量与预期不符");
		}
	}

	@Override
	public Integer grantMenuForRole(Integer menuId, String roleIds) {
		List<Integer> roleIdList = new ArrayList<Integer>();
		if(StringUtils.isNotEmpty(roleIds)){
			String[] roleId = roleIds.split(",");
			if(roleId != null && roleId.length > 0){
				for(int i=0;i<roleId.length;i++){
					roleIdList.add(Integer.parseInt(roleId[i]));
				}
			}
		}
		return this.sysRoleMenuDao.grantMenuForRole(menuId, roleIdList);
	}
	
	/**
	 * 查询角色用户
	 * @param roleId
	 * @return
	 */
	public List<User> selectRoleUser(int roleId){
		
		return userDao.selectRoleUser(roleId);
	}
	
	
}
