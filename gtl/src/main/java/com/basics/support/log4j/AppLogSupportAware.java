package com.basics.support.log4j;

/**
 * AppLogSupportAware.
 * 
 * @author yuwenfeng
 */
public interface AppLogSupportAware {

 /**
  * Do append.
  *
  * @param appLogSupportList
  *         the app log support list
  */
 public void doAppend(AppLogSupport... appLogSupportList);
}
