package com.eshop.webapp.admin.goods.service;

import java.util.List;
import java.util.Map;

import com.eshop.webapp.admin.goods.model.GoodsPrice;
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
public interface IGoodsPriceService {

	public Integer insertGoodsPriceInfo(GoodsPrice GoodsPrice, User currentUser);

	public Integer deleteGoodsPriceById(Integer id);

	public Integer updateGoodsPriceById(GoodsPrice GoodsPrice, User currentUser);

	public GoodsPrice getGoodsPriceById(Integer id);

	public List<GoodsPrice> getGoodsPriceList(Map<String, Object> condition);

	public PageResponse<GoodsPrice> listGoodsPricePage(PageRequest pageRequest, Map<String, Object> condition);

}
