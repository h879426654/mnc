package com.basics.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataPageComonResponse<T> extends DataResponse {

 private static final long serialVersionUID = 1368143247662375878L;

 @JsonProperty("data")
 private DataPageListResponse<T> data = new DataPageListResponse<T>();

 public DataPageListResponse<T> getData() {
  return data;
 }

 public void setData(DataPageListResponse<T> data) {
  this.data = data;
 }
}
