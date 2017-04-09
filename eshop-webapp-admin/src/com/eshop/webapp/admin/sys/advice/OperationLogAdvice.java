package com.eshop.webapp.admin.sys.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eshop.webapp.admin.sys.model.OperationLog;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.sys.service.IOperationLogService;
import com.google.gson.Gson;

/**
 * 
 * <p>
 * 基于配置文件的AOP日志
 * </p>
 * @since 2014-3-28
 * @version V1.0
 * @author yangmeng
 */
@Component
public class OperationLogAdvice {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IOperationLogService operationLogService;

	/**
	 * 拦截Service中不以get开头的方法（日志除外）
	 */
	public Object runOnAround(ProceedingJoinPoint point) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		HttpSession session = request.getSession();
		User userCurrent = (User) session.getAttribute(User.CURRENT_USER_KEY_IN_SESSION);

		if (userCurrent == null) {
			this.logger.error("非法操作：用户没有登录");
			return null;
		}

		OperationLog oplog = new OperationLog();

		oplog.setIpAddr(request.getHeader("X-Real-IP"));
		if (!StringUtils.isNotBlank(oplog.getIpAddr())) {
			oplog.setIpAddr(request.getRemoteAddr());
		}
		oplog.setUserId(userCurrent.getId());
		oplog.setOpModule(point.getSignature().toShortString());
		oplog.setOpPage(request.getRequestURI());

		String json = new Gson().toJson(point.getArgs());
		if (json.length() > 10000) {
			json = json.substring(0, 9997) + "...";
		}
		oplog.setOperation(json);

		Object ret = null;
		try {
			ret = point.proceed();
			oplog.setOpResult(OperationLog.OPRESULT_SUCCESSFUL);
		}
		catch (Throwable e) {
			this.logger
					.error("用户(id={})操作(method={})失败： {}", new Object[] { userCurrent.getId(), point.getThis().getClass().getClass(), e.getMessage() });
			//if (this.logger.isDebugEnabled()) {
				e.printStackTrace();
			//}
			oplog.setOpResult(OperationLog.OPRESULT_FAILURE);

			throw e;
		}
		finally {
			this.operationLogService.insert(oplog);
		}

		return ret;
	}
}