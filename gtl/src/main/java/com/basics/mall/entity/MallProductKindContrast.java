package com.basics.mall.entity;

public class MallProductKindContrast extends MallProductKindContrastBase{
 /**
 * 商品维度对照ID
 */
 public MallProductKindContrast id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 商品主维度ID
 */
 public MallProductKindContrast kindId(String kindId){
  this.setKindId(kindId);
  return this;
 }
 /**
 * 商品子维度ID
 */
 public MallProductKindContrast kindDetailId(String kindDetailId){
  this.setKindDetailId(kindDetailId);
  return this;
 }
 /**
 * 商品ID
 */
 public MallProductKindContrast productId(String productId){
  this.setProductId(productId);
  return this;
 }
}