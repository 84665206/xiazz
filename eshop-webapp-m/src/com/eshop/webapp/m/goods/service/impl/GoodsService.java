package com.eshop.webapp.m.goods.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.m.goods.dao.GoodsAttributeDao;
import com.eshop.webapp.m.goods.dao.GoodsSkuDao;
import com.eshop.webapp.m.goods.model.GoodsInfoVo;
import com.eshop.webapp.m.goods.model.GoodsSku;
import com.eshop.webapp.m.goods.service.IGoodsService;

/** 
 * 商品
 * @author yangmeng
 * @version 创建时间：2017年2月9日 上午10:41:46 
 * 
 */
@Service
public class GoodsService implements IGoodsService{
	
	@Autowired
	private GoodsSkuDao goodsSkuDao;
	@Autowired
	private GoodsAttributeDao goodsAttributeDao;

	@Override
	public GoodsInfoVo getGoodsInfoVo(Integer sku_id) {
		GoodsInfoVo goodsInfoVo=new GoodsInfoVo();
		GoodsSku main_goods=goodsSkuDao.selectGoodsSkuBySkuId(sku_id);
		if(main_goods==null||
				StringUtils.isBlank(main_goods.getGoods_code())){
			return goodsInfoVo;
		}
		goodsInfoVo.setMain_goods(main_goods);
		
		List<GoodsSku> relate_goods=goodsSkuDao.selectGoodsSkuByGoodsCode(main_goods.getGoods_code());
		goodsInfoVo.setRelate_goods(relate_goods);
		
		return goodsInfoVo;
	}

	@Override
	public Map<String, GoodsSku> getGoodsStockByShippingLevel() {
		return goodsSkuDao.selectGoodsStockByShippingLevel();
	}

}
