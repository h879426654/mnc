package com.basics.sys.entity;

import java.util.Date;
public class SysActivemqRequest extends SysActivemqRequestBase{
 /**
 * 消息ID
 */
 public SysActivemqRequest id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 会员ID
 */
 public SysActivemqRequest customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 消息名称
 */
 public SysActivemqRequest activemqType(String activemqType){
  this.setActivemqType(activemqType);
  return this;
 }
 /**
 * 消息内容
 */
 public SysActivemqRequest activemqContext(String activemqContext){
  this.setActivemqContext(activemqContext);
  return this;
 }
 /**
 * 消息描述
 */
 public SysActivemqRequest activemqRemark(String activemqRemark){
  this.setActivemqRemark(activemqRemark);
  return this;
 }
 /**
 * 创建时间
 */
 public SysActivemqRequest createDate(Date createDate){
  this.setCreateDate(createDate);
  return this;
 }
}