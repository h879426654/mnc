package com.basics.app.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.basics.support.LogUtils;
import com.basics.support.UserAwareSupport;

/**
 * AppUser
 *
 * @author yuwenfeng.
 *
 */
public class AppUser extends AppUserBase {

 /**
  * 普通用户
  */
 public static final int TYPE_NORMAL = 0;

 /**
  * 管理用户
  */
 public static final int TYPE_ADMIN = 1;

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = 1L;

 /**
  * ID.
  * 
  * @param id
  *         ID.
  */
 public AppUser id(String id) {
  this.setId(id);
  return this;
 }

 /**
  * 用户编码.
  * 
  * @param code
  *         用户编码.
  */
 public AppUser code(String code) {
  this.setCode(code);
  return this;
 }

 /**
  * 用户密码.
  * 
  * @param password
  *         用户密码.
  */
 public AppUser password(String password) {
  this.setPassword(password);
  return this;
 }

 /**
  * 用户描述.
  * 
  * @param comment
  *         用户描述.
  */
 public AppUser comment(String comment) {
  this.setComment(comment);
  return this;
 }

 /**
  * 用户实名.
  * 
  * @param name
  *         用户实名.
  */
 public AppUser name(String name) {
  this.setName(name);
  return this;
 }

 /**
  * 用户状态.
  * 
  * @param state
  *         用户状态.
  */
 public AppUser state(Integer state) {
  this.setState(state);
  return this;
 }

 public AppUser type(Integer type) {
  this.setType(type);
  return this;
 }

 @JSONField(serialize = false)
 @Override
 public String getPassword() {
  return super.getPassword();
 }

 @Override
 public String toString() {
  return "AppUser [getId()=" + this.getId() + ", getCode()=" + this.getCode() + ", getName()=" + this.getName() + "]";
 }

 private String stateName;

 public String getStateName() {
  return this.stateName;
 }

 public void setStateName(String stateName) {
  this.stateName = stateName;
 }

 public void attach(UserAwareSupport<String> userAware) {
  try {
   String userId = this.getId();
   if (userAware.getCreateTime() == null) {
    userAware.setCreateTime(new Date());
   }
   if (userAware.getCreateUser() == null) {
    userAware.setCreateUser(userId);
   }
   userAware.setUpdateTime(new Date());
   userAware.setUpdateUser(userId);
  } catch (Throwable e) {
   LogUtils.performance.error("User.attach error:{}", e);
  }
 }

 private String passwordNew = "";
 private String passwordConfirmed = "";

 public String getPasswordNew() {
  return this.passwordNew;
 }

 public void setPasswordNew(String passwordNew) {
  this.passwordNew = passwordNew;
 }

 public String getPasswordConfirmed() {
  return this.passwordConfirmed;
 }

 public void setPasswordConfirmed(String passwordConfirmed) {
  this.passwordConfirmed = passwordConfirmed;
 }


 private String roleNames;

 public String getRoleNames() {
  return roleNames;
 }

 public void setRoleNames(String roleNames) {
  this.roleNames = roleNames;
 }

}
