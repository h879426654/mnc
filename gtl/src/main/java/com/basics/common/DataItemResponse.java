package com.basics.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 扩展数据响应.
 *
 * @param <Item>
 *         the generic type
 */
public class DataItemResponse<Item> extends DataResponse {

 private static final long serialVersionUID = 1L;
 /** The item. */
 @JsonProperty("data")
 private Item item;

 /**
  * Gets the item.
  *
  * @return the item
  */
 public Item getItem() {
  return item;
 }

 /**
  * Sets the item.
  *
  * @param item
  *         the item
  */
 public void setItem(Item item) {
  this.item = item;
 }

}
