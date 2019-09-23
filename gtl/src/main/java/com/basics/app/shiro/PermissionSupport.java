package com.basics.app.shiro;

import java.util.ArrayList;
import java.util.List;

/**
 * PermissionSupport.
 *
 * @author yuwenfeng.
 */
public class PermissionSupport extends com.basics.support.ModelSupport implements java.io.Serializable {

 /** serialVersionUID. */
 private static final long serialVersionUID = -1L;

 /** The Constant ICON_DEFAULT. */
 public static final String ICON_DEFAULT = "icon-folder";

 /** The Constant ROOT_ID. */
 public static final long ROOT_ID = 0;

 /** 系统. */
 public static final int TYPE_SYSTEM = 0;

 /** 目录. */
 public static final int TYPE_DIR = 1;

 /** 菜单. */
 public static final int TYPE_MENU = 2;

 /** 按钮. */
 public static final int TYPE_BUTTON = 3;

 /**
  * 权限ID.
  */
 private String id;

 /**
  * 上级权限ID.
  */
 private String parentId;

 /**
  * 权限标识.
  */
 private String code;

 /**
  * 权限名称.
  */
 private String name;

 /**
  * 权限描述.
  */
 private String comment;

 /**
  * 权限级排序.
  */
 private Long order;

 /**
  * 菜单图标.
  */
 private String icon;

 /**
  * 菜单URL.
  */
 private String url;

 /**
  * 权限类型 0=系统 1=目录 2=菜单 3=按钮.
  */
 private Integer type;

 /**
  * 权限状态 0启用 1停用.
  */
 private Integer state;

 /**
  * 是否激活状态.(UI显示辅助字段,不需要持久化).
  */
 private boolean active;

 public boolean isActive() {
  return active;
 }

 public void setActive(boolean active) {
  this.active = active;
  PermissionSupport p = this.getParent();
  while (p != null) {
   p.setActive(active);
   p = p.getParent();
  }
 }

 /**
  * 权限ID.
  * 
  * @return 权限ID.
  */
 public String getId() {
  return id;
 }

 /**
  * 权限ID.
  * 
  * @param id
  *         权限ID.
  */
 public void setId(String id) {
  this.id = id;
 }

 /**
  * 上级权限ID.
  * 
  * @return 上级权限ID.
  */
 public String getParentId() {
  return parentId;
 }

 /**
  * 上级权限ID.
  * 
  * @param parentId
  *         上级权限ID.
  */
 public void setParentId(String parentId) {
  this.parentId = parentId;
 }

 /**
  * 权限标识.
  * 
  * @return 权限标识.
  */
 public String getCode() {
  return code;
 }

 /**
  * 权限标识.
  * 
  * @param code
  *         权限标识.
  */
 public void setCode(String code) {
  this.code = code;
 }

 /**
  * 权限名称.
  * 
  * @return 权限名称.
  */
 public String getName() {
  return name;
 }

 /**
  * 权限名称.
  * 
  * @param name
  *         权限名称.
  */
 public void setName(String name) {
  this.name = name;
 }

 /**
  * 权限描述.
  * 
  * @return 权限描述.
  */
 public String getComment() {
  return comment;
 }

 /**
  * 权限描述.
  * 
  * @param comment
  *         权限描述.
  */
 public void setComment(String comment) {
  this.comment = comment;
 }

 /**
  * 权限级排序.
  * 
  * @return 权限级排序.
  */
 public Long getOrder() {
  return order;
 }

 /**
  * 权限级排序.
  * 
  * @param order
  *         权限级排序.
  */
 public void setOrder(Long order) {
  this.order = order;
 }

 /**
  * 菜单图标.
  * 
  * @return 菜单图标.
  */
 public String getIcon() {
  return icon;
 }

 /**
  * 菜单图标.
  * 
  * @param icon
  *         菜单图标.
  */
 public void setIcon(String icon) {
  this.icon = icon;
 }

 /**
  * 菜单URL.
  * 
  * @return 菜单URL.
  */
 public String getUrl() {
  return url;
 }

 /**
  * 菜单URL.
  * 
  * @param url
  *         菜单URL.
  */
 public void setUrl(String url) {
  this.url = url;
 }

 /**
  * 权限类型 0=系统 1=目录 2=菜单 3=按钮.
  * 
  * @return 权限类型 0=系统 1=目录 2=菜单 3=按钮.
  */
 public Integer getType() {
  return type;
 }

 /**
  * 权限类型 0=系统 1=目录 2=菜单 3=按钮.
  * 
  * @param type
  *         权限类型 0=系统 1=目录 2=菜单 3=按钮.
  */
 public void setType(Integer type) {
  this.type = type;
 }

 /**
  * 权限状态 0启用 1停用.
  * 
  * @return 权限状态 0启用 1停用.
  */
 public Integer getState() {
  return state;
 }

 /**
  * 权限状态 0启用 1停用.
  * 
  * @param state
  *         权限状态 0启用 1停用.
  */
 public void setState(Integer state) {
  this.state = state;
 }

 /** The menus. */
 protected List<PermissionSupport> menus = new ArrayList<PermissionSupport>();

 /**
  * Gets the menus.
  *
  * @return the menus
  */
 public List<PermissionSupport> getMenus() {
  return menus;
 }

 /**
  * Sets the menus.
  *
  * @param menus
  *         the menus
  */
 public void setMenus(List<PermissionSupport> menus) {
  for (PermissionSupport menu : menus) {
   menu.setParent(this);
  }
  this.menus = menus;
 }

 protected List<PermissionSupport> systems = new ArrayList<PermissionSupport>();

 protected List<PermissionSupport> dirs = new ArrayList<PermissionSupport>();

 public List<PermissionSupport> getSystems() {
  return systems;
 }

 public void setSystems(List<PermissionSupport> systems) {
  for (PermissionSupport system : systems) {
   system.setParent(this);
  }
  this.systems = systems;
 }

 public List<PermissionSupport> getDirs() {
  return dirs;
 }

 public void setDirs(List<PermissionSupport> dirs) {
  for (PermissionSupport dir : dirs) {
   dir.setParent(this);
  }
  this.dirs = dirs;
 }

 PermissionSupport parent;

 public PermissionSupport getParent() {
  return parent;
 }

 public void setParent(PermissionSupport parent) {
  this.parent = parent;
 }

}
