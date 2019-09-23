package com.basics.mall.entity;

import java.util.Date;
public class MallCustomerCar extends MallCustomerCarBase{
 /**
 * 购物车ID
 */
 public MallCustomerCar id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 客户ID
 */
 public MallCustomerCar customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 商品ID
 */
 public MallCustomerCar productId(String productId){
  this.setProductId(productId);
  return this;
 }
 /**
 * 商品数量
 */
 public MallCustomerCar productNum(Integer productNum){
  this.setProductNum(productNum);
  return this;
 }
 /**
 * 规格ID
 */
 public MallCustomerCar combinationId(String combinationId){
  this.setCombinationId(combinationId);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallCustomerCar flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallCustomerCar createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallCustomerCar createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallCustomerCar modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallCustomerCar modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}