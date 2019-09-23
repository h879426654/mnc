package com.basics.support;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DebugSupport {

 public JsonSerialize jsonSerialize = new Jackson2() {
  public void onConfig(ObjectMapper mapper) {
   mapper.setSerializationInclusion(Include.NON_NULL);
  }
 };

 public String string(Object response) {
  return jsonSerialize.toJson(response);
 }

 public <T> T object(String json, Class<T> classOfT) {
  return jsonSerialize.fromJson(json, classOfT);
 }

 public void show(String header, String message) {
  if (LogUtils.performance.isInfoEnabled()) {
   System.out.println("=============================");
   System.out.println(header);
   System.out.println(message);
   System.out.println("=============================");
  }
 }

 public void show(String header, Object message) {
  if (LogUtils.performance.isInfoEnabled()) {
   System.out.println("=============================");
   System.out.println(header);
   System.out.println(string(message));
   System.out.println("=============================");
  }
 }

 public void show(String header, Object request, Object response) {
  if (LogUtils.performance.isInfoEnabled()) {
   System.out.println("=============================");
   System.out.println(String.format("var %sRequest  = %s; ", header, string(request)));
   System.out.println(String.format("var %sResponse = %s; ", header, string(response)));
   System.out.println("=============================");
  }
 }

 public void show(String header, Object request, Object response, String url) {
  if (LogUtils.performance.isInfoEnabled()) {
   System.out.println("=============================");
   System.out.println(String.format("var %sRequest  = %s; ", header, string(request)));
   System.out.println(String.format("var %sResponse = %s; ", header, string(response)));
   System.out.println("=============================");
  }
 }
}
