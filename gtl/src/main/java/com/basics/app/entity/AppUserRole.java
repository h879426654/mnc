package com.basics.app.entity;

/**
 * AppUserRole
 * 
 * @author yuwenfeng.
 * 
 */
public class AppUserRole extends AppUserRoleBase {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = 1L;

 /**
  * 用户id.
  * 
  * @param userId
  *         用户id.
  */
 public AppUserRole userId(String userId) {
  this.setUserId(userId);
  return this;
 }

 /**
  * 角色id.
  * 
  * @param roleId
  *         角色id.
  */
 public AppUserRole roleId(String roleId) {
  this.setRoleId(roleId);
  return this;
 }
}
