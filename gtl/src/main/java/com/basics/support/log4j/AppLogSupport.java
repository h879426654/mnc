package com.basics.support.log4j;

/**
 * AppLogSupport.
 *
 * @author yuwenfeng
 */
public class AppLogSupport {

 /**
  * ID.
  */
 private String id;

 /**
  * 应用ID.
  */
 private String appId;

 /**
  * 日志名称.
  */
 private String name;

 /**
  * 日志级别.
  */
 private String level;

 /**
  * 日志消息.
  */
 private String message;

 /**
  * 日志错误栈.
  */
 private String stacktrace;

 /**
  * 日志文件名称.
  */
 private String fileName;

 /**
  * 日志代码行.
  */
 private String lineNumber;

 /**
  * 日志类名.
  */
 private String className;

 /**
  * 日志方法名称.
  */
 private String methodName;

 /**
  * 创建时间.
  */
 private java.util.Date createTime;

 /**
  * 创建人.
  */
 private String createUser;

 /**
  * 更新时间.
  */
 private java.util.Date updateTime;

 /**
  * 更新人.
  */
 private String updateUser;

 /**
  * 是否可用0：否，1：是.
  */
 private Integer enabledFlag;

 /**
  * ID.
  * 
  * @return ID.
  */
 public String getId() {
  return id;
 }

 /**
  * ID.
  * 
  * @param id
  *         ID.
  */
 public void setId(String id) {
  this.id = id;
 }

 /**
  * 应用ID.
  * 
  * @return 应用ID.
  */
 public String getAppId() {
  return appId;
 }

 /**
  * 应用ID.
  * 
  * @param appId
  *         应用ID.
  */
 public void setAppId(String appId) {
  this.appId = appId;
 }

 /**
  * 日志名称.
  * 
  * @return 日志名称.
  */
 public String getName() {
  return name;
 }

 /**
  * 日志名称.
  * 
  * @param name
  *         日志名称.
  */
 public void setName(String name) {
  this.name = name;
 }

 /**
  * 日志级别.
  * 
  * @return 日志级别.
  */
 public String getLevel() {
  return level;
 }

 /**
  * 日志级别.
  * 
  * @param level
  *         日志级别.
  */
 public void setLevel(String level) {
  this.level = level;
 }

 /**
  * 日志消息.
  * 
  * @return 日志消息.
  */
 public String getMessage() {
  return message;
 }

 /**
  * 日志消息.
  * 
  * @param message
  *         日志消息.
  */
 public void setMessage(String message) {
  this.message = message;
 }

 /**
  * 日志错误栈.
  * 
  * @return 日志错误栈.
  */
 public String getStacktrace() {
  return stacktrace;
 }

 /**
  * 日志错误栈.
  * 
  * @param stacktrace
  *         日志错误栈.
  */
 public void setStacktrace(String stacktrace) {
  this.stacktrace = stacktrace;
 }

 /**
  * 日志文件名称.
  * 
  * @return 日志文件名称.
  */
 public String getFileName() {
  return fileName;
 }

 /**
  * 日志文件名称.
  * 
  * @param fileName
  *         日志文件名称.
  */
 public void setFileName(String fileName) {
  this.fileName = fileName;
 }

 /**
  * 日志代码行.
  * 
  * @return 日志代码行.
  */
 public String getLineNumber() {
  return lineNumber;
 }

 /**
  * 日志代码行.
  * 
  * @param lineNumber
  *         日志代码行.
  */
 public void setLineNumber(String lineNumber) {
  this.lineNumber = lineNumber;
 }

 /**
  * 日志类名.
  * 
  * @return 日志类名.
  */
 public String getClassName() {
  return className;
 }

 /**
  * 日志类名.
  * 
  * @param className
  *         日志类名.
  */
 public void setClassName(String className) {
  this.className = className;
 }

 /**
  * 日志方法名称.
  * 
  * @return 日志方法名称.
  */
 public String getMethodName() {
  return methodName;
 }

 /**
  * 日志方法名称.
  * 
  * @param methodName
  *         日志方法名称.
  */
 public void setMethodName(String methodName) {
  this.methodName = methodName;
 }

 /**
  * 创建时间.
  * 
  * @return 创建时间.
  */
 public java.util.Date getCreateTime() {
  return createTime;
 }

 /**
  * 创建时间.
  * 
  * @param createTime
  *         创建时间.
  */
 public void setCreateTime(java.util.Date createTime) {
  this.createTime = createTime;
 }

 /**
  * 创建人.
  * 
  * @return 创建人.
  */
 public String getCreateUser() {
  return createUser;
 }

 /**
  * 创建人.
  * 
  * @param createUser
  *         创建人.
  */
 public void setCreateUser(String createUser) {
  this.createUser = createUser;
 }

 /**
  * 更新时间.
  * 
  * @return 更新时间.
  */
 public java.util.Date getUpdateTime() {
  return updateTime;
 }

 /**
  * 更新时间.
  * 
  * @param updateTime
  *         更新时间.
  */
 public void setUpdateTime(java.util.Date updateTime) {
  this.updateTime = updateTime;
 }

 /**
  * 更新人.
  * 
  * @return 更新人.
  */
 public String getUpdateUser() {
  return updateUser;
 }

 /**
  * 更新人.
  * 
  * @param updateUser
  *         更新人.
  */
 public void setUpdateUser(String updateUser) {
  this.updateUser = updateUser;
 }

 /**
  * 是否可用0：否，1：是.
  * 
  * @return 是否可用0：否，1：是.
  */
 public Integer getEnabledFlag() {
  return enabledFlag;
 }

 /**
  * 是否可用0：否，1：是.
  * 
  * @param enabledFlag
  *         是否可用0：否，1：是.
  */
 public void setEnabledFlag(Integer enabledFlag) {
  this.enabledFlag = enabledFlag;
 }

}
