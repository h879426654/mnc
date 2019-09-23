package com.basics.sys.entity;

import java.util.Date;
public class SysActivemqResponse extends SysActivemqResponseBase{
 /**
 * 消息ID
 */
 public SysActivemqResponse id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 会员ID
 */
 public SysActivemqResponse customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 消息名称
 */
 public SysActivemqResponse activemqType(String activemqType){
  this.setActivemqType(activemqType);
  return this;
 }
 /**
 * 消息结果
 */
 public SysActivemqResponse activemqResponse(String activemqResponse){
  this.setActivemqResponse(activemqResponse);
  return this;
 }
 /**
 * 完成时间
 */
 public SysActivemqResponse createDate(Date createDate){
  this.setCreateDate(createDate);
  return this;
 }
}