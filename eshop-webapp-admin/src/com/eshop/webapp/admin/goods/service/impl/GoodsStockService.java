package com.eshop.webapp.admin.goods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.webapp.admin.goods.dao.GoodsStockDao;
import com.eshop.webapp.admin.goods.model.GoodsStock;
import com.eshop.webapp.admin.goods.service.IGoodsStockService;
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
public class GoodsStockService implements IGoodsStockService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodsStockDao goodsStockDao;
	
	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsStockService#insertGoodsStockInfo(com.eshop.webapp.admin.goods.model.GoodsStock, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer insertGoodsStockInfo(GoodsStock goodsStock, User currentUser) {
		if(goodsStock!=null && currentUser!=null){
			goodsStock.setInsertUser(currentUser.getUser_name());
			goodsStock.setUpdateUser(currentUser.getUser_name());
		}
		return goodsStockDao.insertGoodsStockInfo(goodsStock);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsStockService#deleteGoodsStockById(com.eshop.webapp.admin.goods.model.GoodsStock, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer deleteGoodsStockById(Integer id) {
		// 使用逻辑删除，物理删除暂时为空
		return null;
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsStockService#updateGoodsStockById(com.eshop.webapp.admin.goods.model.GoodsStock, com.eshop.webapp.admin.sys.model.User)
	 */
	@Override
	public Integer updateGoodsStockById(GoodsStock goodsStock, User currentUser) {
		if(goodsStock!=null && currentUser!=null){
			goodsStock.setUpdateUser(currentUser.getUser_name());
			goodsStock.setUpdateTime(new Date());
		}
		return goodsStockDao.updateGoodsStockById(goodsStock);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsStockService#getGoodsStockById(java.lang.Integer)
	 */
	@Override
	public GoodsStock getGoodsStockById(Integer id) {
		return goodsStockDao.getGoodsStockById(id);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsStockService#getGoodsStockList(java.util.Map)
	 */
	@Override
	public List<GoodsStock> getGoodsStockList(Map<String, Object> condition) {
		return goodsStockDao.selectGoodsStockList(condition);
	}

	/* (non-Javadoc)
	 * @see com.eshop.webapp.admin.goods.service.IGoodsStockService#listGoodsStockPage(com.eshop.webapp.admin.util.PageRequest, java.util.Map)
	 */
	@Override
	public PageResponse<GoodsStock> listGoodsStockPage(PageRequest pageRequest, Map<String, Object> condition) {
		return goodsStockDao.listGoodsStockPage(pageRequest, condition);
	}

}
