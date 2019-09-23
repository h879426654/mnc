package com.basics.support;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * QueryFilterBuilder.
 * 
 * @author yuwenfeng.
 */
public class QueryFilterBuilder {

 /** The filter. */
 private QueryFilter filter = new QueryFilter();

 /**
  * Builds the.
  * 
  * @return the query filter
  */
 public QueryFilter build() {
  return filter;
 }

 /**
  * Params.
  * 
  * @param params
  *         the params
  * @return the query filter builder
  */
 public QueryFilterBuilder params(Map<String, Object> params) {
  filter.setParams(params);
  return this;
 }

 /**
  * Offset.
  * 
  * @param offset
  *         the offset
  * @return the query filter builder
  */
 public QueryFilterBuilder offset(int offset) {
  filter.setOffset(offset);
  return this;
 }

 /**
  * Limit.
  * 
  * @param limit
  *         the limit
  * @return the query filter builder
  */
 public QueryFilterBuilder limit(int limit) {
  filter.setLimit(limit);
  return this;
 }

 /**
  * Order by.
  * 
  * @param orderBy
  *         the order by
  * @return the query filter builder
  */
 public QueryFilterBuilder orderBy(String orderBy) {
  filter.setOrderBy(orderBy);
  return this;
 }

 public QueryFilterBuilder put(String key, Object value) {
  // 查询条件trim:
  if (value != null && value instanceof String) {
   value = CommonSupport.trim((String) value);
  }
  filter.getParams().put(key, value);
  return this;
 }

 public QueryFilterBuilder putAll(Map<String, Object> params) {
  if (params == null) {
   return this;
  }
  // 查询条件trim:
  for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
   String key = it.next();
   Object value = params.get(key);
   if (value != null && value instanceof String) {
    value = CommonSupport.trim((String) value);
   }
   filter.getParams().put(key, value);
  }
  return this;
 }

 public QueryFilterBuilder remove(String key) {
  filter.getParams().remove(key);
  return this;
 }

 /**
  * 指定单个排序字段和排序方向.
  * 
  * @param sort
  *         排序字段.
  * @param order
  *         排序方向(asc/desc).
  * @return
  */
 public QueryFilterBuilder sortAndOrder(String sort, String order) {
  if (StringUtils.isBlank(sort)) {
   return this;
  }
  StringBuilder orderBy = new StringBuilder();
  orderBy.append(sort);
  if (StringUtils.equalsIgnoreCase("desc", order)) {
   orderBy.append(" desc");
  }
  filter.setOrderBy(orderBy.toString());
  return this;
 }

 /**
  * 按指定的字段排序.
  * 
  * @param fieldName
  *         属性名称.
  * @param isAscending
  *         是否升序
  * @return
  */
 public QueryFilterBuilder sortAndOrder(String fieldName, boolean isAscending) {
  return this.sortAndOrder(fieldName, isAscending ? "ASC" : "DESC");
 }
}
