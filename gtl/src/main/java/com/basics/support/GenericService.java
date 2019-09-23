package com.basics.support;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * GenericService.
 * 
 * @param <T>
 *         the generic type
 * @author yuwenfeng.
 */
public interface GenericService<T> {

	/**
	 * Query by pk.
	 * 
	 * @param id
	 *         the id
	 * @return the t
	 */
	public T queryByPK(Serializable id);

	/**
	 * Exists.
	 * 
	 * @param id
	 *         the id
	 * @return true, if successful
	 */
	public boolean exists(Serializable id);

	/**
	 * Save.
	 * 
	 * @param entity
	 *         the entity
	 */
	public void save(T entity);

	/**
	 * Delete.
	 *
	 * @param entity
	 *         the entity
	 */
	public void delete(T entity);

	/**
	 * Delete by pk.
	 * 
	 * @param id
	 *         the id
	 */
	public void deleteByPK(Serializable id);

	/**
	 * Delete by p ks.
	 * 
	 * @param ids
	 *         the ids
	 */
	public void deleteByPKs(Serializable[] ids);

	/**
	 * Delete all.
	 * 
	 * @param params
	 *         the params
	 */
	public void deleteAll(Map<String, Object> params);

	/**
	 * Count.
	 * 
	 * @param params
	 *         the params
	 * @return the long
	 */
	public long count(Map<String, Object> params);

	/**
	 * Query.
	 * 
	 * @param filter
	 *         the filter
	 * @return the list
	 */
	public List<T> query(QueryFilter filter);

	/**
	 * Query one.
	 * 
	 * @param filter
	 *         the filter
	 * @return the t
	 */
	public T queryOne(QueryFilter filter);

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
	public PaginationSupport query(QueryFilter filter, int pageNum, int pageSize);

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
	public PaginationSupport query(String queryName, String countName, QueryFilter filter, int pageNum, int pageSize);

	/**
	 * Gets the.
	 *
	 * @param id
	 *         the id
	 * @return the item result support< t>
	 */
	public ItemResultSupport<T> get(String id);

	/**
	 * Gets the data source.
	 *
	 * @param request
	 *         the request
	 * @return the data source
	 */
	public DataSourceResponse getDataSource(DataSourceRequest request);

	/**
	 * 查询通用方法无分页
	 * 
	 * @param queryName
	 * @param filter
	 * @return
	 */
	public PaginationSupport query(String queryName, QueryFilter filter);

	/**
	 * 根据主键批量更新某个字段.
	 * 
	 * @param ids
	 * @param field
	 * @param value
	 */
	public void updateByIds(String[] ids, String field, String value);

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