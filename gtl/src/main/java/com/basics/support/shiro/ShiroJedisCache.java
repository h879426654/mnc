/**
 *    Copyright 2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.basics.support.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.cache.CacheException;

import com.basics.support.Application;
import com.basics.support.LogUtils;
import com.basics.support.mybatis.JedisCacheSerializeUtils;
import com.basics.support.redis.JedisCallback;
import com.basics.support.redis.JedisSupport;

import redis.clients.jedis.Jedis;

public final class ShiroJedisCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {

 private String id;

 private JedisSupport jedisSupport;

 public JedisSupport getJedisSupport() {
  if (this.jedisSupport == null) {
   this.jedisSupport = Application.getInstance().getService(JedisSupport.class);
  }
  return jedisSupport;
 }

 public void setJedisSupport(JedisSupport jedisSupport) {
  this.jedisSupport = jedisSupport;
 }

 public ShiroJedisCache(final String id) {
  if (id == null) {
   throw new IllegalArgumentException("Cache instances require an ID");
  }
  this.id = id;

 }

 private Object execute(JedisCallback callback) {
  return this.getJedisSupport().doInJedis(callback);
 }

 public String getId() {
  return this.id;
 }

 public int getSize() {
  return (Integer) execute(new JedisCallback() {

   public Object doWithJedis(Jedis jedis) {
    Map<byte[], byte[]> result = jedis.hgetAll(id.toString().getBytes());
    return result.size();
   }
  });
 }

 public void putObject(final Object key, final Object value) {
  LogUtils.performance.debug("{}.putObject:{} key={} value={}", this.getClass().getName(), this.id, key, value);
  execute(new JedisCallback() {

   public Object doWithJedis(Jedis jedis) {
    jedis.hset(id.toString().getBytes(), key(key).getBytes(), JedisCacheSerializeUtils.serialize(value));
    return null;
   }
  });
 }

 public Object getObject(final Object key) {
  LogUtils.performance.debug("{}.getObject:{} key={}", this.getClass().getName(), this.id, key);
  return execute(new JedisCallback() {

   public Object doWithJedis(Jedis jedis) {
    return JedisCacheSerializeUtils.unserialize(jedis.hget(id.toString().getBytes(), key(key).getBytes()));
   }

  });
 }

 public String key(final Object key) {
  return key.hashCode() + "";
 }

 public Object removeObject(final Object key) {
  LogUtils.performance.debug("{}.remove:{} key={}", this.getClass().getName(), this.id, key);
  return execute(new JedisCallback() {

   public Object doWithJedis(Jedis jedis) {
    return jedis.hdel(id.toString(), key(key));
   }
  });
 }

 public void clear() {
  LogUtils.performance.debug("{}.clear:{}", this.getClass().getName(), this.id);
  execute(new JedisCallback() {

   public Object doWithJedis(Jedis jedis) {
    jedis.del(id.toString());
    return null;
   }
  });

 }

 public String toString() {
  return "ShiroJedisCache {" + id + "}";
 }

 @SuppressWarnings("unchecked")
 public Set<K> keys() {
  return (Set<K>) execute(new JedisCallback() {

   public Object doWithJedis(Jedis jedis) {
    return jedis.hkeys(id);
   }
  });
 }

 @SuppressWarnings("unchecked")
 public Collection<V> values() {
  return (Collection<V>) execute(new JedisCallback() {

   public Object doWithJedis(Jedis jedis) {
    List<byte[]> values = jedis.hvals(id.getBytes());
    List<V> vals = new ArrayList<V>();
    for (byte[] value : values) {
     vals.add((V) JedisCacheSerializeUtils.unserialize(value));
    }
    return vals;
   }
  });
 }

 @SuppressWarnings("unchecked")
 public V get(K key) throws CacheException {
  return (V) this.getObject(key);
 }

 public V put(K key, V value) throws CacheException {
  this.putObject(key, value);
  return value;
 }

 public V remove(K key) throws CacheException {
  V v = this.get(key);
  this.removeObject(key);
  return v;
 }

 public int size() {
  return this.getSize();
 }

}
