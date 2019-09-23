package com.basics.support.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义springmvc类型转换器
 */
public class DateConverter implements Converter<String, Date> {

 private String[] parttens;

 public Date convert(String source) {
  if (null == source)
   return null;
  Date date = null;
  for (String partten : parttens) {
   try {
    date = new SimpleDateFormat(partten).parse(source);
   } catch (ParseException e) {
    continue;
   } finally {
    return date;
   }
  }
  return date;
 }

 public String[] getParttens() {
  return parttens;
 }

 public void setParttens(String[] parttens) {
  this.parttens = parttens;
 }
}
