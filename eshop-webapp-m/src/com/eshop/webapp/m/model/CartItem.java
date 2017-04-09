package com.eshop.webapp.m.model;

import com.eshop.webapp.m.base.BaseModel;
import com.eshop.webapp.m.goods.model.GoodsSku;

/** 
 * 购物车明细
 * @author yangmeng
 * @version 创建时间：2017年2月8日 下午3:07:40 
 * 
 */
public class CartItem extends BaseModel{

	private static final long serialVersionUID = 7667078138844388156L;
	
	public void fillGoodsInfoToItem(GoodsSku sku){
		if(sku!=null&&sku.getGoods_name()!=null){
			this.setSku_id(sku.getId());
			this.setSku_code(sku.getSku_code());
			this.setGoods_code(sku.getGoods_code());
			this.setGoods_name(sku.getGoods_name());
			this.setGoods_varieties(sku.getGoods_varieties());
			this.setGoods_varieties_name(sku.getGoods_varieties_name());
			this.setGoods_spec(sku.getGoods_spec());
			this.setGoods_spec_name(sku.getGoods_spec_name());
			this.setGoods_origin(sku.getGoods_origin());
			this.setGoods_origin_name(sku.getGoods_origin_name());
			this.setGoods_unit(sku.getGoods_unit());
			this.setOther_explain(sku.getOther_explain());
			this.setGoods_shipping(sku.getGoods_shipping());
			this.setGoods_shipping_name(sku.getGoods_shipping_name());
			this.setStock_num(sku.getStock_num());
			this.setSale_price(sku.getSale_price());
			this.setLabel_price(sku.getLabel_price());
			this.setCost_price(sku.getCost_price());
			this.setIs_hide(sku.getIs_hide());
			this.setIs_offline(sku.getIs_offline());
			this.setBuy_price(sku.getSale_price());;
			this.setMain_image_url(sku.getMain_image_url());
		}
	}
	
	public String message;
	
	private Integer sku_id;

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
    
    private Integer buy_num;

    private Integer stock_num;

    private Double sale_price;
    
    private Double buy_price;

    private Double label_price;

    private Double cost_price;

    private Integer is_offline;

    private Integer is_hide;
    
    private String main_image_url;

	public Integer getSku_id() {
		return sku_id;
	}

	public void setSku_id(Integer sku_id) {
		this.sku_id = sku_id;
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

	public String getGoods_varieties_name() {
		return goods_varieties_name;
	}

	public void setGoods_varieties_name(String goods_varieties_name) {
		this.goods_varieties_name = goods_varieties_name;
	}

	public String getGoods_spec() {
		return goods_spec;
	}

	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}

	public String getGoods_spec_name() {
		return goods_spec_name;
	}

	public void setGoods_spec_name(String goods_spec_name) {
		this.goods_spec_name = goods_spec_name;
	}

	public String getGoods_origin() {
		return goods_origin;
	}

	public void setGoods_origin(String goods_origin) {
		this.goods_origin = goods_origin;
	}

	public String getGoods_origin_name() {
		return goods_origin_name;
	}

	public void setGoods_origin_name(String goods_origin_name) {
		this.goods_origin_name = goods_origin_name;
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

	public String getGoods_shipping() {
		return goods_shipping;
	}

	public void setGoods_shipping(String goods_shipping) {
		this.goods_shipping = goods_shipping;
	}

	public String getGoods_shipping_name() {
		return goods_shipping_name;
	}

	public void setGoods_shipping_name(String goods_shipping_name) {
		this.goods_shipping_name = goods_shipping_name;
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
	
	/**
	 * 产品编码可以唯一定位一个item
	 * 
	 * @return 产品辨识码 如果组成PK的参数某个为空，则返回null
	 */
	public String getPK() {
		if (sku_id != null && getSale_price() != null) {
			return this.sku_id.toString();
		} else {
			return null;
		}
	}

	public Integer getBuy_num() {
		return buy_num;
	}

	public void setBuy_num(Integer buy_num) {
		this.buy_num = buy_num;
	}

	public Double getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(Double buy_price) {
		this.buy_price = buy_price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMain_image_url() {
		return main_image_url;
	}

	public void setMain_image_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}
}
