package com.basics.support.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.basics.support.Application;
import com.basics.support.GenericUtils;
import com.basics.support.LogUtils;

/**
 * 
 *
 * @param <T>
 *         the generic type
 */
public abstract class GenericJobSupport<T> implements Job {

	/** 实体类类型(由构造方法自动赋值). */
	protected Class<T> jobSupportClass;

	/** The jobs. */
	protected List<T> jobs;

	/**
	 * The Constructor.
	 */
	public GenericJobSupport() {
		jobSupportClass = GenericUtils.getSuperClassGenericType(super.getClass());
	}

	/**
	 * Do job.
	 */
	public void doJob() {
		// 等待spring容器初始化.
		if (Application.getInstance().getContainer() == null) {
			LogUtils.performance.warn("Application.getInstance().getContainer() not initialized .doJob ignored");
			return;
		}
		// 从spring容器获取job实现类.
		this.jobs = Application.getInstance().getServices(jobSupportClass);
		if (jobs == null || jobs.isEmpty()) {
			LogUtils.performance.warn("getServices({}) no jobs found.", this.jobSupportClass);
			return;
		}
		// 依次执行job.
		for (T job : jobs) {
			try {
				this.doJob(job);
			} catch (Throwable e) {
				LogUtils.performance.error("doJob({}) eror:{}", job.getClass(), e);
			}
		}
	}

	public abstract void doJob(T job);

	public void execute(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		this.doJob();
	}
}
