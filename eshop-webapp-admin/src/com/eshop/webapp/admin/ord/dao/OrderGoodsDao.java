package com.eshop.webapp.admin.ord.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.ord.model.OrderGoods;
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
 * @since 2017年2月10日
 * @version V1.0
 * @author luowen
 */
@Repository
public class OrderGoodsDao extends BaseDao<OrderGoods> {

	/**
	 * 分页查询 - 订单明细信息
	 * @param pageRequest
	 * @param condition
	 * @return
	 */
	public PageResponse<OrderGoods> listOrderGoodsPage(PageRequest pageRequest, Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "OrderGoodsMapper.searchWithPageCount",
			"OrderGoodsMapper.searchWithPage", map);
	}

	/**
	 * 查询所有非删除的 - 订单明细信息
	 * @return
	 */
	public List<OrderGoods> selectOrderGoodsList(Map<String, Object> condition){
		return this.selectList("OrderGoodsMapper.searchWithList", condition);
	}

	/**
	 * 根据ID取得 - 订单明细信息
	 * @param OrderGoodsName
	 * @return
	 */
	public OrderGoods getOrderGoodsById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (OrderGoods) this.selectOne("OrderGoodsMapper.searchOneById", map);
	}

	/**
	 * 新增 - 订单明细信息
	 * @param orderGoods
	 * @return
	 */
	public Integer insertOrderGoodsInfo(OrderGoods orderGoods){
		return this.sqlSession.insert("OrderGoodsMapper.insertOne", orderGoods);
	}


	/**
	 * 更新 - 订单明细信息
	 * @param orderGoods
	 * @return
	 */
	public Integer updateOrderGoodsById(OrderGoods orderGoods){
		return this.sqlSession.update("OrderGoodsMapper.updateById", orderGoods);
	}
	
}
