package com.eshop.webapp.m.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 日期类型操作工具类
 * </p>
 * <p>
 * 提供如下功能：<br>
 * <1>.日期型与字符串之间的转换<br>
 * <2>.日期型的运算<br>
 * </p>
 *
 * @since 2011-11-10
 * @version V1.0
 * @author luowen
 */
public class DateUtils {

	public static final int MILLISECONDS = 1000 * 60 * 60 * 24;
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYMMDD = "yyMMdd";
	public static final String YYYYMM = "yyyyMM";
	public static final String YYMM = "yyMM";
	public static final String YYYY = "yyyy";
	public static final String YY = "yy";
	public static final String MM = "MM";

	/**
	 * @param date
	 * @return 返回默认格式(yyyy-MM-dd)日期字符串
	 */
	public static String DateToString(Date date) {
		DateFormat df = new SimpleDateFormat(YYYY_MM_DD);
		return DateToString(date, df);
	}

	/**
	 * @param date
	 * @param format
	 * @return 返回对应格式日期字符串
	 */
	public static String DateToString(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return DateToString(date, df);
	}

	/**
	 * @param date
	 * @param df
	 * @return 返回对应格式日期字符串
	 */
	private static String DateToString(Date date, DateFormat df) {
		try {
			return df.format(date);
		}
		catch (Exception e) {
			return "";
		}
	}

	/**
	 * @param sdate
	 * @param format
	 * @return 返回对应格式的日期
	 * @throws ParseException
	 */
	public static Date StringToDate(String sdate, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		return StringToDate(sdate, df);
	}

	/**
	 * @param sdate
	 * @param format
	 * @return 返回默认格式(yyyy-MM-dd)的日期
	 * @throws ParseException
	 */
	public static Date StringToDate(String sdate) throws ParseException {
		DateFormat df = new SimpleDateFormat(YYYY_MM_DD);
		return StringToDate(sdate, df);
	}

	/**
	 * @param sdate
	 * @param format
	 * @return 返回对应格式的日期
	 * @throws ParseException
	 */
	private static Date StringToDate(String sdate, DateFormat df) throws ParseException {
		if (StringUtils.isEmpty(sdate)) {
			return null;
		}
		return df.parse(sdate);
	}

	/**
	 * 不带抛出异常的日期转换
	 * @param sdate
	 * @param format
	 * @return Date
	 */
	public static Date StringToDateNoException(String sdate, String format) {
		Date result;
		try {
			result = StringToDate(sdate,format);
		}
		catch (ParseException e) {
			e.printStackTrace();
			result = null;
		}finally{

		}
		return result;
	}

	/**
	 * 不带抛出异常的日期转换(默认格式：yyyy-MM-dd)
	 * @param sdate
	 * @return Date
	 */
	public static Date StringToDateNoException(String sdate) {
		Date result;
		try {
			result = StringToDate(sdate);
		}
		catch (ParseException e) {
			e.printStackTrace();
			result = null;
		}finally{

		}
		return result;
	}

	/**
	 * @param date
	 * @param days
	 * @return 增加xx天数后的日期，days为负数则做减法
	 */
	public static Date DateAdd(Date date, int days) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @param months
	 * @return 增加xx月数后的日期，months为负数则做减法
	 */
	public static Date MonthAdd(Date date, int months) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @param weaks
	 * @return 增加xx周数后的日期，weaks为负数则做减法
	 */
	public static Date WeekAdd(Date date, int weaks) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, weaks);
		return calendar.getTime();
	}

	/**
	 *
	 * @param date
	 * @param hours
	 * @return 增加小时数, hours为负数则做减法
	 */
    public static Date hourAdd(Date date, int hours){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + hours);
        return calendar.getTime();
    }

    /**
	 *
	 * @param date
	 * @param mins
	 * @return 增加分钟数, mins为负数则做减法
	 */
   public static Date minAdd(Date date, int mins){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + mins);
       return calendar.getTime();
   }
    
	/**
	 * @param begin
	 * @param end
	 * @return 两个日期的天数差
	 */
	public static int getDateInterval(Date begin, Date end) {
		return (int) java.lang.Math.round((begin.getTime() - end.getTime()) / (double) MILLISECONDS + 0.4999884);
	}
	/*****
	 * 主要用户时间段查询时的转换，将开始时间
	 * 转换为当天的0时0分0秒000毫秒
	 * @param date
	 * @return
	 */
	public static final Date convertStartDate(Date date){
		Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		return c.getTime();
	}



	/****
	 * 主要用户时间段查询时的转换，将结束时间
	 * 转换为当天的23时59分59秒999毫秒
	 * @param date
	 * @return
	 */
	public static final Date convertEndDate(Date date){
		Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY,23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND,59);
		c.set(Calendar.MILLISECOND,999);
		return c.getTime();
	}
	/****
	 * 获取指定日期前几个月的最后一天。负数则为几个月之后
	 * @param date 指定日期
	 * @param month 几个月之前
	 * @return
	 */
	public static final Date getLastDayBeforeMonth(Date date,int month){
		//获取指定时间的几个月之前的第一天
		Date d = getFirstDayBeforeMonth(date,month);
		Date d1 = MonthAdd(d,1);
		return DateAdd(d1,-1);
	}
	/****
	 * 获取指定日期前几个月的第一天.负数则为几个月之后
 * @param date 指定日期
	 * @param month 几个月之前
	 * @return
	 */
	public static final Date getFirstDayBeforeMonth(Date date,int month){
		Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-month);
		c.set(Calendar.DAY_OF_MONTH,1);
		return c.getTime();
	}


	/**
	 * 取得指定的时间
	 * 设置指定时间的时，分，秒
	 * @param date
	 * @return
	 */
	public static final Date getDateAssignHHMMSS(Date date, int hour, int minute, int second){
		Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY,hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND,second);
		return c.getTime();
	}


}
