package com.basics.support;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import jodd.bean.BeanUtil;

/**
 * 模型支持类,对于分页和排序提供简单支持. (根据easyUI进行了参数适配.)
 * 
 * @author yuwenfeng.
 * 
 */
public class ModelSupport {

 /**
  * 上传的文件.(支持图片上传)
  */
 private MultipartFile imgFile;

 public MultipartFile getImgFile() {
  return imgFile;
 }

 public void setImgFile(MultipartFile imgFile) {
  this.imgFile = imgFile;
 }

 /**
  * 模糊查询条件.
  */
 private String q;

 public String getQ() {
  return q;
 }

 public void setQ(String q) {
  this.q = CommonSupport.trim(q);
 }

 /**
  * 页数.
  */
 private int pageNum = 1;

 /**
  * 分页大小.
  */
 private int pageSize = 10;

 public int getPageNum() {
  return pageNum;
 }

 public void setPageNum(int pageNum) {
  this.pageNum = pageNum;
 }

 public int getPageSize() {
  return pageSize;
 }

 public void setPageSize(int pageSize) {
  this.pageSize = pageSize;
 }

 /**
  * 
  * 适配easyUI请求参数. （datagrid等请求服务端数据分页的时候,参数名称固定为:page)
  * 
  * @return
  */
 public int getPage() {
  return pageNum;
 }

 public void setPage(int page) {
  this.setPageNum(page);
 }

 /**
  * 适配easyUI请求参数. （datagrid等请求服务端数据分页大小的时候,参数名称固定为:rows)
  * 
  * @return
  */
 public String getRows() {
  return this.getPageSize() + "";
 }

 public void setRows(String rows) {
  if (NumberUtils.isDigits(rows)) {
   this.setPageSize(Integer.valueOf(rows));
  } else {
   this.setPageSize(Integer.MAX_VALUE);
  }
 }

 /**
  * 适配easyUI请求参数.升序/降序(asc,desc).
  */
 private String orderBy;

 public String getOrderBy() {
  return orderBy;
 }

 public void setOrderBy(String orderBy) {
  this.orderBy = orderBy;
 }

 public String toDatetimeString(String whatProperty) {
  Object value = BeanUtil.getPropertySilently(this, whatProperty);
  if (value != null) {
   if (value instanceof Date) {
    return DateUtils.formatDateTime((Date) value);
   }
  }
  return "";
 }

 // 转换日期字符格式
 public String toDatetimeString_short(String whatProperty) {
  Object value = BeanUtil.getPropertySilently(this, whatProperty);
  if (value != null) {
   if (value instanceof Date) {
    return DateUtils.formatDateTime_short((Date) value);
   }
  }
  return "";
 }

 public void fromDatetimeString(String whatProperty, String whatValue) {
  BeanUtil.setPropertySilent(this, whatProperty, whatValue);
 }

 public String toDateString(String whatProperty) {
  Object value = BeanUtil.getPropertySilently(this, whatProperty);
  if (value != null) {
   if (value instanceof Date) {
    return DateUtils.formatDate((Date) value);
   }
  }
  return "";
 }

 public void fromDateString(String whatProperty, String whatValue) {
  BeanUtil.setPropertySilent(this, whatProperty, whatValue);
 }

 /**
  * 分转为元
  * 
  * @param whatProperty
  * @return
  */
 public String toYuanString(String whatProperty) {
  Object value = BeanUtil.getPropertySilently(this, whatProperty);
  if (value != null) {
   return MoneyUtils.fenToYuan(value.toString()).toString();
  }
  return "";
 }

 /**
  * 元转为分
  * 
  * @param whatProperty
  * @param whatValue
  */
 public void fromYuanString(String whatProperty, String whatValue) {
  BigDecimal fen = MoneyUtils.yuanToFen(whatValue);
  BeanUtil.setPropertySilent(this, whatProperty, fen.longValue());
 }

 /**
  * 分转为万元
  * 
  * @param whatProperty
  * @return
  */
 public String toWanString(String whatProperty) {
  Object value = BeanUtil.getPropertySilently(this, whatProperty);
  if (value != null) {
   return MoneyUtils.fenToWan(value.toString()).toString();
  }
  return "";
 }

 /**
  * 万元转为分
  * 
  * @param whatProperty
  * @param whatValue
  */
 public void fromWanString(String whatProperty, String whatValue) {
  BigDecimal fen = MoneyUtils.wanToFen(whatValue);
  BeanUtil.setPropertySilent(this, whatProperty, fen.longValue());
 }
}
