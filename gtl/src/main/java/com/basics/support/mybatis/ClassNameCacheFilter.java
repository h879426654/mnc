package com.basics.support.mybatis;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

public class ClassNameCacheFilter implements CacheFilter {

 private String className;
 private INameMapper nameMapper = new DefaultNameMapper();

 private boolean ignoreCase;
 private boolean ignorePackage;

 public String getClassName() {
  return className;
 }

 public void setClassName(String className) {
  this.className = className;
 }

 public boolean isIgnoreCase() {
  return ignoreCase;
 }

 public void setIgnoreCase(boolean ignoreCase) {
  this.ignoreCase = ignoreCase;
 }

 public boolean isIgnorePackage() {
  return ignorePackage;
 }

 public void setIgnorePackage(boolean ignorePackage) {
  this.ignorePackage = ignorePackage;
 }

 public INameMapper getNameMapper() {
  return nameMapper;
 }

 public void setNameMapper(INameMapper nameMapper) {
  this.nameMapper = nameMapper;
 }

 public boolean accept(String appId, String cacheId) {
  if (StringUtils.isBlank(this.className)) {
   return false;
  }
  boolean accept = false;
  String cacheShortClassName = ClassUtils.getShortClassName(cacheId);
  String thisShortClassName = ClassUtils.getShortClassName(this.className);
  if (this.ignoreCase) {
   if (this.ignorePackage) {
    accept = StringUtils.equalsIgnoreCase(cacheShortClassName, thisShortClassName);
   } else {
    accept = StringUtils.equalsIgnoreCase(cacheId, this.className);
   }
  } else {
   if (this.ignorePackage) {
    accept = StringUtils.equals(cacheShortClassName, thisShortClassName);
   } else {
    accept = StringUtils.equals(cacheId, this.className);
   }
  }
  return accept;
 }

}
