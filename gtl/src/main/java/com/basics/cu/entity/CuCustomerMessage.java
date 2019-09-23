package com.basics.cu.entity;

import java.util.Date;

public class CuCustomerMessage extends CuCustomerMessageBase {
 /**
 * 消息ID
 */
 public CuCustomerMessage id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 用户ID
 */
 public CuCustomerMessage customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
  * 用户ID
  */
 public CuCustomerMessage messageType(Integer messageType){
	 this.setMessageType(messageType);
	 return this;
 }
 /**
 * 系统公告ID
 */
 public CuCustomerMessage appMessageId(String appMessageId){
  this.setAppMessageId(appMessageId);
  return this;
 }
 /**
 * 消息标题
 */
 public CuCustomerMessage messageTitle(String messageTitle){
  this.setMessageTitle(messageTitle);
  return this;
 }
 /**
 * 消息内容
 */
 public CuCustomerMessage messageContext(String messageContext){
  this.setMessageContext(messageContext);
  return this;
 }
 /**
 * 是否已读(0未读 1已读)
 */
 public CuCustomerMessage flagRead(Integer flagRead){
  this.setFlagRead(flagRead);
  return this;
 }
 /**
 * 版本号
 */
 public CuCustomerMessage versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public CuCustomerMessage flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public CuCustomerMessage createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public CuCustomerMessage createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public CuCustomerMessage modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public CuCustomerMessage modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}