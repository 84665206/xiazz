package com.eshop.webapp.admin.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

/**
 *<p>
 *	重写jdbc注销类
 *</p>
 * @date 2015-1-30 上午11:37:50
 * @version 1.0
 * @author yangmeng
 */

public class XBasicDataSource extends BasicDataSource{
	
	public synchronized void close() throws SQLException{
	
	  DriverManager.deregisterDriver(DriverManager.getDriver(url));
	  super.close();
	 }
}


