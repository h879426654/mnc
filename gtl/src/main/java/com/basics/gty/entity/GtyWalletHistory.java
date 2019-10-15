package com.basics.gty.entity;

import com.basics.sys.entity.BaseBean;

import java.math.BigDecimal;

public class GtyWalletHistory extends BaseBean {
 private String userId;
 private int recordType;
 private BigDecimal recordNum;
 private String toAccount;
 private String id;
 private String mark;
 private String createTime;
 private String recordName;
 private Integer pageN;
 private Integer pageS;

 public Integer getPageN() {
  return pageN;
 }

 public void setPageN(Integer pageN) {
  this.pageN = pageN;
 }

 public Integer getPageS() {
  return pageS;
 }

 public void setPageS(Integer pageS) {
  this.pageS = pageS;
 }

 public String getRecordName() {
  return recordName;
 }

 public void setRecordName(String recordName) {
  this.recordName = recordName;
 }

 public String getCreateTime() {
  return createTime;
 }

 public void setCreateTime(String createTime) {
  this.createTime = createTime;
 }

 public String getMark() {
  return mark;
 }

 public void setMark(String mark) {
  this.mark = mark;
 }

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public String getUserId() {
  return userId;
 }

 public void setUserId(String userId) {
  this.userId = userId;
 }

 public int getRecordType() {
  return recordType;
 }

 public void setRecordType(int recordType) {
  this.recordType = recordType;
 }

 public BigDecimal getRecordNum() {
  return recordNum;
 }

 public void setRecordNum(BigDecimal recordNum) {
  this.recordNum = recordNum;
 }

 public String getToAccount() {
  return toAccount;
 }

 public void setToAccount(String toAccount) {
  this.toAccount = toAccount;
 }
}