package com.eshop.webapp.admin.goods.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.goods.model.GoodsStock;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 * 商品库存管理dao
 *
 * @author luowen
 */
@Repository
public class GoodsStockDao extends BaseDao<GoodsStock> {

	/**
	 * 分页查询 - 商品SKU信息
	 * @param pageRequest
	 * @param condition
	 * @return
	 */
	public PageResponse<GoodsStock> listGoodsStockPage(PageRequest pageRequest, Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "GoodsStockMapper.searchWithPageCount",
			"GoodsStockMapper.searchWithPage", map);
	}

	/**
	 * 查询所有非删除的 - 商品SKU信息
	 * @return
	 */
	public List<GoodsStock> selectGoodsStockList(Map<String, Object> condition){
		return this.selectList("GoodsStockMapper.searchWithList", condition);
	}

	/**
	 * 根据ID取得 - 商品SKU信息
	 * @param GoodsStockName
	 * @return
	 */
	public GoodsStock getGoodsStockById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (GoodsStock) this.selectOne("GoodsStockMapper.searchOneById", map);
	}

	/**
	 * 新增 - 商品SKU信息
	 * @param goodsSku
	 * @return
	 */
	public Integer insertGoodsStockInfo(GoodsStock goodsSku){
		return this.sqlSession.insert("GoodsStockMapper.insertOne", goodsSku);
	}


	/**
	 * 更新 - 商品SKU信息
	 * @param goodsSku
	 * @return
	 */
	public Integer updateGoodsStockById(GoodsStock goodsSku){
		return this.sqlSession.update("GoodsStockMapper.updateById", goodsSku);
	}
	
}
