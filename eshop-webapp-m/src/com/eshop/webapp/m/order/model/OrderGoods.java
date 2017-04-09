package com.eshop.webapp.m.order.model;

import java.util.Date;

import com.eshop.webapp.m.base.BaseModel;

public class OrderGoods extends BaseModel{
	
	private static final long serialVersionUID = 7271498215467770003L;

	private Integer id;

    private Integer order_id;

    private String order_sn;

    private Integer sku_id;

    private String sku_name;

    private int sku_num;

    private double sale_price;   //销售价

    private double share_price;   //分摊价
    
    private double buy_price;    //实际购买价
    
    private String sku_pic;

    private String memo;

    private Date insert_time;

    private String insert_user;

    private Date update_time;

    private String update_user;

    private Integer is_delete;
    
    private String goods_code;
    
    private String goods_origin;
    
    private String goods_shipping;
    
    private String goods_spec;
    
    private String goods_varieties;
    
    private String goods_origin_name;
    
    private String goods_shipping_name;
    
    private String goods_spec_name;
    
    private String goods_varieties_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public Integer getSku_id() {
        return sku_id;
    }

    public void setSku_id(Integer sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public int getSku_num() {
        return sku_num;
    }

    public void setSku_num(int sku_num) {
        this.sku_num = sku_num;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public double getShare_price() {
        return share_price;
    }

    public void setShare_price(double share_price) {
        this.share_price = share_price;
    }

    public String getSku_pic() {
        return sku_pic;
    }

    public void setSku_pic(String sku_pic) {
        this.sku_pic = sku_pic;
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

	public double getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(double buy_price) {
		this.buy_price = buy_price;
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

	public String getGoods_spec_name() {
		return goods_spec_name;
	}

	public void setGoods_spec_name(String goods_spec_name) {
		this.goods_spec_name = goods_spec_name;
	}

	public String getGoods_varieties_name() {
		return goods_varieties_name;
	}

	public void setGoods_varieties_name(String goods_varieties_name) {
		this.goods_varieties_name = goods_varieties_name;
	}

	public String getGoods_code() {
		return goods_code;
	}

	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}

	public String getGoods_origin() {
		return goods_origin;
	}

	public void setGoods_origin(String goods_origin) {
		this.goods_origin = goods_origin;
	}

	public String getGoods_shipping() {
		return goods_shipping;
	}

	public void setGoods_shipping(String goods_shipping) {
		this.goods_shipping = goods_shipping;
	}

	public String getGoods_spec() {
		return goods_spec;
	}

	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}

	public String getGoods_varieties() {
		return goods_varieties;
	}

	public void setGoods_varieties(String goods_varieties) {
		this.goods_varieties = goods_varieties;
	}

}