
package com.basics.support.mybatis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.support.LogUtils;

import jodd.io.findfile.FindFile.Match;
import jodd.io.findfile.WildcardFindFile;

/**
 * The Class CacheUsedMapper.
 */
public class CacheUsedMapper {

 /** The mapper file. */
 protected File mapperFile;

 /** The content. */
 protected String content;

 /** The entity class. */
 protected Class<?> entityClass;

 /** The namespace. */
 protected String namespace;

 /** The table name. */
 protected String tableName;

 /** The name mapper. */
 protected INameMapper nameMapper;
 /** The using. */
 protected List<CacheUsedMapper> usedCaches = new ArrayList<CacheUsedMapper>();

 /**
  * Gets the used caches.
  *
  * @return the used caches
  */
 public List<CacheUsedMapper> getUsedCaches() {
  return usedCaches;
 }

 /**
  * Sets the used caches.
  *
  * @param usedCaches
  *         the new used caches
  */
 public void setUsedCaches(List<CacheUsedMapper> usedCaches) {
  this.usedCaches = usedCaches;
 }

 /**
  * Gets the namespace.
  *
  * @return the namespace
  */
 public String getNamespace() {
  return namespace;
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
  * Gets the mapper file.
  *
  * @return the mapper file
  */
 public File getMapperFile() {
  return mapperFile;
 }

 /**
  * Gets the content.
  *
  * @return the content
  */
 public String getContent() {
  return content;
 }

 /**
  * Gets the entity class.
  *
  * @return the entity class
  */
 public Class<?> getEntityClass() {
  return entityClass;
 }

 /**
  * Instantiates a new cache used mapper.
  *
  * @param mapperFile
  *         the mapper file
  */
 public CacheUsedMapper(File mapperFile) {
  super();
  this.mapperFile = mapperFile;
 }

 /**
  * Gets the table name.
  *
  * @return the table name
  */
 public String getTableName() {
  return tableName;
 }

 /**
  * Used.
  *
  * @param another
  *         the another
  * @return true, if successful
  */
 public boolean used(CacheUsedMapper another) {
  if (StringUtils.equals(this.getNamespace(), another.getNamespace())) {
   return false;
  }
  if (StringUtils.containsIgnoreCase(this.content, another.getTableName() + " ")) {
   return true;
  }
  return false;
 }

 /** The caches. */
 protected static Map<File, CacheUsedMapper> caches = new java.util.concurrent.ConcurrentHashMap<File, CacheUsedMapper>();
 /** The cache reference on. */
 protected static Map<String, List<String>> cacheReferenceOn = new java.util.concurrent.ConcurrentHashMap<String, List<String>>();
 /** The cache reference. */
 protected static Map<String, List<String>> cacheReference = new java.util.concurrent.ConcurrentHashMap<String, List<String>>();

 /**
  * Used.
  *
  * @param resourceDir
  *         the resource dir
  * @param entityClassArray
  *         the entity class array
  * @throws ClassNotFoundException
  *          the class not found exception
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public static void used(File resourceDir, Class<?>... entityClassArray) throws ClassNotFoundException, IOException {
  for (Class<?> entityClass : entityClassArray) {
   used(resourceDir, entityClass, new DefaultNameMapper());
  }
 }

 /**
  * Used.
  *
  * @param resourceDir
  *         the resource dir
  * @param entityClass
  *         the entity class
  * @param nameMapper
  *         the name mapper
  * @throws ClassNotFoundException
  *          the class not found exception
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public static void used(File resourceDir, Class<?> entityClass, INameMapper nameMapper) throws ClassNotFoundException, IOException {
  String mapperFile = StringUtils.replace(entityClass.getName(), ".", "/") + "Mapper.xml";
  mapperFile = StringUtils.replace(mapperFile, "/entity/", "/mapper/");
  CacheUsedMapper.used(new File(resourceDir, mapperFile), nameMapper);
 }

 /**
  * Used.
  *
  * @param mapperFile
  *         the mapper file
  * @param nameMapper
  *         the name mapper
  * @throws ClassNotFoundException
  *          the class not found exception
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public static void used(File mapperFile, INameMapper nameMapper) throws ClassNotFoundException, IOException {
  if (!caches.containsKey(mapperFile)) {
   caches.put(mapperFile, CacheUsedMapper.build(mapperFile, nameMapper));
  }
 }

 /**
  * Scan mapper.
  *
  * @param resourceDir
  *         the resource dir
  * @throws ClassNotFoundException
  *          the class not found exception
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public static void scanMapper(File resourceDir) throws ClassNotFoundException, IOException {
  scanMapper(resourceDir, new DefaultNameMapper());
 }

 /**
  * Scan mapper.
  *
  * @param resourceDir
  *         the resource dir
  * @param nameMapper
  *         the name mapper
  */
 public static void scanMapper(File resourceDir, INameMapper nameMapper) {
  WildcardFindFile find = new jodd.io.findfile.WildcardFindFile();
  find.setRecursive(true);
  find.setIncludeDirs(false);
  find.setMatchType(Match.NAME).include("*Mapper.xml");
  find.searchPath(resourceDir);
  for (Iterator<File> eachFile = find.iterator(); eachFile.hasNext();) {
   File mapperFile = eachFile.next();
   LogUtils.performance.info("{}", mapperFile);
   try {
    used(mapperFile, nameMapper);
   } catch (ClassNotFoundException e) {
    LogUtils.performance.error("{}", e);
   } catch (IOException e) {
    LogUtils.performance.error("{}", e);
   }
  }
 }

 /**
  * Caches.
  *
  * @return the list
  */
 public static List<CacheUsedMapper> caches() {
  List<CacheUsedMapper> items = new ArrayList<CacheUsedMapper>();
  items.addAll(caches.values());
  return items;
 }

 /**
  * Process.
  */
 public static void process() {
  List<CacheUsedMapper> items = new ArrayList<CacheUsedMapper>();
  items.addAll(caches.values());
  for (CacheUsedMapper item : items) {
   CacheUsed cacheUsed = new CacheUsed();
   cacheUsed.cache(item.getNamespace());
   for (CacheUsedMapper used : items) {
    if (!used.getNamespace().equals(item.getNamespace())) {
     if (item.used(used)) {
      cacheUsed.used(used.getNamespace());
      item.usedCaches.add(used);
     }
    }
   }
   CacheUsedMapper.used(cacheUsed);
   CacheUsed.used(new CacheAdapter(item.getEntityClass().getName()), cacheUsed);
  }
 }

 /**
  * Builds the.
  *
  * @param file
  *         the file
  * @param nameMapper
  *         the name mapper
  * @return the cache used mapper
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  * @throws ClassNotFoundException
  *          the class not found exception
  */
 public static CacheUsedMapper build(File file, INameMapper nameMapper) throws IOException, ClassNotFoundException {
  CacheUsedMapper mapper = new CacheUsedMapper(file);
  String content = FileUtils.readFileToString(file, "UTF-8");
  Document doc = Jsoup.parse(file, "UTF-8");
  String namespace = doc.getElementsByTag("mapper").get(0).attr("namespace");
  Class<?> entityClass = ClassUtils.getClass(namespace);
  String entityName = ClassUtils.getSimpleName(entityClass);
  String tableName = nameMapper.getColumnName(entityName);
  mapper.tableName = tableName;
  mapper.entityClass = entityClass;
  mapper.content = content;
  mapper.nameMapper = nameMapper;
  mapper.namespace = namespace;
  return mapper;
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
  cacheReferenceOn.put(cache, dependOnCaches);
 }

 /**
  * Used.
  *
  * @param cacheUsed
  *         the cache used
  */
 public static void used(CacheUsed cacheUsed) {
  for (String usedCache : cacheUsed.getUsedCaches()) {
   cacheReferenceOn(usedCache, cacheUsed.getCache());
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
  if (cacheReferenceOn.containsKey(cacheId)) {
   return cacheReferenceOn.get(cacheId);
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
   List<String> dependOns = getCacheReferenceOn(cacheId);
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
  if (cacheReference.containsKey(cacheId)) {
   return cacheReference.get(cacheId);
  } else {
   List<String> reference = new ArrayList<String>();
   searchReference(cacheId, reference);
   cacheReference.put(cacheId, reference);
   return reference;
  }
 }

 /**
  * Generate cache use.
  *
  * @param usedMapper
  *         the used mapper
  * @return the string
  */
 public static String generateCacheUse(CacheUsedMapper usedMapper) {
  StringBuilder sb = new StringBuilder();
  sb.append("@com.basics.support.annotation.CacheUse(types = {");
  for (int i = 0; i < usedMapper.usedCaches.size(); i++) {
   if (i > 0) {
    sb.append(",");
   }
   CacheUsedMapper used = usedMapper.usedCaches.get(i);
   sb.append(used.getEntityClass().getName());
   sb.append(".class");
  }
  sb.append("})");
  return sb.toString();
 }

 /**
  * Generate cache use.
  *
  * @param srcDir
  *         the src dir
  * @param genDir
  *         the gen dir
  * @param items
  *         the items
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public static void generateCacheUse(File srcDir, File genDir, List<CacheUsedMapper> items) throws IOException {
  for (CacheUsedMapper item : items) {
   generateCacheUse(srcDir, genDir, item);
  }
 }

 /**
  * Generate cache use.
  *
  * @param srcDir
  *         the src dir
  * @param genDir
  *         the gen dir
  * @param items
  *         the items
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public static void generateCacheUse(File srcDir, File genDir, CacheUsedMapper... items) throws IOException {
  for (CacheUsedMapper item : items) {
   if (item.getUsedCaches().size() > 0) {
    String javaName = StringUtils.replace(item.getEntityClass().getName(), ".", "/") + ".java";
    File java = new File(srcDir, javaName);
    String javaContent = FileUtils.readFileToString(java, "UTF-8");
    String generateCacheUse = CacheUsedMapper.generateCacheUse(item);
    LogUtils.performance.debug("generateCacheUse:{}", generateCacheUse);
    StringBuilder sb = new StringBuilder();
    String publicClass = "public class";
    sb.append(StringUtils.substringBefore(javaContent, publicClass));
    sb.append(generateCacheUse);
    sb.append("\r\n");
    sb.append(publicClass);
    sb.append(StringUtils.substringAfter(javaContent, publicClass));
    File javaGen = new File(genDir, javaName);
    LogUtils.performance.debug("generateCacheUse:{}", javaGen);
    FileUtils.writeStringToFile(javaGen, sb.toString(), "UTF-8");
   }
  }
 }

 /**
  * Scan mapper and generate cache use to java source.
  *
  * @param resourceDir
  *         the resource dir
  * @param srcDir
  *         the src dir
  * @param genDir
  *         the gen dir
  * @throws ClassNotFoundException
  *          the class not found exception
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public static void scanMapperAndGenerateCacheUseToJavaSource(File resourceDir, File srcDir, File genDir)
  throws ClassNotFoundException, IOException {
  CacheUsedMapper.scanMapper(resourceDir);
  CacheUsedMapper.process();
  CacheUsedMapper.generateCacheUse(srcDir, genDir, CacheUsedMapper.caches());
 }

 /**
  * Scan mapper and generate cache clean to properties.
  *
  * @param resourceDir
  *         the resource dir
  * @param props
  *         the props
  * @throws ClassNotFoundException
  *          the class not found exception
  * @throws IOException
  *          the IO exception
  */
 public static void scanMapperAndGenerateCacheCleanToProperties(File resourceDir, Properties props)
  throws ClassNotFoundException, IOException {
  CacheUsedMapper.scanMapper(resourceDir);
  CacheUsedMapper.process();
  CacheUsedMapper.generateCacheClean(props, CacheUsedMapper.caches());
 }

 /**
  * Generate cache clean.
  *
  * @param props
  *         the props
  * @param items
  *         the items
  * @throws IOException
  *          the IO exception
  */
 public static void generateCacheClean(Properties props, List<CacheUsedMapper> items) throws IOException {
  for (CacheUsedMapper item : items) {
   String key = item.getEntityClass().getName();
   StringBuilder sb = new StringBuilder();
   List<String> refs = CacheUsed.getReference(key);
   for (int i = 0; i < refs.size(); i++) {
    if (i > 0) {
     sb.append(",");
    }
    sb.append(refs.get(i));
   }
   props.put(key, sb.toString());
  }
 }
}
