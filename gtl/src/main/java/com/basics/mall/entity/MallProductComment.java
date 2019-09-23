package com.basics.mall.entity;

import java.math.BigDecimal;
import java.util.Date;
public class MallProductComment extends MallProductCommentBase{
 /**
 * 商品评价ID
 */
 public MallProductComment id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 商品ID
 */
 public MallProductComment productId(String productId){
  this.setProductId(productId);
  return this;
 }
 /**
 * 会员ID
 */
 public MallProductComment customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 评价内容
 */
 public MallProductComment commentContext(String commentContext){
  this.setCommentContext(commentContext);
  return this;
 }
 /**
 * 评价类型(1好评 2中评 3差评)
 */
 public MallProductComment commentType(Integer commentType){
  this.setCommentType(commentType);
  return this;
 }
 /**
 * 描述星级评价
 */
 public MallProductComment commentDescribeSart(BigDecimal commentDescribeSart){
  this.setCommentDescribeSart(commentDescribeSart);
  return this;
 }
 /**
 * 服务星级评价
 */
 public MallProductComment commentServiceSart(BigDecimal commentServiceSart){
  this.setCommentServiceSart(commentServiceSart);
  return this;
 }
 /**
 * 物流星级评价
 */
 public MallProductComment commentLogisticsSart(BigDecimal commentLogisticsSart){
  this.setCommentLogisticsSart(commentLogisticsSart);
  return this;
 }
 /**
 * 是否匿名(1是 0否)
 */
 public MallProductComment flagAnonymous(Integer flagAnonymous){
  this.setFlagAnonymous(flagAnonymous);
  return this;
 }
 /**
 * 回复内容
 */
 public MallProductComment replyContext(String replyContext){
  this.setReplyContext(replyContext);
  return this;
 }
 /**
 * 回复时间
 */
 public MallProductComment replyTime(Date replyTime){
  this.setReplyTime(replyTime);
  return this;
 }
 /**
 * 版本号
 */
 public MallProductComment versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public MallProductComment flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public MallProductComment createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public MallProductComment createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public MallProductComment modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public MallProductComment modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}