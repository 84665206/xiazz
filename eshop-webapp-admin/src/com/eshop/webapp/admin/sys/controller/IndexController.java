package com.eshop.webapp.admin.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.webapp.admin.base.BaseController;
import com.eshop.webapp.admin.enums.SimpleStatusEnum;
import com.eshop.webapp.admin.sys.model.Menu;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.sys.model.UserLog;
import com.eshop.webapp.admin.sys.service.IMenuService;
import com.eshop.webapp.admin.sys.service.IUserLogService;
import com.eshop.webapp.admin.sys.service.IUserService;
import com.eshop.webapp.admin.util.IPUtil;
import com.eshop.webapp.admin.util.PasswordHash;

/**
 * 系统首页
 * @author yangmeng
 *
 */
@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserLogService userLogService;
	
	
	/**
	 * 这个方法是用户登录后第一个执行的方法，所以可以在这里做一些欢迎信息，待办事项等<br>
	 */
	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer userId = this.getCurrentUserId();
			if (userId == null) {
				//request.getRequestDispatcher("login.do").forward(request, response);
				response.sendRedirect("login.do");
				return null;
			}
			
			//获取当前用户的所有权限菜单列表
			List<Menu> myMenuList = menuService.getMenuListForUser(userId);
			model.addAttribute("myMenuList", myMenuList);
			model.addAttribute("userName", getCurrentUser().getUser_name());
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Integer userId = this.getCurrentUserId();
			if (userId != null) {
				response.sendRedirect("index.do");
				return null;
			}else {
				return "login";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/submit")
	public String submit(Model model, 
			@RequestParam(value="user_name", required=true)String user_name ,
			@RequestParam(value="password", required=true)String password ,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			UserLog userLog = new UserLog();
			if (StringUtils.isBlank(user_name)) {
				return "login";
			}
			
			if (StringUtils.isBlank(password)) {
				return "login";
			}
			
			User user= new User();
			
			//检查验证码
//			String imageCode = (String) this
//					.getSessionAttribute(SessionConstant.SESSION_IMAGE_CODE);
//			if(StringUtils.isEmpty(imageCode)){
//				this.messages = "验证码错误，请重新登录";
//				return Action.LOGIN;
//			}
			
//			if(!writeCode.toUpperCase().equals(imageCode)){
//				this.messages = "验证码输入错误";
//				return Action.LOGIN;
//			}
			
//			if ((this.user.getUserName() == null) || (this.user.getUserName().trim().length() < 1)
//					|| (this.user.getPassword() == null) || (this.user.getPassword().trim().length() < 6)) {
//				this.messages = "用户名和密码不能为空且密码不能少于6位！";
//				writeUserLog(userLog, this.user, SimpleStatusEnum.FAILED);
//				return Action.LOGIN;
//			}
			user.setUser_name(user_name.trim().toLowerCase());

			User currentUser = this.userService.getUserByUserName(user.getUser_name());
			if (currentUser == null) {
				model.addAttribute("msg", "用户信息不存在");
				//记录登陆日志
				writeUserLog(userLog, user, SimpleStatusEnum.FAILED, "用户信息不存在", request, response);
				return "login";
			}

			if (currentUser.getIs_delete() == User.IS_DELETE) {
				model.addAttribute("msg", "账号被删除，请联系系统管理员恢复账号");
				//记录登陆日志
				writeUserLog(userLog, user, SimpleStatusEnum.FAILED, "账号被删除，请联系系统管理员恢复账号", request, response);
				return "login";
			}

			if (currentUser.getIs_valid() != User.IS_VALID) {
				model.addAttribute("msg", "账号无效");
				//记录登陆日志
				writeUserLog(userLog, user, SimpleStatusEnum.FAILED, "账号无效", request, response);
				return "login";
			}

			String hashedPassword = PasswordHash.md5HashWithUsername(currentUser.getUser_name(), password);

			if (hashedPassword.equals(currentUser.getPassword())) {
				
//				if(currentUser.getPwdTerminalTime()!=null
//						&& new Date().getTime() > currentUser.getPwdTerminalTime().getTime()){
//					// 当前时间 大于 密码过期时间，需要修改密码
//					this.messages = "账户密码已过期，请设置新密码！";
//					this.uid = currentUser.getId();
//					return "isRepwd";
//				}
				
				currentUser.setPassword("********");
				//将登陆用户信息写入Session
				this.getSession().setAttribute(User.CURRENT_USER_KEY_IN_SESSION, currentUser);
				//更新用户登陆次数信息
				this.userService.updateUserLoginSum(currentUser);
				//记录登陆日志
				writeUserLog(userLog, user, SimpleStatusEnum.FAILED, "登录成功", request, response);
				
				response.sendRedirect("index.do");
			} else {
				model.addAttribute("msg", "用户名或密码错误");
				//记录登陆日志
				writeUserLog(userLog, user, SimpleStatusEnum.FAILED, "用户名或密码错误", request, response);
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request ,
			HttpServletResponse response) {
		try {
			if (this.getCurrentUser() != null) {
				this.logger.info("用户{}退出", this.getCurrentUser().getUser_name());
				this.getSession().invalidate();
			}
			response.sendRedirect(request.getContextPath()+"/");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 记录登陆日志
	 * @param userLog
	 * @param currentUser
	 */
	private void writeUserLog(UserLog userLog, 
			User currentUser, 
			SimpleStatusEnum resultEnum,
			String message,
			HttpServletRequest request,
			HttpServletResponse response) {
		userLog.setUser_name(currentUser.getUser_name());
		userLog.setUser_ip(IPUtil.getIpAddr(request));
		userLog.setLogin_result(resultEnum.value);
		userLog.setMemo(message);
		this.userLogService.insertUserLog(userLog);
	}

	/**
	 * 未认证时跳转<br>
	 */
	public String unauthorized() {
//		this.resultMap.put("unauthorized", true);
//		this.messages = "您暂时没有权限使用“" + "(" + requestUri + ")”的功能, 请联系管理员为您分配权限！";
//
//		if (this.requestedWith != null && this.requestedWith.equals(XML_HTTP_REQUEST)) {
//			logger.debug("这个请求{}是Ajax请求，header['X-Requested-With']={}", this.requestUri,  this.requestedWith);
//			if(this.ccept.startsWith(ACCEPT_TEXT_HTML)) {
//				return "unauthorized2";
//			}
//			this.addErrorMsg(messages);
//			Struts2Utils.renderJson(this.resultMap);
//			return null;
//		}
//		
		return "success";
	}
	
}
