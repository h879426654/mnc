package com.basics.mall.entity;

import java.math.BigDecimal;
import java.util.Date;
public class MallShop extends MallShopBase{
 /**
 * 店铺ID
 */
 public MallShop id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 会员ID
 */
 public MallShop customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 店铺名称
 */
 public MallShop shopName(String shopName){
  this.setShopName(shopName);
  return this;
 }
 /**
 * 店铺信誉
 */
 public MallShop shopStar(BigDecimal shopStar){
  this.setShopStar(shopStar);
  return this;
 }
 /**
  * 开店需要金额
  */
 public MallShop needMoney(BigDecimal needMoney){
	 this.setNeedMoney(needMoney);
	 return this;
 }
 /**
 * 店铺LOGO
 */
 public MallShop shopLogo(String shopLogo){
  this.setShopLogo(shopLogo);
  return this;
 }
 /**
  * 店铺
  */
 public MallShop shopLicence(String shopLicence){
	 this.setShopLicence(shopLicence);
	 return this;
 }
 /**
 * 店铺编号
 */
 public MallShop shopNumber(Integer shopNumber){
  this.setShopNumber(shopNumber);
  return this;
 }
 /**
 * 店铺状态(1营业 2停业)
 */
 public MallShop shopStatus(Integer shopStatus){
  this.setShopStatus(shopStatus);
  return this;
 }
 /**
  * 审核
  */
 public MallShop applyStatus(Integer applyStatus){
	 this.setApplyStatus(applyStatus);
	 return this;
 }
 /**
 * 店铺折扣比例
 */
 public MallShop shopDiscount(BigDecimal shopDiscount){
  this.setShopDiscount(shopDiscount);
  return this;
 }
 /**
  * 店铺密码
  */
 public MallShop shopPass(String shopPass){
	 this.setShopPass(shopPass);
	 return this;
 }
 /**
 * 店铺说明
 */
 public MallShop shopService(String shopService){
  this.setShopService(shopService);
  return this;
 }
 /**
 * 店铺地址
 */
 public MallShop shopAddress(String shopAddress){
  this.setShopAddress(shopAddress);
  return this;
 }
 /**
 * 版本号
 */
 public MallShop versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallShop flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallShop createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallShop createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallShop modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallShop modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}