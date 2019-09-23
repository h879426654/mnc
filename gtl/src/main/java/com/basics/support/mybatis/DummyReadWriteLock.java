
package com.basics.support.mybatis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

class DummyReadWriteLock implements ReadWriteLock {

 private Lock lock = new DummyLock();

 public Lock readLock() {
  return lock;
 }

 public Lock writeLock() {
  return lock;
 }

 static class DummyLock implements Lock {

  public void lock() {
   // Not implemented
  }

  public void lockInterruptibly() throws InterruptedException {
   // Not implemented
  }

  public boolean tryLock() {
   return true;
  }

  public boolean tryLock(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException {
   return true;
  }

  public void unlock() {
   // Not implemented
  }

  public Condition newCondition() {
   return null;
  }
 }

}
