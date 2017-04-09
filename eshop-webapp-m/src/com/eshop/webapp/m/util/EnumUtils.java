package com.eshop.webapp.m.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author liuyi 2012-6-26
 */
public class EnumUtils {
	protected final Logger logger = LoggerFactory
			.getLogger(EnumUtils.class.getName());
	private static final EnumUtils enumUtils = new EnumUtils();

	private EnumUtils() {

	}

	public static EnumUtils getInstance() {
		return enumUtils;
	}

	class OrderStatusDto{
		private String name;
		private Integer value;


	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	}
}
