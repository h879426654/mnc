package com.basics.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import jodd.bean.BeanUtil;

// TODO: Auto-generated Javadoc
/**
 * GenericMybatisDao.
 * 
 * @param <T>
 *         the generic type
 * @author yuwenfeng.
 */
public class GenericMybatisDao<T> implements GenericDao<T>, ApplicationContextAware {

 /** SessionFactory. */

 protected SqlSessionFactory sessionFactory;

 /** The template. */
 protected SqlSessionTemplate template;

 /** 实体类类型(由构造方法自动赋值). */
 protected Class<?> entityClass;

 /** The id generation type. */
 protected IDGenerationType idGenerationType = IDGenerationType.UUID;

 /** The context. */
 ApplicationContext context;

 /*
  * (non-Javadoc)
  * 
  * @see
  * org.springframework.context.ApplicationContextAware#setApplicationContext
  * (org.springframework.context.ApplicationContext)
  */
 public void setApplicationContext(ApplicationContext conext) throws BeansException {
  this.context = conext;
 }

 /**
  * Instantiates a new generic mybatis dao.
  */
 public GenericMybatisDao() {
  super();
  entityClass = GenericUtils.getSuperClassGenericType(super.getClass());
  onSetting();
 }

 /**
  * On setting.
  */
 public void onSetting() {

 }

 /**
  * Gets the session factory.
  * 
  * @return the session factory
  */
 public SqlSessionFactory getSessionFactory() {
  return sessionFactory;
 }

 /**
  * Sets the session factory.
  * 
  * @param sessionFactory
  *         the new session factory
  */
 public void setSessionFactory(SqlSessionFactory sessionFactory) {
  this.sessionFactory = sessionFactory;
 }

 /**
  * Gets the qualifier session factory.
  * 
  * @return the qualifier session factory
  */
 public String getQualifierSessionFactory() {
  return "";
 }

 /**
  * Wire session factory.
  */
 public void wireSessionFactory() {
  String qualifier = this.getQualifierSessionFactory();
  if (StringUtils.isBlank(qualifier)) {
   this.sessionFactory = this.context.getBean(SqlSessionFactory.class);
  } else {
   this.sessionFactory = (SqlSessionFactory) this.context.getBean(qualifier);
  }
 }

 /**
  * Gets the template.
  * 
  * @return the template
  */
 public SqlSessionTemplate getTemplate() {
  if (this.sessionFactory == null) {
   this.wireSessionFactory();
  }
  if (this.sessionFactory == null) {
   throw new RuntimeException("SqlSessionFactory not autowired.");
  }
  if (this.template == null) {
   this.template = new SqlSessionTemplate(sessionFactory);
  }
  return template;
 }

 /** The namespace. */
 private String namespace = "";

 /**
  * Gets the namespace.
  * 
  * @return the namespace
  */
 public String getNamespace() {
  return namespace;
 }

 /**
  * Sets the namespace.
  * 
  * @param namespace
  *         the new namespace
  */
 public void setNamespace(String namespace) {
  this.namespace = namespace;
 }

 /**
  * Statement.
  * 
  * @param methodName
  *         the method name
  * @return the string
  */
 public String statement(String methodName) {
  if (StringUtils.isBlank(this.namespace)) {
   this.namespace = entityClass.getName();
  }
  return this.namespace + "." + methodName;
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.thinkgem.support.GenericDao#query(java.util.Map, int, int)
  */

 public List<T> query(Map<String, Object> paramMap, int offset, int limit) {
  return this.query(paramMap, offset, limit, "query");
 }

 /**
  * Query.
  * 
  * @param paramMap
  *         the param map
  * @param offset
  *         the offset
  * @param limit
  *         the limit
  * @param methodName
  *         the method name
  * @return the list
  */
 public List<T> query(Map<String, Object> paramMap, int offset, int limit, String methodName) {
  return this.getTemplate().selectList(statement(methodName), paramMap, new RowBounds(offset, limit));
 }

 /**
  * Query.
  * 
  * @param paramMap
  *         the param map
  * @param methodName
  *         the method name
  * @return the list
  */
 public List<T> query(Map<String, Object> paramMap, String methodName) {
  return this.getTemplate().selectList(statement(methodName), paramMap);
 }

 /** The name mapper. */
 INameMapper nameMapper;

 /**
  * Gets the name mapper.
  * 
  * @return the name mapper
  */
 public INameMapper getNameMapper() {
  if (this.nameMapper == null) {
   this.nameMapper = this.onBuildNameMapper();
  }
  return nameMapper;
 }

 public INameMapper onBuildNameMapper() {
  DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
  return defaultNameMapper;
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

 /**
  * Gets the table alias.
  * 
  * @return the table alias
  */
 public String getTableAlias() {
  return StringUtils.uncapitalize(this.entityClass.getSimpleName());
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.GenericDao#query(java.util.Map, java.lang.String, int,
  * int)
  */
 public List<T> query(Map<String, Object> paramMap, String orderBy, int offset, int limit) {
  return query(new QueryFilterBuilder().params(paramMap).orderBy(orderBy).offset(offset).limit(limit).build(), "query");
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.GenericDao#query(com.basics.support.QueryFilter)
  */
 public List<T> query(QueryFilter filter) {
  return query(filter, "query");
 }

 public T queryOne(QueryFilter filter) {
  filter.setLimit(1);
  List<T> items = query(filter);
  if (items.size() > 0) {
   return items.get(0);
  } else {
   return null;
  }
 }

 /**
  * Query.
  * 
  * @param filter
  *         the filter
  * @param methodName
  *         the method name
  * @return the list
  */
 public List<T> query(QueryFilter filter, String methodName) {
  Map<String, Object> params = new HashMap<String, Object>();
  if (filter.getParams() != null) {
   params.putAll(filter.getParams());
  }
  if (StringUtils.isNotBlank(filter.getOrderBy())) {
   params.put("orderBy", OrderByField.buildOrderBySql(this.getNameMapper(), filter.getOrderBy(), this.getTableAlias()));
  }
  String statement = statement(methodName);
  if (filter.hasLimit() || filter.hasOffset()) {
   int offset = filter.hasOffset() ? filter.getOffset() : 0;
   int limit = filter.hasLimit() ? filter.getLimit() : Integer.MAX_VALUE;
   return this.getTemplate().selectList(statement, params, new RowBounds(offset, limit));
  } else {
   return this.getTemplate().selectList(statement, params);
  }
 }

 /**
  * Query.
  *
  * @param <Item>
  *         the generic type
  * @param filter
  *         the filter
  * @param methodName
  *         the method name
  * @return the list
  */
 public <Item> List<Item> queryExtend(QueryFilter filter, String methodName) {
  Map<String, Object> params = new HashMap<String, Object>();
  if (filter.getParams() != null) {
   params.putAll(filter.getParams());
  }
  if (StringUtils.isNotBlank(filter.getOrderBy())) {
   params.put("orderBy", OrderByField.buildOrderBySql(this.getNameMapper(), filter.getOrderBy(), this.getTableAlias()));
  }
  String statement = statement(methodName);
  if (filter.hasLimit() || filter.hasOffset()) {
   int offset = filter.hasOffset() ? filter.getOffset() : 0;
   int limit = filter.hasLimit() ? filter.getLimit() : Integer.MAX_VALUE;
   return this.getTemplate().selectList(statement, params, new RowBounds(offset, limit));
  } else {
   return this.getTemplate().selectList(statement, params);
  }
 }
 /*
  * (non-Javadoc)
  * 
  * @see com.thinkgem.support.GenericDao#insert(java.lang.Object)
  */

 public int insert(T entity) {
  return this.getTemplate().insert(statement("insert"), entity);
 }

 /**
  * Insert extend.
  *
  * @param <Item>
  *         the generic type
  * @param entity
  *         the entity
  * @param methodName
  *         the method name
  * @return the int
  */
 public <Item> int insertExtend(Item entity, String methodName) {
  return this.getTemplate().insert(statement(methodName), entity);
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.thinkgem.support.GenericDao#update(java.lang.Object)
  */

 public int update(T entity) {
  return this.getTemplate().update(statement("update"), entity);
 }

 /**
  * Update extend.
  *
  * @param <Item>
  *         the generic type
  * @param entity
  *         the entity
  * @param methodName
  *         the method name
  * @return the int
  */
 public <Item> int updateExtend(Item entity, String methodName) {
  return this.getTemplate().update(statement(methodName), entity);
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.thinkgem.support.GenericDao#delete(java.lang.Object)
  */

 public int delete(T entity) {
  return this.getTemplate().delete(statement("delete"), entity);
 }

 /**
  * Delete extend.
  *
  * @param entity
  *         the entity
  * @param methodName
  *         the method name
  * @return the int
  */
 public int deleteExtend(Object entity, String methodName) {
  return this.getTemplate().delete(statement(methodName), entity);
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.thinkgem.support.GenericDao#deleteAll(java.util.Map)
  */

 public int deleteAll(Map<String, Object> paramMap) {
  return this.getTemplate().delete(statement("deleteAll"), paramMap);
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.thinkgem.support.GenericDao#count(java.util.Map)
  */

 public long count(Map<String, Object> paramMap) {
  return count(paramMap, "count");
 }

 /**
  * Count.
  * 
  * @param paramMap
  *         the param map
  * @param methodName
  *         the method name
  * @return the long
  */
 public long count(Map<String, Object> paramMap, String methodName) {
  return Long.valueOf(this.getTemplate().selectOne(statement(methodName), paramMap).toString());
 }
 
 /**
  * sumExtend
  * @param paramMap
  * @param methodName
  * @return
  */
 public long sumExtend(Map<String, Object> paramMap, String methodName) {
  return Long.valueOf(this.getTemplate().selectOne(statement(methodName),paramMap).toString());
 }
 
 /** The primary key fields. */
 private String[] primaryKeyFields = new String[] {};

 /**
  * Gets the primary key fields.
  * 
  * @return the primary key fields
  */
 public String[] getPrimaryKeyFields() {
  return primaryKeyFields;
 }

 /**
  * Sets the primary key fields.
  * 
  * @param primaryKeyFields
  *         the new primary key fields
  */
 public void setPrimaryKeyFields(String[] primaryKeyFields) {
  this.primaryKeyFields = primaryKeyFields;
 }

 /**
  * Sets the primary key fields.
  * 
  * @param primaryKeyFields
  *         the new primary key fields
  */
 public void setPrimaryKeyFields(String primaryKeyFields) {
  setPrimaryKeyFields(StringUtils.split(primaryKeyFields, ","));
 }

 /**
  * Gets the primary key.
  * 
  * @param o
  *         the o
  * @return the primary key
  */
 public Map<String, Object> getPrimaryKey(T o) {
  if (o == null) {
   throw new RuntimeException("参数不允许为空");
  }
  String[] keyFields = this.getPrimaryKeyFields();
  Map<String, Object> keyValues = new HashMap<String, Object>();
  if (keyFields == null || keyFields.length == 0) {
   throw new RuntimeException("必须指定主键字段名称.");
  }
  for (int i = 0; i < keyFields.length; i++) {
   try {
    String keyField = keyFields[i];
    Object keyFieldValue = BeanUtils.getProperty(o, keyField);
    if (keyFieldValue != null) {
     keyValues.put(keyField, BeanUtils.getProperty(o, keyField));
    } else {
     throw new RuntimeException("主键不允许为空");
    }
   } catch (Throwable e) {
    throw new RuntimeException(e);
   }
  }
  return keyValues;
 }

 public Map<String, Object> getPrimaryKeySilently(T o) {
  if (o == null) {
   return null;
  }
  String[] keyFields = this.getPrimaryKeyFields();
  Map<String, Object> keyValues = new HashMap<String, Object>();
  if (keyFields == null || keyFields.length == 0) {
   throw new RuntimeException("必须指定主键字段名称.");
  }
  for (int i = 0; i < keyFields.length; i++) {
   try {
    String keyField = keyFields[i];
    Object keyFieldValue = BeanUtil.getPropertySilently(o, keyField);
    if (keyFieldValue != null) {
     keyValues.put(keyField, keyFieldValue);
    }
   } catch (Throwable e) {
    throw new RuntimeException(e);
   }
  }
  return keyValues;
 }
 /*
  * (non-Javadoc)
  * 
  * @see com.thinkgem.support.GenericDao#get(java.io.Serializable)
  */

 public T get(Serializable id) {
  if (id == null || "".equals(id)) {
   return null;
  }
  Map<String, Object> keyValues = getPrimaryKey(id);
  // 根据主键查询.
  List<T> items = this.query(keyValues, 0, 1);
  if (items.size() > 0) {
   return items.get(0);
  } else {
   return null;
  }
 }

 /**
  * Gets the extend.
  *
  * @param <Item>
  *         the generic type
  * @param parameter
  *         the parameter
  * @param methodName
  *         the method name
  * @return the extend
  */
 public <Item> Item getExtend(Object parameter, String methodName) {
  return this.getTemplate().selectOne(this.statement(methodName), parameter);
 }

 /**
  * Gets the primary key.
  * 
  * @param id
  *         the id
  * @return the primary key
  */
 public Map<String, Object> getPrimaryKey(Serializable id) {
  if (id == null) {
   throw new RuntimeException("主键不允许为空");
  }
  String[] keyFields = this.getPrimaryKeyFields();
  Map<String, Object> keyValues = new HashMap<String, Object>();
  if (keyFields == null || keyFields.length == 0) {
   throw new RuntimeException("必须指定主键字段名称.");
  }
  // 单一字段主键
  if (keyFields.length == 1) {
   // 检查空的主键.
   if (id instanceof String) {
    if (StringUtils.isBlank((String) id)) {
     throw new RuntimeException("必须指定主键字段值.");
    }
   }
   keyValues.put(keyFields[0], id);
  } else {
   // 联合主键
   String[] values = StringUtils.split(id.toString(), "_");
   if (values == null || values.length == 0) {
    throw new RuntimeException("必须指定主键字段值.");
   }
   if (values.length < keyFields.length) {
    throw new RuntimeException("主键字段值数量必须为" + keyFields.length);
   }
   for (int i = 0; i < keyFields.length; i++) {
    keyValues.put(keyFields[i], values[i]);
   }
  }
  return keyValues;
 }

 /**
  * doGenerateKey.
  *
  * @param entity
  *         the entity
  * @param keyField
  *         the key field
  * @return the string
  */
 public Object doGenerateKey(T entity, String keyField) {
  @SuppressWarnings("rawtypes")
  Class keyFieldType = BeanUtil.getPropertyType(entity, keyField);
  if (idGenerationType.isAuto()) {
   if (keyFieldType.equals(String.class)) {
    return onGenerateUUID();
   }
   if (Number.class.isAssignableFrom(keyFieldType)) {
    return this.onGetNextValueFromSequence();
   }
  } else if (idGenerationType.isUuid()) {
   return this.onGenerateUUID();
  } else if (idGenerationType.isSeq()) {
   return this.onGetNextValueFromSequence();
  }
  throw new RuntimeException("IDGenerationType：" + this.idGenerationType + " for keyFieldType " + keyFieldType.getName() + " on field "
   + keyField + " is not supported or implemented.");
 }

 /**
  * On generate uuid.
  * 
  * @return the string
  */
 public String onGenerateUUID() {
  return GuidUtils.generateSimpleGuid();
 }

 /** The id sequence name. */
 protected String idSequenceName;

 /**
  * On get next value from sequence.
  * 
  * @return the object
  */
 public Object onGetNextValueFromSequence() {
  String idSequenceNameUsed = this.idSequenceName;
  // 没指定序列名称,根据默认命名规则生成.
  if (StringUtils.isBlank(idSequenceNameUsed)) {
   idSequenceNameUsed = this.getNameMapper().getColumnName(this.entityClass.getSimpleName()) + "_SEQ";
  }
  return getNextValueFromSequence(idSequenceNameUsed);
 }

 /**
  * Gets the next value from sequence.
  * 
  * @param seq
  *         the seq
  * @return the next value from sequence
  */
 public long getNextValueFromSequence(String seq) {
  throw new RuntimeException("getNextValueFromSequence " + seq + " not implemented!");
 }

 /**
  * 默认生成字符串类型的单一主键.
  * 
  * @param entity
  *         the entity
  */
 public void onGeneratePrimaryKey(T entity) {
  if (this.primaryKeyFields != null && this.primaryKeyFields.length == 1) {
   String keyField = this.primaryKeyFields[0];
   try {
    Object singleKeyValue = BeanUtils.getProperty(entity, keyField);
    if (singleKeyValue == null || StringUtils.isBlank(singleKeyValue.toString())) {
     BeanUtils.setProperty(entity, keyField, this.doGenerateKey(entity, keyField));
    }
   } catch (Throwable e) {
    LogUtils.performance.error("can't read primary key property from entity,cause :{}", e);
   }
  }
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.GenericDao#save(java.lang.Object)
  */
 public int save(T entity) {
  // 生成主键.
  if (!this.idGenerationType.isIdentity()) {
   this.onGeneratePrimaryKey(entity);
  }
  Map<String, Object> primaryKey = this.getPrimaryKey(entity);
  // 没指定主键,依赖数据库生成.
  if (primaryKey == null || primaryKey.isEmpty()) {
   return insert(entity);
  }
  long countByPrimaryKey = this.count(primaryKey);
  if (countByPrimaryKey > 1) {
   throw new RuntimeException(String.format("根据主键%s找到%s条记录.", new Object[] { primaryKey, countByPrimaryKey }));
  }
  if (countByPrimaryKey == 1) {
   return update(entity);
  } else {
   return insert(entity);
  }
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.GenericDao#delete(java.io.Serializable)
  */
 public int delete(Serializable id) {
  Map<String, Object> primaryKey = this.getPrimaryKey(id);
  if (primaryKey == null || primaryKey.isEmpty()) {
   throw new RuntimeException("必须指定主键字段值.");
  }
  return deleteAll(primaryKey);
 }

 /**
  * Query pagination.
  *
  * @param queryName
  *         the query name
  * @param countName
  *         the count name
  * @param params
  *         the params
  * @param pageNum
  *         the page num
  * @param pageSize
  *         the page size
  * @return the pagination support
  */
 public PaginationSupport queryPagination(Map<String, Object> params, int pageNum, int pageSize) {
  return this.queryPaginationExtend("query", "count", params, pageNum, pageSize);
 }

 /**
  * Query pagination extend.
  *
  * @param queryName
  *         the query name
  * @param countName
  *         the count name
  * @param params
  *         the params
  * @param pageNum
  *         the page num
  * @param pageSize
  *         the page size
  * @return the pagination support
  */
 public PaginationSupport queryPaginationExtend(String queryName, String countName, Map<String, Object> params, int pageNum, int pageSize) {
  long total = this.count(params, countName);
  PaginationSupport page = new PaginationSupport(pageNum, pageSize, total);
  if (page.hasCurrentPageNo()) {
   QueryFilter filter = new QueryFilter();
   filter.setParams(params);
   filter.setOffset(page.getStartOfPage());
   filter.setLimit(pageSize);
   page.setItems(this.query(filter, queryName));
  }
  return page;
 }

 /***
  * 扩展的count方法
  */
 public long countExtend(Map<String, Object> paramMap, String methodName) {
  return count(paramMap, methodName);
 }

 /**
  * Gets the components.
  *
  * @param <Component>
  *         the generic type
  * @param key
  *         the key
  * @return the components
  */
 public <Component> Map<String, Component> getComponents(Class<Component> key) {
  Map<String, Component> beans = this.context.getBeansOfType(key);
  return beans;
 }

 /**
  * Gets the services.
  *
  * @param <Service>
  *         the generic type
  * @param key
  *         the key
  * @return the services
  */
 public <Service> List<Service> getServices(Class<Service> key) {
  List<Service> beans = new ArrayList<Service>();
  beans.addAll(this.getComponents(key).values());
  return beans;
 }

 /**
  * Gets the service.
  *
  * @param <Service>
  *         the generic type
  * @param key
  *         the key
  * @return the service
  */
 public <Service> Service getService(Class<Service> key) {
  return this.context.getBean(key);
 }

 /**
  * 判断指定的字段是否唯一.
  * 
  * @param entity
  *         实体.
  * @param field
  *         字段.
  * @return 指定的字段是否唯一
  */
 public boolean uniqueByField(T entity, String field) {
  CommonSupport.checkNotNull(field, "checkUnique field not allow null.");
  QueryFilterBuilder builder = new QueryFilterBuilder();
  Object fieldValue = BeanUtil.getProperty(entity, field);
  builder.put(field, fieldValue);
  T one = this.queryOne(builder.build());
  if (one != null) {
   Map<String, Object> oneId = this.getPrimaryKeySilently(one);
   Map<String, Object> id = this.getPrimaryKeySilently(entity);
   return oneId.equals(id);
  }
  return true;
 }

}