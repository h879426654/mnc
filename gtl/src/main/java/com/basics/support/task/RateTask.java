package com.basics.support.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.basics.common.BaseApiService;

@Component
public class RateTask {
	@Autowired
	private BaseApiService baseApiService;

	@Scheduled(cron = "0 0/30 * * * ? ")
	public void doTask() {
		// apiService.doClearTask();
	}
}
