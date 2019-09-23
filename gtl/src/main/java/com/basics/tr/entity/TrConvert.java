package com.basics.tr.entity;

import java.math.BigDecimal;
import java.util.Date;
public class TrConvert extends TrConvertBase{
 /**
 * 兑换ID
 */
 public TrConvert id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 兑换流水号
 */
 public TrConvert convertSerial(String convertSerial){
  this.setConvertSerial(convertSerial);
  return this;
 }
 /**
 * 兑换主题
 */
 public TrConvert convertName(String convertName){
  this.setConvertName(convertName);
  return this;
 }
 /**
 * 兑换说明
 */
 public TrConvert convertRemark(String convertRemark){
  this.setConvertRemark(convertRemark);
  return this;
 }
 /**
 * 兑换数量
 */
 public TrConvert convertNum(BigDecimal convertNum){
  this.setConvertNum(convertNum);
  return this;
 }
 /**
 * 总量
 */
 public TrConvert convertTotalNum(BigDecimal convertTotalNum){
  this.setConvertTotalNum(convertTotalNum);
  return this;
 }
 /**
 * 开始时间
 */
 public TrConvert convertStartTime(Date convertStartTime){
  this.setConvertStartTime(convertStartTime);
  return this;
 }
 /**
 * 结束时间
 */
 public TrConvert convertEndTime(Date convertEndTime){
  this.setConvertEndTime(convertEndTime);
  return this;
 }
 /**
 * 版本号
 */
 public TrConvert versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public TrConvert flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public TrConvert createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public TrConvert createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public TrConvert modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public TrConvert modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}