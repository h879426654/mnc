package com.basics.app.entity;

import com.basics.app.shiro.UserSupport;

/**
 * AppUser
 * 
 * @author yuwenfeng.
 * 
 */
public class AppUserBase extends UserSupport {
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
  * 用户状态.
  */
 private Integer state;

 /**
  * 用户类型 0普通用户 1管理用户.
  */
 private Integer type;


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
  * @return 用户密码.
  */
 public String getPassword() {
  return password;
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

 /**
  * 用户实名.
  * 
  * @param name
  *         用户实名.
  */
 public void setName(String name) {
  this.name = name;
 }

 /**
  * 用户状态.
  * 
  * @return 用户状态.
  */
 public Integer getState() {
  return state;
 }

 /**
  * 用户状态.
  * 
  * @param state
  *         用户状态.
  */
 public void setState(Integer state) {
  this.state = state;
 }

 /**
  * 用户类型 0:消费者 10:商家 100:代理商 1000:运营商 10000:总部/总公司.
  * 
  * @return 用户类型 0:消费者 10:商家 100:代理商 1000:运营商 10000:总部/总公司.
  */
 public Integer getType() {
  return type;
 }

 /**
  * 用户类型 0:消费者 10:商家 100:代理商 1000:运营商 10000:总部/总公司.
  * 
  * @param type
  *         0普通用户 1管理用户.
  */
 public void setType(Integer type) {
  this.type = type;
 }


}
