package com.eshop.webapp.admin.ord.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.ord.model.OrderBatchSend;
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
public class OrderBatchSendDao extends BaseDao<OrderBatchSend> {

	/**
	 * 分页查询 - 订单配送信息
	 * @param pageRequest
	 * @param condition
	 * @return
	 */
	public PageResponse<OrderBatchSend> listOrderBatchSendPage(PageRequest pageRequest, Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "OrderBatchSendMapper.searchWithPageCount",
			"OrderBatchSendMapper.searchWithPage", map);
	}

	/**
	 * 根据条件取得 - 订单配送汇总信息
	 * @param OrderBatchSendName
	 * @return
	 */
	public OrderBatchSend searchOrderSendSumData(Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return (OrderBatchSend) this.selectOne("OrderBatchSendMapper.searchOrderSendSumData", map);
	}

	/**
	 * 查询所有非删除的 - 订单配送信息
	 * @return
	 */
	public List<OrderBatchSend> selectOrderBatchSendList(Map<String, Object> condition){
		return this.selectList("OrderBatchSendMapper.searchWithList", condition);
	}

	/**
	 * 根据ID取得 - 订单配送信息
	 * @param OrderBatchSendName
	 * @return
	 */
	public OrderBatchSend getOrderBatchSendById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (OrderBatchSend) this.selectOne("OrderBatchSendMapper.searchOneById", map);
	}

	/**
	 * 根据ID取得 - 订单配送信息
	 * @param OrderBatchSendName
	 * @return
	 */
	public OrderBatchSend getOrderBatchSendMoreById(Integer batchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("batchId", batchId);
		return (OrderBatchSend) this.selectOne("OrderBatchSendMapper.searchOneMoreById", map);
	}

	/**
	 * 新增 - 订单配送信息
	 * @param orderBatchSend
	 * @return
	 */
	public Integer insertOrderBatchSendInfo(OrderBatchSend orderBatchSend){
		return this.sqlSession.insert("OrderBatchSendMapper.insertOne", orderBatchSend);
	}


	/**
	 * 更新 - 订单配送信息
	 * @param orderBatchSend
	 * @return
	 */
	public Integer updateOrderBatchSendById(OrderBatchSend orderBatchSend){
		return this.sqlSession.update("OrderBatchSendMapper.updateById", orderBatchSend);
	}

	/**
	 * 审核 - 订单配送信息
	 * @param orderBatchSend
	 * @return
	 */
	public Integer approveOrderBatchSendById(OrderBatchSend orderBatchSend){
		return this.sqlSession.update("OrderBatchSendMapper.updateApproveById", orderBatchSend);
	}

	/**
	 * 查询所有 订单配送单的 支付配送信息
	 * @return
	 */
	public List<OrderBatchSend> searchPayInfoByBatchId(Integer batchId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("batchId", batchId);
		return this.selectList("OrderBatchSendMapper.searchPayInfoByBatchId", map);
	}

}
