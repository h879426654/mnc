package com.basics.support.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.util.SoftHashMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.basics.support.LogUtils;
import com.basics.support.redis.JedisSupport;

public class ShiroJedisCacheManager<K, V> extends AbstractCacheManager {

 @Autowired(required = false)
 protected JedisSupport jedisSupport;

 protected boolean jedisOn = true;

 public boolean isJedisOn() {
  return jedisOn;
 }

 public void setJedisOn(boolean jedisOn) {
  this.jedisOn = jedisOn;
 }

 public JedisSupport getJedisSupport() {
  return jedisSupport;
 }

 public void setJedisSupport(JedisSupport jedisSupport) {
  this.jedisSupport = jedisSupport;
 }

 @SuppressWarnings("unchecked")
 protected Cache<K, V> createCache(String name) {
  LogUtils.performance.info("ShiroJedisCacheManager:createCache:{} jedisOn={} jedisSupport={}", name, this.jedisOn,
   this.jedisSupport != null);
  if (this.jedisOn && this.jedisSupport != null) {
   LogUtils.performance.info("createCache:ShiroJedisCache<K, V>({})", name);
   ShiroJedisCache<K, V> shiroJedisCache = new ShiroJedisCache<K, V>(name);
   shiroJedisCache.setJedisSupport(this.getJedisSupport());
   return shiroJedisCache;
  }
  return this.createMapCache(name);
 }

 @SuppressWarnings("rawtypes")
 protected Cache createMapCache(String name) {
  LogUtils.performance.info("createCache:MapCache<Object, Object>({})", name);
  return new MapCache<Object, Object>(name, new SoftHashMap<Object, Object>());
 }
}