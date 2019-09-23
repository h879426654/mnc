package com.basics.common;

import java.util.Map;

import com.basics.support.QueryFilter;

public class QueryFilterUtil {
 public static QueryFilter buildQueryFilter(Map<String, Object> queryParams, String orderBy, Integer limit) {
  QueryFilter queryFilter = new QueryFilter();
  if (null != queryParams) {
   queryFilter.setParams(queryParams);
  }
  if (null != orderBy) {
   queryFilter.setOrderBy(orderBy);
  }
  if (null != limit) {
   queryFilter.setLimit(limit);
  }
  return queryFilter;
 }

}
