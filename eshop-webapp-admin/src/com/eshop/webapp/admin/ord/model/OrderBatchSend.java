package com.eshop.webapp.admin.ord.model;

import java.math.BigDecimal;
import java.util.Date;

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
 * @since 2017年2月10日
 * @version V1.0
 * @author luowen
 */
public class OrderBatchSend extends OrderInfo {
	private static final long serialVersionUID = 1;

	//private Integer id;
	private String batchSn;
	private Integer ogId;
	private Integer sendNo;
	private Integer skuId;
	private String skuName;
	private Integer skuNum;
	private BigDecimal salePrice;
	private BigDecimal sharePrice;
//	private Integer payStatus;
//	private BigDecimal needPayAmount;
	private Date needSendTime;
//	private BigDecimal payAmount;
//	private Date payTime;
//	private Integer shippingStatus;
	private Date shippingTime;
	private String shippingPerson;
	private String shippingPhone;
	
	private Integer orderId;		//订单表id
	private Integer batchId;		//配送表id
	private BigDecimal batchAmount;//配送金额
	
	private String goodsVarietiesName;
	private String goodsSpecName;
	
	private Integer fromShippingStatus;	//前一个配送状态值
	

	//格式化 - 下单日期
	public String getNeedSendTimeDesc(){
		return needSendTime==null ? "" : DateUtils.DateToString(needSendTime, DateUtils.YYYY_MM_DD);
	}

	//格式化 - 下单日期
	public String getShippingTimeDesc(){
		return shippingTime==null ? "" : DateUtils.DateToString(shippingTime, DateUtils.YYYY_MM_DD_HH_MM_SS);
	}

	//格式化 - 配送状态
	public String getShippingStatusDesc() {
		if(this.getShippingStatus()==null) return "";
		
		if(this.getShippingStatus()==0){
          	return "初始";
		}else if(this.getShippingStatus()==1){
		  	return "待配送";
		}else if(this.getShippingStatus()==2){
		  	return "已配送";
		}else if(this.getShippingStatus()==3){
		  	return "已签收";
		}else if(this.getShippingStatus()==8){
		  	return "卖家作废";
		}else if(this.getShippingStatus()==9){
		  	return "客户拒收";
		}else{
		  	return "未知";
		}
	}

	//格式化 - 支付状态
	public String getPayStatusDesc() {
		if(this.getPayStatus()==null) return "";
		
		if(this.getPayStatus()==0){
          	return "未支付";
		}else if(this.getPayStatus()==1){
		  	return "已支付";
		}else{
		  	return "未知";
		}
	}

	
//	/**
//	 * @return the id
//	 */
//	public Integer getId() {
//		return id;
//	}
//	
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	/**
	 * @return the batchSn
	 */
	public String getBatchSn() {
		return batchSn;
	}
	
	/**
	 * @param batchSn the batchSn to set
	 */
	public void setBatchSn(String batchSn) {
		this.batchSn = batchSn;
	}
	
	/**
	 * @return the ogId
	 */
	public Integer getOgId() {
		return ogId;
	}
	
	/**
	 * @param ogId the ogId to set
	 */
	public void setOgId(Integer ogId) {
		this.ogId = ogId;
	}
	
	/**
	 * @return the sendNo
	 */
	public Integer getSendNo() {
		return sendNo;
	}
	
	/**
	 * @param sendNo the sendNo to set
	 */
	public void setSendNo(Integer sendNo) {
		this.sendNo = sendNo;
	}
	
	/**
	 * @return the skuId
	 */
	public Integer getSkuId() {
		return skuId;
	}
	
	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	
	/**
	 * @return the skuName
	 */
	public String getSkuName() {
		return skuName;
	}
	
	/**
	 * @param skuName the skuName to set
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	/**
	 * @return the skuNum
	 */
	public Integer getSkuNum() {
		return skuNum;
	}
	
	/**
	 * @param skuNum the skuNum to set
	 */
	public void setSkuNum(Integer skuNum) {
		this.skuNum = skuNum;
	}
	
	/**
	 * @return the salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	
	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	/**
	 * @return the sharePrice
	 */
	public BigDecimal getSharePrice() {
		return sharePrice;
	}
	
	/**
	 * @param sharePrice the sharePrice to set
	 */
	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}
	
//	/**
//	 * @return the payStatus
//	 */
//	public Integer getPayStatus() {
//		return payStatus;
//	}
//	
//	/**
//	 * @param payStatus the payStatus to set
//	 */
//	public void setPayStatus(Integer payStatus) {
//		this.payStatus = payStatus;
//	}
//	
//	/**
//	 * @return the needPayAmount
//	 */
//	public BigDecimal getNeedPayAmount() {
//		return needPayAmount;
//	}
//	
//	/**
//	 * @param needPayAmount the needPayAmount to set
//	 */
//	public void setNeedPayAmount(BigDecimal needPayAmount) {
//		this.needPayAmount = needPayAmount;
//	}
	
	/**
	 * @return the needSendTime
	 */
	public Date getNeedSendTime() {
		return needSendTime;
	}
	
	/**
	 * @param needSendTime the needSendTime to set
	 */
	public void setNeedSendTime(Date needSendTime) {
		this.needSendTime = needSendTime;
	}
	
//	/**
//	 * @return the payAmount
//	 */
//	public BigDecimal getPayAmount() {
//		return payAmount;
//	}
//	
//	/**
//	 * @param payAmount the payAmount to set
//	 */
//	public void setPayAmount(BigDecimal payAmount) {
//		this.payAmount = payAmount;
//	}
//	
//	/**
//	 * @return the payTime
//	 */
//	public Date getPayTime() {
//		return payTime;
//	}
//	
//	/**
//	 * @param payTime the payTime to set
//	 */
//	public void setPayTime(Date payTime) {
//		this.payTime = payTime;
//	}
//	
//	/**
//	 * @return the shippingStatus
//	 */
//	public Integer getShippingStatus() {
//		return shippingStatus;
//	}
//	
//	/**
//	 * @param shippingStatus the shippingStatus to set
//	 */
//	public void setShippingStatus(Integer shippingStatus) {
//		this.shippingStatus = shippingStatus;
//	}
	
	/**
	 * @return the shippingTime
	 */
	public Date getShippingTime() {
		return shippingTime;
	}
	
	/**
	 * @param shippingTime the shippingTime to set
	 */
	public void setShippingTime(Date shippingTime) {
		this.shippingTime = shippingTime;
	}

	
	/**
	 * @return the shippingPerson
	 */
	public String getShippingPerson() {
		return shippingPerson;
	}

	
	/**
	 * @param shippingPerson the shippingPerson to set
	 */
	public void setShippingPerson(String shippingPerson) {
		this.shippingPerson = shippingPerson;
	}

	
	/**
	 * @return the shippingPhone
	 */
	public String getShippingPhone() {
		return shippingPhone;
	}

	
	/**
	 * @param shippingPhone the shippingPhone to set
	 */
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	
	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	/**
	 * @return the batchid
	 */
	public Integer getBatchId() {
		return batchId;
	}

	
	/**
	 * @param batchid the batchid to set
	 */
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	
	/**
	 * @return the batchAmount
	 */
	public BigDecimal getBatchAmount() {
		return batchAmount;
	}

	
	/**
	 * @param batchAmount the batchAmount to set
	 */
	public void setBatchAmount(BigDecimal batchAmount) {
		this.batchAmount = batchAmount;
	}

	
	/**
	 * @return the goodsVarietiesName
	 */
	public String getGoodsVarietiesName() {
		return goodsVarietiesName;
	}

	
	/**
	 * @param goodsVarietiesName the goodsVarietiesName to set
	 */
	public void setGoodsVarietiesName(String goodsVarietiesName) {
		this.goodsVarietiesName = goodsVarietiesName;
	}

	
	/**
	 * @return the goodsSpecName
	 */
	public String getGoodsSpecName() {
		return goodsSpecName;
	}

	
	/**
	 * @param goodsSpecName the goodsSpecName to set
	 */
	public void setGoodsSpecName(String goodsSpecName) {
		this.goodsSpecName = goodsSpecName;
	}

	
	/**
	 * @return the fromShippingStatus
	 */
	public Integer getFromShippingStatus() {
		return fromShippingStatus;
	}

	
	/**
	 * @param fromShippingStatus the fromShippingStatus to set
	 */
	public void setFromShippingStatus(Integer fromShippingStatus) {
		this.fromShippingStatus = fromShippingStatus;
	}
	
}
