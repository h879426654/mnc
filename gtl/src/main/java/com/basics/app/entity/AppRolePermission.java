package com.basics.app.entity;

/**
 * AppRolePermission
 *
 * @author yuwenfeng.
 *
 */
public class AppRolePermission extends AppRolePermissionBase {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = 1L;

 /**
  * 角色id.
  * 
  * @param roleId
  *         角色id.
  */
 public AppRolePermission roleId(String roleId) {
  this.setRoleId(roleId);
  return this;
 }

 /**
  * 权限id.
  * 
  * @param permissionId
  *         权限id.
  */
 public AppRolePermission permissionId(String permissionId) {
  this.setPermissionId(permissionId);
  return this;
 }
}
