package com.basics.support;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class FastjsonUtils {

 public static String string(Object object) {
  return JSON.toJSONString(object);
 }

 public static final <T> T object(String text, Class<T> clazz) {
  return JSON.parseObject(text, clazz);
 }

 public static final <T> List<T> array(String text, Class<T> clazz) {
  return JSON.parseArray(text, clazz);
 }

}
