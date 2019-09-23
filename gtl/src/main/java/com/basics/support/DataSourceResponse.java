package com.basics.support;

import java.util.List;
import java.util.Map;

/**
 * The Class DataSourceResponse.
 */
public class DataSourceResponse {

 /** The total. */
 private long total;

 /** The data. */
 private List<?> data;

 /** The aggregates. */
 private Map<String, Object> aggregates;

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
  * Gets the data.
  *
  * @return the data
  */
 public List<?> getData() {
  return data;
 }

 /**
  * Sets the data.
  *
  * @param data
  *         the data
  */
 public void setData(List<?> data) {
  this.data = data;
 }

 /**
  * Gets the aggregates.
  *
  * @return the aggregates
  */
 public Map<String, Object> getAggregates() {
  return aggregates;
 }

 /**
  * Sets the aggregates.
  *
  * @param aggregates
  *         the aggregates
  */
 public void setAggregates(Map<String, Object> aggregates) {
  this.aggregates = aggregates;
 }

}
