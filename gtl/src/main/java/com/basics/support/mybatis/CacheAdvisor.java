package com.basics.support.mybatis;

import org.apache.ibatis.cache.Cache;

/**
 * CacheAdvisor <br>
 * 1.onGetCacheId:解决缓存id冲突.<br>
 * 2.onBuildCache:支持自定义缓存实现.<br>
 * 3.didClear:解决多项目缓存冲突.
 */
public interface CacheAdvisor {

 /**
  * Gets the cache id.
  *
  * @param id
  *         the id
  * @return the cache id
  */
 public String onGetCacheId(String id);

 public String onGetCacheId(String appId, String id);

 /**
  * On get cache.
  *
  * @param id
  *         the id
  * @return the cache
  */
 public Cache onGetCache(String id);

 public Cache onGetCache(String appId, String id);

 /**
  * Did clear.
  *
  * @param cacheAdapter
  *         the cache adapter
  */
 public void didClear(String id);

 /**
  * Clear.
  *
  * @param cacheFilter
  *         the cache filter
  */
 public void clear(CacheFilter cacheFilter);

}