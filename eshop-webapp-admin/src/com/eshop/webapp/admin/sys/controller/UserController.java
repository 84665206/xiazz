package com.eshop.webapp.admin.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.webapp.admin.base.BaseController;
import com.eshop.webapp.admin.base.ResponseResult;
import com.eshop.webapp.admin.enums.ResponseResultEnum;
import com.eshop.webapp.admin.sys.model.User;
import com.eshop.webapp.admin.sys.service.IRoleService;
import com.eshop.webapp.admin.sys.service.IUserService;
import com.eshop.webapp.admin.util.PageRequest;
import com.eshop.webapp.admin.util.PageResponse;
import com.eshop.webapp.admin.util.PasswordHash;
import com.eshop.webapp.admin.util.XStreamWrapper;
@RequestMapping("/sys")
@Controller
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 进入用户信息界面
	 *
	 * @return
	 */
	@RequestMapping("user/page")
	public String userPage() {
		return "/sys/userinfo";
	}

	/**
	 * 查询用户信息
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("user/list")
	public PageResponse<User> userList(@ModelAttribute PageRequest pageRequest, 
			@RequestParam(value="user_name", required=false)String user_name,
			@RequestParam(value="full_name", required=false)String full_name,
			@RequestParam(value="phone", required=false)String phone
			) {
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(user_name)){
			condition.put("user_name", user_name);
		}
		if(StringUtils.isNotBlank(full_name)){
			condition.put("full_name", full_name);
		}
		if(StringUtils.isNotBlank(phone)){
			condition.put("phone", phone);
		}
		PageResponse<User> pageResponse = this.userService.listUserPage(pageRequest, condition);
		
        return pageResponse;
	}
	
	/**
	 * 创建用户
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("user/create")
	public ResponseResult userCreate(@RequestParam(value="user_name", required=false)String user_name,
			@RequestParam(value="full_name", required=false)String full_name,
			@RequestParam(value="phone", required=false)String phone,
			@RequestParam(value="mail", required=false)String mail,
			@RequestParam(value="is_valid", required=false)Integer is_valid,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();
		try {
			User user = new User();
			if (StringUtils.isEmpty(user_name)) {
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("用户名称不能为空");
				return responseResult;
			}
			
			String phone_valid = "0?(13[0-9]|15[012356789]|18[01236789]|14[57])[0-9]{8}";
			if (StringUtils.isNotBlank(StringUtils.trim(phone)) && !StringUtils.trim(phone).matches(phone_valid)) {
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("手机号码格式不正确");
				return responseResult;
			}
			String email = "\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+";
			if (!StringUtils.isEmpty(StringUtils.trim(mail)) && !StringUtils.trim(mail).matches(email)) {
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("邮箱格式不正确");
				return responseResult;
			}
			
			user.setPhone(phone);
			user.setIs_valid(is_valid);
			user.setFull_name(full_name);
			user.setMail(mail);
			user.setCreate_user(getCurrentUser().getUser_name());
			
			//转成小写
			if(!StringUtils.isEmpty(StringUtils.trim(user_name))) {
				user.setUser_name(StringUtils.trim(user_name).toLowerCase());
			}
			//用户名是否存在
			if(userService.getUserByUserName(StringUtils.trim(user_name))!= null){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("用户已存在");
				return responseResult;
			}
			
			this.logger.debug(XStreamWrapper.toXML(user));
			Integer itotal=this.userService.insertUserInfo(user, getCurrentUser());
			if(itotal==0){
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("用户创建失败");
				return responseResult;
			}else {
				responseResult.setCode(ResponseResultEnum.SUCCESS.code);
				responseResult.setMsg("用户创建成功！初始密码为："+user.getUser_name()+PasswordHash.defaultPassword);
				return responseResult;
			}

		}
		catch (Exception e) {
			logger.error("添加用户失败!", e);
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg(e.getMessage());
			return responseResult;
		}
	}

	/**
	 * 密码复杂性校验
	 * @param passWord
	 * @return
	 */
	public boolean checkComplexPass(String passWord) {
		if (Pattern.compile(".*\\d+.*").matcher(passWord).find()&&Pattern.compile("(?i)[a-z]").matcher(passWord).find()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 进入密码修改页面
	 */
	@RequestMapping("enterModifyPasswordPage")
	public String enterModifyPasswordPage(){
		return "sys/modifypassword";
	}

	/**
	 * 修改密码
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("modifyUserPassword")
	public ResponseResult modifyUserPassword(
			@RequestParam(value="newPassword", required=false)String newPassword,
			@RequestParam(value="rePassword", required=false)String rePassword,
			@RequestParam(value="oldPassword", required=false)String oldPassword,
			@RequestParam(value="anticache", required=false)String anticache,
			Model model
			) {
		ResponseResult responseResult =new ResponseResult();
		
		String message = "原始密码: [" + oldPassword + "] 新密码: [" + newPassword + "] 重复新密码: [" + rePassword + "]";
		this.logger.info(message);
		if (StringUtils.isBlank(newPassword)) {
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("操作失败,新密码不能为空!");
			return responseResult;
		}
		if (newPassword.length()<8) {
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("操作失败,密码不能少于8位!");
			return responseResult;
		}
		if (!checkComplexPass(newPassword)) {
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("操作失败,密码必须包含数字和字母!");
			return responseResult;
		}
		
		User user = this.userService.getUserById(this.getCurrentUserId());
		String hashedPassword = PasswordHash.md5HashWithUsername(user.getUser_name(), oldPassword);

		String newHashedPassword= PasswordHash.md5HashWithUsername(user.getUser_name(), newPassword);
		if (!hashedPassword.equals(user.getPassword())) {
			responseResult.setCode(ResponseResultEnum.FAIL.code);
			responseResult.setMsg("操作失败!</br>原始密码不正确!");
			return responseResult;
		} else {
			if (!newPassword.equals(rePassword)) {
				responseResult.setCode(ResponseResultEnum.FAIL.code);
				responseResult.setMsg("操作失败!</br>二次新密码输入不一致!");
				return responseResult;
			} else {
				user.setUpdate_date(new Date());
				user.setUpdateUser(this.getCurrentUsername());
				user.setPassword(newHashedPassword);
				if (this.userService.updateUserById(user) != 1) {
					responseResult.setCode(ResponseResultEnum.FAIL.code);
					responseResult.setMsg("操作失败!</br>数据更新出错!");
					return responseResult;
				} else {
					responseResult.setCode(ResponseResultEnum.FAIL.code);
					responseResult.setMsg("操作成功!</br>" + message);
					return responseResult;
				}

			}

		}

	}
	
}
