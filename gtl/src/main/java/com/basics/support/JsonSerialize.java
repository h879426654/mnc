package com.basics.support;

/**
 * JsonSerialize.
 * 
 * @author yuwenfeng.
 */
public interface JsonSerialize {

 /**
  * 将指定的对象序列化成json字符串
  * 
  * @param obj
  *         the obj
  * @return the string
  */
 public String toJson(Object obj);

 /**
  * 反序列json字符串到对象.
  * 
  * @param <T>
  *         the generic type
  * @param json
  *         the json
  * @param classOfT
  *         the class of t
  * @return the t
  */
 public <T> T fromJson(String json, Class<T> classOfT);

}
