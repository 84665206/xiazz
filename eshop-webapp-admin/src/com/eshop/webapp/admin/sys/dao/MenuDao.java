package com.eshop.webapp.admin.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
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
@Repository
public class MenuDao extends BaseDao<Menu> {
	
	/**
	 * 获取某用户所持有的所有Menu列表信息
	 * @param userId
	 * @return
	 */
	public List<Menu> getMenuListForUser(int userId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		
		return this.selectList("MenuMapper.selectMenuListForUser",map);
	}
	/**
	 * 编辑menu
	 * @param menu
	 * @return
	 */
	public Integer editMenu(Menu menu){
		return this.update("MenuMapper.update", menu);
	}
	/**
	 * 新增menu
	 * @param menu
	 * @return
	 */
	public Integer addMenu(Menu menu){
		return this.insert("MenuMapper.insert", menu);
	}
	
	public Integer deleteMenuById(Integer id){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("id", id);
		return this.delete("MenuMapper.deleteMenuById", condition);
	}

	public Integer deleteMenuById(String ids){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("ids", ids);
		return this.delete("MenuMapper.deleteMenuByIds", condition);
	}
	
	
	/**
	 * 获取某用户所持有的所有Menu列表信息
	 * @param userId
	 * @param menuUri
	 * @return
	 */
	public List<Menu> getMenuListForUser(int userId, String menuUri){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("menuUri", menuUri);
		
		return this.selectList("MenuMapper.selectMenuListForUser",map);
	}

	/**
	 * 获取所有Menu列表信息
	 * @return
	 */
	public List<Menu> getAllMenuList(){
		return this.selectList("MenuMapper.selectAllMenuList");
	}

	/**
	 * 获取Menu列表信息
	 * @return
	 */
	public List<Menu> getMenuList(Map<String, Object> map){
		return this.selectList("MenuMapper.selectMenuList",map);
	}

}
