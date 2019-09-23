package com.basics.support.reward;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;

import com.basics.sys.entity.SysTurntable;

public class RewardUtil {

	/**
	 * 获取中奖结果
	 */
	public static SysTurntable generateReward(List<SysTurntable> list) {
		int num = generateRewardNum(list);
		SysTurntable info = selectReward(list, num);
		if (null != info) {
			return info;
		} else {
			return generateReward(list);
		}
	}

	/**
	 * 根据奖励配置返回中奖结果，要求配置的中奖比例大于等于万分之一
	 */
	public static int generateRewardNum(List<SysTurntable> list) {
		BigDecimal totalNum = BigDecimal.ZERO;
		if (CollectionUtils.isNotEmpty(list)) {
			for (SysTurntable turntable : list) {
				totalNum = totalNum.add(turntable.getRewardRate());
			}
			int total = totalNum.multiply(new BigDecimal(10000)).intValue();
			Random random = new Random();
			return 1 + random.nextInt(total);
		}
		return -1;
	}

	/**
	 * 根据中奖值获取对应的奖励
	 */
	public static SysTurntable selectReward(List<SysTurntable> list, int rewardNum) {
		if (CollectionUtils.isNotEmpty(list)) {
			BigDecimal initNum = BigDecimal.ZERO;
			for (SysTurntable turntable : list) {
				if (turntable.getRewardRate().doubleValue() > 0) {
					if (rewardNum > initNum.doubleValue() && rewardNum <= initNum.add(turntable.getRewardRate().multiply(new BigDecimal(10000))).doubleValue()) {
						return turntable;
					}
					initNum = initNum.add(turntable.getRewardRate().multiply(new BigDecimal(10000)));
				}
			}
		}
		return null;
	}
}
