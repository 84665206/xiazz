package com.eshop.webapp.m.goods.model;


import java.util.Date;

import com.eshop.webapp.m.base.BaseModel;

public class GoodsSku extends BaseModel{
	private static final long serialVersionUID = -8869746755618956882L;

	private Integer id;

    private String sku_code;

    private String goods_code;

    private String goods_name;

    private String goods_varieties;
    
    private String goods_varieties_name;

    private String goods_spec;
    
    private String goods_spec_name;

    private String goods_origin;
    
    private String goods_origin_name;

    private String goods_unit;

    private String other_explain;

    private String goods_shipping;
    
    private String goods_shipping_name;

    private Integer stock_num;

    private Double sale_price;

    private Double label_price;

    private Double cost_price;

    private Integer is_offline;

    private Integer is_hide;

    private Integer is_valid;
    
    private String main_image_url;

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

    public String getSku_code() {
        return sku_code;
    }

    public void setSku_code(String sku_code) {
        this.sku_code = sku_code;
    }

    public String getGoods_code() {
        return goods_code;
    }

    public void setGoods_code(String goods_code) {
        this.goods_code = goods_code;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_varieties() {
        return goods_varieties;
    }

    public void setGoods_varieties(String goods_varieties) {
        this.goods_varieties = goods_varieties;
    }

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    public String getGoods_origin() {
        return goods_origin;
    }

    public void setGoods_origin(String goods_origin) {
        this.goods_origin = goods_origin;
    }

    public String getGoods_unit() {
        return goods_unit;
    }

    public void setGoods_unit(String goods_unit) {
        this.goods_unit = goods_unit;
    }

    public String getOther_explain() {
        return other_explain;
    }

    public void setOther_explain(String other_explain) {
        this.other_explain = other_explain;
    }

    public Integer getStock_num() {
        return stock_num;
    }

    public void setStock_num(Integer stock_num) {
        this.stock_num = stock_num;
    }

    public Double getSale_price() {
        return sale_price;
    }

    public void setSale_price(Double sale_price) {
        this.sale_price = sale_price;
    }

    public Double getLabel_price() {
        return label_price;
    }

    public void setLabel_price(Double label_price) {
        this.label_price = label_price;
    }

    public Double getCost_price() {
        return cost_price;
    }

    public void setCost_price(Double cost_price) {
        this.cost_price = cost_price;
    }

    public Integer getIs_offline() {
        return is_offline;
    }

    public void setIs_offline(Integer is_offline) {
        this.is_offline = is_offline;
    }

    public Integer getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(Integer is_hide) {
        this.is_hide = is_hide;
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

	public String getGoods_shipping() {
		return goods_shipping;
	}

	public void setGoods_shipping(String goods_shipping) {
		this.goods_shipping = goods_shipping;
	}

	public String getGoods_varieties_name() {
		return goods_varieties_name;
	}

	public void setGoods_varieties_name(String goods_varieties_name) {
		this.goods_varieties_name = goods_varieties_name;
	}

	public String getGoods_spec_name() {
		return goods_spec_name;
	}

	public void setGoods_spec_name(String goods_spec_name) {
		this.goods_spec_name = goods_spec_name;
	}

	public String getGoods_origin_name() {
		return goods_origin_name;
	}

	public void setGoods_origin_name(String goods_origin_name) {
		this.goods_origin_name = goods_origin_name;
	}

	public String getGoods_shipping_name() {
		return goods_shipping_name;
	}

	public void setGoods_shipping_name(String goods_shipping_name) {
		this.goods_shipping_name = goods_shipping_name;
	}

	public String getMain_image_url() {
		return main_image_url;
	}

	public void setMain_image_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}
}