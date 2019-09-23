package com.basics.app.support;

import org.apache.commons.lang3.StringUtils;

/**
 * App类型枚举.
 * 
 * @author yuwenfeng
 *
 */
public enum AppSystemAppTypeEnum {

 WEB(0, "WEB"), NATIVE(1, "NATIVE");

 private String message;
 private int value;

 private AppSystemAppTypeEnum(int value, String message) {
  this.value = value;
  this.message = message;
 }

 public String getMessage() {
  return message;
 }

 public int getValue() {
  return value;
 }

 public static boolean in(int value, AppSystemAppTypeEnum... values) {
  if (values == null) {
   return false;
  }
  for (AppSystemAppTypeEnum v : values) {
   if (v.getValue() == value) {
    return true;
   }
  }
  return false;
 }

 public static boolean noIn(int value, AppSystemAppTypeEnum... values) {
  if (values == null) {
   return true;
  }
  for (AppSystemAppTypeEnum v : values) {
   if (v.getValue() == value) {
    return false;
   }
  }
  return true;
 }

 public static AppSystemAppTypeEnum from(String value) {
  AppSystemAppTypeEnum[] values = AppSystemAppTypeEnum.values();
  for (AppSystemAppTypeEnum v : values) {
   if (StringUtils.equalsIgnoreCase(String.valueOf(v.getValue()), value)) {
    return v;
   }
  }
  return null;
 }

}
