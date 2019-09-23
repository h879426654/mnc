package com.basics.cu.entity;

import java.util.Date;

public class CuCustomerCollection extends CuCustomerCollectionBase {
 /**
 * 收藏ID
 */
 public CuCustomerCollection id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 收藏会员ID
 */
 public CuCustomerCollection customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 类型(1商品 2新闻)
 */
 public CuCustomerCollection collectionType(Integer collectionType){
  this.setCollectionType(collectionType);
  return this;
 }
 /**
 * 来源ID
 */
 public CuCustomerCollection sourceId(String sourceId){
  this.setSourceId(sourceId);
  return this;
 }
 /**
 * 版本号
 */
 public CuCustomerCollection versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuCustomerCollection flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerCollection createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuCustomerCollection createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuCustomerCollection modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuCustomerCollection modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}