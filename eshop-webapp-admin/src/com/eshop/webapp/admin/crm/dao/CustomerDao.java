package com.eshop.webapp.admin.crm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshop.webapp.admin.base.BaseDao;
import com.eshop.webapp.admin.crm.model.Customer;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 * 客户管理dao
 *
 * @author luowen
 */
@Repository
public class CustomerDao extends BaseDao<Customer> {

	/**
	 * 分页查询 - 客户会员信息
	 * @param pageRequest
	 * @param condition
	 * @return
	 */
	public PageResponse<Customer> listCustomerPage(PageRequest pageRequest, Map<String, Object> condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(condition);
		return this.findPage(pageRequest, "CustomerMapper.searchWithPageCount",
			"CustomerMapper.searchWithPage", map);
	}

	/**
	 * 查询所有非删除的 - 客户会员信息
	 * @return
	 */
	public List<Customer> selectCustomerList(Map<String, Object> condition){
		return this.selectList("CustomerMapper.searchWithList", condition);
	}

	/**
	 * 根据ID取得 - 客户会员信息
	 * @param CustomerName
	 * @return
	 */
	public Customer getCustomerById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (Customer) this.selectOne("CustomerMapper.searchOneById", map);
	}

	/**
	 * 新增 - 客户会员信息
	 * @param customer
	 * @return
	 */
	public Integer insertCustomerInfo(Customer customer){
		return this.sqlSession.insert("CustomerMapper.insertOne", customer);
	}


	/**
	 * 更新 - 客户会员信息
	 * @param customer
	 * @return
	 */
	public Integer updateCustomerById(Customer customer){
		return this.sqlSession.update("CustomerMapper.updateById", customer);
	}
	
}
