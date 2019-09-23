
package com.basics.support.redis;

import redis.clients.jedis.Jedis;

/**
 * 扩展JedisConnectionFactory,实现JedisSupport
 * 
 * @author yuwenfeng
 *
 */
public class JedisConnectionFactory extends org.springframework.data.redis.connection.jedis.JedisConnectionFactory implements JedisSupport {
 public Jedis getJedis() {
  return this.fetchJedisConnector();
 }

 public void returnJedis(Jedis jedis) {
  // TODO:
  // jedis.close();
  this.returnJedisConnector(jedis);
 }

 public Object doInJedis(JedisCallback callback) {
  Jedis jedis = this.getJedis();
  try {
   Object result = callback.doWithJedis(jedis);
   return result;
  } finally {
   returnJedis(jedis);
  }
 }
}
