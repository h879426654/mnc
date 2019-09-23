package com.basics.support;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import jodd.bean.BeanUtil;
import jodd.util.ReflectUtil;

/**
 * GenericMybatisService.
 * 
 * @param <T>
 *         the generic type
 * @author yuwenfeng.
 */
@SuppressWarnings("unchecked")
public class GenericMybatisService<T> implements GenericService<T>, InitializingBean, ApplicationContextAware, ServiceSupport {

 /** The dao. */
 protected GenericMybatisDao<T> dao;

 /** 实体类类型(由构造方法自动赋值). */
 private Class<?> entityClass;

 /**
  * Instantiates a new generic ibatis service.
  */
 public GenericMybatisService() {
  entityClass = GenericUtils.getSuperClassGenericType(super.getClass());
 }

 /**
  * Query by pk.
  *
  * @param id
  *         the id
  * @return the t
  */
 public T queryByPK(Serializable id) {
  return findByPK(id);
 }

 /**
  * Find by pk.
  *
  * @param id
  *         the id
  * @return the t
  */
 @SuppressWarnings("rawtypes")
 public T findByPK(Serializable id) {
  if (id == null) {
   throw new RuntimeException("id cannt be null!");
  }
  // 空字符串.
  if (id instanceof String) {
   if (StringUtils.isBlank((String) id)) {
    return null;
   }
  }
  Map<String, Object> params = dao.getPrimaryKey(id);
  if (params.isEmpty()) {
   throw new RuntimeException("primary key required.");
  }
  List items = dao.query(params, 0, 1);
  if (items.isEmpty()) {
   return null;
  } else {
   return (T) items.get(0);
  }
 }

 /**
  * Exists.
  *
  * @param id
  *         the id
  * @return true, if exists
  */
 public boolean exists(Serializable id) {
  Map<String, Object> params = dao.getPrimaryKey(id);
  return dao.count(params) > 0;
 }

 /**
  * Save.
  *
  * @param entity
  *         the entity
  */
 public void save(T entity) {
  if (entity == null) {
   throw new RuntimeException("entity is null.");
  }
  this.willSave(entity);
  dao.save(entity);
  this.didSave(entity);
 }

 /**
  * Will save.
  *
  * @param entity
  *         the entity
  */
 public void willSave(T entity) {
  if (entity instanceof SaveAware) {
   SaveAware saveAware = (SaveAware) entity;
   saveAware.willSave();
  }
 }

 /**
  * Did save.
  *
  * @param entity
  *         the entity
  */
 public void didSave(T entity) {
  if (entity instanceof SaveAware) {
   SaveAware saveAware = (SaveAware) entity;
   saveAware.didSave();
  }
 }

 /**
  * Check unique.
  *
  * @param entity
  *         the entity
  * @param field
  *         the field
  * @param fieldName
  *         the field name
  */
 public void checkUnique(T entity, String field, String fieldName) {
  CommonSupport.checkNotNull(field, "checkUnique field not allow null.");
  CommonSupport.checkNotNull(field, "checkUnique fieldName not allow null.");
  QueryFilterBuilder builder = new QueryFilterBuilder();
  Object fieldValue = BeanUtil.getProperty(entity, field);
  CommonSupport.checkNotNull(fieldValue, "字段:%s不允许为空.", fieldName);
  builder.put(field, fieldValue);
  T one = this.queryOne(builder.build());
  if (one != null) {
   Map<String, Object> oneId = this.dao.getPrimaryKeySilently(one);
   Map<String, Object> id = this.dao.getPrimaryKeySilently(entity);
   CommonSupport.checkState(oneId.equals(id), "%s不允许重复", fieldName);
  }
 }

 /**
  * Delete by pk.
  *
  * @param id
  *         the id
  */
 public void deleteByPK(Serializable id) {
  T entity = this.findByPK(id);
  if (entity != null) {
   dao.delete(entity);
  }
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.GenericService#delete(java.lang.Object)
  */
 public void delete(T entity) {
  if (entity != null) {
   dao.delete(entity);
  }
 }

 /**
  * Delete by p ks.
  *
  * @param ids
  *         the ids
  */
 public void deleteByPKs(Serializable[] ids) {
  for (Serializable id : ids)
   deleteByPK(id);
 }

 /**
  * Query all.
  *
  * @param params
  *         the params
  * @param orderBy
  *         the order by
  * @return the list< t>
  */
 public List<T> queryAll(Map<String, Object> params, String orderBy) {
  return dao.query(params, orderBy);
 }

 /**
  * Count.
  * 
  * @param params
  *         the params
  * @return the long
  */
 public long count(Map<String, Object> params) {
  return dao.count(params);
 }

 /**
  * Delete all.
  * 
  * @param paramMap
  *         the param map
  */
 public void deleteAll(Map<String, Object> paramMap) {
  dao.deleteAll(paramMap);
 }

 /**
  * Query.
  * 
  * @param filter
  *         the filter
  * @return the list
  */
 public List<T> query(QueryFilter filter) {
  return dao.query(filter);
 }

 /**
  * Query one.
  * 
  * @param filter
  *         the filter
  * @return the t
  */
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
  * @param pageNum
  *         the page num
  * @param pageSize
  *         the page size
  * @return the pagination support
  */
 public PaginationSupport query(QueryFilter filter, int pageNum, int pageSize) {
  return query("query", "count", filter, pageNum, pageSize);
 }

 /**
  * Query.
  *
  * @param queryName
  *         the query name
  * @param countName
  *         the count name
  * @param filter
  *         the filter
  * @param pageNum
  *         the page num
  * @param pageSize
  *         the page size
  * @return the pagination support
  */
 public PaginationSupport query(String queryName, String countName, QueryFilter filter, int pageNum, int pageSize) {
  long total = dao.count(filter.getParams(), countName);
  PaginationSupport page = new PaginationSupport(pageNum, pageSize, total);
  if (page.hasCurrentPageNo()) {
   filter.setOffset(page.getStartOfPage());
   filter.setLimit(pageSize);
   page.setItems(dao.query(filter, queryName));
  }
  return page;
 }

 /**
  * Query.
  *
  * @param queryName
  *         the query name
  * @param filter
  *         the filter
  * @return the pagination support
  */
 public PaginationSupport query(String queryName, QueryFilter filter) {
  PaginationSupport page = new PaginationSupport();
  page.setItems(dao.query(filter, queryName));
  return page;
 }

 /**
  * After properties set.
  *
  * @throws Exception
  *          the exception
  */
 public void afterPropertiesSet() throws Exception {
  String mybatisDao = getMybatisDaoBean();
  this.dao = (GenericMybatisDao<T>) this.context.getBean(mybatisDao);
 }

 /**
  * Gets the mybatis dao bean.
  *
  * @return the mybatis dao bean
  */
 public String getMybatisDaoBean() {
  String mybatisDao = String.format("%sMybatisDao", StringUtils.uncapitalize(this.entityClass.getSimpleName()));
  return mybatisDao;
 }

 /** The context. */
 ApplicationContext context;

 /**
  * Sets the application context.
  *
  * @param conext
  *         the application context
  * @throws BeansException
  *          the beans exception
  */
 public void setApplicationContext(ApplicationContext conext) throws BeansException {
  this.context = conext;
 }

 /**
  * On get.
  *
  * @param id
  *         the id
  * @return the t
  */
 public T onGet(String id) {
  return this.findByPK(id);
 }

 /**
  * Gets the.
  *
  * @param id
  *         the id
  * @return the item result support< t>
  */
 public ItemResultSupport<T> get(String id) {
  ItemResultSupport<T> result = new ItemResultSupport<T>();
  try {
   if (StringUtils.isBlank(id)) {
    throw new RuntimeException("id 不允许为空.");
   }
   T item = this.onGet(id);
   if (item != null) {
    result.setItem(item);
    result.onSuccess("读取成功");
   } else {
    result.onException("数据不存在(id=" + id + ")");
   }
  } catch (Throwable e) {
   result.onException(e);
  }
  return result;
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
  * Gets the data source.
  *
  * @param request
  *         the request
  * @return the data source
  */
 public DataSourceResponse getDataSource(DataSourceRequest request) {
  DataSourceResponse response = new DataSourceResponse();
  QueryFilterBuilder filterBuilder = new QueryFilterBuilder();
  FilterDescriptor filter = request.getFilter();
  if (filter != null) {
   appendFilter(filterBuilder, filter);
  }
  long total = this.count(filterBuilder.build().getParams());
  // 排序.
  this.sort(filterBuilder, request.getSort());
  // 分页.
  if (request.getPage() > 0 && request.getPageSize() > 0) {
   PaginationSupport ps = new PaginationSupport(request.getPage(), request.getPageSize(), total);
   filterBuilder.limit(ps.getPageSize()).offset(ps.getStartOfPage());
  } else {
   filterBuilder.limit(request.getTake()).offset(request.getSkip());
  }
  // 返回结果.
  response.setTotal(total);
  response.setData(this.query(filterBuilder.build()));
  // TODO:分组,聚合.
  return response;
 }

 /**
  * Gets the filter field suffix.
  *
  * @param filter
  *         the filter
  * @return the filter field suffix
  */
 public String getFilterFieldSuffix(FilterDescriptor filter) {
  // 按目前的约定:相等不增加后缀.
  if (StringUtils.equalsIgnoreCase(FilterOperatorEnum.EQ.getValue(), filter.getOperator())) {
   return "";
  }
  return filter.getOperator().toUpperCase();
 }

 /**
  * Append filter.
  *
  * @param filterBuilder
  *         the filter builder
  * @param filter
  *         the filter
  */
 public void appendFilter(QueryFilterBuilder filterBuilder, FilterDescriptor filter) {
  if (filter != null && StringUtils.isNotBlank(filter.getField()) && StringUtils.isNotBlank(filter.getOperator())
   && filter.getValue() != null) {
   filterBuilder.put(filter.getField() + getFilterFieldSuffix(filter), filter.getValue());
  }
  if (filter != null && filter.getFilters() != null) {
   for (FilterDescriptor f : filter.getFilters()) {
    appendFilter(filterBuilder, f);
   }
  }
 }

 /**
  * Sort.
  *
  * @param filterBuilder
  *         the filter builder
  * @param sort
  *         the sort
  */
 public void sort(QueryFilterBuilder filterBuilder, List<SortDescriptor> sort) {
  List<OrderByField> orderByFields = new ArrayList<OrderByField>();
  if (sort != null && !sort.isEmpty()) {
   for (SortDescriptor entry : sort) {
    String field = entry.getField();
    String dir = entry.getDir();
    boolean isAscending = !StringUtils.equalsIgnoreCase("desc", dir);
    OrderByField orderField = new OrderByField(this.dao.getNameMapper(), field, isAscending);
    orderByFields.add(orderField);
   }
  }
  if (orderByFields.size() > 0) {
   filterBuilder.orderBy(OrderByField.buildOrderBySql(orderByFields));
  }
 }

 /**
  * 根据主键批量更新某个字段.
  * 
  * @param ids
  * @param field
  * @param value
  */
 public void updateByIds(String[] ids, String field, String value) {
  CommonSupport.checkNotNull(ids, "主键不允许为空.");
  CommonSupport.checkState(CommonSupport.isNotBlank(field), "字段名称不允许为空.");
  String[] pkFields = this.dao.getPrimaryKeyFields();
  CommonSupport.checkState(pkFields.length == 1, "不支持联合主键.");
  String pkField = pkFields[0];
  try {
   T entity = (T) ReflectUtil.newInstance(this.entityClass);
   for (String pk : ids) {
    BeanUtil.setProperty(entity, pkField, pk);
    BeanUtil.setProperty(entity, field, value);
    this.dao.update(entity);
   }
  } catch (Throwable e) {
   CommonSupport.checkState(true, e.getMessage());
  }
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
  return this.dao.uniqueByField(entity, field);
 }
 
//保留5位小数，取下
 public BigDecimal keepFiveNum(BigDecimal num) {
		return num.setScale(8, RoundingMode.FLOOR);
 }
 
 
}