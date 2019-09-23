package com.basics.support.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class EveryDayJobSupport extends GenericJobSupport<EveryDayJob> {

 @Override
 public void doJob(EveryDayJob job) {
  job.doJob();
 }

}
