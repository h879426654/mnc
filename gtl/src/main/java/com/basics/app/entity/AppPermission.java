package com.basics.app.entity;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * AppPermission
 *
 * @author yuwenfeng.
 *
 */
public class AppPermission extends AppPermissionBase {

 public static final String ICON_DEFAULT = "icon-folder";

 public String getStateString() {
  if (this.getState() == null) {
   return "";
  } else {
   return this.getState().toString();
  }
 }

 public void setStateString(String value) {
  if (NumberUtils.isDigits(value)) {
   this.setState(Integer.valueOf(value));
  }
 }

 @JSONField(name = "_parentId")
 public Object get_parentId() {
  String pid = this.getParentId();
  if (StringUtils.isBlank(pid) || StringUtils.equals("0", pid)) {
   return 0;
  } else {
   return pid;
  }
 }

 @JSONField(name = "_parentId")
 public void set_parentId(String _parentId) {
  this.setParentId(_parentId);
 }

 public String getIconCls() {
  if (StringUtils.isBlank(super.getIcon())) {
   return AppPermission.ICON_DEFAULT;
  }
  return this.getIcon();
 }

 public void setIconCls(String iconCls) {
  this.setIcon(iconCls);
 }

 // 权限类型 0=系统 1=目录 2=菜单 3=按钮.
 public static final String ROOT_ID = "0";
 /**
  * 系统
  */
 public static final int TYPE_SYSTEM = 0;
 /**
  * 目录
  */
 public static final int TYPE_DIR = 1;
 /**
  * 菜单
  */
 public static final int TYPE_MENU = 2;
 /**
  * 按钮
  */
 public static final int TYPE_BUTTON = 3;

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = 1L;

 /**
  * 权限ID.
  *
  * @param id
  *         权限ID.
  */
 public AppPermission id(String id) {
  this.setId(id);
  return this;
 }

 /**
  * 上级权限ID.
  *
  * @param parentId
  *         上级权限ID.
  */
 public AppPermission parentId(String parentId) {
  this.setParentId(parentId);
  return this;
 }

 /**
  * 权限标识.
  *
  * @param code
  *         权限标识.
  */
 public AppPermission code(String code) {
  this.setCode(code);
  return this;
 }

 /**
  * 权限名称.
  *
  * @param name
  *         权限名称.
  */
 public AppPermission name(String name) {
  this.setName(name);
  return this;
 }

 /**
  * 权限描述.
  *
  * @param comment
  *         权限描述.
  */
 public AppPermission comment(String comment) {
  this.setComment(comment);
  return this;
 }

 /**
  * 权限级排序.
  *
  * @param order
  *         权限级排序.
  */
 public AppPermission order(Long order) {
  this.setOrder(order);
  return this;
 }

 /**
  * 菜单图标.
  *
  * @param icon
  *         菜单图标.
  */
 public AppPermission icon(String icon) {
  this.setIcon(icon);
  return this;
 }

 /**
  * 菜单URL.
  *
  * @param url
  *         菜单URL.
  */
 public AppPermission url(String url) {
  this.setUrl(url);
  return this;
 }

 /**
  * 权限类型 0=系统 1=目录 2=菜单 3=按钮.
  *
  * @param type
  *         权限类型 0=系统 1=目录 2=菜单 3=按钮.
  */
 public AppPermission type(Integer type) {
  this.setType(type);
  return this;
 }

 /**
  * 权限状态 0启用 1停用.
  *
  * @param state
  *         权限状态 0启用 1停用.
  */
 public AppPermission state(Integer state) {
  this.setState(state);
  return this;
 }

 public AppPermission state(boolean enabled) {
  this.setState(enabled ? 1 : 0);
  return this;
 }

 public AppPermission system(String code, String name) {
  return this.parentId(AppPermission.ROOT_ID).code(code).name(name).type(AppPermission.TYPE_SYSTEM).state(true);
 }

 public AppPermission dir(String code, String name) {
  return this.code(code).name(name).type(AppPermission.TYPE_DIR).state(true);
 }

 public AppPermission menu(String code, String name) {
  return this.code(code).name(name).type(AppPermission.TYPE_MENU).state(true);
 }

 public AppPermission childMenu(String code, String name) {
  return new AppPermission().parentId(this.getId()).menu(code, name);
 }

 public AppPermission childDir(String code, String name) {
  return new AppPermission().parentId(this.getId()).dir(code, name);
 }

 public AppPermission button(String code, String name) {
  return this.code(code).name(name).type(AppPermission.TYPE_BUTTON).state(true);
 }

 @Override
 public String toString() {
  return "AppPermission [getCode()=" + this.getCode() + ", getName()=" + this.getName() + ", getUrl()=" + this.getUrl() + ", getType()="
   + this.getType() + ", getState()=" + this.getState() + "]";
 }

 private String stateName;

 public String getStateName() {
  return this.stateName;
 }

 public void setStateName(String stateName) {
  this.stateName = stateName;
 }
}
