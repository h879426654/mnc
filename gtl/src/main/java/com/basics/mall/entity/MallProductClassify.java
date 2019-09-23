package com.basics.mall.entity;

import java.util.Date;
public class MallProductClassify extends MallProductClassifyBase{
	
	public String getProductFirstClassify() {
		return this.getId();
	}

	public void setProductFirstClassify(String productFirstClassify) {
		this.setId(productFirstClassify);
	}
	
	
 /**
 * 商品分类ID
 */
 public MallProductClassify id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 分类名称
 */
 public MallProductClassify classifyName(String classifyName){
  this.setClassifyName(classifyName);
  return this;
 }
 /**
 * 分类图片
 */
 public MallProductClassify classifyImg(String classifyImg){
  this.setClassifyImg(classifyImg);
  return this;
 }
 /**
 * 父级ID
 */
 public MallProductClassify classifyParentId(String classifyParentId){
  this.setClassifyParentId(classifyParentId);
  return this;
 }
 /**
 * 权重
 */
 public MallProductClassify classifySort(Integer classifySort){
  this.setClassifySort(classifySort);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallProductClassify flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallProductClassify createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallProductClassify createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallProductClassify modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallProductClassify modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}