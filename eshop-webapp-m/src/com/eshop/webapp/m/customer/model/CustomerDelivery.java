package com.eshop.webapp.m.customer.model;

import java.util.Date;

import com.eshop.webapp.m.base.BaseModel;

public class CustomerDelivery extends BaseModel{
	
	private static final long serialVersionUID = 7884449731488452584L;

	private Integer id;

    private Integer customer_id;

    private String consignee;

    private String province;

    private String city;

    private String district;

    private String address;

    private String tel;

    private String mobile;

    private Date insert_time;

    private String insert_user;

    private Date update_time;

    private String update_user;

    private Integer is_delete;
    
    private Integer is_default;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

	public Integer getIs_default() {
		return is_default;
	}

	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}
}