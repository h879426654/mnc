package com.basics.support;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.apache.commons.lang.StringUtils;

/**
 * CRCUtils.
 * 
 * @author yuwenfeng.
 */
public class CRCUtils {

 /**
  * Generate crc32.
  * 
  * @param bytes
  *         the bytes
  * @return the string
  */
 public static String generateCRC32(byte[] bytes) {
  Checksum checksum = new CRC32();
  checksum.update(bytes, 0, bytes.length);
  long lngChecksum = checksum.getValue();
  return Long.toHexString(lngChecksum);
 }

 /**
  * Generate crc32.
  * 
  * @param keys
  *         the keys
  * @return the string
  */
 public static String generateCRC32(String... keys) {
  if (keys == null || keys.length == 0) {
   return "";
  } else {
   return generateCRC32(StringUtils.join(keys, "_").getBytes());
  }
 }

}
