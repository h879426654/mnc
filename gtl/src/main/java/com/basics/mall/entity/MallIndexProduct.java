package com.basics.mall.entity;

import java.util.Date;
public class MallIndexProduct extends MallIndexProductBase{
 /**
 * 主键
 */
 public MallIndexProduct id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 首页模块
 */
 public MallIndexProduct typeId(String typeId){
  this.setTypeId(typeId);
  return this;
 }
 /**
 * 商品ID
 */
 public MallIndexProduct productId(String productId){
  this.setProductId(productId);
  return this;
 }
 /**
 * 图片地址
 */
 public MallIndexProduct productImage(String productImage){
  this.setProductImage(productImage);
  return this;
 }
 /**
 * 权重
 */
 public MallIndexProduct indexSort(Integer indexSort){
  this.setIndexSort(indexSort);
  return this;
 }
 /**
 * 版本号
 */
 public MallIndexProduct versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallIndexProduct flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallIndexProduct createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallIndexProduct createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallIndexProduct modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallIndexProduct modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}