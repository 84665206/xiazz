/**
 * Copyright (c) 2005-2011 springside.org.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Fixtures.java 1593 2011-05-11 10:37:12Z calvinxiu $
 */
package com.eshop.webapp.m.util;

import com.eshop.webapp.m.base.BaseModel;

/**
 * 分页参数封装类.
 */
public class PageRequest extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int start = 0; //从第几条开始
	protected int length = 10; //每页多少条

	public PageRequest() {}

	public PageRequest(int start, int length) {
		this.start = start;
		this.length = length;
	}

	/**
	 * 获取当前从第几条开始
	 */
	public int getCurrentStart() {
		return start;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
}
