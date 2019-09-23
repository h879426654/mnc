package com.basics.cu.entity;

import java.util.Date;

public class CuCustomerInfoBase extends BaseBean {
	/**
	* 用户ID
	*/
	private String id;

	/**
	 * 国家ID
	 */
	private String countryId;

	/**
	* 用户账号
	*/
	private String customerNumber;

	/**
	* 用户名
	*/
	private String customerName;

	/**
	* 用户头像的路径
	*/
	private String customerHead;

	/**
	* 用户手机号
	*/
	private String customerPhone;

	/**
	* 支付宝账号
	*/
	private String customerAlipay;

	/**
	* 用户微信账号
	*/
	private String customerWechat;
	/**
	    * 邮箱
	    */
	private String customerEmail;

	/**
	* 用户真实姓名
	*/
	private String realName;

	/**
	* 身份证号
	*/
	private String customerCard;

	/**
	* 银行卡号
	*/
	private String bankCard;

	/**
	* 开户行
	*/
	private String bankName;

	/**
	* 是否实名认证(1是 0否)
	*/
	private Integer flagAuth;

	/**
	* 是否可交易(1是 0否)
	*/
	private Integer flagTrade;

	/**
	 * 是否为特殊标记(1是 0否)
	 */
	private Integer flagSpecial;

	/**
	* 用户状态 0代表冻结 1代表正常
	*/
	private Integer customerStatus;

	/**
	* 用户冻结原因
	*/
	private String customerFreezeContext;

	/**
	* 注册时间
	*/
	private Date registerTime;

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

	public Integer getFlagTrade() {
		return flagTrade;
	}

	public void setFlagTrade(Integer flagTrade) {
		this.flagTrade = flagTrade;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerHead() {
		return this.customerHead;
	}

	public void setCustomerHead(String customerHead) {
		this.customerHead = customerHead;
	}

	public String getCustomerPhone() {
		return this.customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAlipay() {
		return this.customerAlipay;
	}

	public void setCustomerAlipay(String customerAlipay) {
		this.customerAlipay = customerAlipay;
	}

	public String getCustomerWechat() {
		return this.customerWechat;
	}

	public void setCustomerWechat(String customerWechat) {
		this.customerWechat = customerWechat;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCustomerCard() {
		return this.customerCard;
	}

	public void setCustomerCard(String customerCard) {
		this.customerCard = customerCard;
	}

	public String getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getCustomerFreezeContext() {
		return this.customerFreezeContext;
	}

	public void setCustomerFreezeContext(String customerFreezeContext) {
		this.customerFreezeContext = customerFreezeContext;
	}

	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
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

	public Integer getFlagSpecial() {
		return flagSpecial;
	}

	public void setFlagSpecial(Integer flagSpecial) {
		this.flagSpecial = flagSpecial;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}