package com.basics.app.entity;

import java.math.BigDecimal;
public class AppArea extends AppAreaBase{
 /**
 * 地区ID
 */
 public AppArea id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 上级地区
 */
 public AppArea areaParentId(String areaParentId){
  this.setAreaParentId(areaParentId);
  return this;
 }
 /**
 * 地区标识
 */
 public AppArea areaCode(String areaCode){
  this.setAreaCode(areaCode);
  return this;
 }
 /**
 * 地区名称
 */
 public AppArea areaName(String areaName){
  this.setAreaName(areaName);
  return this;
 }
 /**
 * 地区描述
 */
 public AppArea areaComment(String areaComment){
  this.setAreaComment(areaComment);
  return this;
 }
 /**
 * 地区排序
 */
 public AppArea areaOrder(BigDecimal areaOrder){
  this.setAreaOrder(areaOrder);
  return this;
 }
 /**
 * 地区图标
 */
 public AppArea areaIcon(String areaIcon){
  this.setAreaIcon(areaIcon);
  return this;
 }
 /**
 * 地区URL
 */
 public AppArea areaUrl(String areaUrl){
  this.setAreaUrl(areaUrl);
  return this;
 }
 /**
 * 地区类型 0省 10市 100区
 */
 public AppArea areaType(Integer areaType){
  this.setAreaType(areaType);
  return this;
 }
 /**
 * 地区状态
 */
 public AppArea areaFlag(Integer areaFlag){
  this.setAreaFlag(areaFlag);
  return this;
 }
}