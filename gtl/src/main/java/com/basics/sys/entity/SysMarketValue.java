package com.basics.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
public class SysMarketValue extends SysMarketValueBase{
 /**
 * 主键
 */
 public SysMarketValue id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 平台币与美元间的汇率
 */
 public SysMarketValue dollarRate(BigDecimal dollarRate){
  this.setDollarRate(dollarRate);
  return this;
 }
 /**
 * 人民币转BLC
 */
 public SysMarketValue rmbRate(BigDecimal rmbRate){
  this.setRmbRate(rmbRate);
  return this;
 }
 /**
 * 时间
 */
 public SysMarketValue rateTime(Date rateTime){
  this.setRateTime(rateTime);
  return this;
 }
 /**
 * 版本号
 */
 public SysMarketValue versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysMarketValue flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysMarketValue createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysMarketValue createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysMarketValue modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysMarketValue modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}