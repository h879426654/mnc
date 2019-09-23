package com.basics.support;

import java.util.HashMap;
import java.util.Map;

/**
 * QueryFilter.
 * 
 * @author yuwenfeng.
 */
public class QueryFilter implements java.io.Serializable {

 /** The Constant serialVersionUID. */
 private static final long serialVersionUID = 1679740967650334671L;

 /** The no limit. */
 public static int NO_LIMIT = -1;

 /** The no offset. */
 public static int NO_OFFSET = -1;

 /** The params. */
 private Map<String, Object> params = new HashMap<String, Object>();

 /** The order by. */
 private String orderBy;

 /** The limit. */
 private int limit = NO_LIMIT;

 /** The offset. */
 private int offset = NO_OFFSET;

 /**
  * Gets the params.
  * 
  * @return the params
  */
 public Map<String, Object> getParams() {
  return params;
 }

 /**
  * Sets the params.
  * 
  * @param params
  *         the params
  */
 public void setParams(Map<String, Object> params) {
  this.params = params;
 }

 /**
  * Gets the order by.
  * 
  * @return the order by
  */
 public String getOrderBy() {
  return orderBy;
 }

 /**
  * Sets the order by.
  * 
  * @param orderBy
  *         the new order by
  */
 public void setOrderBy(String orderBy) {
  this.orderBy = orderBy;
 }

 /**
  * Gets the limit.
  * 
  * @return the limit
  */
 public int getLimit() {
  return limit;
 }

 /**
  * Sets the limit.
  * 
  * @param limit
  *         the new limit
  */
 public void setLimit(int limit) {
  this.limit = limit;
 }

 /**
  * Gets the offset.
  * 
  * @return the offset
  */
 public int getOffset() {
  return offset;
 }

 /**
  * Sets the offset.
  * 
  * @param offset
  *         the new offset
  */
 public void setOffset(int offset) {
  this.offset = offset;
 }

 /**
  * Checks for limit.
  * 
  * @return true, if successful
  */
 public boolean hasLimit() {
  return this.limit != NO_LIMIT && this.limit > 0;
 }

 /**
  * Checks for offset.
  * 
  * @return true, if successful
  */
 public boolean hasOffset() {
  return this.offset != NO_OFFSET && this.offset >= 0;
 }

}
