package com.basics.app.shiro;

import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.basics.support.CommonSupport;
import com.basics.support.LogUtils;
import com.basics.support.UserAwareSupport;

/**
 * UserSupport
 * 
 * @author yuwenfeng.
 * 
 */
public class UserSupport extends com.basics.support.ModelSupport implements java.io.Serializable {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = -1L;

 /**
  * ID.
  */
 private String id;

 /**
  * 用户编码.
  */
 private String code;

 /**
  * 用户密码.
  */
 private String password;

 /**
  * 用户描述.
  */
 private String comment;

 /**
  * 用户实名.
  */
 private String name;

 /**
  * 用户状态 0启用 1停用.
  */
 private Integer state;

 private String stateName;

 private String passwordNew = "";

 private String passwordConfirmed = "";

 /**
  * 用户类型
  */
 private Integer type;

 /**
  * 用户手机号码.
  */
 private String mobile;

 /**
  * 用户邮箱.
  */
 private String email;

 /**
  * 用户头像.
  */
 private String image;

 /**
  * 用户昵称.
  */
 private String nickname;

 /**
  * 用户组织.
  */
 private String orgId;

 /**
  * ID.
  * 
  * @return ID.
  */
 public String getId() {
  return id;
 }

 /**
  * ID.
  * 
  * @param id
  *         ID.
  */
 public void setId(String id) {
  this.id = id;
 }

 /**
  * 用户编码.
  * 
  * @return 用户编码.
  */
 public String getCode() {
  return code;
 }

 /**
  * 用户编码.
  * 
  * @param code
  *         用户编码.
  */
 public void setCode(String code) {
  this.code = code;
 }

 /**
  * 用户密码.
  * 
  * @param password
  *         用户密码.
  */
 public void setPassword(String password) {
  this.password = password;
 }

 /**
  * 用户描述.
  * 
  * @return 用户描述.
  */
 public String getComment() {
  return comment;
 }

 /**
  * 用户描述.
  * 
  * @param comment
  *         用户描述.
  */
 public void setComment(String comment) {
  this.comment = comment;
 }

 /**
  * 用户实名.
  * 
  * @return 用户实名.
  */
 public String getName() {
  return name;
 }

 @JSONField(serialize = false)
 public String getPassword() {
  return this.password;
 }

 /**
  * 用户实名.
  * 
  * @param name
  *         用户实名.
  */
 public void setName(String name) {
  this.name = name;
 }

 public String getStateName() {
  return stateName;
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
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
  }
 }

 /**
  * 用户状态 0启用 1停用.
  * 
  * @return 用户状态 0启用 1停用.
  */
 public Integer getState() {
  return state;
 }

 /**
  * 用户状态 0启用 1停用.
  * 
  * @param state
  *         用户状态 0启用 1停用.
  */
 public void setState(Integer state) {
  this.state = state;
 }

 public String getPasswordNew() {
  return passwordNew;
 }

 public void setPasswordNew(String passwordNew) {
  this.passwordNew = passwordNew;
 }

 public String getPasswordConfirmed() {
  return passwordConfirmed;
 }

 public void setPasswordConfirmed(String passwordConfirmed) {
  this.passwordConfirmed = passwordConfirmed;
 }

 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = prime * result + ((id == null) ? 0 : id.hashCode());
  return result;
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  UserSupport other = (UserSupport) obj;
  if (id == null) {
   if (other.id != null)
    return false;
  } else if (!id.equals(other.id))
   return false;
  return true;
 }

 public Integer getType() {
  return type;
 }

 public void setType(Integer type) {
  this.type = type;
 }

 public String getMobile() {
  return mobile;
 }

 public void setMobile(String mobile) {
  this.mobile = mobile;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getImage() {
  return image;
 }

 public void setImage(String image) {
  this.image = image;
 }

 public String getNickname() {
  return nickname;
 }

 public void setNickname(String nickname) {
  this.nickname = nickname;
 }

 public String getOrgId() {
  return orgId;
 }

 public void setOrgId(String orgId) {
  this.orgId = orgId;
 }


 public String getDisplayName() {
  if (CommonSupport.isNotBlank(this.getNickname())) {
   return this.getNickname();
  }
  if (CommonSupport.isNotBlank(this.getName())) {
   return this.getName();
  }
  if (CommonSupport.isNotBlank(this.getMobile())) {
   return this.getMobile();
  }
  return "";
 }
}
