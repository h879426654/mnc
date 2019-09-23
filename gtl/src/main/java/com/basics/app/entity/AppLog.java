package com.basics.app.entity;

import java.util.Date;
public class AppLog extends AppLogBase{
 /**
 * 日志ID
 */
 public AppLog id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 日志说明
 */
 public AppLog logName(String logName){
  this.setLogName(logName);
  return this;
 }
 /**
 * 日志详情
 */
 public AppLog logContext(String logContext){
  this.setLogContext(logContext);
  return this;
 }
 /**
 * 日志描述
 */
 public AppLog logRemark(String logRemark){
  this.setLogRemark(logRemark);
  return this;
 }
 /**
 * 创建时间
 */
 public AppLog logCreateDate(Date logCreateDate){
  this.setLogCreateDate(logCreateDate);
  return this;
 }
}