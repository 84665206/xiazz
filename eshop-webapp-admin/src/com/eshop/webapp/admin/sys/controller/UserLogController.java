package com.eshop.webapp.admin.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.admin.base.BaseController;
import com.eshop.webapp.admin.sys.model.UserLog;
import com.eshop.webapp.admin.sys.service.IUserLogService;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;

/**
 * 用户日志
 *
 * @author yangmeng
 *
 */
@Controller
@RequestMapping("sys")
public class UserLogController extends BaseController {
	@Autowired
	private IUserLogService userLogService;
	/**
	 * 进入日志查询页面
	 *
	 * @return
	 */
	@RequestMapping("log/page")
	public String logPage() {
		return "sys/userLogList";
	}
	
	@ResponseBody
	@RequestMapping("log/list")
	public PageResponse<UserLog> listUserLog(@ModelAttribute PageRequest pageRequest,String userName,Date beginDate,Date endDate) {
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userName)) {
			condition.put("userName", userName);
		}
		if (beginDate != null) {
			condition.put("beginDate", beginDate);
		}
		if (endDate != null) {
			condition.put("endDate", endDate);
		}
		// 日期相反时,做取反处理
		if ((beginDate != null) && (endDate != null)&& (beginDate.after(endDate))) {
			condition.put("beginDate", endDate);
			condition.put("endDate", beginDate);
		}
		PageResponse<UserLog> pageResponse = this.userLogService.listUserLogPage(pageRequest,condition);

		return pageResponse;
	}
}
