package com.eshop.webapp.m.goods.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.m.base.BaseController;
import com.eshop.webapp.m.base.ResponseResult;
import com.eshop.webapp.m.enums.ResponseResultEnum;
import com.eshop.webapp.m.goods.model.GoodsInfoVo;
import com.eshop.webapp.m.goods.service.IGoodsService;
import com.eshop.webapp.m.model.ConfigValue;


/** 
 * 商品
 * @author yangmeng
 * @version 创建时间：2017年2月5日 下午6:33:27 
 * 
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController{
	
	@Autowired
	private IGoodsService goodsService;
	
	@RequestMapping(value="/view")
    public String view(@RequestParam("sku_id")Integer sku_id, 
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		if(sku_id==null||sku_id==0){
			
			return "goods/info";
		}
		
		GoodsInfoVo goodsInfoVo=goodsService.getGoodsInfoVo(sku_id);
		model.addAttribute("goodsInfoVo", goodsInfoVo);
		
		ConfigValue configValue=getConfigValue("goods_min_buy_count");
		if(configValue==null||configValue.getValue()==null){
			model.addAttribute("min_num", 20);
		}else{
			model.addAttribute("min_num", configValue.getValue());
		}
		return "goods/info";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="/ajax/info",method=RequestMethod.POST)
    public ResponseResult ajaxInfo(@RequestParam("sku_id")Integer sku_id, 
    		HttpServletRequest request, HttpServletResponse response,
    		Model model){
		ResponseResult responseResult = new ResponseResult();
		if(sku_id==null||sku_id==0){
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("SKU_ID不能为空");
			return responseResult;
		}
		
		GoodsInfoVo goodsInfoVo=goodsService.getGoodsInfoVo(sku_id);
		if(goodsInfoVo!=null){
			responseResult.setCode(ResponseResultEnum.SUCCESS.code);
			responseResult.setData(goodsInfoVo);
		}else{
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("商品信息未找到");
			return responseResult;
		}
		
		
		return responseResult;
	}
}
