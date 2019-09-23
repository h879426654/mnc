package com.basics.or.entity;

import java.util.Date;
public class OrOrderDetails extends OrOrderDetailsBase{
 /**
 * 订单详情ID
 */
 public OrOrderDetails id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 订单ID
 */
 public OrOrderDetails orderId(String orderId){
  this.setOrderId(orderId);
  return this;
 }
 /**
 * 修改时间
 */
 public OrOrderDetails modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
 /**
 * 规格ID
 */
 public OrOrderDetails combinationId(String combinationId){
  this.setCombinationId(combinationId);
  return this;
 }
 /**
 * 商品ID
 */
 public OrOrderDetails productId(String productId){
  this.setProductId(productId);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public OrOrderDetails flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 商品数量
 */
 public OrOrderDetails productNum(Integer productNum){
  this.setProductNum(productNum);
  return this;
 }
 /**
 * 创建时间
 */
 public OrOrderDetails createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public OrOrderDetails createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public OrOrderDetails modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
}