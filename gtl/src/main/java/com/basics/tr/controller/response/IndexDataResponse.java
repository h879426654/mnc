package com.basics.tr.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.basics.tr.entity.TrConvert;

public class IndexDataResponse implements Serializable{

	private static final long serialVersionUID = -8524092468718698983L;

	private TrConvert convert;
	
	private BigDecimal dollarRate;
	
	private BigDecimal moneyRate;
	
	private BigDecimal useMoney;
	
	private BigDecimal useCoin;
	
	private BigDecimal customerIntegral;
	
	private BigDecimal rateToIntegralMore;
	
	private String customerPurse;
	
	private BigDecimal ratioRate;
	
	private BigDecimal lockMoney;
	
	private BigDecimal lockCoin;
	
	private BigDecimal discountNum; //折扣
	
	private BigDecimal tradeRate; //交易手续费
	
	private BigDecimal yesterdayStaitcIntegral; //昨天释放金额
	
	private BigDecimal rewardNum; //抽奖金额
	
	private Integer signRewardNum; //签到奖励
	
	private Integer needUploadLicence;
	
	private Map<String,Object> tables = new HashMap<>();
	

	public BigDecimal getYesterdayStaitcIntegral() {
		return yesterdayStaitcIntegral;
	}

	public void setYesterdayStaitcIntegral(BigDecimal yesterdayStaitcIntegral) {
		this.yesterdayStaitcIntegral = yesterdayStaitcIntegral;
	}

	public Integer getSignRewardNum() {
		return signRewardNum;
	}

	public void setSignRewardNum(Integer signRewardNum) {
		this.signRewardNum = signRewardNum;
	}

	public Integer getNeedUploadLicence() {
		return needUploadLicence;
	}

	public void setNeedUploadLicence(Integer needUploadLicence) {
		this.needUploadLicence = needUploadLicence;
	}

	public BigDecimal getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}

	public BigDecimal getDiscountNum() {
		return discountNum;
	}

	public void setDiscountNum(BigDecimal discountNum) {
		this.discountNum = discountNum;
	}

	public BigDecimal getTradeRate() {
		return tradeRate;
	}

	public void setTradeRate(BigDecimal tradeRate) {
		this.tradeRate = tradeRate;
	}

	public Map<String, Object> getTables() {
		return tables;
	}

	public void setTables(Map<String, Object> tables) {
		this.tables = tables;
	}

	public BigDecimal getRatioRate() {
		return ratioRate;
	}

	public void setRatioRate(BigDecimal ratioRate) {
		this.ratioRate = ratioRate;
	}

	public String getCustomerPurse() {
		return customerPurse;
	}

	public void setCustomerPurse(String customerPurse) {
		this.customerPurse = customerPurse;
	}

	public BigDecimal getRateToIntegralMore() {
		return rateToIntegralMore;
	}

	public void setRateToIntegralMore(BigDecimal rateToIntegralMore) {
		this.rateToIntegralMore = rateToIntegralMore;
	}

	public BigDecimal getCustomerIntegral() {
		return customerIntegral;
	}

	public void setCustomerIntegral(BigDecimal customerIntegral) {
		this.customerIntegral = customerIntegral;
	}

	public BigDecimal getDollarRate() {
		return dollarRate;
	}

	public void setDollarRate(BigDecimal dollarRate) {
		this.dollarRate = dollarRate;
	}
	
	public BigDecimal getUseCoin() {
		return useCoin;
	}

	public void setUseCoin(BigDecimal useCoin) {
		this.useCoin = useCoin;
	}

	public BigDecimal getLockMoney() {
		return lockMoney;
	}

	public void setLockMoney(BigDecimal lockMoney) {
		this.lockMoney = lockMoney;
	}

	public BigDecimal getLockCoin() {
		return lockCoin;
	}

	public void setLockCoin(BigDecimal lockCoin) {
		this.lockCoin = lockCoin;
	}

	public TrConvert getConvert() {
		return convert;
	}

	public void setConvert(TrConvert convert) {
		this.convert = convert;
	}

	public BigDecimal getMoneyRate() {
		return moneyRate;
	}

	public void setMoneyRate(BigDecimal moneyRate) {
		this.moneyRate = moneyRate;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	
	
	
	
	
}
