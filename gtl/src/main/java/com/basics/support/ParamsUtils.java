package com.basics.support;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 查询参数工具类.
 * 
 * @author yuwenfeng.
 */
public class ParamsUtils {

 public static final String DEFAULT_DATE_ENDS_WITH = "End";

 public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

 /**
  * 返回查询参数结束日期值. 例如:2013-1-1 返回2013-1-1 23:59:59 日期值.
  * 
  * @param date
  *         指定的结束日期.
  * @return 返回指定日期所在天的最后一秒.
  */
 private static Date getQueryParameterDateEndValue(Date date) {
  Calendar cal = Calendar.getInstance();
  cal.setTime(date);
  cal.set(Calendar.HOUR_OF_DAY, 23);
  cal.set(Calendar.MINUTE, 59);
  cal.set(Calendar.SECOND, 59);
  return cal.getTime();
 }

 /**
  * 将指定的查询参数(String)转换成日期(Date)类型.
  * 
  * @param queryParameterMap
  *         查询参数Map.
  * @param dateFormat
  *         查询参数(String)所使用的日期格式.例如:yyyy-MM-dd
  * @param paramNames
  *         需要转换的参数名称数组.
  */
 public static Map<String, Object> converQueryParameterStringToDate(Map<String, Object> queryParameterMap, String... paramNames) {
  if (paramNames == null || paramNames.length == 0) {
   return queryParameterMap;
  }
  Map<String, Object> newQueryParameterMap = new HashMap<String, Object>();
  newQueryParameterMap.putAll(queryParameterMap);
  converQueryParameterStringToDate(newQueryParameterMap, DEFAULT_DATE_FORMAT, DEFAULT_DATE_ENDS_WITH, paramNames);
  return newQueryParameterMap;
 }

 /**
  * 将指定的查询参数(String)转换成日期(Date)类型.
  * 
  * @param queryParameterMap
  *         查询参数Map.
  * @param dateFormat
  *         查询参数(String)所使用的日期格式.例如:yyyy-MM-dd
  * @param paramNames
  *         需要转换的参数名称数组.
  */
 public static void converQueryParameterStringToDate(Map<String, Object> queryParameterMap, String dateFormat, String endsWith,
  String[] paramNames) {
  for (String paramName : paramNames) {
   if (queryParameterMap.containsKey(paramName)) {
    Object paramValueObject = queryParameterMap.get(paramName);
    if (paramValueObject instanceof String) {
     String paramString = (String) paramValueObject;
     try {
      Date paramDate = DateUtils.parseDate(paramString, new String[] { dateFormat });
      if (StringUtils.isNotBlank(endsWith) && paramName.endsWith(endsWith)) {
       paramDate = getQueryParameterDateEndValue(paramDate);
       queryParameterMap.put(paramName, paramDate);
      } else {
       queryParameterMap.put(paramName, paramDate);
      }
     } catch (ParseException e) {
      queryParameterMap.put(paramName, null);
     }
    }
   }
  }
 }

 /**
  * 将指定的查询参数(String)转换成整数(Integer)类型.
  * 
  * @param queryParameterMap
  *         查询参数Map.
  * @param paramNames
  *         需要转换的参数名称数组.
  */
 public static void converQueryParameterStringToInteger(Map<String, Object> queryParameterMap, String[] paramNames) {
  for (String paramName : paramNames) {
   if (queryParameterMap.containsKey(paramName)) {
    Object paramValueObject = queryParameterMap.get(paramName);
    if (paramValueObject instanceof String) {
     String paramString = (String) paramValueObject;
     try {
      Integer paramInteger = Integer.parseInt(paramString);
      queryParameterMap.put(paramName, paramInteger);
     } catch (Exception e) {
      queryParameterMap.put(paramName, null);
     }
    }
   }
  }
 }

 /**
  * 将指定的查询参数(String)转换成BigDecimal类型.
  * 
  * @param queryParameterMap
  *         查询参数Map.
  * @param paramNames
  *         需要转换的参数名称数组.
  */
 public static void converQueryParameterStringToBigDecimal(Map<String, Object> queryParameterMap, String[] paramNames) {
  for (String paramName : paramNames) {
   if (queryParameterMap.containsKey(paramName)) {
    Object paramValueObject = queryParameterMap.get(paramName);
    if (paramValueObject instanceof String) {
     String paramString = (String) paramValueObject;
     try {
      java.math.BigDecimal paramBigDecimal = java.math.BigDecimal.valueOf(Double.valueOf(paramString));
      queryParameterMap.put(paramName, paramBigDecimal);
     } catch (Exception e) {
      queryParameterMap.put(paramName, null);
     }
    }
   }
  }
 }

 /**
  * 构建查询参数.
  * 
  * @param args
  *         参数名1,参数值1...
  * @return
  */
 public static Map<String, Object> build(Object... args) {
  Map<String, Object> params = new HashMap<String, Object>();
  if (args != null) {
   for (int i = 0; i < args.length - 1; i = i + 2) {
    if (args[i] == null) {
     continue;
    }
    String key = args[i].toString();
    Object value = args[i + 1];
    params.put(key, value);
   }
  }
  return params;
 }
}
