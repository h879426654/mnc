package com.basics.support.redis;

import redis.clients.jedis.Jedis;

/**
 * JedisSupport.
 */
public interface JedisSupport {

 /**
  * Gets the jedis.
  *
  * @return the jedis
  */
 public Jedis getJedis();

 /**
  * Return jedis.
  *
  * @param jedis
  *         the jedis
  */
 public void returnJedis(Jedis jedis);

 /**
  * Do in jedis.
  *
  * @param callback
  *         the callback
  * @return the object
  */
 public Object doInJedis(JedisCallback callback);
}
