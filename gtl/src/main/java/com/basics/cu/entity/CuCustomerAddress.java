package com.basics.cu.entity;

import java.util.Date;

public class CuCustomerAddress extends CuCustomerAddressBase {
 /**
 * 地址ID
 */
 public CuCustomerAddress id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 客户ID
 */
 public CuCustomerAddress customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 联系电话
 */
 public CuCustomerAddress addressPhone(String addressPhone){
  this.setAddressPhone(addressPhone);
  return this;
 }
 /**
 * 联系姓名
 */
 public CuCustomerAddress addressName(String addressName){
  this.setAddressName(addressName);
  return this;
 }
 
 /**
 * 地址
 */
 public CuCustomerAddress location(String location){
  this.setLocation(location);
  return this;
 }
 
 /**
 * 省ID
 */
 public CuCustomerAddress addressProvince(String addressProvince){
  this.setAddressProvince(addressProvince);
  return this;
 }
 /**
 * 市ID
 */
 public CuCustomerAddress addressCity(String addressCity){
  this.setAddressCity(addressCity);
  return this;
 }
 /**
 * 区域ID
 */
 public CuCustomerAddress addressArea(String addressArea){
  this.setAddressArea(addressArea);
  return this;
 }
 /**
 * 详细地址
 */
 public CuCustomerAddress addressInfo(String addressInfo){
  this.setAddressInfo(addressInfo);
  return this;
 }
 /**
 * 是否为默认地址(1是 0否)
 */
 public CuCustomerAddress addressFlag(Integer addressFlag){
  this.setAddressFlag(addressFlag);
  return this;
 }
 /**
 * 版本号
 */
 public CuCustomerAddress versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuCustomerAddress flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerAddress createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuCustomerAddress createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuCustomerAddress modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuCustomerAddress modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}