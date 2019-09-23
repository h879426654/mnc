package com.basics.support.redis;

import redis.clients.jedis.Jedis;

/**
 * JedisCallback.
 */
public interface JedisCallback {

 /**
  * Do with jedis.
  *
  * @param jedis
  *         the jedis
  * @return the object
  */
 Object doWithJedis(Jedis jedis);
}
