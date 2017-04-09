package com.eshop.webapp.admin.sys.model;

import java.util.Date;

import com.eshop.webapp.admin.base.BaseModel;


public class User extends BaseModel {

	private static final long serialVersionUID = 1;

	public static final String CURRENT_USER_KEY_IN_SESSION = "current_user";
	public static final Integer IS_DELETE = 1;
	public static final Integer IS_VALID = 1;
	public static final int PWD_VALID_DAYS = 90; //密码过期天数

	private Integer id;

	private String user_name;

	private String password;

	// 用户姓名
	private String full_name;

	private String phone;

	private String mail;

	// 登陆次数
	private Integer login_sum;

	// 是否有效
	private Integer is_valid;

	// 最近密码修改时间
	private Date pwd_terminal_time;
	
	//用户编号
	private String user_code;
	
	//用户类型
	private Integer user_type;
	
	private Date create_date;
	
	private String create_user;
	
	private Date update_date;
	
	private String update_user;
	
	private Integer is_delete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getLogin_sum() {
		return login_sum;
	}

	public void setLogin_sum(Integer login_sum) {
		this.login_sum = login_sum;
	}

	public Integer getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
	}

	public Date getPwd_terminal_time() {
		return pwd_terminal_time;
	}

	public void setPwd_terminal_time(Date pwd_terminal_time) {
		this.pwd_terminal_time = pwd_terminal_time;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}


}