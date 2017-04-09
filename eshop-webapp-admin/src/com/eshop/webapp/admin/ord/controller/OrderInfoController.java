package com.eshop.webapp.admin.ord.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.admin.base.BaseController;
import com.eshop.webapp.admin.base.ResponseResult;
import com.eshop.webapp.admin.enums.ResponseResultEnum;
import com.eshop.webapp.admin.ord.model.OrderBatchSend;
import com.eshop.webapp.admin.ord.model.OrderGoods;
import com.eshop.webapp.admin.ord.model.OrderInfo;
import com.eshop.webapp.admin.ord.service.IOrderInfoService;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

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
@RequestMapping("/ord")
@Controller
public class OrderInfoController extends BaseController {

	@Autowired
	private IOrderInfoService orderInfoService;
	

	/**
	 * 进入订单信息信息界面
	 *
	 * @return
	 */
	@RequestMapping("orderInfo/page")
	public String orderInfoPage() {
		return "/ord/orderInfo";
	}

	/**
	 * 查询订单信息信息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("orderInfo/list")
	public PageResponse<OrderInfo> orderInfoList(@ModelAttribute PageRequest pageRequest, 
			@RequestParam(value="orderStatus", required=false)String orderStatus,
			@RequestParam(value="shippingLevel", required=false)String shippingLevel,
			@RequestParam(value="orderSn", required=false)String orderSn,
			@RequestParam(value="receiverName", required=false)String receiverName,
			@RequestParam(value="receiverMobile", required=false)String receiverMobile,
			@RequestParam(value="orderDateStart", required=false)String orderDateStart,
			@RequestParam(value="orderDateEnd", required=false)String orderDateEnd
			) {
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(orderStatus) && !"-1".equals(orderStatus)){
			condition.put("orderStatus", orderStatus);
		}
		if(StringUtils.isNotBlank(shippingLevel) && !"-1".equals(shippingLevel)){
			condition.put("shippingLevel", shippingLevel);
		}
		if(StringUtils.isNotBlank(orderSn)){
			condition.put("orderSn", orderSn);
		}
		if(StringUtils.isNotBlank(receiverName)){
			condition.put("receiverName", receiverName);
		}
		if(StringUtils.isNotBlank(receiverMobile)){
			condition.put("receiverMobile", receiverMobile);
		}
		if(StringUtils.isNotBlank(orderDateStart)){
			condition.put("orderDateStart", orderDateStart);
		}
		if(StringUtils.isNotBlank(orderDateEnd)){
			condition.put("orderDateEnd", orderDateEnd);
		}
		PageResponse<OrderInfo> pageResponse = this.orderInfoService.listOrderInfoPage(pageRequest, condition);
		
        return pageResponse;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("orderInfo/showSum")
	public ResponseResult searchOrderSum(
			@RequestParam(value="orderStatus", required=false)String orderStatus,
			@RequestParam(value="shippingLevel", required=false)String shippingLevel,
			@RequestParam(value="orderSn", required=false)String orderSn,
			@RequestParam(value="receiverName", required=false)String receiverName,
			@RequestParam(value="receiverMobile", required=false)String receiverMobile,
			@RequestParam(value="orderDateStart", required=false)String orderDateStart,
			@RequestParam(value="orderDateEnd", required=false)String orderDateEnd,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();

		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(orderStatus) && !"-1".equals(orderStatus)){
			condition.put("orderStatus", orderStatus);
		}
		if(StringUtils.isNotBlank(shippingLevel) && !"-1".equals(shippingLevel)){
			condition.put("shippingLevel", shippingLevel);
		}
		if(StringUtils.isNotBlank(orderSn)){
			condition.put("orderSn", orderSn);
		}
		if(StringUtils.isNotBlank(receiverName)){
			condition.put("receiverName", receiverName);
		}
		if(StringUtils.isNotBlank(receiverMobile)){
			condition.put("receiverMobile", receiverMobile);
		}
		if(StringUtils.isNotBlank(orderDateStart)){
			condition.put("orderDateStart", orderDateStart);
		}
		if(StringUtils.isNotBlank(orderDateEnd)){
			condition.put("orderDateEnd", orderDateEnd);
		}
		//查询汇总
		OrderInfo orderInfoSum = this.orderInfoService.searchOrderSumData(condition);

		responseResult.setCode(ResponseResultEnum.SUCCESS.code);
		responseResult.setMsg(
			"查询汇总数据 -- 总数量：" + (orderInfoSum != null && orderInfoSum.getGoodsSum() != null ? orderInfoSum.getGoodsSum() : 0)
				+ " kg；总金额：" + (orderInfoSum != null && orderInfoSum.getGoodsAmount() != null ? orderInfoSum.getGoodsAmount() : 0) + " 元。");
		
		return responseResult;
	}
	

	/**
	 * 进入订单详情界面
	 *
	 * @return
	 */
	@RequestMapping("orderInfo/detailpage")
	public String orderInfoDescPage(
			@RequestParam(value="orderId", required=false)Integer orderId,
			Model model) {
		if(orderId==null) return null;

		// 查询-订单头
		OrderInfo orderInfo = orderInfoService.getOrderInfoById(orderId);
		if(orderInfo == null) return null;
		
		//传值给 view
		model.addAttribute("orderInfo", orderInfo);
		
		// 查询-订单明细
		List<OrderGoods> orderGoodsList = orderInfoService.getOrderGoodsList(orderId);
		//传值给 view
		model.addAttribute("orderGoodsList", orderGoodsList);

		// 查询出订单配送
		List<OrderBatchSend> orderBatchSendList = orderInfoService.getOrderBatchSendList(orderId);
		//传值给 view
		model.addAttribute("orderBatchSendList", orderBatchSendList);
		
		return "/ord/orderInfoDesc";
	}


	/**
	 * 进入订单 审核
	 *
	 * @return
	 */
	@RequestMapping("orderInfo/approve")
	public String orderInfoApprove(
			@RequestParam(value="orderId", required=false)Integer orderId,
			Model model) {
		if(orderId==null) return null;

		//执行审核操作
		orderInfoService.approveOrderInfoById(orderId, this.getCurrentUser());
		
		return orderInfoDescPage(orderId, model);
	}

	/**
	 * 进入订单 取消
	 *
	 * @return
	 */
	@RequestMapping("orderInfo/cancel")
	public String orderInfoCancel(
			@RequestParam(value="orderId", required=false)Integer orderId,
			Model model) {
		if(orderId==null) return null;
		
		//执行取消操作
		orderInfoService.cancelOrderInfoById(orderId, this.getCurrentUser());
		
		return orderInfoDescPage(orderId, model);
	}

	/**
	 * 进入订单 完成
	 *
	 * @return
	 */
	@RequestMapping("orderInfo/complete")
	public String orderInfoComplete(
			@RequestParam(value="orderId", required=false)Integer orderId,
			Model model) {
		if(orderId==null) return null;
		
		//执行完成操作
		orderInfoService.completeOrderInfoById(orderId, this.getCurrentUser());
		
		return orderInfoDescPage(orderId, model);
	}


	/**
	 * 进入订单配送管理界面
	 *
	 * @return
	 */
	@RequestMapping("orderSend/page")
	public String orderSendPage() {
		return "/ord/orderSend";
	}

	/**
	 * 查询订单配送管理信息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("orderSend/list")
	public PageResponse<OrderBatchSend> orderSendList(@ModelAttribute PageRequest pageRequest, 
			@RequestParam(value="shippingStatus", required=false)String shippingStatus,
			@RequestParam(value="batchSn", required=false)String batchSn,
			@RequestParam(value="orderSn", required=false)String orderSn,
			@RequestParam(value="receiverName", required=false)String receiverName,
			@RequestParam(value="receiverMobile", required=false)String receiverMobile,
			@RequestParam(value="needSendStart", required=false)String needSendStart,
			@RequestParam(value="needSendEnd", required=false)String needSendEnd,
			@RequestParam(value="orderDateStart", required=false)String orderDateStart,
			@RequestParam(value="orderDateEnd", required=false)String orderDateEnd
			) {
		Map<String, Object> condition = new HashMap<String, Object>();
		
		if(StringUtils.isNotBlank(shippingStatus) && !"-1".equals(shippingStatus)){
			condition.put("shippingStatus", shippingStatus);
		}
		if(StringUtils.isNotBlank(batchSn)){
			condition.put("batchSn", batchSn);
		}
		if(StringUtils.isNotBlank(orderSn)){
			condition.put("orderSn", orderSn);
		}
		if(StringUtils.isNotBlank(receiverName)){
			condition.put("receiverName", receiverName);
		}
		if(StringUtils.isNotBlank(receiverMobile)){
			condition.put("receiverMobile", receiverMobile);
		}
		if(StringUtils.isNotBlank(needSendStart)){
			condition.put("needSendStart", needSendStart);
		}
		if(StringUtils.isNotBlank(needSendEnd)){
			condition.put("needSendEnd", needSendEnd);
		}
		if(StringUtils.isNotBlank(orderDateStart)){
			condition.put("orderDateStart", orderDateStart);
		}
		if(StringUtils.isNotBlank(orderDateEnd)){
			condition.put("orderDateEnd", orderDateEnd);
		}
		
		PageResponse<OrderBatchSend> pageResponse = this.orderInfoService.listOrderBatchSendPage(pageRequest, condition);
		
        return pageResponse;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("orderSend/showSum")
	public ResponseResult searchOrderSendSum(
			@RequestParam(value="shippingStatus", required=false)String shippingStatus,
			@RequestParam(value="batchSn", required=false)String batchSn,
			@RequestParam(value="orderSn", required=false)String orderSn,
			@RequestParam(value="receiverName", required=false)String receiverName,
			@RequestParam(value="receiverMobile", required=false)String receiverMobile,
			@RequestParam(value="needSendStart", required=false)String needSendStart,
			@RequestParam(value="needSendEnd", required=false)String needSendEnd,
			@RequestParam(value="orderDateStart", required=false)String orderDateStart,
			@RequestParam(value="orderDateEnd", required=false)String orderDateEnd,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();

		Map<String, Object> condition = new HashMap<String, Object>();

		if(StringUtils.isNotBlank(shippingStatus) && !"-1".equals(shippingStatus)){
			condition.put("shippingStatus", shippingStatus);
		}
		if(StringUtils.isNotBlank(batchSn)){
			condition.put("batchSn", batchSn);
		}
		if(StringUtils.isNotBlank(orderSn)){
			condition.put("orderSn", orderSn);
		}
		if(StringUtils.isNotBlank(receiverName)){
			condition.put("receiverName", receiverName);
		}
		if(StringUtils.isNotBlank(receiverMobile)){
			condition.put("receiverMobile", receiverMobile);
		}
		if(StringUtils.isNotBlank(needSendStart)){
			condition.put("needSendStart", needSendStart);
		}
		if(StringUtils.isNotBlank(needSendEnd)){
			condition.put("needSendEnd", needSendEnd);
		}
		if(StringUtils.isNotBlank(orderDateStart)){
			condition.put("orderDateStart", orderDateStart);
		}
		if(StringUtils.isNotBlank(orderDateEnd)){
			condition.put("orderDateEnd", orderDateEnd);
		}
		
		//查询汇总
		OrderBatchSend orderSendSum = this.orderInfoService.searchOrderSendSumData(condition);
		
		responseResult.setCode(ResponseResultEnum.SUCCESS.code);
		responseResult.setMsg(
			"查询汇总数据 -- 总数量：" + (orderSendSum != null && orderSendSum.getSkuNum() != null ? orderSendSum.getSkuNum() : 0)
				+ " kg；总金额：" + (orderSendSum != null && orderSendSum.getBatchAmount() != null ? orderSendSum.getBatchAmount() : 0) + " 元。");

		return responseResult;
	}
	
	/**
	 * 进入订单配送管理界面
	 *
	 * @return
	 */
	@RequestMapping("orderSend/detailpage")
	public String orderSendDetailPage(
			@RequestParam(value="batchId", required=false)Integer batchId,
			Model model) {
		if(batchId==null) return null;
		
		OrderBatchSend orderBatchSend = this.orderInfoService.getOrderBatchSendMoreById(batchId);
		//传值给 view
		model.addAttribute("orderBatchSend", orderBatchSend);
		
		return "/ord/orderSendDesc";
	}


	/**
	 * 进入订单配送管理界面
	 *
	 * @return
	 */
	@RequestMapping("orderSend/detailprint")
	public String orderSendDetailPrint(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(value="batchId", required=false)Integer batchId,
			Model model) {
		if(batchId==null) return null;
		
		OrderBatchSend orderBatchSend = this.orderInfoService.getOrderBatchSendMoreById(batchId);
		
		//生产Excel文档
		this.exportFromXlsTemplate(request, response, "exportOrderSend.xls", orderBatchSend, "配送单打印");
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("orderSend/saveSendPerson")
	public ResponseResult saveSendPerson(
			@RequestParam(value="batchId", required=false)Integer batchId,
			@RequestParam(value="shippingPerson", required=false)String shippingPerson,
			@RequestParam(value="shippingPhone", required=false)String shippingPhone,
			@RequestParam(value="shippingWay", required=false)String shippingWay,
			@RequestParam(value="shippingNo", required=false)String shippingNo
			) {
		if(batchId==null) return null;
		
		ResponseResult responseResult =new ResponseResult();
		OrderBatchSend obj = new OrderBatchSend();
		obj.setId(batchId);
		obj.setShippingStatus(2);//已配送
		obj.setShippingTime(new Date());
		obj.setFromShippingStatus(1);;//待配送
		
		if(StringUtils.isNotBlank(shippingPerson)){
			obj.setShippingPerson(shippingPerson);
		}else{
			obj.setShippingPerson("未填入");
		}
		if(StringUtils.isNotBlank(shippingPhone)){
			obj.setShippingPhone(shippingPhone);
		}else{
			obj.setShippingPhone("未填入");
		}
		
		String memo = "";
		if(StringUtils.isNotBlank(shippingWay)){
			memo += "【配送方式：" + shippingWay + "】";
		}
		if(StringUtils.isNotBlank(shippingNo)){
			memo += "【配送单号：" + shippingNo + "】";
		}
		obj.setMemo(memo);
		
		//保持数据
		try {
			Integer itotal = this.orderInfoService.updateOrderSendById(obj, getCurrentUser());
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("数据保存失败");
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("数据保存成功");
			}
		}
		catch (Exception e) {
			logger.error("保持数据-发生系统异常", e);
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg(e.getMessage());
			return responseResult;
		}
		
		return responseResult;
	}
	

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("orderSend/saveSendResult")
	public ResponseResult saveSendResult(
			@RequestParam(value="batchId", required=false)Integer batchId,
			@RequestParam(value="sendResult", required=false)Integer sendResult,
			@RequestParam(value="payAmount", required=false)String payAmount
			) {
		if(batchId==null) return null;
		
		ResponseResult responseResult =new ResponseResult();
		OrderBatchSend obj = new OrderBatchSend();
		obj.setId(batchId);
		obj.setShippingTime(new Date());
		obj.setFromShippingStatus(2);//已配送
		
		if(sendResult!=null && sendResult==1){
			obj.setShippingStatus(3);//已签收
		}else if(sendResult!=null && sendResult==0){
			obj.setShippingStatus(9);//顾客拒收
		}else{
			return null;
		}

		if(StringUtils.isNotBlank(payAmount)){
			obj.setPayStatus(1);
			obj.setPayAmount(new BigDecimal(payAmount));
			obj.setNeedPayAmount(new BigDecimal("0"));
		}
		
		//保存数据
		try {
			Integer itotal = this.orderInfoService.saveSendResult(obj, getCurrentUser());
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("数据保存失败");
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("数据保存成功");
			}
		}
		catch (Exception e) {
			logger.error("保持数据-发生系统异常", e);
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg(e.getMessage());
			return responseResult;
		}
		
		return responseResult;
	}
	
	
}
