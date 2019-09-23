package com.basics.support.jsonSerializers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * json日期类型反序列化
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {

 private DateFormat[] formats = { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-ddHH:mm:ss"),
  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"), new SimpleDateFormat("yyyy/MM/ddHH:mm:ss"), new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"),
  new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss"), };

 @Override
 public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
  Date date = null;
  for (DateFormat dateFormat : formats) {
   try {
   	String text = p.getText();
    date = dateFormat.parse(text);
   } catch (ParseException e) {
    continue;
   }
  }
  return date;
 }
}
