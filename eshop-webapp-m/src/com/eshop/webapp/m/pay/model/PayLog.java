package com.eshop.webapp.m.pay.model;

import java.util.Date;

import com.eshop.webapp.m.base.BaseModel;

public class PayLog extends BaseModel{
	
	private static final long serialVersionUID = -2241720265457396812L;

	private Integer id;

    private Integer pay_handle_type;

    private String pay_body;

    private String pay_url;

    private String deal_code;

    private String pay_change;

    private Integer verify_result;

    private String pay_ip;

    private Integer customer_id;

    private Date insert_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPay_handle_type() {
        return pay_handle_type;
    }

    public void setPay_handle_type(Integer pay_handle_type) {
        this.pay_handle_type = pay_handle_type;
    }

    public String getPay_body() {
        return pay_body;
    }

    public void setPay_body(String pay_body) {
        this.pay_body = pay_body;
    }

    public String getPay_url() {
        return pay_url;
    }

    public void setPay_url(String pay_url) {
        this.pay_url = pay_url;
    }

    public String getDeal_code() {
        return deal_code;
    }

    public void setDeal_code(String deal_code) {
        this.deal_code = deal_code;
    }

    public String getPay_change() {
        return pay_change;
    }

    public void setPay_change(String pay_change) {
        this.pay_change = pay_change;
    }

    public Integer getVerify_result() {
        return verify_result;
    }

    public void setVerify_result(Integer verify_result) {
        this.verify_result = verify_result;
    }

    public String getPay_ip() {
        return pay_ip;
    }

    public void setPay_ip(String pay_ip) {
        this.pay_ip = pay_ip;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }
    
    public enum LogType {
		PAY_REQUEST(1), // 支付请求日志
		PAY_RETURN(2), // 支付前台回掉日志
		PAY_NOTIFY(3);// 支付后台回掉请求日志

		private final int value;

		private LogType(int i) {
			this.value = i;
		}

		public int getValue() {
			return this.value;
		}
	}
}