package com.eshop.webapp.admin.goods.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.goods.model.GoodsSku;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 * 商品管理dao
 *
 * @author luowen
 */
@Repository
public class GoodsSkuDao extends BaseDao<GoodsSku> {

	/**
	 * 分页查询 - 商品SKU信息
	 * @param pageRequest
	 * @param condition
	 * @return
	 */
	public PageResponse<GoodsSku> listGoodsSkuPage(PageRequest pageRequest, Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "GoodsSkuMapper.searchWithPageCount",
			"GoodsSkuMapper.searchWithPage", map);
	}

	/**
	 * 查询所有非删除的 - 商品SKU信息
	 * @return
	 */
	public List<GoodsSku> selectGoodsSkuList(Map<String, Object> condition){
		return this.selectList("GoodsSkuMapper.searchWithList", condition);
	}

	/**
	 * 根据ID取得 - 商品SKU信息
	 * @param GoodsSkuName
	 * @return
	 */
	public GoodsSku getGoodsSkuById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (GoodsSku) this.selectOne("GoodsSkuMapper.searchOneById", map);
	}

	/**
	 * 新增 - 商品SKU信息
	 * @param goodsSku
	 * @return
	 */
	public Integer insertGoodsSkuInfo(GoodsSku goodsSku){
		return this.sqlSession.insert("GoodsSkuMapper.insertOne", goodsSku);
	}


	/**
	 * 更新 - 商品SKU信息
	 * @param goodsSku
	 * @return
	 */
	public Integer updateGoodsSkuById(GoodsSku goodsSku){
		return this.sqlSession.update("GoodsSkuMapper.updateById", goodsSku);
	}
	
}
