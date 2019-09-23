package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;
public class CuAccountConvert extends CuAccountConvertBase{
 /**
 * 转换ID
 */
 public CuAccountConvert id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 用户ID
 */
 public CuAccountConvert customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 转换类型(1余额转积分 2余额出售 3余额转账)
 */
 public CuAccountConvert convertType(Integer convertType){
  this.setConvertType(convertType);
  return this;
 }
 /**
 * 转换余额
 */
 public CuAccountConvert convertMoney(BigDecimal convertMoney){
  this.setConvertMoney(convertMoney);
  return this;
 }
 /**
 * 转换值
 */
 public CuAccountConvert convertNum(BigDecimal convertNum){
  this.setConvertNum(convertNum);
  return this;
 }
 /**
 * 对象ID
 */
 public CuAccountConvert sourceId(String sourceId){
  this.setSourceId(sourceId);
  return this;
 }
 /**
 * 说明
 */
 public CuAccountConvert convertRemark(String convertRemark){
  this.setConvertRemark(convertRemark);
  return this;
 }
 /**
 * 版本号
 */
 public CuAccountConvert versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuAccountConvert flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuAccountConvert createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuAccountConvert createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuAccountConvert modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuAccountConvert modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}