package com.basics.mall.entity;

import java.util.Date;
public class MallShopClassify extends MallShopClassifyBase{
 /**
 * 商品分类ID
 */
 public MallShopClassify id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 分类名称
 */
 public MallShopClassify classifyName(String classifyName){
  this.setClassifyName(classifyName);
  return this;
 }
 /**
 * 分类图片
 */
 public MallShopClassify classifyImg(String classifyImg){
  this.setClassifyImg(classifyImg);
  return this;
 }
 /**
 * 父级ID
 */
 public MallShopClassify classifyParentId(String classifyParentId){
  this.setClassifyParentId(classifyParentId);
  return this;
 }
 /**
 * 权重
 */
 public MallShopClassify classifySort(Integer classifySort){
  this.setClassifySort(classifySort);
  return this;
 }
 /**
 * 版本号
 */
 public MallShopClassify versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallShopClassify flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallShopClassify createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallShopClassify createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallShopClassify modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallShopClassify modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}