package com.basics.support.ueditor;

import org.springframework.web.multipart.MultipartFile;

/**
 * 2. uploadimage
 * 
 * 请求参数:
 * 
 * GET {"action": "uploadimage"} POST "upfile": File Data
 * 
 * 返回格式:
 * 
 * { "state": "SUCCESS", "url": "upload/demo.jpg", "title": "demo.jpg",
 * "original": "demo.jpg" }
 * 
 * @author yuwenfeng.
 *
 */
public class UEditorUploadImageRequest {

 /**
  * 上传的文件.
  */
 private MultipartFile upfile;

 public MultipartFile getUpfile() {
  return upfile;
 }

 public void setUpfile(MultipartFile upfile) {
  this.upfile = upfile;
 }

 private String mainId;

 public String getMainId() {
  return mainId;
 }

 public void setMainId(String mainId) {
  this.mainId = mainId;
 }

}
