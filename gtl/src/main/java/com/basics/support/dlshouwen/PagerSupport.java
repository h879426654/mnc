package com.basics.support.dlshouwen;

import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilter;

/**
 * 
 * @author yuwenfeng
 *
 */
public interface PagerSupport {

 /**
  * Query.
  * 
  * @param filter
  *         the filter
  * @param pageNum
  *         the page num
  * @param pageSize
  *         the page size
  * @return the pagination support
  */
 public PaginationSupport query(QueryFilter filter, int pageNum, int pageSize);
}
