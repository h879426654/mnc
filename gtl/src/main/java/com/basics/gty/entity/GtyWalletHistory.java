package com.basics.gty.entity;

import com.basics.sys.entity.BaseBean;

import java.math.BigDecimal;

public class GtyWalletHistory extends BaseBean {
 private String userId;
 private int recordType;
 private BigDecimal recordNum;
 private String toAccount;
 private String id;

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