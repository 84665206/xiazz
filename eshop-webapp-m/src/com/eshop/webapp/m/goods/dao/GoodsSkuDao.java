package com.eshop.webapp.m.goods.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.goods.model.GoodsSku;

/** 
 * 商品信息
 * @author yangmeng
 * @version 创建时间：2017年2月9日 上午10:42:42 
 * 
 */
@Repository
public class GoodsSkuDao extends BaseDao<GoodsSku>{
	
	public GoodsSku selectGoodsSkuBySkuId(Integer sku_id){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("sku_id", sku_id);
		
		return (GoodsSku)this.selectOne("GoodsSkuMapper.selectGoodsSkuBySkuId", condition);
	}
	
	public List<GoodsSku> selectGoodsSkuByGoodsCode(String goods_code){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("goods_code", goods_code);
		
		return this.selectList("GoodsSkuMapper.selectGoodsSkuByGoodsCode", condition);
	}
	
	public Map<String, GoodsSku> selectGoodsStockByShippingLevel(){
		
		return this.sqlSession.selectMap("GoodsSkuMapper.selectGoodsStockByShippingLevel", "goods_shipping");
	}
}
