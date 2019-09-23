package com.basics.support;

/**
 * GenericMybatisDao.
 * 
 * @param <T>
 *         the generic type
 * @author yuwenfeng.
 */
public abstract class GenericRunnable<T> implements Runnable {
 T owner;

 public GenericRunnable(T owner) {
  super();
  this.owner = owner;
 }

 public void run() {
  this.run(this.owner);
 }

 public abstract void run(T owner);

}