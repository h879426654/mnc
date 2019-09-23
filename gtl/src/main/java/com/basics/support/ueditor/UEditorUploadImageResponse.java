package com.basics.support.ueditor;

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
public class UEditorUploadImageResponse extends UEditorResponse {

 private String urlDelete;
 private String url;
 private String title;
 private String original;
 private String id;

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public String getUrlDelete() {
  return urlDelete;
 }

 public void setUrlDelete(String urlDelete) {
  this.urlDelete = urlDelete;
 }

 public String getUrl() {
  return url;
 }

 public void setUrl(String url) {
  this.url = url;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getOriginal() {
  return original;
 }

 public void setOriginal(String original) {
  this.original = original;
 }

}
