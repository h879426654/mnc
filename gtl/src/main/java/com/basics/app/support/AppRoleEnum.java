package com.basics.app.support;

import org.apache.commons.lang3.StringUtils;

/**
 * 角色枚举.
 * 
 * @author yuwenfeng.
 */
public enum AppRoleEnum {

 HQ("HQ", "总部"), //
 ADMIN("ADMIN", "系统管理员");

 private String message;

 /** The value. */
 private String value;

 /**
  * The Constructor.
  *
  * @param value
  *         the value
  * @param message
  *         the message
  * @param type
  *         the type
  */
 private AppRoleEnum(String value, String message) {
  this.value = value;
  this.message = message;
 }

 /**
  * Gets the message.
  *
  * @return the message
  */
 public String getMessage() {
  return message;
 }

 /**
  * Gets the value.
  *
  * @return the value
  */
 public String getValue() {
  return value;
 }

 /**
  * In.
  *
  * @param value
  *         the value
  * @param values
  *         the values
  * @return true, if in
  */
 public static boolean in(String value, AppRoleEnum... values) {
  if (values == null) {
   return false;
  }
  for (AppRoleEnum v : values) {
   if (StringUtils.equalsIgnoreCase(v.getValue(), value)) {
    return true;
   }
  }
  return false;
 }

 /**
  * No in.
  *
  * @param value
  *         the value
  * @param values
  *         the values
  * @return true, if no in
  */
 public static boolean noIn(String value, AppRoleEnum... values) {
  if (values == null) {
   return true;
  }
  for (AppRoleEnum v : values) {
   if (StringUtils.equalsIgnoreCase(v.getValue(), value)) {
    return false;
   }
  }
  return true;
 }

 /**
  * From.
  *
  * @param value
  *         the value
  * @return the app system enum
  */
 public static AppRoleEnum from(String value) {
  AppRoleEnum[] values = AppRoleEnum.values();
  for (AppRoleEnum v : values) {
   if (StringUtils.equalsIgnoreCase(v.getValue(), value)) {
    return v;
   }
  }
  return null;
 }
}
