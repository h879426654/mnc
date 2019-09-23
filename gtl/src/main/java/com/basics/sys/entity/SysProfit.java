package com.basics.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
public class SysProfit extends SysProfitBase{
 /**
 * 收益ID
 */
 public SysProfit id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 收益来源ID
 */
 public SysProfit profitSourceId(String profitSourceId){
  this.setProfitSourceId(profitSourceId);
  return this;
 }
 /**
 * 收益来源(1商家申请 2商家升级 3会员消费)
 */
 public SysProfit profitSource(Integer profitSource){
  this.setProfitSource(profitSource);
  return this;
 }
 /**
 * 收益值
 */
 public SysProfit profitNum(BigDecimal profitNum){
  this.setProfitNum(profitNum);
  return this;
 }
 /**
 * 收益说明
 */
 public SysProfit profitRemark(String profitRemark){
  this.setProfitRemark(profitRemark);
  return this;
 }
 /**
 * 版本号
 */
 public SysProfit versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysProfit flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysProfit createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysProfit createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysProfit modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysProfit modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}