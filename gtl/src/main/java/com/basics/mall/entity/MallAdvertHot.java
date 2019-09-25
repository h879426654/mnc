package com.basics.mall.entity;


import java.util.Date;

public class MallAdvertHot extends MallAdvertHotBase{
 public MallAdvertHot id(String id){
  this.setId(id);
  return this;
 }
 public MallAdvertHot advertId(String advertId){
  this.setAdvertId(advertId);
  return this;
 }
 public MallAdvertHot imageUrl(String imageUrl){
  this.setImageUrl(imageUrl);
  return this;
 }
 public MallAdvertHot advertName(String advertName){
  this.setAdvertName(advertName);
  return this;
 }
 public MallAdvertHot count(String count) {
  this.setCount(count);
  return this;
 }
 public MallAdvertHot distance(String distance) {
  this.setDistance(distance);
  return this;
 }
 public MallAdvertHot isHot(String isHot) {
  this.setIsHot(isHot);
  return this;
 }
 public MallAdvertHot createTime(Date createTime) {
  this.setCreateTime(createTime);
  return this;
 }
}