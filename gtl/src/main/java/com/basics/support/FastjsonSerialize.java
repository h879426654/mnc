package com.basics.support;

import com.alibaba.fastjson.JSON;

/**
 * FastjsonSerialize.
 * 
 * @author yuwenfeng.
 * 
 */
public class FastjsonSerialize implements JsonSerialize {

 public String toJson(Object obj) {
  return JSON.toJSONString(obj);
 }

 public <T> T fromJson(String json, Class<T> classOfT) {
  return JSON.parseObject(json, classOfT);
 }
}
