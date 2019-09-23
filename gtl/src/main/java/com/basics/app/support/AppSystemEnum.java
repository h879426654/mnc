package com.basics.app.support;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统枚举.
 * 
 * @author yuwenfeng.
 */
public enum AppSystemEnum {

 BACKEND_ADMIN("BACKEND_ADMIN", "系统管理后台", AppSystemTypeEnum.BACKEND, AppSystemAppTypeEnum.WEB), //
 /** The backend hq. */
 BACKEND_HQ("BACKEND_HQ", "全国总后台", AppSystemTypeEnum.BACKEND, AppSystemAppTypeEnum.WEB);

 private String message;

 /** The value. */
 private String value;

 /** The type. */
 private AppSystemTypeEnum type;

 private AppSystemAppTypeEnum appType;

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
 private AppSystemEnum(String value, String message, AppSystemTypeEnum type, AppSystemAppTypeEnum appType) {
  this.value = value;
  this.message = message;
  this.type = type;
  this.appType = appType;
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
  * Gets the type.
  *
  * @return the type
  */
 public AppSystemTypeEnum getType() {
  return type;
 }

 public AppSystemAppTypeEnum getAppType() {
  return appType;
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
 public static boolean in(String value, AppSystemEnum... values) {
  if (values == null) {
   return false;
  }
  for (AppSystemEnum v : values) {
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
 public static boolean noIn(String value, AppSystemEnum... values) {
  if (values == null) {
   return true;
  }
  for (AppSystemEnum v : values) {
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
 public static AppSystemEnum from(String value) {
  AppSystemEnum[] values = AppSystemEnum.values();
  for (AppSystemEnum v : values) {
   if (StringUtils.equalsIgnoreCase(v.getValue(), value)) {
    return v;
   }
  }
  return null;
 }
}
