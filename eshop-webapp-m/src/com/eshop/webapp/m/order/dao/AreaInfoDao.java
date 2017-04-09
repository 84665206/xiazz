package com.eshop.webapp.m.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.order.model.AreaInfo;

/** 
 * @author yangmeng
 * @version 创建时间：2017年2月13日 下午6:37:01 
 * 
 */
@Repository
public class AreaInfoDao extends BaseDao<AreaInfo>{
	
	public List<AreaInfo> selectAreaInfoList(Integer parent_id, Integer area_type){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("parent_id", parent_id);
		condition.put("area_type", area_type);
		return this.selectList("AreaInfoMapper.selectAreaInfoList", condition);
	}
}
