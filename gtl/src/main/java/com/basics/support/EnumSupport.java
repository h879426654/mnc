package com.basics.support;

public class EnumSupport<Value> {

 private Value value;
 private String message;

 public Value getValue() {
  return value;
 }

 public void setValue(Value value) {
  this.value = value;
 }

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

 public EnumSupport(Value value, String message) {
  super();
  this.value = value;
  this.message = message;
 }

}
