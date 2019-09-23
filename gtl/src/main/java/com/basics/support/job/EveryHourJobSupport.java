package com.basics.support.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class EveryHourJobSupport extends GenericJobSupport<EveryHourJob> {

 @Override
 public void doJob(EveryHourJob job) {
  job.doJob();
 }

}
