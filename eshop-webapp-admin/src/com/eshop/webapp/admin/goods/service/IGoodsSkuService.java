package com.eshop.webapp.admin.goods.service;

import java.util.List;
import java.util.Map;

import com.eshop.webapp.admin.goods.model.GoodsSku;
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
public interface IGoodsSkuService {

	public Integer insertGoodsSkuInfo(GoodsSku goodsSku, User currentUser);

	public Integer deleteGoodsSkuById(Integer id);

	public Integer updateGoodsSkuById(GoodsSku goodsSku, User currentUser);

	public GoodsSku getGoodsSkuById(Integer id);

	public List<GoodsSku> getGoodsSkuList(Map<String, Object> condition);

	public PageResponse<GoodsSku> listGoodsSkuPage(PageRequest pageRequest, Map<String, Object> condition);

}
