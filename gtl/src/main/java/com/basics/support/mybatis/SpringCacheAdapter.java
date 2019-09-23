package com.basics.support.mybatis;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.basics.support.Application;
import com.basics.support.LogUtils;

public final class SpringCacheAdapter implements Cache {

 /** Cache id. */
 private final String id;

 /** {@code ReadWriteLock}. */
 private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

 CacheManager cacheManager;
 org.springframework.cache.Cache cache;

 public org.springframework.cache.Cache onBuildCache() {
  if (this.cacheManager == null) {
   this.cacheManager = (CacheManager) Application.getInstance().getBean("app.cacheManager");
  }
  if (this.cacheManager == null) {
   throw new RuntimeException("CacheManager with name app.cacheManager not found.");
  }
  return this.cacheManager.getCache(this.id);
 }

 public org.springframework.cache.Cache getCache() {
  if (this.cache == null) {
   this.cache = this.onBuildCache();
  }
  return cache;
 }

 /**
  * Constructor.
  *
  * @param id
  *         Cache id.
  */
 public SpringCacheAdapter(String id) {
  if (id == null)
   throw new IllegalArgumentException("Cache instances require an ID");
  this.id = id;
 }

 public String getId() {
  return this.id;
 }

 public void putObject(Object key, Object value) {
  getCache().put(key, value);
 }

 public Object getObject(Object key) {
  return getCache().get(key);
 }

 public Object removeObject(Object key) {
  LogUtils.performance.debug("removeObject:{}", key);
  ValueWrapper wrap = this.getCache().get(key);
  Object obj = null;
  if (wrap != null) {
   obj = wrap.get();
  }
  getCache().evict(key);
  return obj;
 }

 public void clear() {
  getCache().clear();
 }

 public int getSize() {
  return 1024;
 }

 public ReadWriteLock getReadWriteLock() {
  return readWriteLock;
 }
}
