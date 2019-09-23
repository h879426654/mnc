
package com.basics.support.mybatis;

import org.apache.ibatis.cache.Cache;

public class JedisCacheAdvisor extends DefaultCacheAdvisor {

 public Cache onBuildCache(String appId, String id) {
  String cacheId = this.onGetCacheId(appId, id);
  return new JedisCacheAdapter(cacheId);
 }
}
