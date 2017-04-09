package com.eshop.webapp.admin.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.webapp.admin.sys.dao.MenuDao;
import com.eshop.webapp.admin.sys.dao.RoleMenuDao;
import com.eshop.webapp.admin.sys.model.Menu;
import com.eshop.webapp.admin.sys.service.IMenuService;
import com.eshop.webapp.admin.util.XStreamWrapper;

/**
 * 菜单服务
 * @author yangmeng
 *
 */
@Service
public class MenuService implements IMenuService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RoleMenuDao sysRoleMenuDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Menu> getMenuListForUser(int userId) {
		List<Menu> result = null;
		if(userId == 1){
			//super账号id（系统指定）
			result = menuDao.getAllMenuList();
		}else{
			result = menuDao.getMenuListForUser(userId);
		}
		
		if(this.logger.isDebugEnabled()){
			this.logger.debug(XStreamWrapper.toXML(result));
		}
		return result;
	}

	@Override
	public String addMenu(Menu menu,List<Integer> roleCk){
		if(menu.getId()!=null){
			int flag = menuDao.editMenu(menu);
			if(flag == 1){
				return "SUCCESS";
			}
		}
		
		//1、插入功能菜单数据
		int flag = menuDao.addMenu(menu);
		if(flag == 0){
			return "ERROR";
		}
		
		//2、插入功能菜单与角色关系数据
//		if(roleCk!=null && roleCk.size()>0){
//			flag = sysRoleMenuDao.grantMenuForRole(menu.getId(), roleCk);
//			if(flag == 0){
//				return "ERROR";
//			}
//		}
		
		return "SUCCESS";
	}

	@Override
	public String deleteMenuById(Integer id){
		
		//1、删除菜单数据
		menuDao.deleteMenuById(id);
			
		//2、删除菜单关联的 菜单角色关系 数据
		sysRoleMenuDao.deleteMenuByMenuId(id);
		
		return "SUCCESS";
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> getAllMenuList() {
		List<Menu> result = menuDao.getAllMenuList();
		if(this.logger.isDebugEnabled()){
			this.logger.debug(XStreamWrapper.toXML(result));
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean authorizate(int userId, String requestUri) {
		List<Menu> menuList = null;
		if(userId == 1){
			//超级用户
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("menuUri", requestUri);
			menuList = menuDao.getMenuList(map);
		}else{
			menuList = menuDao.getMenuListForUser(userId, requestUri);
		}
		
		if(menuList!=null && menuList.size()>0){
			return true;
		}
		return false;
	}

}
