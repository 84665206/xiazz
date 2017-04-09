package com.eshop.webapp.admin.ord.model;

import java.math.BigDecimal;
import java.util.Date;

import com.eshop.webapp.admin.base.BaseModel;
import com.eshop.webapp.admin.util.DateUtils;

/**
 * <p>
 * TODO类概述
 * </p>
 * <p>
 * 提供如下功能：<br>
 * <1>.TODO<br>
 * <2>.TODO<br>
 * </p>
 * @since 2017年1月26日
 * @version V1.0
 * @author luowen
 */
public class OrderInfo extends BaseModel {
	private static final long serialVersionUID = 1;

	private Integer id;
	private String orderSn;
	private Integer orderStatus;
	private Date addTime;
	private String orderType;
	private String shippingLevel;
	private String shippingWay;
	private Integer shippingStatus;
	
	private Integer customerId;
	private String customerName;
	private String recruitCode;
	private String shopName;
	private String shopAddress;
	private String customerMemo;
	
	private Integer goodsSum;
	private BigDecimal goodsAmount;
	private BigDecimal orderShippingFee;
	private BigDecimal orderDiscount;
	private BigDecimal orderAmount;
	private String orderMemo;
	
	private String payWay;
	private Integer payStatus;
	private Date payTime;
	private BigDecimal payAmount;
	private BigDecimal needPayAmount;
	
	private String receiverName;
	private String receiverMobile;
	private String receiverPhone;
	private String receiverState;
	private String receiverProvince;
	private String receiverCity;
	private String receiverDistrict;
	private String receiverStreet;
	private String receiverAddr;
	
	//格式化 - 下单日期
	public String getAddTimeDesc(){
		return addTime==null ? "" : DateUtils.DateToString(addTime, DateUtils.YYYY_MM_DD_HH_MM_SS);
	}
	//格式化 - 订单状态
	public String getOrderStatusDesc() {
		if(shippingStatus==null) return "";
		
		if(orderStatus==10){
          	return "初始";
		}else if(orderStatus==20){
		  	return "已审核";
		}else if(orderStatus==30){
		  	return "已完成";
		}else if(orderStatus==-99){
		  	return "取消";
		}else{
		  	return "未知";
		}
	}
	//格式化 - 配送状态
	public String getShippingStatusDesc() {
		if(shippingStatus==null) return "";
		
		if(shippingStatus==0){
          	return "未配送";
		}else if(shippingStatus==1){
		  	return "部分已配送";
		}else if(shippingStatus==2){
		  	return "全部已配送";
		}else{
		  	return "未知";
		}
	}

	//格式化 - 支付状态
	public String getPayStatusDesc() {
		if(payStatus==null) return "";
		
		if(payStatus==0){
          	return "未支付";
		}else if(payStatus==1){
		  	return "部分已支付";
		}else if(payStatus==2){
		  	return "全部已支付";
		}else{
		  	return "未知";
		}
	}

	//格式化 - 配送级别
	public String getShippingLevelDesc() {
		if(shippingLevel==null) return "";
		
		if("4001".equals(shippingLevel)){
          	return "次日配送";
		}else if("4002".equals(shippingLevel)){
		  	return "当天配送";
		}else{
		  	return "未知";
		}
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the orderSn
	 */
	public String getOrderSn() {
		return orderSn;
	}
	
	/**
	 * @param orderSn the orderSn to set
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	/**
	 * @return the orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}
	
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * @return the shippingLevel
	 */
	public String getShippingLevel() {
		return shippingLevel;
	}
	
	/**
	 * @param shippingLevel the shippingLevel to set
	 */
	public void setShippingLevel(String shippingLevel) {
		this.shippingLevel = shippingLevel;
	}
	
	/**
	 * @return the shippingWay
	 */
	public String getShippingWay() {
		return shippingWay;
	}
	
	/**
	 * @param shippingWay the shippingWay to set
	 */
	public void setShippingWay(String shippingWay) {
		this.shippingWay = shippingWay;
	}
	
	/**
	 * @return the shippingStatus
	 */
	public Integer getShippingStatus() {
		return shippingStatus;
	}
	
	/**
	 * @param shippingStatus the shippingStatus to set
	 */
	public void setShippingStatus(Integer shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	
	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * @return the customerCode
	 */
	public String getRecruitCode() {
		return recruitCode;
	}
	
	/**
	 * @param customerCode the customerCode to set
	 */
	public void setRecruitCode(String recruitCode) {
		this.recruitCode = recruitCode;
	}
	
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}
	
	/**
	 * @param shopName the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	/**
	 * @return the shopAddress
	 */
	public String getShopAddress() {
		return shopAddress;
	}
	
	/**
	 * @param shopAddress the shopAddress to set
	 */
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	
	/**
	 * @return the customerMemo
	 */
	public String getCustomerMemo() {
		return customerMemo;
	}
	
	/**
	 * @param customerMemo the customerMemo to set
	 */
	public void setCustomerMemo(String customerMemo) {
		this.customerMemo = customerMemo;
	}
	
	/**
	 * @return the goodsSum
	 */
	public Integer getGoodsSum() {
		return goodsSum;
	}
	
	/**
	 * @param goodsSum the goodsSum to set
	 */
	public void setGoodsSum(Integer goodsSum) {
		this.goodsSum = goodsSum;
	}
	
	/**
	 * @return the goodsAmount
	 */
	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}
	
	/**
	 * @param goodsAmount the goodsAmount to set
	 */
	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	
	/**
	 * @return the orderShippingFee
	 */
	public BigDecimal getOrderShippingFee() {
		return orderShippingFee;
	}
	
	/**
	 * @param orderShippingFee the orderShippingFee to set
	 */
	public void setOrderShippingFee(BigDecimal orderShippingFee) {
		this.orderShippingFee = orderShippingFee;
	}
	
	/**
	 * @return the orderDiscount
	 */
	public BigDecimal getOrderDiscount() {
		return orderDiscount;
	}
	
	/**
	 * @param orderDiscount the orderDiscount to set
	 */
	public void setOrderDiscount(BigDecimal orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	
	/**
	 * @return the orderAmount
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	
	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	/**
	 * @return the orderMemo
	 */
	public String getOrderMemo() {
		return orderMemo;
	}
	
	/**
	 * @param orderMemo the orderMemo to set
	 */
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	
	/**
	 * @return the payStatus
	 */
	public Integer getPayStatus() {
		return payStatus;
	}
	
	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	/**
	 * @return the payTime
	 */
	public Date getPayTime() {
		return payTime;
	}
	
	/**
	 * @param payTime the payTime to set
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	/**
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}
	
	/**
	 * @param receiverName the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	/**
	 * @return the receiverMobile
	 */
	public String getReceiverMobile() {
		return receiverMobile;
	}
	
	/**
	 * @param receiverMobile the receiverMobile to set
	 */
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	
	/**
	 * @return the receiverPhone
	 */
	public String getReceiverPhone() {
		return receiverPhone;
	}
	
	/**
	 * @param receiverPhone the receiverPhone to set
	 */
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	
	/**
	 * @return the receiverState
	 */
	public String getReceiverState() {
		return receiverState;
	}
	
	/**
	 * @param receiverState the receiverState to set
	 */
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	
	/**
	 * @return the receiverProvince
	 */
	public String getReceiverProvince() {
		return receiverProvince;
	}
	
	/**
	 * @param receiverProvince the receiverProvince to set
	 */
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}
	
	/**
	 * @return the receiverCity
	 */
	public String getReceiverCity() {
		return receiverCity;
	}
	
	/**
	 * @param receiverCity the receiverCity to set
	 */
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	
	/**
	 * @return the receiverDistrict
	 */
	public String getReceiverDistrict() {
		return receiverDistrict;
	}
	
	/**
	 * @param receiverDistrict the receiverDistrict to set
	 */
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	
	/**
	 * @return the receiverStreet
	 */
	public String getReceiverStreet() {
		return receiverStreet;
	}
	
	/**
	 * @param receiverStreet the receiverStreet to set
	 */
	public void setReceiverStreet(String receiverStreet) {
		this.receiverStreet = receiverStreet;
	}
	
	/**
	 * @return the receiverAddr
	 */
	public String getReceiverAddr() {
		return receiverAddr;
	}
	
	/**
	 * @param receiverAddr the receiverAddr to set
	 */
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}

	
	/**
	 * @return the payWay
	 */
	public String getPayWay() {
		return payWay;
	}

	
	/**
	 * @param payWay the payWay to set
	 */
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	
	/**
	 * @return the payAmount
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	
	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	
	/**
	 * @return the needPayAmount
	 */
	public BigDecimal getNeedPayAmount() {
		return needPayAmount;
	}

	
	/**
	 * @param needPayAmount the needPayAmount to set
	 */
	public void setNeedPayAmount(BigDecimal needPayAmount) {
		this.needPayAmount = needPayAmount;
	}
	
}
