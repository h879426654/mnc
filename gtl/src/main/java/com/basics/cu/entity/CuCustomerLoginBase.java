package com.basics.cu.entity;

import java.util.Date;

public class CuCustomerLoginBase extends BaseBean {
	/**
	* 用户ID
	*/
	private String id;

	/**
	* 用户密码
	*/
	private String customerPassword;

	/**
	* 密码盐值
	*/
	private String passSalt;

	/**
	* 用户手机号
	*/
	private String customerPhone;

	/**
	* 是否实名认证(1是 0否)
	*/
	private Integer flagAuth;
	/**
	 * 邮箱
	 */
//	private String customerEmail;

	/**
	* 用户状态 0代表冻结 1代表正常
	*/
	private Integer customerStatus;

	/**
	* 登录错误次数
	*/
	private Integer loginErrorNum;

	/**
	* 最后登录时间
	*/
	private Date lastLoginTime;

	/**
	* 版本号
	*/
	private Integer versionNum;

	/**
	* 是否删除（1是 0否）
	*/
	private Integer flagDel;

	/**
	* 创建时间
	*/
	private Date createTime;

	/**
	* 创建人
	*/
	private String createUser;

	/**
	* 修改人
	*/
	private String modifyUser;

	/**
	* 修改时间
	*/
	private Date modifyTime;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerPassword() {
		return this.customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getPassSalt() {
		return this.passSalt;
	}

	public void setPassSalt(String passSalt) {
		this.passSalt = passSalt;
	}

	public String getCustomerPhone() {
		return this.customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Integer getFlagAuth() {
		return this.flagAuth;
	}

	public void setFlagAuth(Integer flagAuth) {
		this.flagAuth = flagAuth;
	}

	public Integer getCustomerStatus() {
		return this.customerStatus;
	}

	public void setCustomerStatus(Integer customerStatus) {
		this.customerStatus = customerStatus;
	}

	public Integer getLoginErrorNum() {
		return this.loginErrorNum;
	}

	public void setLoginErrorNum(Integer loginErrorNum) {
		this.loginErrorNum = loginErrorNum;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public Integer getFlagDel() {
		return this.flagDel;
	}

	public void setFlagDel(Integer flagDel) {
		this.flagDel = flagDel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

//	public String getCustomerEmail() {
//		return customerEmail;
//	}
//
//	public void setCustomerEmail(String customerEmail) {
//		this.customerEmail = customerEmail;
//	}
}