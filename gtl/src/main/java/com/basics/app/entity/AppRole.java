package com.basics.app.entity;

/**
 * AppRole
 *
 * @author yuwenfeng.
 *
 */
public class AppRole extends AppRoleBase {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = 1L;

 /**
  * 角色ID.
  * 
  * @param id
  *         角色ID.
  */
 public AppRole id(String id) {
  this.setId(id);
  return this;
 }

 /**
  * 角色编码.
  * 
  * @param code
  *         角色编码.
  */
 public AppRole code(String code) {
  this.setCode(code);
  return this;
 }

 /**
  * 角色名称.
  * 
  * @param name
  *         角色名称.
  */
 public AppRole name(String name) {
  this.setName(name);
  return this;
 }

 /**
  * 角色描述.
  * 
  * @param comment
  *         角色描述.
  */
 public AppRole comment(String comment) {
  this.setComment(comment);
  return this;
 }

 /**
  * 角色状态.
  * 
  * @param state
  *         角色状态.
  */
 public AppRole state(Integer state) {
  this.setState(state);
  return this;
 }

 @Override
 public String toString() {
  return "AppRole [getId()=" + this.getId() + ", getCode()=" + this.getCode() + ", getName()=" + this.getName() + ", getState()="
   + this.getState() + "]";
 }

 private String stateName;

 public String getStateName() {
  return this.stateName;
 }

 public void setStateName(String stateName) {
  this.stateName = stateName;
 }
}
