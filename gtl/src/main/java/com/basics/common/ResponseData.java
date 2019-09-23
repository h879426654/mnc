package com.basics.common;

import java.util.List;

/**
 *
 * @param <T>
 *         列表对象泛型
 */
public class ResponseData<T> {

 /**
  * 总记录条数
  */
 private Long total;

 /**
  * 列表数据
  */
 private List<T> items;

 public Long getTotal() {
  return total;
 }

 public void setTotal(Long total) {
  this.total = total;
 }

 public List<T> getItems() {
  return items;
 }

 public void setItems(List<T> items) {
  this.items = items;
 }
}
