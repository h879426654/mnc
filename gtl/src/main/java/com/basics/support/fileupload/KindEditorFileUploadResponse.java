package com.basics.support.fileupload;

public class KindEditorFileUploadResponse {

 /**
  * 错误代码.
  */
 private int error = 0;

 /**
  * 错误消息.
  */
 private String message = "";

 /**
  * 外网地址.
  */
 private String url;

 /**
  * 外网缩略图地址.
  */
 private String thumbUrl;

 /**
  * 文件存储相对路径.
  */
 private String path;

 /**
  * 缩略图路径.
  */
 private String thumbPath;

 /**
  * 文件详细信息(CmsFile id)
  */
 private String fileId;

 public String getFileId() {
  return fileId;
 }

 public void setFileId(String fileId) {
  this.fileId = fileId;
 }

 public String getPath() {
  return path;
 }

 public void setPath(String path) {
  this.path = path;
 }

 public String getThumbPath() {
  return thumbPath;
 }

 public void setThumbPath(String thumbPath) {
  this.thumbPath = thumbPath;
 }

 public int getError() {
  return error;
 }

 public void setError(int error) {
  this.error = error;
 }

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

 public String getUrl() {
  return url;
 }

 public void setUrl(String url) {
  this.url = url;
 }

 public void onException(int error, String message) {
  this.error = error;
  this.message = message;
 }

 public void onException(Throwable e) {
  this.onException(1, e.getMessage());
 }

 public boolean isSuccess() {
  return this.getError() == 0;
 }

 public String getThumbUrl() {
  return thumbUrl;
 }

 public void setThumbUrl(String thumbUrl) {
  this.thumbUrl = thumbUrl;
 }

}
