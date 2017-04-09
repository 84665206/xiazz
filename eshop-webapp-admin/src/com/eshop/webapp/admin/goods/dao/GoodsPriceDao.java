package com.eshop.webapp.admin.goods.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.goods.model.GoodsPrice;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 * 商品价格管理dao
 *
 * @author luowen
 */
@Repository
public class GoodsPriceDao extends BaseDao<GoodsPrice> {

	/**
	 * 分页查询 - 商品价格信息
	 * @param pageRequest
	 * @param condition
	 * @return
	 */
	public PageResponse<GoodsPrice> listGoodsPricePage(PageRequest pageRequest, Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "GoodsPriceMapper.searchWithPageCount",
			"GoodsPriceMapper.searchWithPage", map);
	}

	/**
	 * 查询所有非删除的 - 商品价格信息
	 * @return
	 */
	public List<GoodsPrice> selectGoodsPriceList(Map<String, Object> condition){
		return this.selectList("GoodsPriceMapper.searchWithList", condition);
	}

	/**
	 * 根据ID取得 - 商品价格信息
	 * @param id
	 * @return
	 */
	public GoodsPrice getGoodsPriceById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (GoodsPrice) this.selectOne("GoodsPriceMapper.searchOneById", map);
	}

	/**
	 * 新增 - 商品价格信息
	 * @param goodsPrice
	 * @return
	 */
	public Integer insertGoodsPriceInfo(GoodsPrice goodsPrice){
		return this.sqlSession.insert("GoodsPriceMapper.insertOne", goodsPrice);
	}


	/**
	 * 更新 - 商品价格信息
	 * @param goodsPrice
	 * @return
	 */
	public Integer updateGoodsPriceById(GoodsPrice goodsPrice){
		return this.sqlSession.update("GoodsPriceMapper.updateById", goodsPrice);
	}
	
}
