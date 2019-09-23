package com.basics.support;

/**
 * The Interface INameMapper. Java Bean 属性名称和数据库字段名称的映射.
 * 
 * @author yuwenfeng
 */
public interface INameMapper {

 /** The Constant UNDERCORE. */
 public static final String UNDERCORE = "_";

 /**
  * Gets the property name.
  * 
  * @param columnName
  *         the column name
  * @return the property name
  */
 public String getPropertyName(String columnName);

 /**
  * Gets the column name.
  * 
  * @param propertyName
  *         the property name
  * @return the column name
  */
 public String getColumnName(String propertyName);
}
