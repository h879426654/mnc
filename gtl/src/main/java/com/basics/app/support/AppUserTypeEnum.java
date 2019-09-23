package com.basics.app.support;

import org.apache.commons.lang3.StringUtils;

import com.basics.support.CommonSupport;

/**
 * 用户类型枚举
 * 
 * @author yuwenfeng
 *
 */
public enum AppUserTypeEnum {

 HQ(10000, "总部", AppRoleEnum.HQ), ADMIN(1, "系统管理员", AppRoleEnum.ADMIN);

 private String message;
 private int value;
 private AppRoleEnum defaultRole;

 private AppUserTypeEnum(int value, String message, AppRoleEnum defaultRole) {
  this.value = value;
  this.message = message;
  this.defaultRole = defaultRole;
 }

 public String getMessage() {
  return message;
 }

 public int getValue() {
  return value;
 }

 public AppRoleEnum getDefaultRole() {
  return defaultRole;
 }

 public static boolean in(int value, AppUserTypeEnum... values) {
  if (values == null) {
   return false;
  }
  for (AppUserTypeEnum v : values) {
   if (v.getValue() == value) {
    return true;
   }
  }
  return false;
 }

 public static boolean noIn(int value, AppUserTypeEnum... values) {
  if (values == null) {
   return true;
  }
  for (AppUserTypeEnum v : values) {
   if (v.getValue() == value) {
    return false;
   }
  }
  return true;
 }

 public static AppUserTypeEnum from(String value) {
  AppUserTypeEnum[] values = AppUserTypeEnum.values();
  for (AppUserTypeEnum v : values) {
   if (StringUtils.equalsIgnoreCase(String.valueOf(v.getValue()), value)) {
    return v;
   }
  }
  return null;
 }

 public static AppUserTypeEnum from(Integer value) {
  if (value == null) {
   return null;
  }
  AppUserTypeEnum[] values = AppUserTypeEnum.values();
  for (AppUserTypeEnum v : values) {
   if (CommonSupport.ObjectUtils.equals(v.value, value)) {
    return v;
   }
  }
  return null;
 }

 public boolean is(Integer type) {
  return type != null && type.equals(this.getValue());
 }
}
