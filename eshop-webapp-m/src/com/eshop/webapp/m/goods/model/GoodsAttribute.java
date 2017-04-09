package com.eshop.webapp.m.goods.model;

import java.util.Date;

import com.eshop.webapp.m.base.BaseModel;

public class GoodsAttribute extends BaseModel{
	
	private static final long serialVersionUID = 629595118653177268L;

	private Integer id;

    private String attr_type_code;

    private String attr_type_name;

    private String attr_code;

    private String attr_name;

    private String attr_desc;

    private Integer is_valid;

    private String memo;

    private Date insert_time;

    private String insert_user;

    private Date update_time;

    private String update_user;

    private Integer is_delete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttr_type_code() {
        return attr_type_code;
    }

    public void setAttr_type_code(String attr_type_code) {
        this.attr_type_code = attr_type_code;
    }

    public String getAttr_type_name() {
        return attr_type_name;
    }

    public void setAttr_type_name(String attr_type_name) {
        this.attr_type_name = attr_type_name;
    }

    public String getAttr_code() {
        return attr_code;
    }

    public void setAttr_code(String attr_code) {
        this.attr_code = attr_code;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getAttr_desc() {
        return attr_desc;
    }

    public void setAttr_desc(String attr_desc) {
        this.attr_desc = attr_desc;
    }

    public Integer getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Integer is_valid) {
        this.is_valid = is_valid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public String getInsert_user() {
        return insert_user;
    }

    public void setInsert_user(String insert_user) {
        this.insert_user = insert_user;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }
}