package com.eshop.webapp.m.order.model;


import java.util.Date;

import com.eshop.webapp.m.base.BaseModel;

public class OrderBatchSend extends BaseModel{
    
	private static final long serialVersionUID = 4314278224527049623L;

	private Integer id;

    private String batch_sn;

    private Integer og_id;

    private Integer send_no;

    private Integer sku_id;

    private String sku_name;

    private Integer sku_num;

    private double sale_price;

    private double share_price;

    private Integer pay_status;

    private double need_pay_amount;

    private String need_send_time;

    private double pay_amount;

    private Date pay_time;

    private Integer shipping_status;

    private Date shipping_time;

    private String shipping_person;

    private String shipping_phone;

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

    public String getBatch_sn() {
        return batch_sn;
    }

    public void setBatch_sn(String batch_sn) {
        this.batch_sn = batch_sn;
    }

    public Integer getOg_id() {
        return og_id;
    }

    public void setOg_id(Integer og_id) {
        this.og_id = og_id;
    }

    public Integer getSend_no() {
        return send_no;
    }

    public void setSend_no(Integer send_no) {
        this.send_no = send_no;
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

    public Integer getSku_num() {
        return sku_num;
    }

    public void setSku_num(Integer sku_num) {
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

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }

    public double getNeed_pay_amount() {
        return need_pay_amount;
    }

    public void setNeed_pay_amount(double need_pay_amount) {
        this.need_pay_amount = need_pay_amount;
    }

    public String getNeed_send_time() {
        return need_send_time;
    }

    public void setNeed_send_time(String need_send_time) {
        this.need_send_time = need_send_time;
    }

    public double getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(double pay_amount) {
        this.pay_amount = pay_amount;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Integer getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(Integer shipping_status) {
        this.shipping_status = shipping_status;
    }

    public Date getShipping_time() {
        return shipping_time;
    }

    public void setShipping_time(Date shipping_time) {
        this.shipping_time = shipping_time;
    }

    public String getShipping_person() {
        return shipping_person;
    }

    public void setShipping_person(String shipping_person) {
        this.shipping_person = shipping_person;
    }

    public String getShipping_phone() {
        return shipping_phone;
    }

    public void setShipping_phone(String shipping_phone) {
        this.shipping_phone = shipping_phone;
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
}