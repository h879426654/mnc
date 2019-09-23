package com.basics.cu.vo;

import java.math.BigDecimal;

import com.basics.cu.entity.CuCustomerCount;

public class CustomerCountLevelVo extends CuCustomerCount {

	private static final long serialVersionUID = -57767141679375246L;

	private BigDecimal levelSalfNum;

	private BigDecimal salfRewardRate;

	private BigDecimal teamOutRewardRate;

	private BigDecimal teamInRewardRate;
	
	private Integer levelSort;

	public BigDecimal getSalfRewardRate() {
		return salfRewardRate;
	}

	public void setSalfRewardRate(BigDecimal salfRewardRate) {
		this.salfRewardRate = salfRewardRate;
	}

	public BigDecimal getTeamOutRewardRate() {
		return teamOutRewardRate;
	}

	public void setTeamOutRewardRate(BigDecimal teamOutRewardRate) {
		this.teamOutRewardRate = teamOutRewardRate;
	}

	public BigDecimal getTeamInRewardRate() {
		return teamInRewardRate;
	}

	public void setTeamInRewardRate(BigDecimal teamInRewardRate) {
		this.teamInRewardRate = teamInRewardRate;
	}

	public BigDecimal getLevelSalfNum() {
		return levelSalfNum;
	}

	public void setLevelSalfNum(BigDecimal levelSalfNum) {
		this.levelSalfNum = levelSalfNum;
	}

	public Integer getLevelSort() {
		return levelSort;
	}

	public void setLevelSort(Integer levelSort) {
		this.levelSort = levelSort;
	}
	
}
