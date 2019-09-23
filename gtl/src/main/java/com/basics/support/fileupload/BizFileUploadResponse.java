package com.basics.support.fileupload;

/**
 * 文件上传结果.
 * 
 * @author yuwenfeng.
 * 
 */
public class BizFileUploadResponse implements java.io.Serializable {

 /**
  * 
  */
 private static final long serialVersionUID = 1L;

 public void onException(String message) {
  this.onException(400000, message);
 }

 private boolean success = true;

 /**
  * 返回状态
  */
 private int status = 0;

 /**
  * 错误信息
  */
 private String msg = "";

 /**
  * 文件存储的相对路径.
  */
 private String path;

 /**
  * 访问文件的http地址.
  */
 private String url;

 /**
  * @return the status
  */
 public int getStatus() {
  return status;
 }

 /**
  * @param status
  *         the status to set
  */
 public void setStatus(int status) {
  this.status = status;
 }

 /**
  * @return the msg
  */
 public String getMsg() {
  return msg;
 }

 /**
  * @param msg
  *         the msg to set
  */
 public void setMsg(String msg) {
  this.msg = msg;
 }

 /**
  * 
  * @param status
  * @param msg
  */
 public void onException(int status, String msg) {
  this.status = status;
  this.msg = msg;
  this.success = false;
 }

 public String getPath() {
  return path;
 }

 public void setPath(String path) {
  this.path = path;
 }

 public String getUrl() {
  return url;
 }

 public void setUrl(String url) {
  this.url = url;
 }

 public boolean isSuccess() {
  return success;
 }

 public void setSuccess(boolean success) {
  this.success = success;
 }

}
