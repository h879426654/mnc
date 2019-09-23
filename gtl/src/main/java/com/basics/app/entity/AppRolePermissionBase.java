package com.basics.app.entity;

/**
 * AppRolePermission
 *
 * @author yuwenfeng.
 *
 */
public class AppRolePermissionBase extends BaseBean {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = -1L;

 /**
  * 角色id.
  */
 private String roleId;

 /**
  * 权限id.
  */
 private String permissionId;

 /**
  * 角色id.
  * 
  * @return 角色id.
  */
 public String getRoleId() {
  return this.roleId;
 }

 /**
  * 角色id.
  * 
  * @param roleId
  *         角色id.
  */
 public void setRoleId(String roleId) {
  this.roleId = roleId;
 }

 /**
  * 权限id.
  * 
  * @return 权限id.
  */
 public String getPermissionId() {
  return this.permissionId;
 }

 /**
  * 权限id.
  * 
  * @param permissionId
  *         权限id.
  */
 public void setPermissionId(String permissionId) {
  this.permissionId = permissionId;
 }

}
