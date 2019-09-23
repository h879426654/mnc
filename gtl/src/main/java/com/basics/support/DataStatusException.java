package com.basics.support;

public class DataStatusException extends RuntimeException {

 /**
   *  
   */
 private static final long serialVersionUID = 1L;

 /**
  * 状态.(0表示成功;其他表示错误).
  */
 private int status = 0;

 /**
  * 成功返回空串,否则返回错误消息.
  */
 private String msg = "";

 public int getStatus() {
  return status;
 }

 public void setStatus(int status) {
  this.status = status;
 }

 public String getMsg() {
  return msg;
 }

 public void setMsg(String msg) {
  this.msg = msg;
 }

 public DataStatusException(int status, String msg) {
  super(msg);
  this.status = status;
  this.msg = msg;
 }

 public DataStatusException(String msg) {
  this(500, msg);
 }

}
