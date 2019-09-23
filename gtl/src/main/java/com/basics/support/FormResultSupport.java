package com.basics.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 表单提交结果.
 * 
 * @author yuwenfeng.
 * 
 */
public class FormResultSupport extends ResultSupport {

 /**
 * 
 */
 private static final long serialVersionUID = 1L;

 /**
  * 错误消息列表.
  */
 private List<Message> errorMessages = new ArrayList<Message>();

 public List<Message> getErrorMessages() {
  return errorMessages;
 }

 public void setErrorMessages(List<Message> errorMessages) {
  this.errorMessages = errorMessages;
 }

 public void onFieldError(String name, String code, String message) {
  this.errorMessages.add(new MessageBuilder().name(name).code(code).message(message).build());
 }

 public void onFieldError(Message message) {
  this.errorMessages.add(message);
 }

}
