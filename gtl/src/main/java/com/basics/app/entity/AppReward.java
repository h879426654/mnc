package com.basics.app.entity;

import java.util.Date;
public class AppReward extends AppRewardBase{
 /**
 * 奖励ID
 */
 public AppReward id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 奖励类型（1实名认证 2商家奖励 3晋升奖励）
 */
 public AppReward rewardType(Integer rewardType){
  this.setRewardType(rewardType);
  return this;
 }
 /**
 * 矿机ID
 */
 public AppReward machineId(String machineId){
  this.setMachineId(machineId);
  return this;
 }
 /**
 * 矿机数量
 */
 public AppReward machineNum(Integer machineNum){
  this.setMachineNum(machineNum);
  return this;
 }
 /**
 * 版本号
 */
 public AppReward versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public AppReward flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public AppReward createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public AppReward createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public AppReward modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public AppReward modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}