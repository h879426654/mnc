package com.basics.support;

/**
 * 主键生成类型.
 * <p>
 * 1.UUID(默认java.util.UUID.randomUUID().toString().replaceAll("-",
 * "").toUpperCase()).
 * </p>
 * <p>
 * 2.序列.
 * </p>
 * <p>
 * 3.自增.
 * </p>
 * <p>
 * 4.自动.
 * </p>
 * 
 * @author yuwenfeng
 * 
 */
public enum IDGenerationType {
 UUID, SEQUENCE, IDENTITY, AUTO;

 boolean isAuto() {
  return this.equals(IDGenerationType.AUTO);
 }

 boolean isSeq() {
  return this.equals(IDGenerationType.SEQUENCE);
 }

 boolean isUuid() {
  return this.equals(IDGenerationType.UUID);
 }

 boolean isIdentity() {
  return this.equals(IDGenerationType.IDENTITY);
 }
}