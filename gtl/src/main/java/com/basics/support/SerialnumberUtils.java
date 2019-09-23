package com.basics.support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 流水号生成
 */
public class SerialnumberUtils {

 /**
  * 生成时间流水号
  * 
  * @return
  */
 public static synchronized String generate() {
  return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
 }

 /**
  * 生成带前缀的订单号
  * 
  * @param prefix
  * @param uppcase
  * @return
  */
 public static String generateUsePrefix(String prefix, boolean uppcase) {
  if (uppcase) {
   return prefix.toUpperCase() + generate();
  } else {
   return prefix + generate();
  }
 }

}
