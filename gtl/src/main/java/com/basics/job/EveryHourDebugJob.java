package com.basics.job;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.basics.support.LogUtils;
import com.basics.support.job.EveryHourJob;

@Component
public class EveryHourDebugJob implements EveryHourJob {

	@Override
	public void doJob() {
		LogUtils.performance.info("{} begin at {}", this.getClass().getName(), new Date());
		LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
	}

}
