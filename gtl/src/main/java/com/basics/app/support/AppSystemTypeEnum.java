package com.basics.app.support;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统类型枚举
 * 
 * @author yuwenfeng
 *
 */
public enum AppSystemTypeEnum {

 BACKEND(0, "后台"), FRONTEND(1, "前台"), APP(10, "APP");

 private String message;
 private int value;

 private AppSystemTypeEnum(int value, String message) {
  this.value = value;
  this.message = message;
 }

 public String getMessage() {
  return message;
 }

 public int getValue() {
  return value;
 }

 public static boolean in(int value, AppSystemTypeEnum... values) {
  if (values == null) {
   return false;
  }
  for (AppSystemTypeEnum v : values) {
   if (v.getValue() == value) {
    return true;
   }
  }
  return false;
 }

 public static boolean noIn(int value, AppSystemTypeEnum... values) {
  if (values == null) {
   return true;
  }
  for (AppSystemTypeEnum v : values) {
   if (v.getValue() == value) {
    return false;
   }
  }
  return true;
 }

 public static AppSystemTypeEnum from(String value) {
  AppSystemTypeEnum[] values = AppSystemTypeEnum.values();
  for (AppSystemTypeEnum v : values) {
   if (StringUtils.equalsIgnoreCase(String.valueOf(v.getValue()), value)) {
    return v;
   }
  }
  return null;
 }

}
