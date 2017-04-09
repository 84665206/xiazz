package com.eshop.webapp.m.util;

import java.text.DecimalFormat;

/**
 * 
 * @Description: 数字格式化 
 * @author zhangjun
 * @date 2013-12-19 下午5:21:48
 */
public class NumUtils {
	public static Double formatToDecimal(Double num){
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.valueOf(df.format(num));
	}
}
