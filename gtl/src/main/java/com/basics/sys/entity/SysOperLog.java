package com.basics.sys.entity;

import java.util.Date;
public class SysOperLog extends SysOperLogBase{
 /**
 * 日志主键
 */
 public SysOperLog id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 方法名称
 */
 public SysOperLog operMethod(String operMethod){
  this.setOperMethod(operMethod);
  return this;
 }
 /**
 * 登录账号
 */
 public SysOperLog loginName(String loginName){
  this.setLoginName(loginName);
  return this;
 }
 /**
 * 主机地址
 */
 public SysOperLog operIp(String operIp){
  this.setOperIp(operIp);
  return this;
 }
 /**
 * 请求参数
 */
 public SysOperLog operParam(String operParam){
  this.setOperParam(operParam);
  return this;
 }
 /**
 * 操作状态 0正常 1异常
 */
 public SysOperLog operStatus(Integer operStatus){
  this.setOperStatus(operStatus);
  return this;
 }
 /**
 * 错误消息
 */
 public SysOperLog errorMsg(String errorMsg){
  this.setErrorMsg(errorMsg);
  return this;
 }
 /**
 * 操作时间
 */
 public SysOperLog operTime(Date operTime){
  this.setOperTime(operTime);
  return this;
 }
}