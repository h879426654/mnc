package com.basics.support;

import java.nio.charset.Charset;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.apache.commons.lang.StringUtils;

/**
 * GuidUtils.
 * 
 * @author yuwenfeng.
 */
public class GuidUtils {

 private static final String CHARSET_NAME = "UTF-8";

 /**
  * Generate default guid.
  * 
  * @return the string
  */
 public static String generateDefaultGuid() {
  return java.util.UUID.randomUUID().toString();
 }

 /**
  * 主键
  * Generate simple guid.
  * 
  * @return the string
  */
 public static String generateSimpleGuid() {
  return java.util.UUID.randomUUID().toString().replaceAll("-", "");
 }

 /**
  * Generate default long guid.
  * 
  * @return the long
  */
 public static long generateDefaultLongGuid() {
  return generateCheckSumCRC32(generateDefaultGuid().getBytes(Charset.forName(CHARSET_NAME)));
 }

 /**
  * Generate simple long guid.
  * 
  * @return the long
  */
 public static long generateSimpleLongGuid() {
  return generateCheckSumCRC32(generateSimpleGuid().getBytes(Charset.forName(CHARSET_NAME)));
 }

 /**
  * Generate default long guid hex.
  * 
  * @return the string
  */
 public static String generateDefaultLongGuidHex() {
  return generateCheckSumCRC32Hex(generateDefaultGuid().getBytes(Charset.forName(CHARSET_NAME)));
 }

 /**
  * Generate simple long guid hex.
  * 
  * @return the string
  */
 public static String generateSimpleLongGuidHex() {
  return generateCheckSumCRC32Hex(generateSimpleGuid().getBytes(Charset.forName(CHARSET_NAME)));
 }

 /**
  * Generate check sum cr c32.
  * 
  * @param bytes
  *         the bytes
  * @return the long
  */
 public static long generateCheckSumCRC32(byte[] bytes) {
  Checksum checksum = new CRC32();
  checksum.update(bytes, 0, bytes.length);
  return checksum.getValue();
 }

 /**
  * Generate check sum cr c32 hex.
  * 
  * @param bytes
  *         the bytes
  * @return the string
  */
 public static String generateCheckSumCRC32Hex(byte[] bytes) {
  Checksum checksum = new CRC32();
  checksum.update(bytes, 0, bytes.length);
  long lngChecksum = checksum.getValue();
  return Long.toHexString(lngChecksum);
 }

 /**
  * Generate.
  * 
  * @param fields
  *         the fields
  * @return the string
  */
 public static String generate(String... fields) {
  if (fields == null || fields.length == 0) {
   throw new RuntimeException("generate need fields.");
  }
  StringBuilder sb = new StringBuilder();
  for (String field : fields) {
   if (StringUtils.isBlank(field)) {
    throw new RuntimeException("generate need fields.");
   }
   sb.append(generateCheckSumCRC32Hex(field.getBytes(Charset.forName(CHARSET_NAME))));
  }
  return sb.toString();
 }

 /**
  * The main method.
  * 
  * @param args
  *         the arguments
  */
 public static void main(String[] args) {
  LogUtils.performance.error("generateDefaultGuid:{}", generateDefaultGuid());
  LogUtils.performance.error("generateSimpleGuid:{}", generateSimpleGuid());
  LogUtils.performance.error("generateDefaultLongGuid:{}", generateDefaultLongGuid());
  LogUtils.performance.error("generateSimpleLongGuid:{}", generateSimpleLongGuid());
  LogUtils.performance.error("generateDefaultLongGuidHex:{}", generateDefaultLongGuidHex());
  LogUtils.performance.error("generateSimpleLongGuidHex:{}", generateSimpleLongGuidHex());
  String[] fields = new String[] { "A", "B", "C" };
  LogUtils.performance.error("generate({})={}", fields, generate(fields));
  String[] ids = new String[] { generateSimpleGuid(), generateSimpleGuid() };
  LogUtils.performance.error("generate({})={}", ids, generate(ids));
 }
}
