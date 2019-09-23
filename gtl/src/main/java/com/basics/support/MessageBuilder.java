package com.basics.support;

import org.apache.commons.lang.StringUtils;

/**
 * MessageBuilder.
 */
public class MessageBuilder {

 /** The message. */
 private Message message = new Message();

 /**
  * Builds the.
  * 
  * @return the message
  */
 public Message build() {
  return message;
 }

 /**
  * Name.
  * 
  * @param name
  *         the name
  * @return the message builder
  */
 public MessageBuilder name(String name) {
  if (StringUtils.isNotBlank(name)) {
   message.setName(name);
  }
  return this;
 }

 /**
  * Code.
  * 
  * @param code
  *         the code
  * @return the message builder
  */
 public MessageBuilder code(String code) {
  if (StringUtils.isNotBlank(code)) {
   message.setCode(code);
  }
  return this;
 }

 /**
  * Messsage.
  * 
  * @param message
  *         the message
  * @return the message builder
  */
 public MessageBuilder message(String message) {
  if (StringUtils.isNotBlank(message)) {
   this.message.setMessage(message);
  }
  return this;
 }
}
