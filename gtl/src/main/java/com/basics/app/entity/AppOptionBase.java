package com.basics.app.entity;

/**
 * AppOption
 *
 * @author yuwenfeng.
 *
 */
public class AppOptionBase extends BaseBean {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = -1L;

 /**
  * 选项ID.
  */
 private String id;

 /**
  * 上级选项ID.
  */
 private String parentId;

 /**
  * 选项标识.
  */
 private String code;

 /**
  * 选项名称.
  */
 private String name;

 /**
  * 选项描述.
  */
 private String comment;

 /**
  * 选项排序.
  */
 private Integer order;

 /**
  * 选项图标.
  */
 private String icon;

 /**
  * 选项URL.
  */
 private String url;

 /**
  * 选项类型 .
  */
 private Integer type;

 /**
  * 选项状态 0启用 1停用.
  */
 private Integer flag;

 /**
  * 选项ID.
  * 
  * @return 选项ID.
  */
 public String getId() {
  return this.id;
 }

 /**
  * 选项ID.
  * 
  * @param id
  *         选项ID.
  */
 public void setId(String id) {
  this.id = id;
 }

 /**
  * 上级选项ID.
  * 
  * @return 上级选项ID.
  */
 public String getParentId() {
  return this.parentId;
 }

 /**
  * 上级选项ID.
  * 
  * @param parentId
  *         上级选项ID.
  */
 public void setParentId(String parentId) {
  this.parentId = parentId;
 }

 /**
  * 选项标识.
  * 
  * @return 选项标识.
  */
 public String getCode() {
  return this.code;
 }

 /**
  * 选项标识.
  * 
  * @param code
  *         选项标识.
  */
 public void setCode(String code) {
  this.code = code;
 }

 /**
  * 选项名称.
  * 
  * @return 选项名称.
  */
 public String getName() {
  return this.name;
 }

 /**
  * 选项名称.
  * 
  * @param name
  *         选项名称.
  */
 public void setName(String name) {
  this.name = name;
 }

 /**
  * 选项描述.
  * 
  * @return 选项描述.
  */
 public String getComment() {
  return this.comment;
 }

 /**
  * 选项描述.
  * 
  * @param comment
  *         选项描述.
  */
 public void setComment(String comment) {
  this.comment = comment;
 }

 /**
  * 选项排序.
  * 
  * @return 选项排序.
  */
 public Integer getOrder() {
  return this.order;
 }

 /**
  * 选项排序.
  * 
  * @param order
  *         选项排序.
  */
 public void setOrder(Integer order) {
  this.order = order;
 }

 /**
  * 选项图标.
  * 
  * @return 选项图标.
  */
 public String getIcon() {
  return this.icon;
 }

 /**
  * 选项图标.
  * 
  * @param icon
  *         选项图标.
  */
 public void setIcon(String icon) {
  this.icon = icon;
 }

 /**
  * 选项URL.
  * 
  * @return 选项URL.
  */
 public String getUrl() {
  return this.url;
 }

 /**
  * 选项URL.
  * 
  * @param url
  *         选项URL.
  */
 public void setUrl(String url) {
  this.url = url;
 }

 /**
  * 选项类型 .
  * 
  * @return 选项类型 .
  */
 public Integer getType() {
  return this.type;
 }

 /**
  * 选项类型 .
  * 
  * @param type
  *         选项类型 .
  */
 public void setType(Integer type) {
  this.type = type;
 }

 /**
  * 选项状态 0启用 1停用.
  * 
  * @return 选项状态 0启用 1停用.
  */
 public Integer getFlag() {
  return this.flag;
 }

 /**
  * 选项状态 0启用 1停用.
  * 
  * @param flag
  *         选项状态 0启用 1停用.
  */
 public void setFlag(Integer flag) {
  this.flag = flag;
 }

}
