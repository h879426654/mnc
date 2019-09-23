package com.basics.support.fileupload;

import org.springframework.web.multipart.MultipartFile;

public class KindEditorFileUploadRequest {

 private String dir;

 /**
  * 图片的最大宽度
  */
 private Integer maxWidth = 640;

 /**
  * 图片的最大高度
  */
 private Integer maxHeight = 960;

 /**
  * 缩略图宽度
  */
 private Integer thumbWidth = 150;

 /**
  * 缩略图高度
  */
 private Integer thumbHeight = 150;

 /**
  * 业务集成id(为图片集/附件集提供支持).
  */
 private String bizSetId;

 public String getDir() {
  return dir;
 }

 public void setDir(String dir) {
  this.dir = dir;
 }

 /**
  * 上传的文件.
  */
 private MultipartFile imgFile;

 public MultipartFile getImgFile() {
  return imgFile;
 }

 public void setImgFile(MultipartFile imgFile) {
  this.imgFile = imgFile;
 }

 public Integer getMaxWidth() {
  return maxWidth;
 }

 public void setMaxWidth(Integer maxWidth) {
  this.maxWidth = maxWidth;
 }

 public Integer getMaxHeight() {
  return maxHeight;
 }

 public void setMaxHeight(Integer maxHeight) {
  this.maxHeight = maxHeight;
 }

 public boolean needResize() {
  return this.getMaxWidth() != null && this.getMaxWidth() != null && this.getMaxWidth() > 0 && this.getMaxHeight() > 0;
 }

 public Integer getThumbWidth() {
  return thumbWidth;
 }

 public void setThumbWidth(Integer thumbWidth) {
  this.thumbWidth = thumbWidth;
 }

 public Integer getThumbHeight() {
  return thumbHeight;
 }

 public void setThumbHeight(Integer thumbHeight) {
  this.thumbHeight = thumbHeight;
 }

 public boolean needThumb() {
  return this.getThumbWidth() != null && this.getThumbWidth() > 0 && this.getThumbHeight() != null && this.getThumbHeight() > 0;
 }

 public void noThumb() {
  this.setThumbHeight(0);
  this.setThumbWidth(0);
 }

 public String getBizSetId() {
  return bizSetId;
 }

 public void setBizSetId(String bizSetId) {
  this.bizSetId = bizSetId;
 }

 /**
  * 内容id.
  */
 private String contentId;

 public String getContentId() {
  return contentId;
 }

 public void setContentId(String contentId) {
  this.contentId = contentId;
 }

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
}
