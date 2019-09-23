package com.basics.cu.entity;

import java.util.Date;
public class CuCustomerFeedback extends CuCustomerFeedbackBase{
 /**
 * 反馈ID
 */
 public CuCustomerFeedback id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 反馈类型(1用户反馈 3商家反馈)
 */
 public CuCustomerFeedback feedbackType(Integer feedbackType){
  this.setFeedbackType(feedbackType);
  return this;
 }
 /**
 * 反馈用户ID
 */
 public CuCustomerFeedback customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 反馈内容
 */
 public CuCustomerFeedback feedbackContext(String feedbackContext){
  this.setFeedbackContext(feedbackContext);
  return this;
 }
 /**
 * 反馈状态（1待处理 2已处理 3拒绝处理）
 */
 public CuCustomerFeedback feedbackStatus(Integer feedbackStatus){
  this.setFeedbackStatus(feedbackStatus);
  return this;
 }
 /**
 * 反馈处理说明
 */
 public CuCustomerFeedback feedbackRemark(String feedbackRemark){
  this.setFeedbackRemark(feedbackRemark);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerFeedback createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
}