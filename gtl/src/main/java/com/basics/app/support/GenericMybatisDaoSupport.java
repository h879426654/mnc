package com.basics.app.support;

public class GenericMybatisDaoSupport<T> extends com.basics.support.GenericMybatisDao<T> {

 @Override
 public String getQualifierSessionFactory() {
  return "app.sqlSessionFactory";
 }

}
