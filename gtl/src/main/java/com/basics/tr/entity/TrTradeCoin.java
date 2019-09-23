package com.basics.tr.entity;

import java.math.BigDecimal;
import java.util.Date;
public class TrTradeCoin extends TrTradeCoinBase{
 /**
 * 交易ID
 */
 public TrTradeCoin id(String id){
  this.setId(id);
  return this;
 }
 /**
  * 城市ID
  */
 public TrTradeCoin countryId(String countryId){
	 this.setCountryId(countryId);
	 return this;
 }
 /**
 * 交易流水号
 */
 public TrTradeCoin tradeSerial(String tradeSerial){
  this.setTradeSerial(tradeSerial);
  return this;
 }
 /**
 * 发布用户ID
 */
 public TrTradeCoin customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 交易类型(1出售 2购买)
 */
 public TrTradeCoin tradeType(Integer tradeType){
  this.setTradeType(tradeType);
  return this;
 }
 /**
 * 交易价格
 */
 public TrTradeCoin tradePrice(BigDecimal tradePrice){
  this.setTradePrice(tradePrice);
  return this;
 }
 /**
 * 平台币数量
 */
 public TrTradeCoin moneyNum(BigDecimal moneyNum){
  this.setMoneyNum(moneyNum);
  return this;
 }
 /**
 * 交易状态(1待交易2待支付 3待确认4交易投诉5交易取消6交易完成)
 */
 public TrTradeCoin tradeStatus(Integer tradeStatus){
  this.setTradeStatus(tradeStatus);
  return this;
 }
 /**
 * 购买用户ID
 */
 public TrTradeCoin customerBuyId(String customerBuyId){
  this.setCustomerBuyId(customerBuyId);
  return this;
 }
 /**
 * 匹配时间
 */
 public TrTradeCoin tradeMatchTime(Date tradeMatchTime){
  this.setTradeMatchTime(tradeMatchTime);
  return this;
 }
 /**
 * 支付方式(1微信 2支付宝3余额)
 */
 public TrTradeCoin tradePayType(Integer tradePayType){
  this.setTradePayType(tradePayType);
  return this;
 }
 /**
 * 支付时间
 */
 public TrTradeCoin tradePayTime(Date tradePayTime){
  this.setTradePayTime(tradePayTime);
  return this;
 }
 /**
 * 交易完成时间
 */
 public TrTradeCoin tradeFinishTime(Date tradeFinishTime){
  this.setTradeFinishTime(tradeFinishTime);
  return this;
 }
 /**
 * 审核状态状态(1待审核 2审核通过 3审核不通过)
 */
 public TrTradeCoin applyStatus(Integer applyStatus){
  this.setApplyStatus(applyStatus);
  return this;
 }
 /**
 * 审核意见
 */
 public TrTradeCoin applyContext(String applyContext){
  this.setApplyContext(applyContext);
  return this;
 }
 /**
 * 审核时间
 */
 public TrTradeCoin applyTime(Date applyTime){
  this.setApplyTime(applyTime);
  return this;
 }
 /**
 * 卖方应冻结平台币
 */
 public TrTradeCoin lockMoneyNum(BigDecimal lockMoneyNum){
  this.setLockMoneyNum(lockMoneyNum);
  return this;
 }
 /**
 * 版本号
 */
 public TrTradeCoin versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public TrTradeCoin flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public TrTradeCoin createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public TrTradeCoin createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public TrTradeCoin modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public TrTradeCoin modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}