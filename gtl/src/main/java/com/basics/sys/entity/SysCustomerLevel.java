package com.basics.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
public class SysCustomerLevel extends SysCustomerLevelBase{
 /**
 * 等级ID
 */
 public SysCustomerLevel id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 等级名称
 */
 public SysCustomerLevel levelName(String levelName){
  this.setLevelName(levelName);
  return this;
 }
 /**
 * 等级最小值
 */
 public SysCustomerLevel levelMinIntegral(BigDecimal levelMinIntegral){
  this.setLevelMinIntegral(levelMinIntegral);
  return this;
 }
 /**
 * 等级最大值
 */
 public SysCustomerLevel levelMaxIntegral(BigDecimal levelMaxIntegral){
  this.setLevelMaxIntegral(levelMaxIntegral);
  return this;
 }
 /**
 * 直推人数
 */
 public SysCustomerLevel salfNum(Integer salfNum){
  this.setSalfNum(salfNum);
  return this;
 }
 /**
 * 直推奖励比例
 */
 public SysCustomerLevel salfRewardRate(BigDecimal salfRewardRate){
  this.setSalfRewardRate(salfRewardRate);
  return this;
 }
 /**
 * 团队支出奖励比例
 */
 public SysCustomerLevel teamOutRewardRate(BigDecimal teamOutRewardRate){
  this.setTeamOutRewardRate(teamOutRewardRate);
  return this;
 }
 /**
 * 团队支入奖励比例
 */
 public SysCustomerLevel teamInRewardRate(BigDecimal teamInRewardRate){
  this.setTeamInRewardRate(teamInRewardRate);
  return this;
 }
 /**
  * 平级奖
  */
 public SysCustomerLevel flatRewardRate(BigDecimal flatRewardRate){
	 this.setFlatRewardRate(flatRewardRate);
	 return this;
 }
 /**
  * 兑换奖
  */
 public SysCustomerLevel exchangeRate(BigDecimal exchangeRate){
	 this.setExchangeRate(exchangeRate);
	 return this;
 }
 /**
 * 等级权重
 */
 public SysCustomerLevel levelSort(Integer levelSort){
  this.setLevelSort(levelSort);
  return this;
 }
 /**
  * 代数
  */
 public SysCustomerLevel floorNum(Integer floorNum){
	 this.setFloorNum(floorNum);
	 return this;
 }
 /**
 * 版本号
 */
 public SysCustomerLevel versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysCustomerLevel flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysCustomerLevel createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysCustomerLevel createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysCustomerLevel modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysCustomerLevel modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}