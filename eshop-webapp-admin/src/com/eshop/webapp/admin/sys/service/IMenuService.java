package com.eshop.webapp.admin.sys.service;

import java.util.List;

import com.eshop.webapp.admin.sys.model.Menu;


/**
 * <p>
 * TODO类概述
 * </p>
 * <p>
 * 提供如下功能：<br>
 * <1>.TODO<br>
 * <2>.TODO<br>
 * </p>
 * @since 2012-11-15
 * @version V1.0
 * @author luowen
 */
public interface IMenuService {

	/**
	 * 获取某用户所持有的所有action列表
	 * @param userId
	 * @return
	 */
	public List<Menu> getMenuListForUser(int userId);

	/**
	 * 获取所有action列表
	 * @return
	 */
	public List<Menu> getAllMenuList();
	
	/**
	 * 验证用户是否有某权限
	 * @param userId
	 * @param requestUri
	 * @return
	 */
	public boolean authorizate(int userId, String requestUri);

	/**
	 * 新增菜单
	 * @param menu 对象
	 * @return
	 */
	public String addMenu(Menu menu,List<Integer> roleCk);

	/**
	 * 根据一组ID 删除菜单
	 * @param ids
	 * @return
	 */
	public String deleteMenuById(Integer id);
	
	
	
}
