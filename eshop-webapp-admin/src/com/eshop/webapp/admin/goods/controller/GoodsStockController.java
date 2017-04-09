package com.eshop.webapp.admin.goods.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.eshop.webapp.admin.goods.model.GoodsStock;
import com.eshop.webapp.admin.goods.service.IGoodsStockService;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;
import com.eshop.webapp.admin.util.XStreamWrapper;

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
@RequestMapping("/goods")
@Controller
public class GoodsStockController extends BaseController {

	@Autowired
	private IGoodsStockService goodsStockService;
	

	/**
	 * 进入商品SKU信息界面
	 *
	 * @return
	 */
	@RequestMapping("goodsStock/page")
	public String goodsStockPage() {
		return "/goods/goodsStockInfo";
	}

	/**
	 * 查询商品SKU信息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("goodsStock/list")
	public PageResponse<GoodsStock> goodsStockList(@ModelAttribute PageRequest pageRequest, 
			@RequestParam(value="skuCode", required=false)String skuCode,
			@RequestParam(value="goodsName", required=false)String goodsName
			) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("isValid", 1);//只需要查询出有效的sku
		if(StringUtils.isNotBlank(skuCode)){
			condition.put("skuCode", skuCode);
		}
		if(StringUtils.isNotBlank(goodsName)){
			condition.put("goodsName", goodsName);
		}
		PageResponse<GoodsStock> pageResponse = this.goodsStockService.listGoodsStockPage(pageRequest, condition);
		
        return pageResponse;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("goodsStock/create")
	public ResponseResult goodsStockCreate(
			@RequestParam(value="skuCode", required=false)String skuCode,
			@RequestParam(value="goodsName", required=false)String goodsName,
			@RequestParam(value="goodsVarieties", required=false)String goodsVarieties,
			@RequestParam(value="goodsSpec", required=false)String goodsSpec,
			@RequestParam(value="goodsOrigin", required=false)String goodsOrigin,
			@RequestParam(value="goodsUnit", required=false)String goodsUnit,
			@RequestParam(value="otherExplain", required=false)String otherExplain,
			@RequestParam(value="isValid", required=false)Integer isValid,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();
		GoodsStock goodsStock = new GoodsStock();
		goodsStock.setIsDelete(0);
		
		if(StringUtils.isNotBlank(skuCode)){
			goodsStock.setSkuCode(skuCode);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("SKU编码不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(goodsName)){
			goodsStock.setGoodsName(goodsName);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("商品名称不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(goodsVarieties)){
			goodsStock.setGoodsVarieties(goodsVarieties);
		}
		if(StringUtils.isNotBlank(goodsSpec)){
			goodsStock.setGoodsSpec(goodsSpec);
		}
		if(StringUtils.isNotBlank(goodsOrigin)){
			goodsStock.setGoodsOrigin(goodsOrigin);
		}
		if(StringUtils.isNotBlank(goodsUnit)){
			goodsStock.setGoodsUnit(goodsUnit);
		}
		if(StringUtils.isNotBlank(otherExplain)){
			goodsStock.setOtherExplain(otherExplain);
		}
		if(isValid != null){
			goodsStock.setIsValid(isValid);
		}
		
		try {
			this.logger.debug(XStreamWrapper.toXML(goodsStock));
			Integer itotal=this.goodsStockService.insertGoodsStockInfo(goodsStock, getCurrentUser());
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("商品新增失败");
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("商品新增成功");
			}
		}
		catch (Exception e) {
			logger.error("商品新增-发生系统异常", e);
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg(e.getMessage());
			return responseResult;
		}
		
		return responseResult;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("goodsStock/edit")
	public ResponseResult goodsStockEdit(
			@RequestParam(value="id", required=false)Integer id,
			@RequestParam(value="stockNum", required=false)String stockNum,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();
		GoodsStock goodsStock = new GoodsStock();

		if(id != null){
			goodsStock.setId(id);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("SKUID不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(stockNum)){
			goodsStock.setStockNum(Integer.valueOf(stockNum));
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("库存不能为空");
			return responseResult;
		}
		
		try {
			this.logger.debug(XStreamWrapper.toXML(goodsStock));
			Integer itotal=this.goodsStockService.updateGoodsStockById(goodsStock, getCurrentUser());
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("库存修改失败");
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("库存修改成功");
			}
		}
		catch (Exception e) {
			logger.error("库存修改-发生系统异常", e);
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg(e.getMessage());
			return responseResult;
		}
		
		return responseResult;
	}
	
}
