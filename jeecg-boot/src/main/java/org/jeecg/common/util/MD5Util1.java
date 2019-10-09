package org.jeecg.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Util1 {
 // 全局数组
 private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

 public MD5Util1() {

 }

 // 返回形式为数字跟字符串
 private static String byteToArrayString(byte bByte) {
  int iRet = bByte;
  if (iRet < 0) {
   iRet += 256;
  }
  int iD1 = iRet / 16;
  int iD2 = iRet % 16;
  return strDigits[iD1] + strDigits[iD2];
 }

 // 返回形式只为数字
 @SuppressWarnings("unused")
 private static String byteToNum(byte bByte) {
  int iRet = bByte;
  if (iRet < 0) {
   iRet += 256;
  }
  return String.valueOf(iRet);
 }

 // 转换字节数组为16进制字串
 private static String byteToString(byte[] bByte) {
  StringBuffer sBuffer = new StringBuffer();
  for (int i = 0; i < bByte.length; i++) {
   sBuffer.append(byteToArrayString(bByte[i]));
  }
  return sBuffer.toString();
 }

 public static String GetMD5Code(String strObj) {
  String resultString = null;
  try {
   resultString = new String(strObj);
   MessageDigest md = MessageDigest.getInstance("MD5");
   // md.digest() 该函数返回值为存放哈希值结果的byte数组
   resultString = byteToString(md.digest(strObj.getBytes()));
  } catch (NoSuchAlgorithmException ex) {
   ex.printStackTrace();
  }
  return resultString;
 }

 public static String random(int n) {
  if (n < 1 || n > 10) {
   throw new IllegalArgumentException("cannot random " + n + " bit number");
  }
  Random ran = new Random();
  if (n == 1) {
   return String.valueOf(ran.nextInt(10));
  }
  int bitField = 0;
  char[] chs = new char[n];
  for (int i = 0; i < n; i++) {
   while (true) {
    int k = ran.nextInt(10);
    if ((bitField & (1 << k)) == 0) {
     bitField |= 1 << k;
     chs[i] = (char) (k + '0');
     break;
    }
   }
  }
  return new String(chs);
 }

 public static void main(String[] args) {
		System.out.println(GetMD5Code(GetMD5Code("123456")));
 }
}
