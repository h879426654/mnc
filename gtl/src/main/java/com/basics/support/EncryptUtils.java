package com.basics.support;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

public final class EncryptUtils {
 public static String byteArrayToHexString(byte[] b) {
  StringBuilder sb = new StringBuilder();
  for (int i = 0; i < b.length; i++) {
   String hexString = Integer.toHexString(b[i] & 0xFF);
   if (hexString.length() == 1) {
    sb.append("0");
   }
   sb.append(hexString);
  }
  return sb.toString();
 }

 private static String encryptEncode(String algorithm, String origin) {
  String result = origin;
  try {
   MessageDigest md = MessageDigest.getInstance(algorithm);
   result = byteArrayToHexString(md.digest(result.getBytes()));
  } catch (NoSuchAlgorithmException ex) {
  }

  return result;
 }

 public static String MD5Encode(String origin) {
  return encryptEncode("MD5", origin);
 }

 public static String SHAEncode(String origin) {
  return encryptEncode("SHA-1", origin);
 }

 /**
  * 自定义的密码加密算法.
  * 
  * @param password
  * @return
  */
 public static String encodePassword(String password) {
  String passwordMd5 = MD5Encode(password);
  return passwordMd5.substring(8, 24).toString().toUpperCase();
 }

 public static boolean isPasswordMatch(String plainPassword, String encodedPassword) {
  if (StringUtils.isBlank(plainPassword) || StringUtils.isBlank(encodedPassword)) {
   return false;
  }
  return StringUtils.equals(encodePassword(plainPassword), encodedPassword);
 }
}