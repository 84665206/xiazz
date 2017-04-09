package com.eshop.webapp.admin.sys.model;

import java.util.ArrayList;
import java.util.Date;

import com.eshop.webapp.admin.base.BaseModel;


public class Menu extends BaseModel {
	private static final long serialVersionUID = 1L;

	public static final int STATUS_MENU = 1;
	/* 普通uri */
	public static final int STATUS_URI = 2;
	public static final int STATUS_DEVELOP = 4;

	/* 三级菜单 */
	public static final int LEVEL_3 = 3;

	private Integer id;

	private String en_name;

	private String ch_name;

	private Integer parent_id;

	private String menu_uri;

	private Integer sort_index;

	private Integer menu_type;

	private String description;

	private Integer menu_level;

	private String dependence;

	private Integer permission;
	
	private Date update_date;
	
	private String update_user;
	
	private Integer is_delete;
	
	private Date create_date;
	
	private String create_user;
	
	private boolean checked;

	/**
	 * 是否跟角色关联
	 */
	private Integer isCheck = 0;
	/**
	 * 孩子节点列表

	 **/
	public ArrayList<Menu> children = new ArrayList<Menu>();
	

	/** (getter for dependence) */
	public String getDependence() {
		return dependence;
	}

	/** (setter for dependence) */
	public void setDependence(String dependence) {
		this.dependence = dependence;
	}

	/** (getter for id) */
	public Integer getId() {
		return this.id;
	}

	/** (setter for id) */
	public void setId(Integer id) {
		this.id = id;
	}

	/** (getter for description) */
	public String getDescription() {
		return description;
	}

	/** (setter for description) */
	public void setDescription(String description) {
		this.description = description;
	}

	/** (getter for permission) */
	public Integer getPermission() {
		return permission;
	}

	/** (setter for permission) */
	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public ArrayList<Menu> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Menu> children) {
		this.children = children;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getMenu_uri() {
		return menu_uri;
	}
	public void setMenu_uri(String menu_uri) {
		this.menu_uri = menu_uri;
	}
	public Integer getSort_index() {
		return sort_index;
	}
	public void setSort_index(Integer sort_index) {
		this.sort_index = sort_index;
	}
	public Integer getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(Integer menu_type) {
		this.menu_type = menu_type;
	}
	public Integer getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(Integer menu_level) {
		this.menu_level = menu_level;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}



}