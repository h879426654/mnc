package com.basics.support.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class EveryMinuteJobSupport extends GenericJobSupport<EveryMinuteJob> {

 @Override
 public void doJob(EveryMinuteJob job) {
  job.doJob();
 }

}
