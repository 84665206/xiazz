package com.eshop.webapp.admin.goods.controller;

import java.math.BigDecimal;
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
import com.eshop.webapp.admin.goods.model.GoodsPrice;
import com.eshop.webapp.admin.goods.service.IGoodsPriceService;
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
public class GoodsPriceController extends BaseController {

	@Autowired
	private IGoodsPriceService goodsPriceService;
	

	/**
	 * 进入商品SKU信息界面
	 *
	 * @return
	 */
	@RequestMapping("goodsPrice/page")
	public String goodsPricePage() {
		return "/goods/goodsPriceInfo";
	}

	/**
	 * 查询商品SKU信息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("goodsPrice/list")
	public PageResponse<GoodsPrice> goodsPriceList(@ModelAttribute PageRequest pageRequest, 
			@RequestParam(value="skuCode", required=false)String skuCode,
			@RequestParam(value="goodsName", required=false)String goodsName
			) {
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(skuCode)){
			condition.put("skuCode", skuCode);
		}
		if(StringUtils.isNotBlank(goodsName)){
			condition.put("goodsName", goodsName);
		}
		PageResponse<GoodsPrice> pageResponse = this.goodsPriceService.listGoodsPricePage(pageRequest, condition);
		
        return pageResponse;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("goodsPrice/create")
	public ResponseResult goodsPriceCreate(
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
		GoodsPrice goodsPrice = new GoodsPrice();
		goodsPrice.setIsDelete(0);
		
		if(StringUtils.isNotBlank(skuCode)){
			goodsPrice.setSkuCode(skuCode);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("SKU编码不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(goodsName)){
			goodsPrice.setGoodsName(goodsName);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("商品名称不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(goodsVarieties)){
			goodsPrice.setGoodsVarieties(goodsVarieties);
		}
		if(StringUtils.isNotBlank(goodsSpec)){
			goodsPrice.setGoodsSpec(goodsSpec);
		}
		if(StringUtils.isNotBlank(goodsOrigin)){
			goodsPrice.setGoodsOrigin(goodsOrigin);
		}
		if(StringUtils.isNotBlank(goodsUnit)){
			goodsPrice.setGoodsUnit(goodsUnit);
		}
		if(StringUtils.isNotBlank(otherExplain)){
			goodsPrice.setOtherExplain(otherExplain);
		}
		if(isValid != null){
			goodsPrice.setIsValid(isValid);
		}
		
		try {
			this.logger.debug(XStreamWrapper.toXML(goodsPrice));
			Integer itotal=this.goodsPriceService.insertGoodsPriceInfo(goodsPrice, getCurrentUser());
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
	@RequestMapping("goodsPrice/edit")
	public ResponseResult goodsPriceEdit(
			@RequestParam(value="id", required=false)Integer id,
			@RequestParam(value="salePrice", required=false)String salePrice,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();
		GoodsPrice goodsPrice = new GoodsPrice();

		if(id != null){
			goodsPrice.setId(id);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("skuID不能为空");
			return responseResult;
		}

		if(StringUtils.isNotBlank(salePrice)){
			goodsPrice.setSalePrice(new BigDecimal(salePrice));
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("价格不能为空");
			return responseResult;
		}

		try {
			this.logger.debug(XStreamWrapper.toXML(goodsPrice));
			Integer itotal=this.goodsPriceService.updateGoodsPriceById(goodsPrice, getCurrentUser());
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("价格修改失败");
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("价格修改成功");
			}
		}
		catch (Exception e) {
			logger.error("价格修改-发生系统异常", e);
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg(e.getMessage());
			return responseResult;
		}
		
		return responseResult;
	}
	
}
