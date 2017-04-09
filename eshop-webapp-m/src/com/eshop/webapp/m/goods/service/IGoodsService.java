package com.eshop.webapp.m.goods.service;

import java.util.Map;

import com.eshop.webapp.m.goods.model.GoodsInfoVo;
import com.eshop.webapp.m.goods.model.GoodsSku;

/** 
 * 商品信息
 * @author yangmeng
 * @version 创建时间：2017年2月9日 上午10:40:31 
 * 
 */
public interface IGoodsService {
	
	/**
	 * 查询商品信息-主方法
	 * @param sku_id
	 * @return
	 */
	GoodsInfoVo getGoodsInfoVo(Integer sku_id);
	
	/**
	 * 查询配送方式的库存
	 * @param goods_stock
	 * @return
	 */
	Map<String, GoodsSku> getGoodsStockByShippingLevel();
}
