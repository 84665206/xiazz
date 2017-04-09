package com.eshop.webapp.admin.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.sys.model.RoleMenu;

@Repository
public class RoleMenuDao extends BaseDao<RoleMenu> {
	
	/**
	 * 根据角色ID获取该角色的所以权限
	 * @param roleId
	 * @return
	 */
	public List<RoleMenu>listRolesMenus(Integer roleId,Integer menuId){
		Map<String,Object> condition = new HashMap<String,Object>();
		if(roleId!=null){
			condition.put("roleId", roleId);
		}if(menuId!=null){
			condition.put("menuId", menuId);
		}
		return this.selectList("RoleMenuMapper.listRolesMenus", condition);
	}
	
	/**
	 * 给角色分配菜单
	 * @param roleId 角色Id
	 * @param menuList 菜单id集合
	 * @return
	 */
	public Integer grantMenusForRol(int roleId,List<Integer> menuList){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("roleId", roleId);
		condition.put("menuList", menuList);
		return this.update("RoleMenuMapper.insertRoleMenus", condition);
	}
	
	/**
	 * 根据roleId删除菜单角色关系
	 * @param roleId
	 * @return
	 */
	public Integer deleteMenuByRoleId(int roleId){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("roleId", roleId);
		return this.delete("RoleMenuMapper.deleteRowsByRoleId", condition);
	}

	/**
	 * 根据menuId删除菜单角色关系
	 * @param menuId
	 * @return
	 */
	public Integer deleteMenuByMenuId(int menuId){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("menuId", menuId);
		return this.delete("RoleMenuMapper.deleteRowsByMenuId", condition);
	}
	
	/**
	 * 创建菜单的时候给角色分配权限
	 * @param menuId 新增菜单ID
	 * @param roleIdList 角色ID集合
	 * @return
	 */
	public Integer grantMenuForRole(Integer menuId,List<Integer> roleIdList){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("menuId", menuId);
		condition.put("roleIdList", roleIdList);
		return this.insert("RoleMenuMapper.grantMenuForRole", condition);
	}
	
	
	
	
}
