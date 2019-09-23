package com.basics.app.entity;

/**
 * AppImage
 *
 * @author zhoupan.
 *
 */
public class AppImageBase extends BaseBean {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = -1L;

 /**
  * 图片ID.
  */
 private String id;

 /**
  * 拥有者CLASS.
  */
 private String ownerClass;

 /**
  * 拥有者ID.
  */
 private String ownerId;

 /**
  * 图片地址.
  */
 private String url;

 /**
  * 缩略图地址.
  */
 private String thumbnailUrl;

 /**
  * 图片大小.
  */
 private Long size;

 /**
  * 图片排序.
  */
 private Integer order;

 /**
  * 图片名称.
  */
 private String name;

 /**
  * 图片ID.
  *
  * @return 图片ID.
  */
 public String getId() {
  return this.id;
 }

 /**
  * 图片ID.
  *
  * @param id
  *         图片ID.
  */
 public void setId(String id) {
  this.id = id;
 }

 /**
  * 拥有者CLASS.
  *
  * @return 拥有者CLASS.
  */
 public String getOwnerClass() {
  return this.ownerClass;
 }

 /**
  * 拥有者CLASS.
  *
  * @param ownerClass
  *         拥有者CLASS.
  */
 public void setOwnerClass(String ownerClass) {
  this.ownerClass = ownerClass;
 }

 /**
  * 拥有者ID.
  *
  * @return 拥有者ID.
  */
 public String getOwnerId() {
  return this.ownerId;
 }

 /**
  * 拥有者ID.
  *
  * @param ownerId
  *         拥有者ID.
  */
 public void setOwnerId(String ownerId) {
  this.ownerId = ownerId;
 }

 /**
  * 图片地址.
  *
  * @return 图片地址.
  */
 public String getUrl() {
  return this.url;
 }

 /**
  * 图片地址.
  *
  * @param url
  *         图片地址.
  */
 public void setUrl(String url) {
  this.url = url;
 }

 /**
  * 缩略图地址.
  *
  * @return 缩略图地址.
  */
 public String getThumbnailUrl() {
  return this.thumbnailUrl;
 }

 /**
  * 缩略图地址.
  *
  * @param thumbnailUrl
  *         缩略图地址.
  */
 public void setThumbnailUrl(String thumbnailUrl) {
  this.thumbnailUrl = thumbnailUrl;
 }

 /**
  * 图片大小.
  *
  * @return 图片大小.
  */
 public Long getSize() {
  return this.size;
 }

 /**
  * 图片大小.
  *
  * @param size
  *         图片大小.
  */
 public void setSize(Long size) {
  this.size = size;
 }

 /**
  * 图片排序.
  *
  * @return 图片排序.
  */
 public Integer getOrder() {
  return this.order;
 }

 /**
  * 图片排序.
  *
  * @param order
  *         图片排序.
  */
 public void setOrder(Integer order) {
  this.order = order;
 }

 /**
  * 图片名称.
  *
  * @return 图片名称.
  */
 public String getName() {
  return this.name;
 }

 /**
  * 图片名称.
  *
  * @param name
  *         图片名称.
  */
 public void setName(String name) {
  this.name = name;
 }

}
