package com.basics.support;

/**
 * FuncSupport.
 * 
 * @author yuwenfeng.
 */
public class FuncSupport {

 /**
  * The Interface Accept.
  * 
  * @param <T>
  *         the generic type
  */
 public static interface Accept<T> {

  /**
   * Accept.
   * 
   * @param func
   *         the func
   * @return true, if successful
   */
  public boolean accept(T func);
 }

}
