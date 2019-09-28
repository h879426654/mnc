package com.basics.mall.entity;

import java.util.Date;
public class MallShopAdvert extends MallShopAdvertBase{
 /**
 * 商家ID
 */
 public MallShopAdvert id(String id){
  this.id(id);
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
  * 商家视频
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
 * 联系方式
 */
 public MallShopAdvert advertPhone(String advertPhone){
  this.setAdvertPhone(advertPhone);
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
 public MallShopAdvert applyStatus(String applyStatus){
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
 * 是否删除（1是 0否）
 */
 public MallShopAdvert flagDel(String flagDel){
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
  * 销量
  */
 public MallShopAdvert advertSale(int advertSale) {
  this.setAdvertSale(advertSale);
  return this;
 }
 /**
  * 市
  */
 public MallShopAdvert city(String city){
  this.setCity(city);
  return this;
 }
 /**
  * 区
  */
 public MallShopAdvert region(String region){
  this.setRegion(region);
  return this;
 }
 /**
  * 销量
  */
 public MallShopAdvert customerPhone(String customerPhone) {
  this.setCustomerPhone(customerPhone);
  return this;
 }
 public MallShopAdvert classifyId(String classifyId) {
  this.setClassifyId(classifyId);
  return this;
 }
 public MallShopAdvert pageN(Integer pageN) {
  this.setPageN(pageN);
  return this;
 }
 public MallShopAdvert pageS(Integer pageS) {
  this.setPageS(pageS);
  return this;
 }
 public MallShopAdvert list(String list) {
  this.setList(list);
  return this;
 }
 public MallShopAdvert person(String person) {
  this.setPerson(person);
  return this;
 }
}