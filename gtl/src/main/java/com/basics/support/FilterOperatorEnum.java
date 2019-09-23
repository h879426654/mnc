package com.basics.support;

import org.apache.commons.lang3.StringUtils;

public enum FilterOperatorEnum {

 // The filter operator (comparison).
 // The supported operators are: "eq" (equal to), "neq" (not equal to), "isnull"
 // (is equal to null), "isnotnull" (is not equal to null), "lt" (less than),
 // "lte" (less than or equal to), "gt" (greater than),
 // "gte" (greater than or equal to), "startswith", "endswith", "contains",
 // "isempty", "isnotempty".
 // The last five are supported only for string fields.

 /**
  * 
  */
 NULL("", "(无)"), EQ("eq", "(equal to)"), NEQ("neq", "(not equal to)"), IS_NULL("isnull", "(is equal to null)"), IS_NOT_NULL("isnotnull", "(is not equal to null)"), LT("lt", "(less than)"), LTE("lte", "(less than or equal to)"), GT("gt", "(greater than)"), GTE("gte", "(greater than or equal to)"), STARTSWITH("startswith", "startswith"), ENDWITH("endswith", "endswith"), CONTAINS("contains", "contains"), IS_EMPTY("isempty", "isempty"), IS_NOT_EMPTY("isnotempty", "isnotempty");

 private String message;
 private String value;

 private FilterOperatorEnum(String value, String message) {
  this.value = value;
  this.message = message;
 }

 public String getMessage() {
  return message;
 }

 public String getValue() {
  return value;
 }

 public static boolean in(String value, FilterOperatorEnum... values) {
  if (values == null) {
   return false;
  }
  for (FilterOperatorEnum v : values) {
   if (StringUtils.equalsIgnoreCase(v.getValue(), value)) {
    return true;
   }
  }
  return false;
 }

 public static boolean noIn(String value, FilterOperatorEnum... values) {
  if (values == null) {
   return true;
  }
  for (FilterOperatorEnum v : values) {
   if (StringUtils.equalsIgnoreCase(v.getValue(), value)) {
    return false;
   }
  }
  return true;
 }

 public static FilterOperatorEnum from(String value) {
  FilterOperatorEnum[] values = FilterOperatorEnum.values();
  for (FilterOperatorEnum v : values) {
   if (StringUtils.equalsIgnoreCase(v.getValue(), value)) {
    return v;
   }
  }
  return FilterOperatorEnum.NULL;
 }
}
