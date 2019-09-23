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
package com.basics.support.mybatis;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;

import com.basics.support.Application;
import com.basics.support.LogUtils;
import com.basics.support.redis.JedisCallback;
import com.basics.support.redis.JedisSupport;

import redis.clients.jedis.Jedis;

/**
 * Cache adapter for Redis.
 *
 * @author Eduardo Macarron
 */
public final class JedisCacheAdapter implements Cache {

 private final ReadWriteLock readWriteLock = new DummyReadWriteLock();

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

 public JedisCacheAdapter(final String id) {
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
  LogUtils.performance.debug("{}.putObject:{} key={}", this.getClass().getName(), this.id, key);
  execute(new JedisCallback() {
   public Object doWithJedis(Jedis jedis) {
    jedis.hset(id.toString().getBytes(), key.toString().getBytes(), JedisCacheSerializeUtils.serialize(value));
    return null;
   }
  });
 }

 public Object getObject(final Object key) {
  LogUtils.performance.debug("{}.getObject:{} key={}", this.getClass().getName(), this.id, key);
  return execute(new JedisCallback() {
   public Object doWithJedis(Jedis jedis) {
    return JedisCacheSerializeUtils.unserialize(jedis.hget(id.toString().getBytes(), key.toString().getBytes()));
   }
  });
 }

 public Object removeObject(final Object key) {
  LogUtils.performance.debug("{}.remove:{} key={}", this.getClass().getName(), this.id, key);
  return execute(new JedisCallback() {
   public Object doWithJedis(Jedis jedis) {
    return jedis.hdel(id.toString(), key.toString());
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

 public ReadWriteLock getReadWriteLock() {
  return readWriteLock;
 }

 public String toString() {
  return "Redis {" + id + "}";
 }

}
