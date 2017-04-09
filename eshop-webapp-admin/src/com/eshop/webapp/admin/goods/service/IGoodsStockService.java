package com.eshop.webapp.admin.goods.service;

import java.util.List;
import java.util.Map;

import com.eshop.webapp.admin.goods.model.GoodsStock;
import com.eshop.webapp.admin.sys.model.User;
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
 * @since 2017年1月26日
 * @version V1.0
 * @author luowen
 */
public interface IGoodsStockService {

	public Integer insertGoodsStockInfo(GoodsStock GoodsStock, User currentUser);

	public Integer deleteGoodsStockById(Integer id);

	public Integer updateGoodsStockById(GoodsStock GoodsStock, User currentUser);

	public GoodsStock getGoodsStockById(Integer id);

	public List<GoodsStock> getGoodsStockList(Map<String, Object> condition);

	public PageResponse<GoodsStock> listGoodsStockPage(PageRequest pageRequest, Map<String, Object> condition);

}
