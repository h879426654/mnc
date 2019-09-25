package com.basics.job;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.basics.support.LogUtils;
import com.basics.support.job.EveryMinuteJob;

@Component
public class EveryMinuteDebugJob implements EveryMinuteJob {

	@Override
	public void doJob() {
		LogUtils.performance.info("{} begin at {}", this.getClass().getName(), new Date());
		LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
		System.out.println("EveryMinuteDebugJob"+System.currentTimeMillis()/1000);


	}
}
