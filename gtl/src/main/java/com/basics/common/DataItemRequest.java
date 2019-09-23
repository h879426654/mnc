package com.basics.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DataItemRequest. 扩展数据请求,带参数.
 * 
 * @param <Item>
 *         the generic type
 */
public class DataItemRequest<Item> extends DataResponse {

 /**
  * 
  */
 private static final long serialVersionUID = 637974025122284757L;
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
