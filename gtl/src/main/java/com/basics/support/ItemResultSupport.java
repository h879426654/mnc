package com.basics.support;

/**
 * 表单提交结果.
 * 
 * @author yuwenfeng.
 * 
 */
public class ItemResultSupport<Item> extends FormResultSupport {

 /**
 * 
 */
 private static final long serialVersionUID = 1L;

 protected Item item;

 public Item getItem() {
  return item;
 }

 public void setItem(Item item) {
  this.item = item;
 }

 public boolean hasItem() {
  return this.item != null;
 }
}
