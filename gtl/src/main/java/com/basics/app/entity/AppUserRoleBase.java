package com.basics.app.entity;

/**
 * AppUserRole
 *
 * @author yuwenfeng.
 *
 */
public class AppUserRoleBase extends BaseBean {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = -1L;

 /**
  * 用户id.
  */
 private String userId;

 /**
  * 角色id.
  */
 private String roleId;

 /**
  * 用户id.
  * 
  * @return 用户id.
  */
 public String getUserId() {
  return this.userId;
 }

 /**
  * 用户id.
  * 
  * @param userId
  *         用户id.
  */
 public void setUserId(String userId) {
  this.userId = userId;
 }

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

}
