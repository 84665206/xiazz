package com.eshop.webapp.admin.util;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Intercepts({ 
	@Signature(type = StatementHandler.class, method = "query", args = { java.sql.Statement.class,org.apache.ibatis.session.ResultHandler.class }),
	@Signature(type = StatementHandler.class, method = "update", args = { java.sql.Statement.class}),
	@Signature(type = StatementHandler.class, method = "batch", args = { java.sql.Statement.class})})
public class SqlMonitorInterceptor implements Interceptor, java.io.Serializable {
	
	/*****
	 * logger
	 */
	protected static Logger logger = LoggerFactory.getLogger(SqlMonitorInterceptor.class);
	
	//protected AtomicInteger logControl = new AtomicInteger(1);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler ps = (StatementHandler) invocation.getTarget();
		BoundSql boundSql = ps.getBoundSql();
		long start = System.currentTimeMillis();
		//boundSql.get
		try {
			return invocation.proceed();
		} finally {
			long took = System.currentTimeMillis()-start;
			//int logControl = new Random().nextInt(10);
			//if(logControl>7){
				logger.info("[SQL Monitor] method="+invocation.getMethod().getName()+",took="+took+".ms"+",SQL="+convertInfoSQL(boundSql));
			//}		
		}
	}

	public static String convertInfoSQL(BoundSql boundSql){
		return boundSql.getSql().replaceAll("\\n\\t", " ").replaceAll("\\s{2,}", " ");
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// do nothing

	}

}
