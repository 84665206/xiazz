package com.eshop.webapp.admin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.util.DateUtils;

/** 
 * @author yangmeng
 * @version 鍒涘缓鏃堕棿锛�2016骞�11鏈�25鏃� 涓嬪崍2:57:43 
 * 
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 鑾峰彇Request
	 */
	protected HttpSession getSession() {
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession();
	}

	/**
	 * 鑾峰彇Request
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	@InitBinder    
	protected void initBinder(WebDataBinder binder) {    
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));    
	}   
	
	/**
	 * 鑾峰彇褰撳墠宸茬櫥褰曠敤鎴穒d
	 */
	protected Integer getCurrentUserId() {
		User u = this.getCurrentUser();

		if (u == null)
			return null;

		return u.getId();
	}
	
	/**
	 * 鑾峰彇褰撳墠宸茬櫥褰曠敤鎴峰悕
	 */
	protected String getCurrentUsername() {
		User u = this.getCurrentUser();

		if (u == null)
			return null;

		return u.getUser_name();
	}
	
	/**
	 * 鑾峰彇褰撳墠宸茬櫥褰曠敤鎴�
	 */
	protected User getCurrentUser() {
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		return (User) request.getSession().getAttribute(User.CURRENT_USER_KEY_IN_SESSION);
	}
	
	
	/**
	 * 鏍规嵁妯℃澘瀵煎嚭Excel
	 *
	 * @param templateFileName 妯℃澘鏂囦欢鍚嶏紙鏂囦欢缁熶竴鏀剧疆鍦ㄥ伐绋嬫牴鐩綍webapp鐨則emplates/excel/鐩綍涓嬶級
	 * @param beans 瀹炰綋瀵硅薄锛屽璞℃牴涓簉oot锛屽${root.brandName}锛涘鏋滄湁澶氫釜锛岃鏀惧埌map閲岋紝瀵硅薄鐨勬牴鍙互鑷畾涔�
	 * @param exportFileName 瀵煎嚭鐨勬枃浠跺悕
	 */
	@SuppressWarnings("unchecked")
	protected void exportFromXlsTemplate(HttpServletRequest request, 
			HttpServletResponse response,
			String templateFileName, 
			Object beans, 
			String exportFileName) {

		String contentType = "application/vnd.ms-excel";
		response.setContentType(contentType);
		exportFileName = exportFileName 
				+ DateUtils.DateToString(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS_SSS)
				+ ".xls";
		exportFileName = getDownloadFileName(request, response, exportFileName);

		response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName);

		XLSTransformer transformer = new XLSTransformer();

		Map<String, Object> map = new HashMap<String, Object>();
		if (beans instanceof Map) {
			map = (Map<String, Object>) beans;
		}
		else {
			map = new HashMap<String, Object>();
			map.put("root", beans);
		}

		String realpath = request.getSession().getServletContext().getRealPath("/");
		Workbook workbook;
		try {
			workbook = transformer.transformXLS(new FileInputStream(realpath + "/templates/excel/" + templateFileName),
				map);
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		}
		catch (ParsePropertyException e) {
			logger.error("there were any problems in evaluating specified property value from a bean", e);
		}
		catch (InvalidFormatException e) {
			logger.error("鏍煎紡涓嶆纭�", e);
		}
		catch (FileNotFoundException e) {
			logger.error("妯℃澘鏂囦欢涓嶅瓨鍦�", e);
		}
		catch (IOException e) {
			String s = e.getClass().getSimpleName();
			if (!s.equals("ClientAbortException")) {
				logger.error("鏍规嵁妯℃澘瀵煎嚭Excel鏃跺彂鐢熷紓甯�", e);
			}
		}
	}
	
	protected String getDownloadFileName(HttpServletRequest request, 
			HttpServletResponse response, 
			String filename) {
		String s = "";
		try {
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				s = URLEncoder.encode(filename, "UTF-8");
			}
			else {
				s = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
			}
		}
		catch (UnsupportedEncodingException e) {
			logger.error("瀵煎嚭鏂囦欢鏂囦欢鍚嶈浆鐮佹椂鍑虹幇閿欒", e);
		}
		return s;
	}
}
