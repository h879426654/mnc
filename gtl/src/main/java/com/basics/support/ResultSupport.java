package com.basics.support;

/**
 * 操作结果.
 * 
 * @author yuwenfeng.
 * 
 */
public class ResultSupport implements java.io.Serializable {

 /**
 * 
 */
 private static final long serialVersionUID = 1L;

 /**
  * 是否成功.
  */
 private boolean success = true;

 /**
  * 成功、错误消息.
  */
 private String message;

 public boolean isSuccess() {
  return success;
 }

 public void setSuccess(boolean success) {
  this.success = success;
 }

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

 /**
  * 设置异常结果.
  * 
  * @param e
  *         异常.
  */
 public void onException(Throwable e) {
  this.setSuccess(false);
  this.setMessage(e.getMessage());
 }

 /**
  * 设置异常结果.
  * 
  * @param message
  *         异常消息.
  */
 public void onException(String message) {
  this.setSuccess(false);
  this.setMessage(message);
 }

 /**
  * 设置成功结果.
  * 
  * @param message
  *         成功消息.
  */
 public void onSuccess(String message) {
  this.setSuccess(true);
  this.setMessage(message);
 }
}
