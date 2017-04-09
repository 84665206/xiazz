package com.eshop.webapp.m.order.model;

import java.util.Date;
import java.util.List;

import com.eshop.webapp.m.base.BaseModel;
import com.eshop.webapp.m.customer.model.CustomerDelivery;
import com.eshop.webapp.m.enums.PayStatusEnum;
import com.eshop.webapp.m.model.SessionCustomer;
import com.eshop.webapp.m.pay.model.PayMethod;

public class OrderInfo extends BaseModel{
	
	private static final long serialVersionUID = 3835399002671999412L;
	
	public static final double DING_RATE=0.0;

	private Integer id;

    private String order_sn;

    private Integer order_status;

    private Date add_time;

    private Integer order_type;

    private String shipping_level;
    
    private String shipping_level_name;

    private String shipping_way;
    
    private String shipping_way_name;

    private Integer shipping_status;

    private Integer customer_id;

    private String customer_name;

    private String recruit_code;

    private String shop_name;

    private String shop_address;

    private String customer_memo;

    private Integer goods_sum;

    private double goods_amount;

    private double order_shipping_fee;

    private double order_discount;

    private double order_amount;
    
    private double ding_amount;

    private String order_memo;

    private Integer pay_way;

    private Integer pay_status;

    private Date pay_time;

    private double pay_amount;

    private double need_pay_amount;

    private String receiver_name;

    private String receiver_mobile;

    private String receiver_phone;

    private String receiver_state;

    private String receiver_province;

    private String receiver_city;

    private String receiver_district;

    private String receiver_street;

    private String receiver_addr;

    private String memo;

    private Date update_time;

    private String update_user;

    private Integer is_delete;
    
    private String order_ip;
    
    private Double realAllGoodsAmount;
    
    
    private List<OrderGoods> order_goodsList;
    
    private List<CustomerDelivery> customer_deliveries;
    
    private CustomerDelivery customer_delivery;
    
    private PayMethod pay_method;
    
    private List<PayMethod> pay_methods;
    
    private SessionCustomer sessionCustomer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public Integer getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }

    public String getShipping_level() {
        return shipping_level;
    }

    public void setShipping_level(String shipping_level) {
        this.shipping_level = shipping_level;
    }

    public String getShipping_way() {
        return shipping_way;
    }

    public void setShipping_way(String shipping_way) {
        this.shipping_way = shipping_way;
    }

    public Integer getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(Integer shipping_status) {
        this.shipping_status = shipping_status;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getRecruit_code() {
        return recruit_code;
    }

    public void setRecruit_code(String recruit_code) {
        this.recruit_code = recruit_code;
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

    public String getCustomer_memo() {
        return customer_memo;
    }

    public void setCustomer_memo(String customer_memo) {
        this.customer_memo = customer_memo;
    }

    public Integer getGoods_sum() {
        return goods_sum;
    }

    public void setGoods_sum(Integer goods_sum) {
        this.goods_sum = goods_sum;
    }

    public double getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(double goods_amount) {
        this.goods_amount = goods_amount;
    }

    public double getOrder_shipping_fee() {
        return order_shipping_fee;
    }

    public void setOrder_shipping_fee(double order_shipping_fee) {
        this.order_shipping_fee = order_shipping_fee;
    }

    public double getOrder_discount() {
        return order_discount;
    }

    public void setOrder_discount(double order_discount) {
        this.order_discount = order_discount;
    }

    public double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(double order_amount) {
        this.order_amount = order_amount;
    }

    public String getOrder_memo() {
        return order_memo;
    }

    public void setOrder_memo(String order_memo) {
        this.order_memo = order_memo;
    }

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public double getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(double pay_amount) {
        this.pay_amount = pay_amount;
    }

    public double getNeed_pay_amount() {
        return need_pay_amount;
    }

    public void setNeed_pay_amount(double need_pay_amount) {
        this.need_pay_amount = need_pay_amount;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_state() {
        return receiver_state;
    }

    public void setReceiver_state(String receiver_state) {
        this.receiver_state = receiver_state;
    }

    public String getReceiver_province() {
        return receiver_province;
    }

    public void setReceiver_province(String receiver_province) {
        this.receiver_province = receiver_province;
    }

    public String getReceiver_city() {
        return receiver_city;
    }

    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }

    public String getReceiver_district() {
        return receiver_district;
    }

    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district;
    }

    public String getReceiver_street() {
        return receiver_street;
    }

    public void setReceiver_street(String receiver_street) {
        this.receiver_street = receiver_street;
    }

    public String getReceiver_addr() {
        return receiver_addr;
    }

    public void setReceiver_addr(String receiver_addr) {
        this.receiver_addr = receiver_addr;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

	public List<OrderGoods> getOrder_goodsList() {
		return order_goodsList;
	}

	public void setOrder_goodsList(List<OrderGoods> order_goodsList) {
		this.order_goodsList = order_goodsList;
	}

	public String getOrder_ip() {
		return order_ip;
	}

	public void setOrder_ip(String order_ip) {
		this.order_ip = order_ip;
	}

	public List<CustomerDelivery> getCustomer_deliveries() {
		return customer_deliveries;
	}

	public void setCustomer_deliveries(List<CustomerDelivery> customer_deliveries) {
		this.customer_deliveries = customer_deliveries;
	}

	public CustomerDelivery getCustomer_delivery() {
		return customer_delivery;
	}

	public void setCustomer_delivery(CustomerDelivery customer_delivery) {
		this.customer_delivery = customer_delivery;
	}

	public PayMethod getPay_method() {
		return pay_method;
	}

	public void setPay_method(PayMethod pay_method) {
		this.pay_method = pay_method;
	}

	public List<PayMethod> getPay_methods() {
		return pay_methods;
	}

	public void setPay_methods(List<PayMethod> pay_methods) {
		this.pay_methods = pay_methods;
	}

	public Double getRealAllGoodsAmount() {
		return realAllGoodsAmount;
	}

	public void setRealAllGoodsAmount(Double realAllGoodsAmount) {
		this.realAllGoodsAmount = realAllGoodsAmount;
	}

	public SessionCustomer getSessionCustomer() {
		return sessionCustomer;
	}

	public void setSessionCustomer(SessionCustomer sessionCustomer) {
		this.sessionCustomer = sessionCustomer;
	}

	public Integer getPay_way() {
		return pay_way;
	}

	public void setPay_way(Integer pay_way) {
		this.pay_way = pay_way;
	}

	public double getDing_amount() {
		return ding_amount;
	}

	public void setDing_amount(double ding_amount) {
		this.ding_amount = ding_amount;
	}

	public String getShipping_level_name() {
		return shipping_level_name;
	}

	public void setShipping_level_name(String shipping_level_name) {
		this.shipping_level_name = shipping_level_name;
	}

	public String getShipping_way_name() {
		return shipping_way_name;
	}

	public void setShipping_way_name(String shipping_way_name) {
		this.shipping_way_name = shipping_way_name;
	}
	
	public String getPay_status_name() {
		String pay_status_name="";
		if(pay_status!=null){
			PayStatusEnum payStatusEnum[]= PayStatusEnum.values();
			for (PayStatusEnum p : payStatusEnum) {
				if(p.status==pay_status){
					pay_status_name=p.name;
					break;
				}
			}
		}
		return pay_status_name;
	}

}