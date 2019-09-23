package com.basics.cu.controller.response;

import java.io.Serializable;

public class TurntableResponse implements Serializable {

	private static final long serialVersionUID = -6937722980573332071L;

	/**
	* 奖励类型(1余额 2积分 3链)
	*/
	private Integer rewardType;

	/**
	* 奖励数目
	*/
	private Integer rewardNum;

	public Integer getRewardType() {
		return rewardType;
	}

	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}

	public Integer getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(Integer rewardNum) {
		this.rewardNum = rewardNum;
	}

}
