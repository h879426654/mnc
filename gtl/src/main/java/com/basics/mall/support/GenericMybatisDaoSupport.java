package com.basics.mall.support;

import com.basics.support.IDGenerationType;

public class GenericMybatisDaoSupport<T> extends com.basics.support.GenericMybatisDao<T> {

 public String getQualifierSessionFactory() {
 return "app.sqlSessionFactory";
 }

 public void onSetting() {
 this.idGenerationType = IDGenerationType.UUID;
 }
 }
