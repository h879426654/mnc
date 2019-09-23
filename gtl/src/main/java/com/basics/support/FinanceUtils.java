package com.basics.support;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jodd.bean.BeanUtil;

public class FinanceUtils {

 public static double sumFields(Object bean, String... fields) {
  List<Number> paramList = new ArrayList<Number>();
  for (String field : fields) {
   Number fieldNumber = (Number) BeanUtil.getPropertySilently(bean, field);
   if (fieldNumber != null) {
    paramList.add(fieldNumber);
   }
  }
  return sum(paramList);
 }

 public static double sum(List<Number> paramList) {
  double d = 0.0D;
  Iterator localIterator = paramList.iterator();
  while (localIterator.hasNext()) {
   Number localNumber = (Number) localIterator.next();
   d += localNumber.doubleValue();
  }
  return d;
 }

 public static double mean(List<Number> paramList) {
  double d = sum(paramList);
  return d / paramList.size();
 }

 public static double min(List<Number> paramList) {
  double d1 = 2.147483647E9D;
  Iterator localIterator = paramList.iterator();
  while (localIterator.hasNext()) {
   Number localNumber = (Number) localIterator.next();
   double d2 = localNumber.doubleValue();
   if (d2 < d1) {
    d1 = d2;
   }
  }
  return d1;
 }

 public static double max(List<Number> paramList) {
  double d1 = -2.147483648E9D;
  Iterator localIterator = paramList.iterator();
  while (localIterator.hasNext()) {
   Number localNumber = (Number) localIterator.next();
   double d2 = localNumber.doubleValue();
   if (d2 > d1) {
    d1 = d2;
   }
  }
  return d1;
 }

 public static String formatPercent(Number number) {
  try {
   return NumberFormat.getPercentInstance().format(number);
  } catch (Throwable e) {
   LogUtils.performance.error("{}", e);
   return "";
  }
 }

 /**
  * 两个Double数相加
  * 
  * @param v1
  * @param v2
  * @return Double
  */
 public static Double add(Object bean, String field1, String field2) {
  BigDecimal b1;
  BigDecimal b2;
  if (null == field1 || StringUtils.isBlank(field1)) {
   b1 = new BigDecimal(0d);
  } else {
   Number n1 = (Number) BeanUtil.getPropertySilently(bean, field1);
   if (null == n1) {
    b1 = new BigDecimal(0d);
   } else {
    b1 = new BigDecimal(n1.doubleValue());
   }
  }
  if (null == field2 || StringUtils.isBlank(field2)) {
   b2 = new BigDecimal(0d);
  } else {
   Number n2 = (Number) BeanUtil.getPropertySilently(bean, field2);
   if (null == n2) {
    b2 = new BigDecimal(0d);
   } else {
    b2 = new BigDecimal(n2.doubleValue());
   }
  }
  return b1.add(b2).doubleValue();
 }

 /**
  * 两个Double数相减
  * 
  * @param v1
  * @param v2
  * @return Double
  */
 public static Double sub(Object bean, String field1, String field2) {
  BigDecimal b1;
  BigDecimal b2;
  if (null == field1 || StringUtils.isBlank(field1)) {
   b1 = new BigDecimal(0d);
  } else {
   Number n1 = (Number) BeanUtil.getPropertySilently(bean, field1);
   if (null == n1) {
    b1 = new BigDecimal(0d);
   } else {
    b1 = new BigDecimal(n1.doubleValue());
   }
  }
  if (null == field2 || StringUtils.isBlank(field2)) {
   b2 = new BigDecimal(0d);
  } else {
   Number n2 = (Number) BeanUtil.getPropertySilently(bean, field2);
   if (null == n2) {
    b2 = new BigDecimal(0d);
   } else {
    b2 = new BigDecimal(n2.doubleValue());
   }
  }
  return b1.subtract(b2).doubleValue();
 }

 /**
  * 对Double数值四舍五入，保留两位小数
  * 
  * @param paramValue
  * @return
  */
 public static double getDoubleRound(double paramValue) {
  BigDecimal b = new BigDecimal(paramValue);
  double roundValue = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
  return roundValue;
 }

 /**
  * 数字格式化，每三个加一个逗号
  * 
  * @param data
  * @return
  */
 public static String formatTosepara(double data) {
  DecimalFormat df = new DecimalFormat("#,###.00");
  return df.format(data);
 }

 public static void main(String[] args) {
  System.out.println(formatTosepara(211234333.23));
 }
}
