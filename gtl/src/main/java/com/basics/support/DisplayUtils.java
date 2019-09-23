package com.basics.support;

import org.apache.commons.lang.StringUtils;

/**
 * DisplayUtils.
 */
public class DisplayUtils {

 /**
  * 消息隐藏.
  * <p>
  * 
  * DisplayUtils.hiden("13312345678", 3, 4);->头三尾四返回：133****5678
  * </p>
  * 
  * @param information
  *         输入的消息.
  * @param firstCharNum
  *         显示的开始字符长度.
  * @param lastCharNum
  *         显示的尾部字符长度.
  * @return 将输入消息的中部用*替换.
  */
 public static String hiden(String information, int firstCharNum, int lastCharNum) {
  // 空串,给默认的*
  if (StringUtils.isBlank(information)) {
   return StringUtils.repeat("*", 8);
  }
  if (firstCharNum < 1) {
   firstCharNum = 1;
  }
  if (lastCharNum < 0) {
   lastCharNum = 0;
  }
  // 显示的字符
  if (firstCharNum + lastCharNum >= information.length()) {
   return StringUtils.repeat("*", information.length());
  }
  StringBuilder sb = new StringBuilder();
  int informationLength = information.length();
  // 开始部分.
  String firstPart = StringUtils.left(information, firstCharNum);
  if (StringUtils.isNotBlank(firstPart)) {
   sb.append(firstPart);
  } else {
   sb.append(StringUtils.repeat("*", firstCharNum));
  }
  // 中间部分.
  int middleLength = informationLength - firstCharNum - lastCharNum;
  if (middleLength < 1) {
   middleLength = 1;
  }
  sb.append(StringUtils.repeat("*", middleLength));
  // 最后部分.
  String lastPart = StringUtils.right(information, lastCharNum);
  if (StringUtils.isNotBlank(lastPart)) {
   sb.append(lastPart);
  } else {
   sb.append(StringUtils.repeat("*", lastCharNum));
  }
  return sb.toString();
 }

}
