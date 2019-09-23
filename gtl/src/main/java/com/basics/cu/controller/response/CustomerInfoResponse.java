package com.basics.cu.controller.response;

import java.math.BigDecimal;

import com.basics.cu.entity.CuCustomerInfo;

public class CustomerInfoResponse extends CuCustomerInfo {

	private static final long serialVersionUID = -3058578580570630254L;

	private String customerLevelName;
	//区号
	private String countryNum;

	private Integer flagLevelAuto;

	private BigDecimal useMoney;

	private BigDecimal lockMoney;

	private BigDecimal useCoin;

	private BigDecimal lockCoin;

	private BigDecimal customerIntegral; // 积分

	private BigDecimal tradeCoin;//交易链

	private BigDecimal rateNum;//比例

	private String customerPurse;

	private String salfNum; // 直推

	private String teamNum; // 团队

	private String refereeName;

	private String refereeRealName;

	private String refereePhone;

	public String getRefereeRealName() {
		return refereeRealName;
	}

	public void setRefereeRealName(String refereeRealName) {
		this.refereeRealName = refereeRealName;
	}

	public String getRefereeName() {
		return refereeName;
	}

	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}

	public String getRefereePhone() {
		return refereePhone;
	}

	public void setRefereePhone(String refereePhone) {
		this.refereePhone = refereePhone;
	}

	public String getCustomerLevelName() {
		return customerLevelName;
	}

	public void setCustomerLevelName(String customerLevelName) {
		this.customerLevelName = customerLevelName;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getLockMoney() {
		return lockMoney;
	}

	public void setLockMoney(BigDecimal lockMoney) {
		this.lockMoney = lockMoney;
	}

	public BigDecimal getUseCoin() {
		return useCoin;
	}

	public void setUseCoin(BigDecimal useCoin) {
		this.useCoin = useCoin;
	}

	public BigDecimal getLockCoin() {
		return lockCoin;
	}

	public void setLockCoin(BigDecimal lockCoin) {
		this.lockCoin = lockCoin;
	}

	public BigDecimal getCustomerIntegral() {
		return customerIntegral;
	}

	public void setCustomerIntegral(BigDecimal customerIntegral) {
		this.customerIntegral = customerIntegral;
	}

	public String getCustomerPurse() {
		return customerPurse;
	}

	public void setCustomerPurse(String customerPurse) {
		this.customerPurse = customerPurse;
	}

	public String getSalfNum() {
		return salfNum;
	}

	public void setSalfNum(String salfNum) {
		this.salfNum = salfNum;
	}

	public String getTeamNum() {
		return teamNum;
	}

	public void setTeamNum(String teamNum) {
		this.teamNum = teamNum;
	}

	public Integer getFlagLevelAuto() {
		return flagLevelAuto;
	}

	public void setFlagLevelAuto(Integer flagLevelAuto) {
		this.flagLevelAuto = flagLevelAuto;
	}

	public String getCountryNum() {
		return countryNum;
	}

	public void setCountryNum(String countryNum) {
		this.countryNum = countryNum;
	}

	public BigDecimal getTradeCoin() {
		return tradeCoin;
	}

	public void setTradeCoin(BigDecimal tradeCoin) {
		this.tradeCoin = tradeCoin;
	}

	public BigDecimal getRateNum() {
		return rateNum;
	}

	public void setRateNum(BigDecimal rateNum) {
		this.rateNum = rateNum;
	}
}
