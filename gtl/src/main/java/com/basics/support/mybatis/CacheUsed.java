package com.basics.support.mybatis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.basics.support.LogUtils;

/**
 * CacheUsed.
 */
public class CacheUsed {

 /** The cache. */
 protected String cache;

 /** The using. */
 protected List<String> usedCaches = new ArrayList<String>();

 /** The using. */
 protected List<String> cleanCaches = new ArrayList<String>();

 /**
  * Gets the cache.
  *
  * @return the cache
  */
 public String getCache() {
  return cache;
 }

 /**
  * Sets the cache.
  *
  * @param cache
  *         the new cache
  */
 public void setCache(String cache) {
  this.cache = cache;
 }

 /**
  * Gets the used caches.
  *
  * @return the used caches
  */
 public List<String> getUsedCaches() {
  return usedCaches;
 }

 /**
  * Sets the used caches.
  *
  * @param usedCaches
  *         the new used caches
  */
 public void setUsedCaches(List<String> usedCaches) {
  this.usedCaches = usedCaches;
 }

 /**
  * Cache.
  *
  * @param cache
  *         the cache
  * @return the cache used
  */
 public CacheUsed cache(String cache) {
  this.setCache(cache);
  return this;
 }

 /**
  * Used.
  *
  * @param caches
  *         the caches
  * @return the cache used
  */
 public CacheUsed used(String... caches) {
  for (String cache : caches) {
   if (!this.usedCaches.contains(cache)) {
    this.usedCaches.add(cache);
   }
  }
  return this;
 }

 /**
  * Clean.
  *
  * @param caches
  *         the caches
  * @return the cache clean
  */
 public CacheUsed clean(String... caches) {
  for (String cache : caches) {
   if (!this.cleanCaches.contains(cache)) {
    this.cleanCaches.add(cache);
   }
  }
  return this;
 }

 /** The cache reference on. */
 protected static Map<String, List<String>> cacheReferenceOn = new java.util.concurrent.ConcurrentHashMap<String, List<String>>();

 /** The cache reference on. */
 protected static Map<String, List<String>> cacheClean = new java.util.concurrent.ConcurrentHashMap<String, List<String>>();

 /** The caches. */
 protected static Map<String, CacheAdapter> caches = new java.util.concurrent.ConcurrentHashMap<String, CacheAdapter>();

 /** The cache reference. */
 protected static Map<String, List<String>> cacheReference = new java.util.concurrent.ConcurrentHashMap<String, List<String>>();

 /**
  * Gets the caches.
  *
  * @return the caches
  */
 public static Map<String, CacheAdapter> getCaches() {
  return caches;
 }

 /**
  * Cache reference on.
  *
  * @param cache
  *         the cache
  * @param dependOnCache
  *         the depend on cache
  */
 public static void cacheReferenceOn(String cache, String dependOnCache) {
  List<String> dependOnCaches = new ArrayList<String>();
  if (cacheReferenceOn.containsKey(cache)) {
   dependOnCaches = cacheReferenceOn.get(cache);
  }
  if (!dependOnCaches.contains(dependOnCache)) {
   dependOnCaches.add(dependOnCache);
  }
  CacheUsed.cacheReferenceOn.put(cache, dependOnCaches);
 }

 /**
  * Used.
  *
  * @param adapter
  *         the adapter
  */
 public static void used(CacheAdapter adapter) {
  LogUtils.performance.debug("CacheUsed:{}", adapter.getId());
 }

 /**
  * Used.
  *
  * @param adapter
  *         the adapter
  * @param cacheUsed
  *         the cache used
  */
 public static void used(CacheAdapter adapter, CacheUsed cacheUsed) {
  for (String usedCache : cacheUsed.getUsedCaches()) {
   CacheUsed.cacheReferenceOn(usedCache, cacheUsed.getCache());
  }
  CacheUsed.cacheClean.put(adapter.getId(), cacheUsed.cleanCaches);
  CacheUsed.caches.put(adapter.getId(), adapter);
 }

 /**
  * Clear cache adapter.
  *
  * @param cacheAdapter
  *         the cache adapter
  */
 public static void clearCacheAdapter(CacheAdapter cacheAdapter) {
  List<String> references = CacheUsed.getReference(cacheAdapter.getId());
  for (String reference : references) {
   if (CacheUsed.caches.containsKey(reference)) {
    LogUtils.performance.info("CacheUsed.clearCacheAdapter:id={} reference={}", cacheAdapter.getId(), reference);
    CacheUsed.caches.get(reference).getAdapter().clear();
   }
  }
  List<String> cleans = CacheUsed.getClean(cacheAdapter.getId());
  for (String clean : cleans) {
   LogUtils.performance.info("CacheUsed.clearCacheAdapter:id={} clean={}", cacheAdapter.getId(), clean);
   CacheUsed.caches.get(clean).getAdapter().clear();
  }
 }

 public static void clearCache(String cacheId) {
  if (StringUtils.isBlank(cacheId)) {
   throw new RuntimeException("cacheId not allow blank.");
  }
  if (!CacheUsed.getCaches().containsKey(cacheId)) {
   CacheAdapter cacheAdapter = new CacheAdapter(cacheId);
   CacheUsed.used(cacheAdapter);
  }
  CacheUsed.clearCacheAdapter(CacheUsed.getCaches().get(cacheId));
 }

 public static void clearAllCache() {
  for (Iterator<CacheAdapter> eachAdapter = CacheUsed.getCaches().values().iterator(); eachAdapter.hasNext();) {
   eachAdapter.next().getAdapter().clear();
  }
 }

 /**
  * Gets the cache reference on.
  *
  * @param cacheId
  *         the cache id
  * @return the cache reference on
  */
 protected static List<String> getCacheReferenceOn(String cacheId) {
  List<String> referenceOns = new ArrayList<String>();
  if (CacheUsed.cacheReferenceOn.containsKey(cacheId)) {
   return CacheUsed.cacheReferenceOn.get(cacheId);
  } else {
   return referenceOns;
  }
 }

 /**
  * Search reference.
  *
  * @param cacheId
  *         the cache id
  * @param dependOnCaches
  *         the depend on caches
  */
 protected static void searchReference(String cacheId, List<String> dependOnCaches) {
  if (!dependOnCaches.contains(cacheId)) {
   dependOnCaches.add(cacheId);
   List<String> dependOns = CacheUsed.getCacheReferenceOn(cacheId);
   for (String dependOn : dependOns) {
    searchReference(dependOn, dependOnCaches);
   }
  }

 }

 /**
  * Gets the reference.
  *
  * @param cacheId
  *         the cache id
  * @return the reference
  */
 public static List<String> getReference(String cacheId) {
  if (CacheUsed.cacheReference.containsKey(cacheId)) {
   return CacheUsed.cacheReference.get(cacheId);
  } else {
   List<String> reference = new ArrayList<String>();
   searchReference(cacheId, reference);
   CacheUsed.cacheReference.put(cacheId, reference);
   return reference;
  }
 }

 public static List<String> getClean(String cacheId) {
  if (CacheUsed.cacheClean.containsKey(cacheId)) {
   return CacheUsed.cacheClean.get(cacheId);
  } else {
   List<String> clean = new ArrayList<String>();
   return clean;
  }
 }
}
