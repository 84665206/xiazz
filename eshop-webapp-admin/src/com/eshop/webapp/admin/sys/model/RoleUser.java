package com.eshop.webapp.admin.sys.model;

import com.eshop.webapp.admin.base.BaseModel;


public class RoleUser extends BaseModel{

	private static final long serialVersionUID = 932689423378580279L;

	private Integer roleId;

    private Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}