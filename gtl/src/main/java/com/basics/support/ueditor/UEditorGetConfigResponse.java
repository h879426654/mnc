package com.basics.support.ueditor;

/**
 * { "imageUrl":
 * "http://localhost/ueditor/php/controller.php?action=uploadimage",
 * "imagePath": "/ueditor/php/", "imageFieldName": "upfile", "imageMaxSize":
 * 2048, "imageAllowFiles": [".png", ".jpg", ".jpeg", ".gif", ".bmp"] }
 *
 * @author yuwenfeng.
 *
 */
public class UEditorGetConfigResponse extends UEditorResponse {

 private String imageActionName = "uploadimage";
 private String imagePath = "/ueditor/php/";
 private String imageFieldName = "upfile";
 private String imageUrlPrefix = "";/* 图片访问路径前缀 */
 long imageMaxSize = 10 * 1024 * 1024;// 10MB
 String[] imageAllowFiles = new String[] { ".png", ".jpg", ".jpeg", ".gif", ".bmp" };

 public String getImageActionName() {
  return imageActionName;
 }

 public void setImageActionName(String imageActionName) {
  this.imageActionName = imageActionName;
 }

 public String getImagePath() {
  return imagePath;
 }

 public void setImagePath(String imagePath) {
  this.imagePath = imagePath;
 }

 public String getImageFieldName() {
  return imageFieldName;
 }

 public void setImageFieldName(String imageFieldName) {
  this.imageFieldName = imageFieldName;
 }

 public long getImageMaxSize() {
  return imageMaxSize;
 }

 public void setImageMaxSize(long imageMaxSize) {
  this.imageMaxSize = imageMaxSize;
 }

 public String[] getImageAllowFiles() {
  return imageAllowFiles;
 }

 public void setImageAllowFiles(String[] imageAllowFiles) {
  this.imageAllowFiles = imageAllowFiles;
 }

 public String getImageUrlPrefix() {
  return imageUrlPrefix;
 }

 public void setImageUrlPrefix(String imageUrlPrefix) {
  this.imageUrlPrefix = imageUrlPrefix;
 }

}
