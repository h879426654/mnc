package com.basics.cu.entity;

import java.util.Date;
public class CuDrawReward extends CuDrawRewardBase{
 /**
 * ID
 */
 public CuDrawReward id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 用户ID
 */
 public CuDrawReward customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 抽奖金额
 */
 public CuDrawReward rewardNum(Integer rewardNum){
  this.setRewardNum(rewardNum);
  return this;
 }
 /**
 * 版本号
 */
 public CuDrawReward versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuDrawReward flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuDrawReward createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuDrawReward createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuDrawReward modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuDrawReward modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}