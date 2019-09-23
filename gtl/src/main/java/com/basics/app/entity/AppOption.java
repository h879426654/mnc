package com.basics.app.entity;

import org.apache.commons.lang.StringUtils;

/**
 * AppOption
 *
 * @author yuwenfeng.
 *
 */
public class AppOption extends AppOptionBase {

 public static final String ICON_DEFAULT = "icon-folder";

 protected boolean hasChildren;

 public boolean isHasChildren() {
  this.hasChildren = this.getType() != null && TYPE_OPTION_GROUP == this.getType();
  return this.hasChildren;
 }

 public void setHasChildren(boolean hasChildren) {
  this.hasChildren = hasChildren;
 }

 public String getIconCls() {
  if (StringUtils.isBlank(super.getIcon())) {
   return AppOption.ICON_DEFAULT;
  }
  return this.getIcon();
 }

 public void setIconCls(String iconCls) {
  this.setIcon(iconCls);
 }

 /**
  * 分组.
  */
 public static final int TYPE_OPTION_GROUP = 0;
 /**
  * 分组参数.
  */
 public static final int TYPE_OPTION_ITEM = 1;

 public static final String DEFAULT_PARENT = "0";

 public AppOption() {
  super();
 }

 public AppOption buildChildOption(String code, String name, int order) {
  AppOption option = new AppOption();
  option.parentId(this.getId()).id(this.getId() + "_" + code).code(code).name(name).enable(true).type(AppOption.TYPE_OPTION_ITEM)
   .order(order);
  return option;
 }

 public AppOption type(boolean isGroup) {
  return this.type(isGroup ? AppOption.TYPE_OPTION_GROUP : AppOption.TYPE_OPTION_ITEM);
 }

 /**
  *
  * @param enable
  * @return
  */
 public AppOption enable(boolean enable) {
  return this.flag(enable ? 1 : 0);
 }

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = 1L;

 /**
  * 选项ID.
  *
  * @param id
  *         选项ID.
  */
 public AppOption id(String id) {
  this.setId(id);
  return this;
 }

 /**
  * 上级选项ID.
  *
  * @param parentId
  *         上级选项ID.
  */
 public AppOption parentId(String parentId) {
  this.setParentId(parentId);
  return this;
 }

 /**
  * 选项标识.
  *
  * @param code
  *         选项标识.
  */
 public AppOption code(String code) {
  this.setCode(code);
  return this;
 }

 /**
  * 选项名称.
  *
  * @param name
  *         选项名称.
  */
 public AppOption name(String name) {
  this.setName(name);
  return this;
 }

 /**
  * 选项描述.
  *
  * @param comment
  *         选项描述.
  */
 public AppOption comment(String comment) {
  this.setComment(comment);
  return this;
 }

 /**
  * 选项排序.
  *
  * @param order
  *         选项排序.
  */
 public AppOption order(Integer order) {
  this.setOrder(order);
  return this;
 }

 /**
  * 选项图标.
  *
  * @param icon
  *         选项图标.
  */
 public AppOption icon(String icon) {
  this.setIcon(icon);
  return this;
 }

 /**
  * 选项URL.
  *
  * @param url
  *         选项URL.
  */
 public AppOption url(String url) {
  this.setUrl(url);
  return this;
 }

 /**
  * 选项类型 .
  *
  * @param type
  *         选项类型 .
  */
 public AppOption type(Integer type) {
  this.setType(type);
  return this;
 }

 /**
  * 选项状态 0启用 1停用.
  *
  * @param flag
  *         选项状态 0启用 1停用.
  */
 public AppOption flag(Integer flag) {
  this.setFlag(flag);
  return this;
 }

}
