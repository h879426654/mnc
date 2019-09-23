package com.basics.support;

import java.util.List;
import java.util.Map;

/**
 * The Interface GenericDao.
 * 
 * @param <T>
 *         the generic type
 * @author yuwenfeng
 */
public interface GenericDao<T> {

 /**
  * Query.
  * 
  * @param paramMap
  *         the param map
  * @param offset
  *         the offset
  * @param limit
  *         the limit
  * @return the list
  */
 List<T> query(Map<String, Object> paramMap, int offset, int limit);

 /**
  * Query.
  * 
  * @param paramMap
  *         the param map
  * @param orderBy
  *         the order by
  * @param offset
  *         the offset
  * @param limit
  *         the limit
  * @return the list
  */
 List<T> query(Map<String, Object> paramMap, String orderBy, int offset, int limit);

 /**
  * Query.
  * 
  * @param filter
  *         the filter
  * @return the list
  */
 List<T> query(QueryFilter filter);

 /**
  * Query one.
  *
  * @param filter
  *         the filter
  * @return the t
  */
 public T queryOne(QueryFilter filter);

 /**
  * Insert.
  * 
  * @param entity
  *         the entity
  * @return the int
  */
 int insert(T entity);

 /**
  * Update.
  * 
  * @param entity
  *         the entity
  * @return the int
  */
 int update(T entity);

 /**
  * Delete.
  * 
  * @param entity
  *         the entity
  * @return the int
  */
 int delete(T entity);

 /**
  * Delete.
  * 
  * @param id
  *         the id
  * @return the int
  */
 int delete(java.io.Serializable id);

 /**
  * Delete all.
  * 
  * @param paramMap
  *         the param map
  * @return the int
  */
 int deleteAll(Map<String, Object> paramMap);

 /**
  * Count.
  * 
  * @param paramMap
  *         the param map
  * @return the int
  */
 long count(Map<String, Object> paramMap);

 /***
  * 求记录数的扩展方法
  * 
  * @return
  */
 long countExtend(Map<String, Object> paramMap, String methodName);
 
 /**
  * 求和拓展方法
  * @param paramMap
  * @param methodName
  * @return
  */
 long sumExtend(Map<String,Object> paramMap,String methodName);

 /**
  * Gets the.
  * 
  * @param id
  *         the id
  * @return the t
  */
 T get(java.io.Serializable id);

 /**
  * Save.
  * 
  * @param entity
  *         the entity
  * @return the int
  */
 int save(T entity);

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
 public <Item> List<Item> queryExtend(QueryFilter filter, String methodName);

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
 public <Item> int insertExtend(Item entity, String methodName);

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
 public <Item> int updateExtend(Item entity, String methodName);

 /**
  * Delete extend.
  *
  * @param parameter
  *         the entity
  * @param methodName
  *         the method name
  * @return the int
  */
 public int deleteExtend(Object parameter, String methodName);

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
 public <Item> Item getExtend(Object parameter, String methodName);

 /**
  * Query pagination.
  *
  * @param params
  *         the params
  * @param pageNum
  *         the page num
  * @param pageSize
  *         the page size
  * @return the pagination support
  */
 public PaginationSupport queryPagination(Map<String, Object> params, int pageNum, int pageSize);

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
 public PaginationSupport queryPaginationExtend(String queryName, String countName, Map<String, Object> params, int pageNum, int pageSize);

 /**
  * 判断指定的字段是否唯一.
  * 
  * @param entity
  *         实体.
  * @param field
  *         字段.
  * @return 指定的字段是否唯一
  */
 public boolean uniqueByField(T entity, String field);

}