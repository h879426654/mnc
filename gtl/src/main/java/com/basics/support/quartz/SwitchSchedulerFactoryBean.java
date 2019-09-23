package com.basics.support.quartz;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * SwitchSchedulerFactoryBean.
 * <p>
 * 扩展SchedulerFactoryBean,增加开关配置
 * </p>
 * 
 * @author zhoupan
 */
public class SwitchSchedulerFactoryBean extends SchedulerFactoryBean {

 /** The on. */
 protected boolean on;

 /**
  * Checks if is on.
  *
  * @return true, if checks if is on
  */
 public boolean isOn() {
  return on;
 }

 /**
  * Sets the on.
  *
  * @param on
  *         the on
  */
 public void setOn(boolean on) {
  this.on = on;
 }

 /*
  * (non-Javadoc)
  * 
  * @see org.springframework.scheduling.quartz.SchedulerFactoryBean#
  * afterPropertiesSet()
  */
 @Override
 public void afterPropertiesSet() throws Exception {
  if (!this.on) {
   return;
  }
  super.afterPropertiesSet();
 }

}
