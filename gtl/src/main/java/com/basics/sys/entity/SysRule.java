package com.basics.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
public class SysRule extends SysRuleBase{
 /**
 * 规则ID
 */
 public SysRule id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 每日释放比例
 */
 public SysRule releaseRateDay(BigDecimal releaseRateDay){
  this.setReleaseRateDay(releaseRateDay);
  return this;
 }
 /**
 * 余额转积分首次
 */
 public SysRule rateToIntegralFirst(BigDecimal rateToIntegralFirst){
  this.setRateToIntegralFirst(rateToIntegralFirst);
  return this;
 }
 /**
 * 余额转积分非首次
 */
 public SysRule rateToIntegralMore(BigDecimal rateToIntegralMore){
  this.setRateToIntegralMore(rateToIntegralMore);
  return this;
 }
 /**
 * 余额转账个人奖励比例
 */
 public SysRule moneyOutRate(BigDecimal moneyOutRate){
  this.setMoneyOutRate(moneyOutRate);
  return this;
 }
 /**
 * 余额交易个人奖励比例
 */
 public SysRule moneyTradeRate(BigDecimal moneyTradeRate){
  this.setMoneyTradeRate(moneyTradeRate);
  return this;
 }
 /**
  * 余额交易个人奖励比例
  */
 public SysRule moneyTradeRateSale(BigDecimal moneyTradeRateSale){
	 this.setMoneyTradeRateSale(moneyTradeRateSale);
	 return this;
 }
 /**
 * 余额消费奖励比例
 */
 public SysRule moneySaleRate(BigDecimal moneySaleRate){
  this.setMoneySaleRate(moneySaleRate);
  return this;
 }
 /**
 * 转化比例
 */
 public SysRule convertRate(BigDecimal convertRate){
  this.setConvertRate(convertRate);
  return this;
 }
 /**
 * 签到奖励积分数量
 */
 public SysRule signRewardNum(Integer signRewardNum){
  this.setSignRewardNum(signRewardNum);
  return this;
 }
 /**
 * 交易手续费比例
 */
 public SysRule tradeRate(BigDecimal tradeRate){
  this.setTradeRate(tradeRate);
  return this;
 }
 /**
 * 交易开始时间(24小时制)
 */
 public SysRule tradeStartTime(Integer tradeStartTime){
  this.setTradeStartTime(tradeStartTime);
  return this;
 }
 /**
 * 交易结束时间(24小时制)
 */
 public SysRule tradeEndTime(Integer tradeEndTime){
  this.setTradeEndTime(tradeEndTime);
  return this;
 }
 /**
 * 每人单次交易数
 */
 public SysRule customerTradeNum(Integer customerTradeNum){
  this.setCustomerTradeNum(customerTradeNum);
  return this;
 }
 /**
 * 交易是否审核
 */
 public SysRule tradeApplyFlag(Integer tradeApplyFlag){
  this.setTradeApplyFlag(tradeApplyFlag);
  return this;
 }
 /**
 * 交易最小值
 */
 public SysRule tradeMinNum(BigDecimal tradeMinNum){
  this.setTradeMinNum(tradeMinNum);
  return this;
 }
 /**
 * 交易最大值(-1表示无穷大)
 */
 public SysRule tradeMaxNum(BigDecimal tradeMaxNum){
  this.setTradeMaxNum(tradeMaxNum);
  return this;
 }
 /**
 * 卖方最低单价比例(%)
 */
 public SysRule mallMinPrice(BigDecimal mallMinPrice){
  this.setMallMinPrice(mallMinPrice);
  return this;
 }
 /**
 * 卖方最高单价比例(%)
 */
 public SysRule mallMaxPrice(BigDecimal mallMaxPrice){
  this.setMallMaxPrice(mallMaxPrice);
  return this;
 }
 /**
 * 买方最低单价比例(%)
 */
 public SysRule buyMinPrice(BigDecimal buyMinPrice){
  this.setBuyMinPrice(buyMinPrice);
  return this;
 }
 /**
 * 买方最高单价比例(%)
 */
 public SysRule buyMaxPrice(BigDecimal buyMaxPrice){
  this.setBuyMaxPrice(buyMaxPrice);
  return this;
 }
 /**
  * 交易超过时间
  */
 public SysRule tradeTimeOut(Integer tradeTimeOut){
	 this.setTradeTimeOut(tradeTimeOut);
	 return this;
 }
 
 /**
 * 抽奖金额
 */
 public SysRule rewardNum(BigDecimal rewardNum){
  this.setRewardNum(rewardNum);
  return this;
 }
 /**
 * 抽奖开关
 */
 public SysRule rewardFlag(Integer rewardFlag){
  this.setRewardFlag(rewardFlag);
  return this;
 }
 /**
  * 余额出售折扣
  */
 public SysRule discountNum(BigDecimal discountNum){
	 this.setDiscountNum(discountNum);
	 return this;
 }
 
 /**
 * 版本号
 */
 public SysRule versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysRule flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysRule createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysRule createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysRule modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysRule modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}