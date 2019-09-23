package com.basics.app.entity;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

 public static Dictionary BLANK = new Dictionary("", "");
 private String id;
 private String text;

 public String getId() {

  return this.id;
 }

 public void setId(String id) {

  this.id = id;
 }

 public String getText() {

  return this.text;
 }

 public void setText(String text) {

  this.text = text;
 }

 public Map<String, String> asMap() {
  Map<String, String> map = new HashMap<String, String>();
  map.put("key", this.getId());
  map.put("value", this.getText());
  return map;
 }

 public Dictionary() {

 }

 public Dictionary(String id, String text) {
  super();
  this.id = id;
  this.text = text;
 }

}
