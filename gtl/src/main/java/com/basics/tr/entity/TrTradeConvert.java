package com.basics.tr.entity;

import java.math.BigDecimal;
import java.util.Date;
public class TrTradeConvert extends TrTradeConvertBase{
 /**
 * 交易ID
 */
 public TrTradeConvert id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 交易流水号
 */
 public TrTradeConvert tradeSerial(String tradeSerial){
  this.setTradeSerial(tradeSerial);
  return this;
 }
 /**
 * 发布用户ID
 */
 public TrTradeConvert customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 平台币数量
 */
 public TrTradeConvert moneyNum(BigDecimal moneyNum){
  this.setMoneyNum(moneyNum);
  return this;
 }
 /**
 * 交易状态(1待支付2待确认3交易取消4交易完成)
 */
 public TrTradeConvert tradeStatus(Integer tradeStatus){
  this.setTradeStatus(tradeStatus);
  return this;
 }
 /**
 * 购买用户ID
 */
 public TrTradeConvert customerBuyId(String customerBuyId){
  this.setCustomerBuyId(customerBuyId);
  return this;
 }
 /**
 * 支付时间
 */
 public TrTradeConvert tradePayTime(Date tradePayTime){
  this.setTradePayTime(tradePayTime);
  return this;
 }
 /**
 * 交易完成时间
 */
 public TrTradeConvert tradeFinishTime(Date tradeFinishTime){
  this.setTradeFinishTime(tradeFinishTime);
  return this;
 }
 /**
 * 审核状态状态(1待审核 2审核通过 3审核不通过)
 */
 public TrTradeConvert applyStatus(Integer applyStatus){
  this.setApplyStatus(applyStatus);
  return this;
 }
 /**
 * 审核意见
 */
 public TrTradeConvert applyContext(String applyContext){
  this.setApplyContext(applyContext);
  return this;
 }
 /**
 * 审核时间
 */
 public TrTradeConvert applyTime(Date applyTime){
  this.setApplyTime(applyTime);
  return this;
 }
 /**
 * 卖方应冻结平台币
 */
 public TrTradeConvert lockMoneyNum(BigDecimal lockMoneyNum){
  this.setLockMoneyNum(lockMoneyNum);
  return this;
 }
 /**
 * 版本号
 */
 public TrTradeConvert versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public TrTradeConvert flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public TrTradeConvert createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public TrTradeConvert createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public TrTradeConvert modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public TrTradeConvert modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}