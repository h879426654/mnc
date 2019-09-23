package com.basics.support.mybatis;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.ibatis.cache.Cache;

import com.basics.support.Application;
import com.basics.support.LogUtils;

public final class CacheAdapter implements Cache {

 /** Cache id. */
 private final String id;

 private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

 Cache adapter;

 public Cache getAdapter() {
  if (this.adapter == null) {
   this.adapter = this.getCacheAdvisor().onGetCache(this.id);
  }
  return adapter;
 }

 public void setAdapter(Cache adapter) {
  this.adapter = adapter;
 }

 CacheAdvisor cacheAdvisor;

 public CacheAdvisor getCacheAdvisor() {
  if (this.cacheAdvisor == null) {
   this.cacheAdvisor = this.onResolveCacheAdvistor();
  }
  return cacheAdvisor;
 }

 public void setCacheAdvisor(CacheAdvisor cacheAdvisor) {
  this.cacheAdvisor = cacheAdvisor;
 }

 public CacheAdvisor onResolveCacheAdvistor() {
  try {
   return Application.getInstance().getService(CacheAdvisor.class);
  } catch (Throwable e) {
   LogUtils.performance.warn("no CacheAdvisor from Application.getInstance(), return DefaultCacheAdvisor.");
   return new DefaultCacheAdvisor();
  }
 }

 /**
  * Constructor.
  *
  * @param id
  *         Cache id.
  */
 public CacheAdapter(String id) {
  if (id == null)
   throw new IllegalArgumentException("Cache instances require an ID");
  this.id = id;
  CacheUsed.used(this);
 }

 public String getId() {
  return this.id;
 }

 public void putObject(Object key, Object value) {
  try {
   this.getAdapter().putObject(key, value);
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
  }
 }

 public Object getObject(Object key) {
  try {
   return this.getAdapter().getObject(key);
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   return null;
  }
 }

 public Object removeObject(Object key) {
  try {
   return this.getAdapter().removeObject(key);
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   return null;
  }
 }

 public void clear() {
  try {
   CacheUsed.clearCacheAdapter(this);
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
  }
  try {
   this.getCacheAdvisor().didClear(this.id);
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
  }
 }

 public int getSize() {
  try {
   return this.getAdapter().getSize();
  } catch (Throwable e) {
   return 5 * 1024;
  }
 }

 public ReadWriteLock getReadWriteLock() {
  return readWriteLock;
 }
}
