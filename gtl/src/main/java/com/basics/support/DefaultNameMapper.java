package com.basics.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * 默认的命名映射. 数据库命名规则: 1.列名全部大写.例如:USERNAME.
 * 2.多个单词用下划线分开.例如:USER_NAME,USER_PASSWORD. <CODE>
属性->列名:username>USERNAME
列名->属性:USERNAME>username
属性->列名:Username>USERNAME
属性->列名:userName>USER_NAME
属性->列名:UserName>USER_NAME
列名->属性:USER_NAME>userName
 * 
 * @author yuwenfeng. </CODE>
 */
public class DefaultNameMapper implements INameMapper {

 /** The field column name override. */
 protected Map<String, String> fieldColumnNameConfig = new java.util.concurrent.ConcurrentHashMap<String, String>();

 /**
  * Config field column name.
  *
  * @param field
  *         the field
  * @param column
  *         the column
  * @return the default name mapper
  */
 public DefaultNameMapper configFieldColumnName(String field, String column) {
  this.fieldColumnNameConfig.put(field, column);
  return this;
 }

 /**
  * Gets the field column name config.
  *
  * @return the field column name config
  */
 public Map<String, String> getFieldColumnNameConfig() {
  return fieldColumnNameConfig;
 }

 /**
  * Sets the field column name config.
  *
  * @param fieldColumnNameConfig
  *         the field column name config
  */
 public void setFieldColumnNameConfig(Map<String, String> fieldColumnNameConfig) {
  this.fieldColumnNameConfig = fieldColumnNameConfig;
 }

 /**
  * 根据Java命名约定判断指定的名称是否为属性名. 1.首字母小写 2.首个单词小写.(多个单词不用_连接)
  *
  * @param name
  *         the name
  * @return true, if checks if is property name
  */
 public boolean isPropertyName(String name) {
  if (StringUtils.contains(name, INameMapper.UNDERCORE)) {
   return false;
  }
  return StringUtils.equals(StringUtils.uncapitalize(name), name);
 }

 /*
  * (non-Javadoc)
  * 
  * @see
  * com.whcyit.framework.ormlite.INameMapper#getPropertyName(java.lang.String)
  */
 public String getPropertyName(String columnName) {
  if (StringUtils.isBlank(columnName)) {
   return "";
  }
  if (this.isPropertyName(columnName)) {
   return columnName;
  }
  String[] parts = StringUtils.split(columnName.toUpperCase(), INameMapper.UNDERCORE);
  if (parts != null && parts.length > 0) {
   List<String> properties = new ArrayList<String>();
   for (String part : parts) {
    if (StringUtils.isNotBlank(part)) {
     if (properties.isEmpty()) {
      properties.add(part.toLowerCase());
     } else {
      properties.add(StringUtils.capitalize(part.toLowerCase()));
     }
    }
   }
   if (!properties.isEmpty()) {
    return StringUtils.join(properties.iterator(), "");
   }
  }
  return "";
 }

 /*
  * (non-Javadoc)
  * 
  * @see
  * com.whcyit.framework.ormlite.INameMapper#getColumnName(java.lang.String)
  */
 public String getColumnName(String propertyName) {
  if (StringUtils.isBlank(propertyName)) {
   return "";
  }
  // 优先读取配置.
  if (this.fieldColumnNameConfig.containsKey(propertyName)) {
   return this.fieldColumnNameConfig.get(propertyName);
  }
  // 按命名约定生成列名.
  List<String> words = new ArrayList<String>();
  int wordIndex = 0;
  for (int i = 1; i < propertyName.length() - 1; i++) {
   String thisOne = StringUtils.substring(propertyName, i, i + 1);
   if (StringUtils.equals(thisOne.toUpperCase(), thisOne)) {
    words.add(StringUtils.substring(propertyName, wordIndex, i).toUpperCase());
    wordIndex = i;
   }
  }
  words.add(StringUtils.substring(propertyName, wordIndex).toUpperCase());
  String columnName = StringUtils.join(words.iterator(), INameMapper.UNDERCORE);
  // 保存配置.
  this.fieldColumnNameConfig.put(propertyName, columnName);
  return columnName;
 }

 /**
  * The main method.
  * 
  * @param args
  *         the arguments
  */
 public static void main(String[] args) {
  DefaultNameMapper mapper = new DefaultNameMapper();
  String[] props = new String[] { "username", "Username", "userName", "UserName" };
  for (String prop : props) {
   String column = mapper.getColumnName(prop);
   LogUtils.performance.info("属性->列名:{}>{}", prop, column);
   String columnToProp = mapper.getPropertyName(column);
   LogUtils.performance.info("列名->属性:{}>{}", column, columnToProp);
  }

 }
}
