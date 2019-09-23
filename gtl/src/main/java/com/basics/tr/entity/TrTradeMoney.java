package com.basics.tr.entity;

import java.math.BigDecimal;
import java.util.Date;
public class TrTradeMoney extends TrTradeMoneyBase{
 /**
 * 交易ID
 */
 public TrTradeMoney id(String id){
  this.setId(id);
  return this;
 }
 /**
  * 国家ID
  */
 public TrTradeMoney countryId(String countryId){
	 this.setCountryId(countryId);
	 return this;
 }
 /**
 * 交易流水号
 */
 public TrTradeMoney tradeSerial(String tradeSerial){
  this.setTradeSerial(tradeSerial);
  return this;
 }
 /**
 * 发布用户ID
 */
 public TrTradeMoney customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 交易类型(1出售 2购买)
 */
 public TrTradeMoney tradeType(Integer tradeType){
  this.setTradeType(tradeType);
  return this;
 }
 /**
 * 交易价格
 */
 public TrTradeMoney tradePrice(BigDecimal tradePrice){
  this.setTradePrice(tradePrice);
  return this;
 }
 /**
 * 平台币数量
 */
 public TrTradeMoney moneyNum(BigDecimal moneyNum){
  this.setMoneyNum(moneyNum);
  return this;
 }
 /**
 * 交易状态(1待交易2待支付 3待确认4交易投诉5交易取消6交易完成)
 */
 public TrTradeMoney tradeStatus(Integer tradeStatus){
  this.setTradeStatus(tradeStatus);
  return this;
 }
 /**
 * 购买用户ID
 */
 public TrTradeMoney customerBuyId(String customerBuyId){
  this.setCustomerBuyId(customerBuyId);
  return this;
 }
 /**
 * 匹配时间
 */
 public TrTradeMoney tradeMatchTime(Date tradeMatchTime){
  this.setTradeMatchTime(tradeMatchTime);
  return this;
 }
 /**
 * 支付方式(1微信 2支付宝)
 */
 public TrTradeMoney tradePayType(Integer tradePayType){
  this.setTradePayType(tradePayType);
  return this;
 }
 /**
 * 支付时间
 */
 public TrTradeMoney tradePayTime(Date tradePayTime){
  this.setTradePayTime(tradePayTime);
  return this;
 }
 /**
 * 交易完成时间
 */
 public TrTradeMoney tradeFinishTime(Date tradeFinishTime){
  this.setTradeFinishTime(tradeFinishTime);
  return this;
 }
 /**
 * 审核状态状态(1待审核 2审核通过 3审核不通过)
 */
 public TrTradeMoney applyStatus(Integer applyStatus){
  this.setApplyStatus(applyStatus);
  return this;
 }
 /**
 * 审核意见
 */
 public TrTradeMoney applyContext(String applyContext){
  this.setApplyContext(applyContext);
  return this;
 }
 /**
 * 审核时间
 */
 public TrTradeMoney applyTime(Date applyTime){
  this.setApplyTime(applyTime);
  return this;
 }
 /**
 * 卖方应冻结平台币
 */
 public TrTradeMoney lockMoneyNum(BigDecimal lockMoneyNum){
  this.setLockMoneyNum(lockMoneyNum);
  return this;
 }
 /**
 * 版本号
 */
 public TrTradeMoney versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public TrTradeMoney flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public TrTradeMoney createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public TrTradeMoney createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public TrTradeMoney modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public TrTradeMoney modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}