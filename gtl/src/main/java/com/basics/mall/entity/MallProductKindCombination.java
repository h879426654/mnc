package com.basics.mall.entity;

import java.math.BigDecimal;
public class MallProductKindCombination extends MallProductKindCombinationBase{
 /**
 * 组合编号
 */
 public MallProductKindCombination id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 商品编号
 */
 public MallProductKindCombination productId(String productId){
  this.setProductId(productId);
  return this;
 }
 /**
 * 维度组合，程序计算出多个维度阶乘组合存入当前字段使用/隔开
 */
 public MallProductKindCombination combination(String combination){
  this.setCombination(combination);
  return this;
 }
 /**
 * 库存数量
 */
 public MallProductKindCombination combinationStockNum(Integer combinationStockNum){
  this.setCombinationStockNum(combinationStockNum);
  return this;
 }
 /**
 * 已售数量
 */
 public MallProductKindCombination combinationSellNum(Integer combinationSellNum){
  this.setCombinationSellNum(combinationSellNum);
  return this;
 }
 /**
 * 
 */
 public MallProductKindCombination combinationPrice(BigDecimal combinationPrice){
  this.setCombinationPrice(combinationPrice);
  return this;
 }
 /**
 * 规格图片
 */
 public MallProductKindCombination combinationImg(String combinationImg){
  this.setCombinationImg(combinationImg);
  return this;
 }
}