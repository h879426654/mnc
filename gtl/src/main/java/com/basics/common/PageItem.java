package com.basics.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据包装.
 *
 * @param <Item>
 *         the generic type
 * @author yuwenfeng
 */
public class PageItem<Item> {

 /** 集合数据. */
 private List<Item> items = new ArrayList<Item>();

 /** * 当前页数. */
 private Long pageNum;

 /** * 总记录条数. */
 private Long total;

 /**
  * Gets the items.
  *
  * @return the items
  */
 public List<Item> getItems() {
  return items;
 }

 /**
  * Sets the items.
  *
  * @param items
  *         the items
  */
 public void setItems(List<Item> items) {
  this.items = items;
 }

 public Long getPageNum() {
  return pageNum;
 }

 public void setPageNum(Long pageNum) {
  this.pageNum = pageNum;
 }

 public void setTotal(Long total) {
  this.total = total;
 }

 /**
  * Gets the total.
  *
  * @return the total
  */
 public long getTotal() {
  return total;
 }

 /**
  * Sets the total.
  *
  * @param total
  *         the total
  */
 public void setTotal(long total) {
  this.total = total;
 }

 /**
  * Total.
  *
  * @param total
  *         the total
  * @return the page item< item>
  */
 public PageItem<Item> total(long total) {
  this.total = total;
  return this;
 }

 /**
  * Page num.
  *
  * @param pageNum
  *         the page num
  * @return the page item< item>
  */
 public PageItem<Item> pageNum(long pageNum) {
  this.pageNum = pageNum;
  return this;
 }

 /**
  * Items.
  *
  * @param items
  *         the items
  * @return the page item< item>
  */
 public PageItem<Item> items(List<Item> items) {
  this.items = items;
  return this;
 }
}
