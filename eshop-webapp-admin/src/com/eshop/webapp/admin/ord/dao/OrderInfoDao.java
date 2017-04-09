package com.eshop.webapp.admin.ord.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.ord.model.OrderInfo;
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
public class OrderInfoDao extends BaseDao<OrderInfo> {

	/**
	 * 分页查询 - 订单信息
	 * @param pageRequest
	 * @param condition
	 * @return
	 */
	public PageResponse<OrderInfo> listOrderInfoPage(PageRequest pageRequest, Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "OrderInfoMapper.searchWithPageCount",
			"OrderInfoMapper.searchWithPage", map);
	}

	/**
	 * 根据ID取得 - 订单信息
	 * @param OrderInfoName
	 * @return
	 */
	public OrderInfo searchOrderSumData(Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return (OrderInfo) this.selectOne("OrderInfoMapper.searchOrderSumData", map);
	}

	/**
	 * 查询所有非删除的 - 订单信息
	 * @return
	 */
	public List<OrderInfo> selectOrderInfoList(Map<String, Object> condition){
		return this.selectList("OrderInfoMapper.searchWithList", condition);
	}

	/**
	 * 根据ID取得 - 订单信息
	 * @param OrderInfoName
	 * @return
	 */
	public OrderInfo getOrderInfoById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (OrderInfo) this.selectOne("OrderInfoMapper.searchOneById", map);
	}

	/**
	 * 新增 - 订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer insertOrderInfoInfo(OrderInfo orderInfo){
		return this.sqlSession.insert("OrderInfoMapper.insertOne", orderInfo);
	}


	/**
	 * 更新 - 订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer updateOrderInfoById(OrderInfo orderInfo){
		return this.sqlSession.update("OrderInfoMapper.updateById", orderInfo);
	}

	/**
	 * 审核 - 订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer approveOrderInfoById(OrderInfo orderInfo){
		return this.sqlSession.update("OrderInfoMapper.updateApproveById", orderInfo);
	}

	/**
	 * 取消 - 订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer cancelOrderInfoById(OrderInfo orderInfo){
		return this.sqlSession.update("OrderInfoMapper.updateCancelById", orderInfo);
	}

	/**
	 * 完成 - 订单信息
	 * @param orderInfo
	 * @return
	 */
	public Integer completeOrderInfoById(OrderInfo orderInfo){
		return this.sqlSession.update("OrderInfoMapper.updateCompleteById", orderInfo);
	}
	
}
