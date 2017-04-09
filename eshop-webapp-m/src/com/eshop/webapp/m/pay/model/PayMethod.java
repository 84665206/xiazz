package com.eshop.webapp.m.pay.model;

import java.util.List;

import com.eshop.webapp.m.base.BaseModel;


/**
 * 支付方式
 * @author yangmeng
 *
 */
public class PayMethod extends BaseModel {
	private static final long serialVersionUID = -6419256054638999307L;
	final public static int TYPE_NET_PAY = 1;// 网上支付
	final public static int TYPE_COD = 2;// 货到付款

	private Integer id; // 编号
	private String pay_name;// 支付名称
	private Boolean is_visit;// 是否可见
	private Integer pay_sort;// 顺序
	private String pay_icon; // 小图标
	private Integer parent_id;// 父级编号
	private Integer show_type;// 类型 1：支付平台  2网银  3：货到付款 4：银行转账 5支付方式
	private String handle_class;// 支付处理类

	private List<PayMethod> pay_sons;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPay_name() {
		return pay_name;
	}

	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}

	public Boolean getIs_visit() {
		return is_visit;
	}

	public void setIs_visit(Boolean is_visit) {
		this.is_visit = is_visit;
	}

	public Integer getPay_sort() {
		return pay_sort;
	}

	public void setPay_sort(Integer pay_sort) {
		this.pay_sort = pay_sort;
	}

	public String getPay_icon() {
		return pay_icon;
	}

	public void setPay_icon(String pay_icon) {
		this.pay_icon = pay_icon;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getShow_type() {
		return show_type;
	}

	public void setShow_type(Integer show_type) {
		this.show_type = show_type;
	}

	public List<PayMethod> getPay_sons() {
		return pay_sons;
	}

	public void setPay_sons(List<PayMethod> pay_sons) {
		this.pay_sons = pay_sons;
	}

	public String getHandle_class() {
		return handle_class;
	}

	public void setHandle_class(String handle_class) {
		this.handle_class = handle_class;
	}


	
}
