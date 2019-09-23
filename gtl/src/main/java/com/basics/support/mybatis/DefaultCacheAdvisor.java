
package com.basics.support.mybatis;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.basics.support.CommonSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.support.LogUtils;
import com.basics.support.ProfileUtils;
import com.basics.support.SortedProperties;

/**
 * DefaultCacheAdvisor. <br>
 * 1.onGetCacheId:解决缓存id冲突.不同的项目实体类名相同但实体属性名称或者类型不同的场景(通过配置不同的appId)<br>
 * 2.onBuildCache:支持自定义缓存实现.通过此方法来提供具体的缓存实现.<br>
 * 3.didClear:解决多项目缓存冲突.<br>
 * 3.1 通过分析项目的Mapper.xml文件,生成缓存依赖关系配置文件 <br>
 * 3.2 在缓存清理以后,根据缓存依赖配置文件清理相关缓存<br>
 */
public class DefaultCacheAdvisor implements CacheAdvisor, InitializingBean {

 /** The app id. */
 private String appId = "default";

 /** The ignore case. */
 boolean ignoreCase = true;

 /** The ignore package. */
 boolean ignorePackage = true;

 /** The mapper locations. */
 private Resource[] mapperLocations;

 /** The name mapper. */
 private INameMapper nameMapper = new DefaultNameMapper();

 /**
  * Checks if is ignore case.
  *
  * @return true, if checks if is ignore case
  */
 public boolean isIgnoreCase() {
  return ignoreCase;
 }

 /**
  * Sets the ignore case.
  *
  * @param ignoreCase
  *         the ignore case
  */
 public void setIgnoreCase(boolean ignoreCase) {
  this.ignoreCase = ignoreCase;
 }

 /**
  * Checks if is ignore package.
  *
  * @return true, if checks if is ignore package
  */
 public boolean isIgnorePackage() {
  return ignorePackage;
 }

 /**
  * Sets the ignore package.
  *
  * @param ignorePackage
  *         the ignore package
  */
 public void setIgnorePackage(boolean ignorePackage) {
  this.ignorePackage = ignorePackage;
 }

 /**
  * Gets the app id.
  *
  * @return the app id
  */
 public String getAppId() {
  return appId;
 }

 /**
  * Sets the app id.
  *
  * @param appId
  *         the app id
  */
 public void setAppId(String appId) {
  this.appId = appId;
 }

 /**
  * Gets the mapper locations.
  *
  * @return the mapper locations
  */
 public Resource[] getMapperLocations() {
  return mapperLocations;
 }

 /**
  * Sets the mapper locations.
  *
  * @param mapperLocations
  *         the new mapper locations
  */
 public void setMapperLocations(Resource[] mapperLocations) {
  this.mapperLocations = mapperLocations;
 }

 /**
  * Gets the name mapper.
  *
  * @return the name mapper
  */
 public INameMapper getNameMapper() {
  return nameMapper;
 }

 /**
  * Sets the name mapper.
  *
  * @param nameMapper
  *         the new name mapper
  */
 public void setNameMapper(INameMapper nameMapper) {
  this.nameMapper = nameMapper;
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.mybatis.CacheAdvisor#onGetCacheId(java.lang.String)
  */
 public String onGetCacheId(String id) {
  return this.onGetCacheId(this.getAppId(), id);
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.mybatis.CacheAdvisor#onGetCacheId(java.lang.String,
  * java.lang.String)
  */
 public String onGetCacheId(String appId, String id) {
  if (StringUtils.isBlank(appId)) {
   throw new RuntimeException("onGetCacheId:appId not allow blank.");
  }
  if (StringUtils.isBlank(id)) {
   throw new RuntimeException("onGetCacheId:id not allow blank.");
  }
  return String.format("%s[%s]", appId, id);
 }

 /** The app caches. */
 Map<String, Map<String, Cache>> appCaches = new java.util.concurrent.ConcurrentHashMap<String, Map<String, Cache>>();

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.mybatis.CacheAdvisor#onGetCache(java.lang.String)
  */
 public Cache onGetCache(String id) {
  return this.onGetCache(this.getAppId(), id);
 }

 /**
  * On build cache.
  *
  * @param id
  *         the id
  * @return the cache
  */
 public Cache onBuildCache(String id) {
  return this.onBuildCache(this.getAppId(), id);
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.mybatis.CacheAdvisor#onGetCache(java.lang.String,
  * java.lang.String)
  */
 public Cache onGetCache(String appId, String id) {
  if (StringUtils.isBlank(appId)) {
   throw new RuntimeException("onGetCache:appId not allow blank.");
  }
  if (StringUtils.isBlank(id)) {
   throw new RuntimeException("onGetCache:id not allow blank.");
  }
  Map<String, Cache> appCache = this.appCaches.get(appId);
  if (appCache == null) {
   appCache = new java.util.concurrent.ConcurrentHashMap<String, Cache>();
   appCaches.put(appId, appCache);
  }
  if (appCache.containsKey(id)) {
   return appCache.get(id);
  }
  Cache cache = this.onBuildCache(appId, id);
  if (cache != null) {
   appCache.put(id, cache);
  }
  return cache;
 }

 /**
  * On build cache.
  *
  * @param appId
  *         the app id
  * @param id
  *         the id
  * @return the cache
  */
 public Cache onBuildCache(String appId, String id) {
  String cacheId = this.onGetCacheId(appId, id);
  return new SpringCacheAdapter(cacheId);
 }

 /** The cache properties. */
 protected Map<String, Properties> cacheProperties = new HashMap<String, Properties>();

 /**
  * Gets the cache properties.
  *
  * @return the cache properties
  */
 public Map<String, Properties> getCacheProperties() {
  return cacheProperties;
 }

 /**
  * Sets the cache properties.
  *
  * @param cacheProperties
  *         the cache properties
  */
 public void setCacheProperties(Map<String, Properties> cacheProperties) {
  this.cacheProperties = cacheProperties;
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.mybatis.CacheAdvisor#didClear(com.basics.support.
  * mybatis.CacheAdapter)
  */
 public void didClear(String id) {
  this.clearByClassName(id, this.ignoreCase, this.ignorePackage);
 }

 /**
  * Clear cache by id.
  *
  * @param id
  *         the id
  */
 public void clearCacheById(String id) {
  if (this.cacheProperties == null || this.cacheProperties.isEmpty()) {
   LogUtils.performance.error("no cacheProperties,no clearCacheById.");
   return;
  }
  for (Iterator<Entry<String, Properties>> eachCacheProperties = this.getCacheProperties().entrySet().iterator(); eachCacheProperties
   .hasNext();) {
   Entry<String, Properties> cachePropertiesEntry = eachCacheProperties.next();
   String appId = cachePropertiesEntry.getKey();
   Properties cacheProperties = cachePropertiesEntry.getValue();
   String cacheCleanValue = cacheProperties.getProperty(id);
   if (StringUtils.isNotBlank(cacheCleanValue)) {
    LogUtils.performance.debug("clearCacheById:appId={} id={} cleans={}", appId, id, cacheCleanValue);
    String[] cacheCleans = StringUtils.split(cacheCleanValue, ",");
    if (cacheCleans != null && cacheCleans.length > 0) {
     for (String cacheClean : cacheCleans) {
      if (StringUtils.isNotBlank(cacheClean)) {
       Cache cache = this.onGetCache(appId, cacheClean);
       cache.clear();
      }
     }
    }
   }
  }
 }

 /**
  * Clear.
  *
  * @param cacheFilter
  *         the cache filter
  */
 public void clear(CacheFilter cacheFilter) {
  if (this.cacheProperties != null && !this.cacheProperties.isEmpty()) {
   for (Iterator<Entry<String, Properties>> eachCacheProperties = this.getCacheProperties().entrySet().iterator(); eachCacheProperties
    .hasNext();) {
    Entry<String, Properties> cachePropertiesEntry = eachCacheProperties.next();
    String appId = cachePropertiesEntry.getKey();
    Properties cacheProperties = cachePropertiesEntry.getValue();
    for (Iterator<Entry<Object, Object>> eachEntry = cacheProperties.entrySet().iterator(); eachEntry.hasNext();) {
     Entry<Object, Object> entry = eachEntry.next();
     String cacheId = entry.getKey().toString();
     if (cacheFilter != null) {
      if (cacheFilter.accept(appId, cacheId)) {
       LogUtils.performance.debug("clear:{}:accept({},{})", ClassUtils.getShortClassName(cacheFilter.getClass()), appId, cacheId);
       this.clearCacheById(cacheId);
      }
     } else {
      // 不指定cacheFilter,直接清除.
      this.clearCacheById(cacheId);
     }
    }
   }
  }
 }

 /**
  * Clear by class name.
  *
  * @param className
  *         the class name
  * @param ignoreCase
  *         the ignore case
  * @param ignorePackage
  *         the ignore package
  */
 public void clearByClassName(String className, boolean ignoreCase, boolean ignorePackage) {
  ClassNameCacheFilter classNameCacheFilter = new ClassNameCacheFilter();
  classNameCacheFilter.setClassName(className);
  classNameCacheFilter.setIgnoreCase(ignoreCase);
  classNameCacheFilter.setIgnorePackage(ignorePackage);
  this.clear(classNameCacheFilter);
 }

 /**
  * Scan.
  */
 public void scan() {
  if (this.mapperLocations == null) {
   LogUtils.performance.error("No MapperLocations,No scan.");
   return;
  }
  for (Resource mapperResource : this.mapperLocations) {
   if (mapperResource == null) {
    continue;
   }
   try {
    CacheUsedMapper.used(mapperResource.getFile(), nameMapper);
   } catch (ClassNotFoundException e) {
    LogUtils.performance.error("{}", e);
   } catch (IOException e) {
    LogUtils.performance.error("{}", e);
   }
  }
  LogUtils.performance.info("CacheUsedMapper.process begin at {}", DateFormatUtils.format(new Date(), "yyyyMMdd HH:mm:ss"));
  CacheUsedMapper.process();
  LogUtils.performance.info("CacheUsedMapper.process completed at {}", DateFormatUtils.format(new Date(), "yyyyMMdd HH:mm:ss"));
  try {
   Properties cacheCleanProps = new SortedProperties();
   CacheUsedMapper.generateCacheClean(cacheCleanProps, CacheUsedMapper.caches());
   this.cacheProperties.put(this.appId, cacheCleanProps);
   if (ProfileUtils.isDev()) {
    CommonSupport.writeProperties(cacheCleanProps, new File(String.format("%s.cache.properties", this.getAppId())));
   }
  } catch (IOException e) {
   LogUtils.performance.error("{}", e);
  }
 }

 /*
  * (non-Javadoc)
  * 
  * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
  */
 public void afterPropertiesSet() throws Exception {
  this.scan();
 }
}
