package com.basics.mall.entity;

public class MallProductKindDetail extends MallProductKindDetailBase{
 /**
 * 主键
 */
 public MallProductKindDetail id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 商品维度主表ID
 */
 public MallProductKindDetail detailKindId(String detailKindId){
  this.setDetailKindId(detailKindId);
  return this;
 }
 /**
 * 名称
 */
 public MallProductKindDetail detailName(String detailName){
  this.setDetailName(detailName);
  return this;
 }
 /**
 * 描述
 */
 public MallProductKindDetail detailDescription(String detailDescription){
  this.setDetailDescription(detailDescription);
  return this;
 }
 /**
 * 维度值
 */
 public MallProductKindDetail detailKindValue(String detailKindValue){
  this.setDetailKindValue(detailKindValue);
  return this;
 }
}