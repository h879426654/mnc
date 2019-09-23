package com.basics.app.shiro;

/**
 * RoleSupport
 * 
 * @author yuwenfeng.
 * 
 */
public class RoleSupport extends com.basics.support.ModelSupport implements java.io.Serializable {

 /**
  * serialVersionUID
  */
 private static final long serialVersionUID = -1L;

 /**
  * 角色ID.
  */
 private String id;

 /**
  * 角色编码.
  */
 private String code;

 /**
  * 角色名称.
  */
 private String name;

 /**
  * 角色描述.
  */
 private String comment;

 /**
  * 角色状态 0启用 1停用.
  */
 private Integer state;

 /**
  * 角色ID.
  * 
  * @return 角色ID.
  */
 public String getId() {
  return id;
 }

 /**
  * 角色ID.
  * 
  * @param id
  *         角色ID.
  */
 public void setId(String id) {
  this.id = id;
 }

 /**
  * 角色编码.
  * 
  * @return 角色编码.
  */
 public String getCode() {
  return code;
 }

 /**
  * 角色编码.
  * 
  * @param code
  *         角色编码.
  */
 public void setCode(String code) {
  this.code = code;
 }

 /**
  * 角色名称.
  * 
  * @return 角色名称.
  */
 public String getName() {
  return name;
 }

 /**
  * 角色名称.
  * 
  * @param name
  *         角色名称.
  */
 public void setName(String name) {
  this.name = name;
 }

 /**
  * 角色描述.
  * 
  * @return 角色描述.
  */
 public String getComment() {
  return comment;
 }

 /**
  * 角色描述.
  * 
  * @param comment
  *         角色描述.
  */
 public void setComment(String comment) {
  this.comment = comment;
 }

 /**
  * 角色状态 0启用 1停用.
  * 
  * @return 角色状态 0启用 1停用.
  */
 public Integer getState() {
  return state;
 }

 /**
  * 角色状态 0启用 1停用.
  * 
  * @param state
  *         角色状态 0启用 1停用.
  */
 public void setState(Integer state) {
  this.state = state;
 }

}
