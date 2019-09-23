package com.basics.mall.entity;

import java.util.Date;
public class MallIndexType extends MallIndexTypeBase{
 /**
 * 主键
 */
 public MallIndexType id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 模块说明
 */
 public MallIndexType typeTitle(String typeTitle){
  this.setTypeTitle(typeTitle);
  return this;
 }
 /**
 * 图片地址
 */
 public MallIndexType typeImg(String typeImg){
  this.setTypeImg(typeImg);
  return this;
 }
 /**
 * 链接地址
 */
 public MallIndexType typeUrl(String typeUrl){
  this.setTypeUrl(typeUrl);
  return this;
 }
 /**
 * 权重
 */
 public MallIndexType typeSort(Integer typeSort){
  this.setTypeSort(typeSort);
  return this;
 }
 /**
 * 版本号
 */
 public MallIndexType versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallIndexType flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallIndexType createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallIndexType createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallIndexType modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallIndexType modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}