package com.basics.mall.entity;

import java.math.BigDecimal;
import java.util.Date;
public class MallProduct extends MallProductBase{
 /**
 * 商品ID
 */
 public MallProduct id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 店铺ID
 */
 public MallProduct shopId(String shopId){
  this.setShopId(shopId);
  return this;
 }
 /**
  * 国家ID
  */
 public MallProduct countryId(String countryId){
	 this.setCountryId(countryId);
	 return this;
 }
 /**
 * 商品一级分类
 */
 public MallProduct productFirstClassify(String productFirstClassify){
  this.setProductFirstClassify(productFirstClassify);
  return this;
 }
 /**
 * 商品二级分类
 */
 public MallProduct productSecondClassify(String productSecondClassify){
  this.setProductSecondClassify(productSecondClassify);
  return this;
 }
 /**
 * 商品名称
 */
 public MallProduct productName(String productName){
  this.setProductName(productName);
  return this;
 }
 /**
 * 商品状态(1待上架 2上架中 3已下架)
 */
 public MallProduct productStatus(Integer productStatus){
  this.setProductStatus(productStatus);
  return this;
 }
 /**
 * 商品封面图
 */
 public MallProduct productImg(String productImg){
  this.setProductImg(productImg);
  return this;
 }
 /**
 * 商品价格
 */
 public MallProduct productPrice(BigDecimal productPrice){
  this.setProductPrice(productPrice);
  return this;
 }
 /**
 * 商品成本价
 */
 public MallProduct productCost(BigDecimal productCost){
  this.setProductCost(productCost);
  return this;
 }
 /**
 * 运费
 */
 public MallProduct productFreight(BigDecimal productFreight){
  this.setProductFreight(productFreight);
  return this;
 }
 /**
 * 商品详情描述
 */
 public MallProduct productContext(String productContext){
  this.setProductContext(productContext);
  return this;
 }
 /**
 * 销量
 */
 public MallProduct productSale(Integer productSale){
  this.setProductSale(productSale);
  return this;
 }
 /**
 * 库存
 */
 public MallProduct productStock(Integer productStock){
  this.setProductStock(productStock);
  return this;
 }
 /**
 * 商品收藏数
 */
 public MallProduct productCoolection(Integer productCoolection){
  this.setProductCoolection(productCoolection);
  return this;
 }
 /**
 * 版本号
 */
 public MallProduct versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallProduct flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallProduct createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallProduct createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallProduct modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallProduct modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}