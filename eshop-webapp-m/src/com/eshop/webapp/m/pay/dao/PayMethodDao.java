package com.eshop.webapp.m.pay.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.m.base.BaseDao;
import com.eshop.webapp.m.pay.model.PayMethod;

@Repository
public class PayMethodDao extends BaseDao<PayMethod>{
	
	public List<PayMethod> selectPayMethodList(){
		
		return this.selectList("PayMethodMapper.selectPayMethodList");
	}
	
	public PayMethod selectPayMethodById(Integer id){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		
		return (PayMethod)this.selectOne("PayMethodMapper.selectPayMethodById", condition);
	}
}
