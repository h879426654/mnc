package com.basics.tr.entity;

import java.util.Date;
public class TrTradeComplaint extends TrTradeComplaintBase{
 /**
 * 投诉ID
 */
 public TrTradeComplaint id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 交易ID
 */
 public TrTradeComplaint tradeId(String tradeId){
  this.setTradeId(tradeId);
  return this;
 }
 /**
 * 交易类型(1余额 2链)
 */
 public TrTradeComplaint tradeType(Integer tradeType){
  this.setTradeType(tradeType);
  return this;
 }
 /**
 * 用户ID
 */
 public TrTradeComplaint customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 投诉内容
 */
 public TrTradeComplaint complaintContext(String complaintContext){
  this.setComplaintContext(complaintContext);
  return this;
 }
 /**
 * 投诉状态(1待处理 2交易完成 3交易取消)
 */
 public TrTradeComplaint complaintStatus(Integer complaintStatus){
  this.setComplaintStatus(complaintStatus);
  return this;
 }
 /**
 * 处理说明
 */
 public TrTradeComplaint complaintRemark(String complaintRemark){
  this.setComplaintRemark(complaintRemark);
  return this;
 }
 /**
 * 版本号
 */
 public TrTradeComplaint versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public TrTradeComplaint flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public TrTradeComplaint createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public TrTradeComplaint createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public TrTradeComplaint modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public TrTradeComplaint modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}