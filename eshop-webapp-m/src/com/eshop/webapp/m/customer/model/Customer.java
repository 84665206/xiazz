package com.eshop.webapp.m.customer.model;

import java.util.Date;

import com.eshop.webapp.m.base.BaseModel;

public class Customer extends BaseModel{
	
	private static final long serialVersionUID = -7528939133358910696L;

	private Integer id;

    private String customer_name;

    private String customer_pwd;

    private String customer_true_name;

    private String shop_name;

    private String shop_address;

    private String recruit_code;

    private Integer is_valid;

    private String memo;

    private Date insert_time;

    private String insert_user;

    private Date update_time;

    private String update_user;

    private Integer is_delete;
    
    private String open_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_pwd() {
        return customer_pwd;
    }

    public void setCustomer_pwd(String customer_pwd) {
        this.customer_pwd = customer_pwd;
    }

    public String getCustomer_true_name() {
        return customer_true_name;
    }

    public void setCustomer_true_name(String customer_true_name) {
        this.customer_true_name = customer_true_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getRecruit_code() {
        return recruit_code;
    }

    public void setRecruit_code(String recruit_code) {
        this.recruit_code = recruit_code;
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

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
}