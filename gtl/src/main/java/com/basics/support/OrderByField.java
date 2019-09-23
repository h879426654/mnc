package com.basics.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class OrderByField {

 private String tableName;

 private String fieldName;

 private String columnName;

 private boolean isAscending = true;

 INameMapper nameMapper;

 public OrderByField(INameMapper nameMapper, String tableName, String fieldName, boolean isAscending) {
  super();
  this.tableName = tableName;
  this.fieldName = nameMapper.getPropertyName(fieldName);
  this.columnName = nameMapper.getColumnName(fieldName);
  this.isAscending = isAscending;
 }

 public OrderByField(INameMapper nameMapper, String fieldName, boolean isAscending) {
  this(nameMapper, "", fieldName, isAscending);
 }

 public OrderByField(INameMapper nameMapper, String fieldNameAndAscending) {
  if (StringUtils.contains(fieldNameAndAscending, ".")) {
   this.tableName = StringUtils.substringBefore(fieldNameAndAscending, ".");
   fieldNameAndAscending = StringUtils.substringAfter(fieldNameAndAscending, ".");
  }
  String[] parts = StringUtils.split(fieldNameAndAscending, " ");
  if (parts == null) {
   return;
  }
  if (parts.length > 0) {
   fieldName = nameMapper.getPropertyName(parts[0]);
   this.columnName = nameMapper.getColumnName(fieldName);
  }
  if (parts.length > 1 && StringUtils.equalsIgnoreCase("DESC", parts[1])) {
   this.isAscending = false;
  }
 }

 public String getFieldName() {
  return fieldName;
 }

 public void setFieldName(String fieldName) {
  this.fieldName = fieldName;
 }

 public String getColumnName() {
  return columnName;
 }

 public void setColumnName(String columnName) {
  this.columnName = columnName;
 }

 public boolean isAscending() {
  return isAscending;
 }

 public void setAscending(boolean isAscending) {
  this.isAscending = isAscending;
 }

 public INameMapper getNameMapper() {
  return nameMapper;
 }

 public void setNameMapper(INameMapper nameMapper) {
  this.nameMapper = nameMapper;
 }

 public String getTableName() {
  return tableName;
 }

 public void setTableName(String tableName) {
  this.tableName = tableName;
 }

 public String toSql() {
  StringBuilder sb = new StringBuilder();
  if (StringUtils.isNotBlank(this.getColumnName())) {
   if (StringUtils.isNotBlank(this.getTableName())) {
    sb.append(this.getTableName()).append(".");
   }
   sb.append(this.getColumnName());
   if (!this.isAscending) {
    sb.append(" DESC");
   }
  }
  return sb.toString();
 }

 @Override
 public String toString() {
  return "OrderByField [fieldName=" + fieldName + ", columnName=" + columnName + ", isAscending=" + isAscending + "]";
 }

 public static List<OrderByField> build(INameMapper nameMapper, String orderByCause) {
  List<OrderByField> orderByFields = new ArrayList<OrderByField>();
  String[] parts = StringUtils.split(orderByCause, ",");
  if (parts != null && parts.length > 0) {
   for (String part : parts) {
    OrderByField orderByField = new OrderByField(nameMapper, part);
    if (StringUtils.isNotBlank(orderByField.getFieldName())) {
     orderByFields.add(orderByField);
    }
   }
  }
  return orderByFields;
 }

 public static String buildOrderBySql(INameMapper nameMapper, String orderByCause) {
  return buildOrderBySql(nameMapper, orderByCause, "");
 }

 public static String buildOrderBySql(INameMapper nameMapper, String orderByCause, String tableName) {
  StringBuilder sb = new StringBuilder();
  List<OrderByField> orderByFields = build(nameMapper, orderByCause);
  for (int i = 0; i < orderByFields.size(); i++) {
   OrderByField byField = orderByFields.get(i);
   if (StringUtils.isBlank(byField.getTableName())) {
    byField.setTableName(tableName);
   }
   if (i > 0) {
    sb.append(",");
   }
   sb.append(byField.toSql());
  }
  return sb.toString();
 }

 public static String buildOrderBySql(INameMapper nameMapper, String orderByCause, boolean isAscending) {
  return buildOrderBySql(nameMapper, orderByCause, "", isAscending);
 }

 public static String buildOrderBySql(INameMapper nameMapper, String orderByCause, String tableName, boolean isAscending) {
  StringBuilder sb = new StringBuilder();
  List<OrderByField> orderByFields = build(nameMapper, orderByCause);
  for (int i = 0; i < orderByFields.size(); i++) {
   OrderByField byField = orderByFields.get(i);
   if (StringUtils.isBlank(byField.getTableName())) {
    byField.setTableName(tableName);
   }
   if (i <= orderByFields.size() - 1) {
    byField.setAscending(isAscending);
   }
   if (i > 0) {
    sb.append(",");
   }
   sb.append(byField.toSql());
  }
  return sb.toString();
 }

 /**
  * Builds the order by sql.
  *
  * @param orderByFields
  *         the order by fields
  * @return the string
  */
 public static String buildOrderBySql(List<OrderByField> orderByFields) {
  StringBuilder sb = new StringBuilder();
  for (int i = 0; i < orderByFields.size(); i++) {
   OrderByField byField = orderByFields.get(i);
   if (i > 0) {
    sb.append(",");
   }
   sb.append(byField.toSql());
  }
  return sb.toString();
 }

}
