package com.eshop.webapp.m.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.order.model.OrderGoods;

/** 
 * 订单产品
 * @author yangmeng
 * 
 */
@Repository
public class OrderGoodsDao extends BaseDao<OrderGoods>{
	
	public Integer insertOrderGoods(OrderGoods orderGoods){
		
		return this.insert("OrderGoodsMapper.insertOrderGoods", orderGoods);
	}
	
	/**
	 * 减库存
	 * @param num
	 * @param sku_id
	 * @return
	 */
	public Integer reduceStock(Integer num, Integer sku_id){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("num", num);
		condition.put("sku_id", sku_id);
		
		return this.update("GoodsSkuMapper.reduceStock", condition);
	}
	
	public List<OrderGoods> selectOrderGoodsList(Integer order_id){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("order_id", order_id);
		
		return this.selectList("OrderGoodsMapper.selectOrderGoodsList", condition);
	}
}
