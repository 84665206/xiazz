package com.eshop.webapp.m.index.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.webapp.m.enums.ShippingLevelEnum;
import com.eshop.webapp.m.goods.model.GoodsSku;
import com.eshop.webapp.m.goods.service.IGoodsService;

/** 
 * 首页
 * @author yangmeng
 * @version 创建时间：2017年2月1日 
 * 
 */
@Controller
public class IndexController {
	
	@Autowired
	private IGoodsService goodsService;
	
	@RequestMapping(value="/index")
    public String index(Model model){
		Map<String, GoodsSku> stock_map=goodsService.getGoodsStockByShippingLevel();
		model.addAttribute("today_stock", (stock_map!=null&&stock_map.size()>0)?stock_map.get(ShippingLevelEnum.TODAY.level).getStock_num():0);
		model.addAttribute("tomorrow_stock", (stock_map!=null&&stock_map.size()>0)?stock_map.get(ShippingLevelEnum.TOMORROW.level).getStock_num():0);
		
		return "index";
	}
	
	@RequestMapping(value="/about")
    public String about(){
		
		return "about";
	}
}
