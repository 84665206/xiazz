package com.eshop.webapp.m.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eshop.webapp.m.util.PageRequest;
import com.eshop.webapp.m.util.PageResponse;


/**
 * Dao基础类，所有Dao都继承这个类，包含基本的增删改查方法，继承自它的类可以不写增删改查方法
 *
 * @author shangxuejin liuyi
 */
public abstract class BaseDao<T>  {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected static final int BATCH_SIZE=2000;

	@Resource(name="luolai_sqlSession")
	protected SqlSessionTemplate sqlSession;

	protected int insert(String statement) {
		return this.sqlSession.insert(statement);
	}

	protected int insert(String statement, Object parameter) {
		return this.sqlSession.insert(statement, parameter);
	}

	protected int delete(String statement) {
		return this.sqlSession.delete(statement);
	}

	protected int delete(String statement, Object parameter) {
		return this.sqlSession.delete(statement, parameter);
	}

	protected int update(String statement) {
		return this.sqlSession.update(statement);
	}

	protected int update(String statement, Object parameter) {
		return this.sqlSession.update(statement, parameter);
	}

	protected void select(String statement, ResultHandler handler) {
		this.sqlSession.select(statement, handler);
	}

	protected void select(String statement, Object parameter, ResultHandler handler) {
		this.sqlSession.select(statement, parameter, handler);
	}

	protected void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		this.sqlSession.select(statement, parameter, rowBounds, handler);
	}

	protected Object selectOne(String statement) {
		return this.sqlSession.selectOne(statement);
	}

	protected Object selectOne(String statement, Object parameter) {
		return this.sqlSession.selectOne(statement, parameter);
	}

	protected Map<?, ?> selectMap(String statement, String mapKey) {
		return this.sqlSession.selectMap(statement, mapKey);
	}

	protected Map<?, ?> selectMap(String statement, Object parameter, String mapKey) {
		return this.sqlSession.selectMap(statement, parameter, mapKey);
	}

	protected Map<?, ?> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return this.sqlSession.selectMap(statement, parameter, mapKey, rowBounds);
	}

	@SuppressWarnings("unchecked")
	protected List<T> selectList(String statement) {
		return (List<T>) this.sqlSession.selectList(statement);
	}

	@SuppressWarnings("unchecked")
	protected List<T> selectList(String statement, Object parameter) {
		return (List<T>) this.sqlSession.selectList(statement, parameter);
	}

	@SuppressWarnings("unchecked")
	protected List<T> selectList(String statement, Object parameter, RowBounds rowBounds) {
		return (List<T>) this.sqlSession.selectList(statement, parameter, rowBounds);
	}
	
	/****
	 * 该实现，使用MyBatis提供的实现支持，性能提升的原理，在于重用PreparedStatement。
	 * 警告：该方法，受限于其实现，无法和Spring的事务容器集成，只能在方法内控制事务，并且无法获取自动生成的Id。
	 * @param statement
	 * @param collection
	 * @return
	 */
	protected int batchInsert(String statement,Collection<?> collection){
		SqlSession sqlSession = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		int i = 0;
		try{
		
		for(Object parameter:collection){
			sqlSession.insert(statement, parameter);
			i++;
			if(i%BATCH_SIZE==0){
				 //手动每2000个一提交，提交后无法回滚
				sqlSession.commit();
				//清理缓存，防止溢出
				sqlSession.clearCache();
			}
			
		}
		sqlSession.commit();
		//清理缓存，防止溢出
		sqlSession.clearCache();
		}catch(Exception e){
			logger.error("执行批量插入操作失败,将会执行回滚操作",e);
			sqlSession.clearCache();
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		return i;
	}

	/****
	 * 该实现，使用MyBatis提供的实现支持，性能提升的原理，在于重用PreparedStatement。
	 * 警告：该方法，受限于其实现，无法和Spring的事务容器集成，只能在方法内控制事务，并且无法获取自动生成的Id。
	 * @param statement
	 * @param collection
	 * @return
	 */
	protected int batchUpdate(String statement,Collection<?> collection){
		SqlSession sqlSession = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		int i = 0;
		try{
		
		for(Object parameter:collection){
			sqlSession.update(statement, parameter);
			i++;
			if(i==BATCH_SIZE){
				 //手动每2000个一提交，提交后无法回滚
				sqlSession.commit();
				//清理缓存，防止溢出
				sqlSession.clearCache();
			}
			
		}
		sqlSession.commit();
		//清理缓存，防止溢出
		sqlSession.clearCache();
		}catch(Exception e){
			logger.error("执行批量插入操作失败,将会执行回滚操作",e);
			sqlSession.clearCache();
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		return i;
	}

	/**
	 * 获取分页对象
	 * <p>
	 * 使用该方法，分页语句中 获取条数参数名称为 pageSize,获取偏移量参数为 #{pageSize}*(#{pageNo}-1)
	 * <p>
	 *
	 * @param page
	 *            包含页码 每页记录条数的分页对象
	 * @param countStatement
	 *            查询结果记录统计语句
	 * @param listStatement
	 *            分页查询语句
	 * @param parameter
	 *            查询条件
	 * @return 分页对象
	 */
	@SuppressWarnings("unchecked")
	protected PageResponse<T> findPage(PageRequest pageRequest, String countStatement, String listStatement, Map<String, Object> parameter) {
		long totalItems = (Integer) this.sqlSession.selectOne(countStatement, parameter);
		List<T> result = new ArrayList<T>();
		if (totalItems > 0) {
			parameter.put("pageSize", pageRequest.getLength());
			parameter.put("offset", pageRequest.getStart());

			result = (List<T>) this.sqlSession.selectList(listStatement, parameter);
		}
		
		PageResponse<T> page = new PageResponse<T>(pageRequest);
		page.setRecordsTotal(totalItems);
		page.setData(result);
		return page;

	}

	protected void clearCache() {
		this.sqlSession.clearCache();
	}

	protected void colse() {
		this.sqlSession.close();
	}

	protected void commit() {
		this.sqlSession.commit();
	}

}
