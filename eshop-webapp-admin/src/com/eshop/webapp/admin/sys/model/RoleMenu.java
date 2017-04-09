package com.eshop.webapp.admin.sys.model;

import com.eshop.webapp.admin.base.BaseModel;


public class RoleMenu extends BaseModel{

	private static final long serialVersionUID = 8158585039449852848L;

	private Integer menuId;

    private Integer roleId;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}