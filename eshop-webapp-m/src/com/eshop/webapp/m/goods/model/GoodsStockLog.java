package com.eshop.webapp.m.goods.model;

import java.util.Date;

import com.eshop.webapp.m.base.BaseModel;

public class GoodsStockLog extends BaseModel{
	
	private static final long serialVersionUID = -4063627352477200602L;

	private Integer id;

    private Integer sku_id;

    private String shipping_level;

    private String stock_change_type;

    private Integer stock_change_num;

    private Integer old_stock;

    private Integer new_stock;

    private Date insert_time;

    private String insert_user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSku_id() {
        return sku_id;
    }

    public void setSku_id(Integer sku_id) {
        this.sku_id = sku_id;
    }

    public String getShipping_level() {
        return shipping_level;
    }

    public void setShipping_level(String shipping_level) {
        this.shipping_level = shipping_level;
    }

    public String getStock_change_type() {
        return stock_change_type;
    }

    public void setStock_change_type(String stock_change_type) {
        this.stock_change_type = stock_change_type;
    }

    public Integer getStock_change_num() {
        return stock_change_num;
    }

    public void setStock_change_num(Integer stock_change_num) {
        this.stock_change_num = stock_change_num;
    }

    public Integer getOld_stock() {
        return old_stock;
    }

    public void setOld_stock(Integer old_stock) {
        this.old_stock = old_stock;
    }

    public Integer getNew_stock() {
        return new_stock;
    }

    public void setNew_stock(Integer new_stock) {
        this.new_stock = new_stock;
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
}