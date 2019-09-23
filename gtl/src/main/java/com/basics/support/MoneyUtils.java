package com.basics.support;

import java.math.BigDecimal;
import java.math.RoundingMode;

// TODO: Auto-generated Javadoc
/**
 * 货币工具类.
 */
public class MoneyUtils {

 /** The fen. */
 public static long FEN = 1;

 /** The jiao. */
 public static long JIAO = 10 * FEN;

 /** The yuan. */
 public static long YUAN = 10 * JIAO;

 /** The wan. */
 public static long WAN = 10000 * YUAN;

 /**
  * Yuan to fen.
  *
  * @param yuan
  *         the yuan
  * @return the big decimal
  */
 public static BigDecimal yuanToFen(String yuan) {
  return new BigDecimal(yuan).multiply(new BigDecimal(YUAN));
 }

 /**
  * Yuan to fen.
  *
  * @param yuan
  *         the yuan
  * @return the long
  */
 public static long yuanToFen(Number yuan) {
  return yuanToFen(yuan.toString()).longValue();
 }

 /**
  * Yuan to fen.
  *
  * @param yuan
  *         the yuan
  * @return the big decimal
  */
 public static BigDecimal yuanToFen(BigDecimal yuan) {
  return yuan.multiply(new BigDecimal(YUAN));
 }

 /**
  * Fen to yuan.
  *
  * @param fen
  *         the fen
  * @return the big decimal
  */
 public static BigDecimal fenToYuan(String fen) {
  BigDecimal money = new BigDecimal(fen);
  return money.divide(BigDecimal.valueOf(YUAN), 2, RoundingMode.UP);
 }

 /**
  * Fen to yuan.
  *
  * @param fen
  *         the fen
  * @return the big decimal
  */
 public static BigDecimal fenToYuan(Number fen) {
  BigDecimal money = new BigDecimal(fen.longValue());
  return money.divide(BigDecimal.valueOf(YUAN), 2, RoundingMode.UP);
 }

 /**
  * Fen to wan.
  *
  * @param fen
  *         the fen
  * @return the big decimal
  */
 public static BigDecimal fenToWan(String fen) {
  BigDecimal money = new BigDecimal(fen);
  return money.divide(BigDecimal.valueOf(WAN), 2, RoundingMode.UP);
 }

 /**
  * Fen to wan.
  *
  * @param fen
  *         the fen
  * @return the big decimal
  */
 public static BigDecimal fenToWan(Number fen) {
  BigDecimal money = new BigDecimal(fen.longValue());
  return money.divide(BigDecimal.valueOf(WAN), 2, RoundingMode.UP);
 }

 /**
  * Wan to fen.
  *
  * @param wan
  *         the wan
  * @return the big decimal
  */
 public static BigDecimal wanToFen(String wan) {
  return new BigDecimal(wan).multiply(new BigDecimal(WAN));
 }

 /**
  * Wan to fen.
  *
  * @param wan
  *         the wan
  * @return the long
  */
 public static long wanToFen(Number wan) {
  return wanToFen(wan.toString()).longValue();
 }

 /**
  * Wan to fen.
  *
  * @param wan
  *         the wan
  * @return the big decimal
  */
 public static BigDecimal wanToFen(BigDecimal wan) {
  return wan.multiply(new BigDecimal(WAN));
 }

 /**
  * The main method.
  *
  * @param args
  *         the args
  */
 public static void main(String[] args) {
  LogUtils.performance.info("fenToYuan:{}分={}元", 1250, fenToYuan(1250));
  LogUtils.performance.info("fenToYuan:{}分={}元", 1254, fenToYuan(1254));
  LogUtils.performance.info("fenToYuan:{}分={}元", 1255, fenToYuan(1255));
  LogUtils.performance.info("fenToYuan:{}分={}元", 1256, fenToYuan(1256));

  LogUtils.performance.info("yuanToFen:{}分={}元", yuanToFen(new BigDecimal("12.50")), "12.50");
  LogUtils.performance.info("yuanToFen:{}分={}元", yuanToFen(new BigDecimal("12.54")), "12.54");
  LogUtils.performance.info("yuanToFen:{}分={}元", yuanToFen(new BigDecimal("12.55")), "12.55");
  LogUtils.performance.info("yuanToFen:{}分={}元", yuanToFen(new BigDecimal("12.56")), "12.56");

 }
}
