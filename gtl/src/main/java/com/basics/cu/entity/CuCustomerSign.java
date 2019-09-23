package com.basics.cu.entity;

import java.util.Date;
public class CuCustomerSign extends CuCustomerSignBase{
 /**
 * 签到ID
 */
 public CuCustomerSign id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 用户ID
 */
 public CuCustomerSign customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 每人奖励数量（积分）
 */
 public CuCustomerSign signNum(Integer signNum){
  this.setSignNum(signNum);
  return this;
 }
 /**
 * 签到时间
 */
 public CuCustomerSign signTime(Date signTime){
  this.setSignTime(signTime);
  return this;
 }
 /**
 * 版本号
 */
 public CuCustomerSign versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuCustomerSign flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerSign createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuCustomerSign createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuCustomerSign modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuCustomerSign modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}