package com.basics.sys.entity;

import java.util.Date;
public class SysTurntableReward extends SysTurntableRewardBase{
 /**
 * ID
 */
 public SysTurntableReward id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 用户ID
 */
 public SysTurntableReward customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 奖励类型(1余额 2积分 3链)
 */
 public SysTurntableReward rewardType(Integer rewardType){
  this.setRewardType(rewardType);
  return this;
 }
 /**
 * 奖励数目
 */
 public SysTurntableReward rewardNum(Integer rewardNum){
  this.setRewardNum(rewardNum);
  return this;
 }
 /**
 * 版本号
 */
 public SysTurntableReward versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysTurntableReward flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysTurntableReward createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysTurntableReward createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysTurntableReward modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysTurntableReward modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}