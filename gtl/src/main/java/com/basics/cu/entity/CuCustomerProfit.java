package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;
public class CuCustomerProfit extends CuCustomerProfitBase{
 /**
 * 收益ID
 */
 public CuCustomerProfit id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 用户ID
 */
 public CuCustomerProfit customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 领取收益
 */
 public CuCustomerProfit profitNum(BigDecimal profitNum){
  this.setProfitNum(profitNum);
  return this;
 }
 /**
 * 收益类型
 */
 public CuCustomerProfit profitType(Integer profitType){
  this.setProfitType(profitType);
  return this;
 }
 /**
 * 收益状态(1收入 2支出)
 */
 public CuCustomerProfit profitStatus(Integer profitStatus){
  this.setProfitStatus(profitStatus);
  return this;
 }
 /**
 * 领取时间
 */
 public CuCustomerProfit profitHavedTime(Date profitHavedTime){
  this.setProfitHavedTime(profitHavedTime);
  return this;
 }
 /**
 * 收益来源
 */
 public CuCustomerProfit profitSource(String profitSource){
  this.setProfitSource(profitSource);
  return this;
 }
 /**
 * 收益说明
 */
 public CuCustomerProfit profitRemark(String profitRemark){
  this.setProfitRemark(profitRemark);
  return this;
 }
 /**
 * 版本号
 */
 public CuCustomerProfit versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuCustomerProfit flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerProfit createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuCustomerProfit createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuCustomerProfit modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuCustomerProfit modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}