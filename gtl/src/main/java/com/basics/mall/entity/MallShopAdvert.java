package com.basics.mall.entity;

import java.util.Date;
public class MallShopAdvert extends MallShopAdvertBase{
 /**
 * 商家ID
 */
 public MallShopAdvert id(String id){
  this.setId(id);
  return this;
 }
 /**
  * 用户ID
  */
 public MallShopAdvert customerId(String customerId){
	 this.setCustomerId(customerId);
	 return this;
 }
 /**
  * 国家ID
  */
 public MallShopAdvert countryId(String countryId){
	 this.setCountryId(countryId);
	 return this;
 }
 /**
 * 商家名称
 */
 public MallShopAdvert advertName(String advertName){
  this.setAdvertName(advertName);
  return this;
 }
 /**
 * 商家介绍
 */
 public MallShopAdvert advertContext(String advertContext){
  this.setAdvertContext(advertContext);
  return this;
 }
 /**
 * 商家封面图片
 */
 public MallShopAdvert advertImage(String advertImage){
  this.setAdvertImage(advertImage);
  return this;
 }
 /**
  * 商家
  */
 public MallShopAdvert shopVideo(String shopVideo){
	 this.setShopVideo(shopVideo);
	 return this;
 }
 /**
  * 
  */
 public MallShopAdvert shopLicence(String shopLicence){
	 this.setShopLicence(shopLicence);
	 return this;
 }
 /**
 * 商家分类ID
 */
 public MallShopAdvert advertClassifyId(String advertClassifyId){
  this.setAdvertClassifyId(advertClassifyId);
  return this;
 }
 /**
 * 联系方式
 */
 public MallShopAdvert advertPhone(String advertPhone){
  this.setAdvertPhone(advertPhone);
  return this;
 }
 /**
 * 省ID
 */
 public MallShopAdvert addressProvince(String addressProvince){
  this.setAddressProvince(addressProvince);
  return this;
 }
 /**
 * 市ID
 */
 public MallShopAdvert addressCity(String addressCity){
  this.setAddressCity(addressCity);
  return this;
 }
 /**
 * 区域ID
 */
 public MallShopAdvert addressArea(String addressArea){
  this.setAddressArea(addressArea);
  return this;
 }
 /**
 * 地址
 */
 public MallShopAdvert advertAddress(String advertAddress){
  this.setAdvertAddress(advertAddress);
  return this;
 }
 /**
 * 经度
 */
 public MallShopAdvert advertLongitude(String advertLongitude){
  this.setAdvertLongitude(advertLongitude);
  return this;
 }
 /**
 * 纬度
 */
 public MallShopAdvert advertLatitude(String advertLatitude){
  this.setAdvertLatitude(advertLatitude);
  return this;
 }
 /**
  * 审核
  */
 public MallShopAdvert applyStatus(Integer applyStatus){
	 this.setApplyStatus(applyStatus);
	 return this;
 }
 /**
  * 审核
  */
 public MallShopAdvert applyContext(String applyContext){
	 this.setApplyContext(applyContext);
	 return this;
 }
 /**
 * 版本号
 */
 public MallShopAdvert versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallShopAdvert flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallShopAdvert createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallShopAdvert createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallShopAdvert modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallShopAdvert modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}