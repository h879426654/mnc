package com.basics.support;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * PaginationSupport.
 * 
 * @author yuwenfeng.
 */
@SuppressWarnings("rawtypes")
public final class PaginationSupport implements java.io.Serializable {

 /** The Constant DEFAULT_PAGE_SIZE. */
 public static final int DEFAULT_PAGE_SIZE = 10;

 /** The Constant serialVersionUID. */
 private static final long serialVersionUID = 1L;

 /** The page size. */
 private int pageSize;

 /** The total count. */
 private long totalCount;

 /** The current page no. */
 private int currentPageNo;

 /** The items. */
 private List items;

 /**
  * Instantiates a new pagination support.
  */
 public PaginationSupport() {
  this.pageSize = DEFAULT_PAGE_SIZE;
  this.items = new ArrayList();
 }

 /**
  * Instantiates a new pagination support.
  * 
  * @param currentPageNo
  *         the current page no
  */
 public PaginationSupport(int currentPageNo) {
  this.pageSize = DEFAULT_PAGE_SIZE;
  this.items = new ArrayList();
  this.currentPageNo = currentPageNo;
 }

 /**
  * Instantiates a new pagination support.
  * 
  * @param currentPageNo
  *         the current page no
  * @param totalCount
  *         the total count
  */
 public PaginationSupport(int currentPageNo, long totalCount) {
  this(currentPageNo);
  this.totalCount = totalCount;
 }

 /**
  * Instantiates a new pagination support.
  * 
  * @param currentPageNo
  *         the current page no
  * @param pageSize
  *         the page size
  */
 public PaginationSupport(int currentPageNo, int pageSize) {
  this(currentPageNo);
  this.pageSize = pageSize;
 }

 /**
  * Instantiates a new pagination support.
  * 
  * @param currentPageNo
  *         the current page no
  * @param pageSize
  *         the page size
  * @param totalCount
  *         the total count
  */
 public PaginationSupport(int currentPageNo, int pageSize, long totalCount) {
  this(currentPageNo, pageSize);
  this.totalCount = totalCount;
 }

 /**
  * Checks for next page.
  * 
  * @return true, if successful
  */
 public boolean hasNextPage() {
  return (this.currentPageNo < getPageCount());
 }

 /**
  * Checks for previous page.
  * 
  * @return true, if successful
  */
 public boolean hasPreviousPage() {
  return (this.currentPageNo > 1);
 }

 /**
  * Checks for page.
  * 
  * @return true, if successful
  */
 public boolean hasPage() {
  return (getPageCount() > 1);
 }

 /**
  * Gets the current page no.
  * 
  * @return the current page no
  */
 public int getCurrentPageNo() {
  int pageCount = getPageCount();
  if (this.currentPageNo > pageCount)
   this.currentPageNo = pageCount;
  if (pageCount == 0)
   this.currentPageNo = 1;
  if (this.currentPageNo < 1)
   this.currentPageNo = 1;
  return this.currentPageNo;
 }

 /**
  * Checks for current page no.
  * 
  * @return true, if successful
  */
 public boolean hasCurrentPageNo() {
  int pageCount = getPageCount();
  return (1 <= this.currentPageNo && this.currentPageNo <= pageCount);
 }

 /**
  * Gets the page count.
  * 
  * @return the page count
  */
 public int getPageCount() {
  return (int) ((this.totalCount + this.pageSize - 1L) / this.pageSize);
 }

 /**
  * Gets the page size.
  * 
  * @return the page size
  */
 public int getPageSize() {
  return this.pageSize;
 }

 /**
  * Gets the items.
  * 
  * @return the items
  */
 public List getItems() {
  return this.items;
 }

 /**
  * Sets the items.
  * 
  * @param items
  *         the new items
  */
 public void setItems(List items) {
  this.items = items;
 }

 /**
  * Sets the total count.
  * 
  * @param totalCount
  *         the new total count
  */
 public void setTotalCount(long totalCount) {
  this.totalCount = totalCount;
 }

 /**
  * Gets the total count.
  * 
  * @return the total count
  */
 public long getTotalCount() {
  return this.totalCount;
 }

 /**
  * Gets the start of page.
  * 
  * @return the start of page
  */
 public int getStartOfPage() {
  return ((getCurrentPageNo() - 1) * this.pageSize);
 }
}