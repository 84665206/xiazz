package com.eshop.webapp.admin.goods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.admin.goods.dao.GoodsPriceDao;
import com.eshop.webapp.admin.goods.model.GoodsPrice;
import com.eshop.webapp.admin.goods.service.IGoodsPriceService;
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
public class GoodsPriceService implements IGoodsPriceService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodsPriceDao goodsPriceDao;
	
	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsPriceService#insertGoodsPriceInfo(com.eshop.webapp.admin.goods.model.GoodsPrice, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer insertGoodsPriceInfo(GoodsPrice goodsPrice, User currentUser) {
		if(goodsPrice!=null && currentUser!=null){
			goodsPrice.setInsertUser(currentUser.getUser_name());
			goodsPrice.setUpdateUser(currentUser.getUser_name());
		}
		return goodsPriceDao.insertGoodsPriceInfo(goodsPrice);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsPriceService#deleteGoodsPriceById(com.eshop.webapp.admin.goods.model.GoodsPrice, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer deleteGoodsPriceById(Integer id) {
		// 使用逻辑删除，物理删除暂时为空
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsPriceService#updateGoodsPriceById(com.eshop.webapp.admin.goods.model.GoodsPrice, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer updateGoodsPriceById(GoodsPrice goodsPrice, User currentUser) {
		if(goodsPrice!=null && currentUser!=null){
			goodsPrice.setUpdateUser(currentUser.getUser_name());
			goodsPrice.setUpdateTime(new Date());
		}
		return goodsPriceDao.updateGoodsPriceById(goodsPrice);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsPriceService#getGoodsPriceById(java.lang.Integer)
	 */
	@Override
	public GoodsPrice getGoodsPriceById(Integer id) {
		return goodsPriceDao.getGoodsPriceById(id);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsPriceService#getGoodsPriceList(java.util.Map)
	 */
	@Override
	public List<GoodsPrice> getGoodsPriceList(Map<String, Object> condition) {
		return goodsPriceDao.selectGoodsPriceList(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsPriceService#listGoodsPricePage(com.eshop.webapp.admin.util.PageRequest, java.util.Map)
	 */
	@Override
	public PageResponse<GoodsPrice> listGoodsPricePage(PageRequest pageRequest, Map<String, Object> condition) {
		return goodsPriceDao.listGoodsPricePage(pageRequest, condition);
	}

}
