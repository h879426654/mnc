package com.basics.support;

/**
 * 单个数据响应
 */
public class DataResultSupport extends ResultSupport {
 private Object data;
 
 public Object getData() {
  return data;
 }
 
 public void setData(Object data) {
  this.data = data;
 }
}
