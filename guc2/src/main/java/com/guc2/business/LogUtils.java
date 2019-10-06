package com.guc2.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 借用平台Loggers配置,提供slf4j实现.
 * 
 * @author yuwenfeng.
 * 
 */
public class LogUtils {
 public static final Logger performance = LoggerFactory.getLogger("performance");

 public static final Logger business = LoggerFactory.getLogger("business");

 public static final Logger security = LoggerFactory.getLogger("security");

 public static void main(String[] args) {
  performance.debug("LogUtils.debug");
  performance.info("LogUtils.info");
  performance.error("LogUtils.error");
 }
}