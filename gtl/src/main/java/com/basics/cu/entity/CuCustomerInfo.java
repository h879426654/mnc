package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;
public class CuCustomerInfo extends CuCustomerInfoBase{
 /**
 * 用户ID
 */
 public CuCustomerInfo id(String id){
  this.setId(id);
  return this;
 }
 /**
  * 国家ID
  */
 public CuCustomerInfo countryId(String countryId){
	 this.setCountryId(countryId);
	 return this;
 }
 /**
 * 用户账号
 */
 public CuCustomerInfo customerNumber(String customerNumber){
  this.setCustomerNumber(customerNumber);
  return this;
 }
 /**
 * 用户名
 */
 public CuCustomerInfo customerName(String customerName){
  this.setCustomerName(customerName);
  return this;
 }
 /**
 * 用户头像的路径
 */
 public CuCustomerInfo customerHead(String customerHead){
  this.setCustomerHead(customerHead);
  return this;
 }
 /**
 * 用户手机号
 */
 public CuCustomerInfo customerPhone(String customerPhone){
  this.setCustomerPhone(customerPhone);
  return this;
 }
 /**
 * 支付宝账号
 */
 public CuCustomerInfo customerAlipay(String customerAlipay){
  this.setCustomerAlipay(customerAlipay);
  return this;
 }
 /**
 * 用户微信账号
 */
 public CuCustomerInfo customerWechat(String customerWechat){
  this.setCustomerWechat(customerWechat);
  return this;
 }
 /**
 * 用户真实姓名
 */
 public CuCustomerInfo realName(String realName){
  this.setRealName(realName);
  return this;
 }
 /**
 * 身份证号
 */
 public CuCustomerInfo customerCard(String customerCard){
  this.setCustomerCard(customerCard);
  return this;
 }
 /**
 * 银行卡号
 */
 public CuCustomerInfo bankCard(String bankCard){
  this.setBankCard(bankCard);
  return this;
 }
 /**
 * 开户行
 */
 public CuCustomerInfo bankName(String bankName){
  this.setBankName(bankName);
  return this;
 }
 /**
 * 是否实名认证(1是 0否)
 */
 public CuCustomerInfo flagAuth(Integer flagAuth){
  this.setFlagAuth(flagAuth);
  return this;
 }
 /**
  * 是否可交易(1是 0否)
  */
 public CuCustomerInfo flagTrade(Integer flagTrade){
	 this.setFlagTrade(flagTrade);
	 return this;
 }
 /**
  * 是否为特殊标记
  */
 public CuCustomerInfo flagSpecial(Integer flagSpecial){
	 this.setFlagSpecial(flagSpecial);
	 return this;
 }
 /**
 * 用户状态 0代表冻结 1代表正常
 */
 public CuCustomerInfo customerStatus(Integer customerStatus){
  this.setCustomerStatus(customerStatus);
  return this;
 }
 /**
 * 用户冻结原因
 */
 public CuCustomerInfo customerFreezeContext(String customerFreezeContext){
  this.setCustomerFreezeContext(customerFreezeContext);
  return this;
 }
 /**
 * 注册时间
 */
 public CuCustomerInfo registerTime(Date registerTime){
  this.setRegisterTime(registerTime);
  return this;
 }
 /**
 * 版本号
 */
 public CuCustomerInfo versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuCustomerInfo flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerInfo createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuCustomerInfo createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuCustomerInfo modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuCustomerInfo modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
 public CuCustomerInfo mncCoin(BigDecimal mncCoin){
  this.setMncCoin(mncCoin);
  return this;
 }
 public CuCustomerInfo mp(BigDecimal mp){
  this.setMp(mp);
  return this;
 }
 public CuCustomerInfo address(String address){
  this.setAddress(address);
  return this;
 }
}