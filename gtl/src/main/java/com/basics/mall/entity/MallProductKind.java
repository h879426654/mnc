package com.basics.mall.entity;

public class MallProductKind extends MallProductKindBase{
 /**
 * 类别主键
 */
 public MallProductKind id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 类别名称
 */
 public MallProductKind productKindName(String productKindName){
  this.setProductKindName(productKindName);
  return this;
 }
 /**
 * 描述
 */
 public MallProductKind productKindDescription(String productKindDescription){
  this.setProductKindDescription(productKindDescription);
  return this;
 }
 /**
 * 类别拼接权重，值越小越靠前
 */
 public MallProductKind productKindMosaicOrder(Integer productKindMosaicOrder){
  this.setProductKindMosaicOrder(productKindMosaicOrder);
  return this;
 }
}