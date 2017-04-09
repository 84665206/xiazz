package com.eshop.webapp.admin.goods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.admin.goods.dao.GoodsSkuDao;
import com.eshop.webapp.admin.goods.model.GoodsSku;
import com.eshop.webapp.admin.goods.service.IGoodsSkuService;
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
@Service
public class GoodsSkuService implements IGoodsSkuService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodsSkuDao goodsSkuDao;
	
	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsSkuService#insertGoodsSkuInfo(com.eshop.webapp.admin.goods.model.GoodsSku, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer insertGoodsSkuInfo(GoodsSku goodsSku, User currentUser) {
		if(goodsSku!=null && currentUser!=null){
			goodsSku.setInsertUser(currentUser.getUser_name());
			goodsSku.setUpdateUser(currentUser.getUser_name());
		}
		return goodsSkuDao.insertGoodsSkuInfo(goodsSku);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsSkuService#deleteGoodsSkuById(com.eshop.webapp.admin.goods.model.GoodsSku, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer deleteGoodsSkuById(Integer id) {
		// 使用逻辑删除，物理删除暂时为空
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsSkuService#updateGoodsSkuById(com.eshop.webapp.admin.goods.model.GoodsSku, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer updateGoodsSkuById(GoodsSku goodsSku, User currentUser) {
		if(goodsSku!=null && currentUser!=null){
			goodsSku.setUpdateUser(currentUser.getUser_name());
			goodsSku.setUpdateTime(new Date());
		}
		return goodsSkuDao.updateGoodsSkuById(goodsSku);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsSkuService#getGoodsSkuById(java.lang.Integer)
	 */
	@Override
	public GoodsSku getGoodsSkuById(Integer id) {
		return goodsSkuDao.getGoodsSkuById(id);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsSkuService#getGoodsSkuList(java.util.Map)
	 */
	@Override
	public List<GoodsSku> getGoodsSkuList(Map<String, Object> condition) {
		return goodsSkuDao.selectGoodsSkuList(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsSkuService#listGoodsSkuPage(com.eshop.webapp.admin.util.PageRequest, java.util.Map)
	 */
	@Override
	public PageResponse<GoodsSku> listGoodsSkuPage(PageRequest pageRequest, Map<String, Object> condition) {
		return goodsSkuDao.listGoodsSkuPage(pageRequest, condition);
	}

}
