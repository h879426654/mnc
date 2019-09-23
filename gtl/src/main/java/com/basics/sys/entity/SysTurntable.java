package com.basics.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
public class SysTurntable extends SysTurntableBase{
 /**
 * ID
 */
 public SysTurntable id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 奖励类型(1余额 2积分 3链)
 */
 public SysTurntable rewardType(Integer rewardType){
  this.setRewardType(rewardType);
  return this;
 }
 /**
 * 奖励数目
 */
 public SysTurntable rewardNum(Integer rewardNum){
  this.setRewardNum(rewardNum);
  return this;
 }
 /**
 * 中奖比例
 */
 public SysTurntable rewardRate(BigDecimal rewardRate){
  this.setRewardRate(rewardRate);
  return this;
 }
 /**
 * 排序
 */
 public SysTurntable rewardSort(Integer rewardSort){
  this.setRewardSort(rewardSort);
  return this;
 }
 /**
  * 版本号
  */
 public SysTurntable versionNum(Integer versionNum){
	 this.setVersionNum(versionNum);
	 return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysTurntable flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysTurntable createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysTurntable createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysTurntable modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysTurntable modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}