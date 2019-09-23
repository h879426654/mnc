package com.basics.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2 implements JsonSerialize {

 private ObjectMapper mapper = new ObjectMapper();

 public Jackson2() {
  this.onConfig(mapper);
 }

 public Jackson2(ObjectMapper mapper) {
  super();
  this.mapper = mapper;
 }

 public ObjectMapper getMapper() {
  return mapper;
 }

 public void setMapper(ObjectMapper mapper) {
  this.mapper = mapper;
 }

 public String toJson(Object obj) {
  try {
   return this.getMapper().writeValueAsString(obj);
  } catch (JsonProcessingException e) {
   throw new RuntimeException(e.getMessage());
  }
 }

 public <T> T fromJson(String json, Class<T> classOfT) {
  try {
   return mapper.readValue(json, classOfT);
  } catch (Throwable e) {
   throw new RuntimeException(e.getMessage());
  }
 }

 public void onConfig(ObjectMapper mapper) {

 }
}
