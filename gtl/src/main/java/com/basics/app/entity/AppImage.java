package com.basics.app.entity;

import org.springframework.web.multipart.MultipartFile;

import com.basics.support.CommonSupport;

/**
 * AppImage
 *
 * @author zhoupan.
 *
 */
public class AppImage extends AppImageBase {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = 1L;

 public String getCreateTimeString() {
  return this.toDatetimeString("createTime");
 }

 public void setCreateTimeString(String createTimeString) {
  this.fromDatetimeString("createTime", createTimeString);
 }

 public String getUpdateTimeString() {
  return this.toDatetimeString("updateTime");
 }

 public void setUpdateTimeString(String updateTimeString) {
  this.fromDatetimeString("updateTime", updateTimeString);
 }

 /**
  * 设置上传文件信息.
  *
  * @param file
  * @return
  */
 public AppImage attach(MultipartFile file) {
  this.setSize(file.getSize());
  return this;
 }

 private String dir;

 /**
  * 图片的最大宽度
  */
 private Integer maxWidth = 2048;

 /**
  * 图片的最大高度
  */
 private Integer maxHeight = 2048;

 /**
  * 缩略图宽度
  */
 private Integer thumbWidth = 150;

 /**
  * 缩略图高度
  */
 private Integer thumbHeight = 150;

 public String getDir() {
  return this.dir;
 }

 public void setDir(String dir) {
  this.dir = dir;
 }

 /**
  * 上传的文件.
  */
 private MultipartFile imgFile;

 public MultipartFile getImgFile() {
  return this.imgFile;
 }

 public void setImgFile(MultipartFile imgFile) {
  this.imgFile = imgFile;
 }

 public Integer getMaxWidth() {
  return this.maxWidth;
 }

 public void setMaxWidth(Integer maxWidth) {
  this.maxWidth = maxWidth;
 }

 public Integer getMaxHeight() {
  return this.maxHeight;
 }

 public void setMaxHeight(Integer maxHeight) {
  this.maxHeight = maxHeight;
 }

 public boolean needResize() {
  return this.getMaxWidth() != null && this.getMaxWidth() != null && this.getMaxWidth() > 0 && this.getMaxHeight() > 0;
 }

 public Integer getThumbWidth() {
  return this.thumbWidth;
 }

 public void setThumbWidth(Integer thumbWidth) {
  this.thumbWidth = thumbWidth;
 }

 public Integer getThumbHeight() {
  return this.thumbHeight;
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

 public void noResize() {
  this.setMaxWidth(0);
  this.setMaxHeight(0);
 }

 public String getPath() {
  return this.getUrl();
 }

 public void setPath(String path) {
  this.setUrl(path);
 }

 public String getKey() {
  return this.getId();
 }

 public void setKey(String key) {
  this.setId(key);
 }

 /**
  * 是否查看模式.
  */
 private String view = "0";

 public String getView() {
  return view;
 }

 public void setView(String view) {
  this.view = view;
 }

 /**
  * 是否多图片上传.
  */
 private String multiple = "multiple";

 public String getMultiple() {
  return multiple;
 }

 public void setMultiple(String multiple) {
  this.multiple = multiple;
 }

 public boolean multiple() {
  return CommonSupport.equalsIgnoreCase("multiple", this.getMultiple());
 }
}
