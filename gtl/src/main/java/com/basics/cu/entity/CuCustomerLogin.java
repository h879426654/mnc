package com.basics.cu.entity;

import java.util.Date;
public class CuCustomerLogin extends CuCustomerLoginBase{
 /**
 * 用户ID
 */
 public CuCustomerLogin id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 用户密码
 */
 public CuCustomerLogin customerPassword(String customerPassword){
  this.setCustomerPassword(customerPassword);
  return this;
 }
 /**
 * 密码盐值
 */
 public CuCustomerLogin passSalt(String passSalt){
  this.setPassSalt(passSalt);
  return this;
 }
 /**
 * 用户手机号
 */
 public CuCustomerLogin customerPhone(String customerPhone){
  this.setCustomerPhone(customerPhone);
  return this;
 }
 /**
 * 是否实名认证(1是 0否)
 */
 public CuCustomerLogin flagAuth(Integer flagAuth){
  this.setFlagAuth(flagAuth);
  return this;
 }
 
 /**
 * 用户状态 0代表冻结 1代表正常
 */
 public CuCustomerLogin customerStatus(Integer customerStatus){
  this.setCustomerStatus(customerStatus);
  return this;
 }
 /**
 * 登录错误次数
 */
 public CuCustomerLogin loginErrorNum(Integer loginErrorNum){
  this.setLoginErrorNum(loginErrorNum);
  return this;
 }
 /**
 * 最后登录时间
 */
 public CuCustomerLogin lastLoginTime(Date lastLoginTime){
  this.setLastLoginTime(lastLoginTime);
  return this;
 }
 /**
 * 版本号
 */
 public CuCustomerLogin versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuCustomerLogin flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerLogin createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuCustomerLogin createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuCustomerLogin modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuCustomerLogin modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}