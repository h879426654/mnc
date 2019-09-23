package com.basics.support.mybatis;

import org.apache.commons.lang3.StringUtils;

import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

public class TableCacheFilter implements CacheFilter {

 private String tableName;
 private INameMapper nameMapper = new DefaultNameMapper();

 public String getTableName() {
  return tableName;
 }

 public void setTableName(String tableName) {
  this.tableName = tableName;
 }

 public INameMapper getNameMapper() {
  return nameMapper;
 }

 public void setNameMapper(INameMapper nameMapper) {
  this.nameMapper = nameMapper;
 }

 public boolean accept(String appId, String cacheId) {
  String entityName = StringUtils.capitalize(this.nameMapper.getPropertyName(this.tableName));
  boolean accept = StringUtils.endsWith(cacheId, entityName);
  return accept;
 }

}
