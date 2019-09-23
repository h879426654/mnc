package com.basics.cu.entity;

import java.util.Date;
public class CuCustomerCount extends CuCustomerCountBase{
 /**
 * 统计ID
 */
 public CuCustomerCount id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 会员级别
 */
 public CuCustomerCount customerLevelId(String customerLevelId){
  this.setCustomerLevelId(customerLevelId);
  return this;
 }
 /**
 * 直推人数
 */
 public CuCustomerCount salfNum(Integer salfNum){
  this.setSalfNum(salfNum);
  return this;
 }
 /**
 * 团队人数
 */
 public CuCustomerCount teamNum(Integer teamNum){
  this.setTeamNum(teamNum);
  return this;
 }
 /**
 * 版本号
 */
 public CuCustomerCount versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuCustomerCount flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerCount createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuCustomerCount createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuCustomerCount modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuCustomerCount modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}