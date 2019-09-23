package com.basics.support.fileupload;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传请求.
 * 
 * @author yuwenfeng.
 * 
 */
public class BizFileUploadRequest implements java.io.Serializable {

 /**
  * 
  */
 private static final long serialVersionUID = 1L;

 /**
  * 文件路径.
  */
 private String path;

 public String getPath() {
  return path;
 }

 public void setPath(String path) {
  this.path = path;
 }

 /**
  * 业务类型.
  */
 private String bizClass;

 public String getBizClass() {
  return bizClass;
 }

 public void setBizClass(String bizClass) {
  this.bizClass = bizClass;
 }

 /**
  * 上传的文件.
  */
 private MultipartFile file;

 public MultipartFile getFile() {
  return file;
 }

 public void setFile(MultipartFile file) {
  this.file = file;
 }

 /**
  * 商会.
  */
 private Long corceralId;

 public Long getCorceralId() {
  return corceralId;
 }

 public void setCorceralId(Long corceralId) {
  this.corceralId = corceralId;
 }
}
