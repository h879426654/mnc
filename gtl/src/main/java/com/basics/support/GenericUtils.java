package com.basics.support;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@SuppressWarnings("rawtypes")
public class GenericUtils {

 @SuppressWarnings("unchecked")
 public static <T> Class<T> getSuperClassGenericType(Class clazz) {
  return getSuperClassGenericType(clazz, 0);
 }

 public static Class getSuperClassGenericType(Class clazz, int index) {
  Type genType = null;
  Class superclass = clazz;
  while (superclass != null) {
   genType = superclass.getGenericSuperclass();
   if ((genType instanceof ParameterizedType)) {
    break;
   }
   superclass = superclass.getSuperclass();
  }
  if (!(genType instanceof ParameterizedType)) {

   return Object.class;
  }

  Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

  if ((index >= params.length) || (index < 0)) {
   return Object.class;
  }
  if (!(params[index] instanceof Class)) {

   return Object.class;
  }
  return (Class) params[index];
 }

}
