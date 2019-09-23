package com.basics.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yuwenfeng.
 * 
 */
@SuppressWarnings("rawtypes")
public class ListResultSupport extends ResultSupport {

 /**
 * 
 */
 private static final long serialVersionUID = 1L;

 private List rows = new ArrayList();
 private long total;

 public List getRows() {
  return rows;
 }

 public void setRows(List rows) {
  this.rows = rows;
 }

 public long getTotal() {
  return total;
 }

 public void setTotal(long total) {
  this.total = total;
 }

}
