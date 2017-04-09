package com.eshop.webapp.admin.base;


/**
 * 简化的用户信息<BR/>
 * 用于存储在Session中
 * 
 * @author Lixz
 * @version 1.0
 * @since 2012-2-10
 * 
 */
public class SessionUser extends BaseModel {
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String userName;
	private String userTrueName;

	/**
	 * 用户等级id
	 */
	private Integer userLevelId;

	/**
	 * 会员等级的名称
	 */
	private String userLevelName;
	/**
	 * 会员等级的级别
	 */
	private Integer userLevelIndex;

	/** getter **/
	public Integer getUserLevelId() {
		return userLevelId;
	}

	/** setter **/
	public void setUserLevelId(Integer userLevelId) {
		this.userLevelId = userLevelId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTrueName() {
		return userTrueName;
	}

	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}

	public String getUserLevelName() {
		return userLevelName;
	}

	public void setUserLevelName(String userLevelName) {
		this.userLevelName = userLevelName;
	}

	public Integer getUserLevelIndex() {
		return userLevelIndex;
	}

	public void setUserLevelIndex(Integer userLevelIndex) {
		this.userLevelIndex = userLevelIndex;
	}
}
