package com.basics.support;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basics.support.dlshouwen.Pager;
import com.basics.support.dlshouwen.PagerSupport;

import jodd.bean.BeanUtil;

public class GenericModelSupportAction<T extends ModelSupport> implements InitializingBean, ApplicationContextAware {

 /** The service. */
 protected GenericService<T> service;

 /** 实体类类型(由构造方法自动赋值). */
 protected Class<?> entityClass;

 public GenericModelSupportAction() {
  entityClass = GenericUtils.getSuperClassGenericType(super.getClass());
 }

 /**
  * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
  */
 @InitBinder
 protected void initBinder(WebDataBinder binder) {
  // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
  /*
   * binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
   * 
   * public void setAsText(String text) { setValue(text == null ? null :
   * StringEscapeUtils.escapeHtml(text.trim())); }
   * 
   * public String getAsText() { Object value = getValue(); return value != null
   * ? value.toString() : ""; } });
   */
  // Date 类型转换
  binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

   @Override
   public void setAsText(String text) {
    setValue(DateUtils.parseDate(text));
   }
  });
 }

 @SuppressWarnings("unchecked")
 public void afterPropertiesSet() throws Exception {
  String mybatisService = String.format("%sMybatisService", StringUtils.uncapitalize(this.entityClass.getSimpleName()));
  this.service = (GenericService<T>) this.context.getBean(mybatisService);
 }

 protected ApplicationContext context;

 public void setApplicationContext(ApplicationContext conext) throws BeansException {
  this.context = conext;
 }

 /**
  * 根据实体类名、基础目录、方法名称生成对应的视图名.
  * 
  * @param name
  *         方法名.
  * @return
  */
 public String getView(String name) {
  StringBuilder sb = new StringBuilder();
  String baseViewDirName = this.getBaseViewDirName();
  sb.append(baseViewDirName);
  if (StringUtils.isNotBlank(baseViewDirName) && !StringUtils.endsWith(baseViewDirName, "/")) {
   sb.append("/");
  }
  sb.append(getBaseViewFileName());
  sb.append(this.getMethodSeperator());
  sb.append(name);
  return sb.toString();
 }

 public String getBaseViewFileName() {
  return entityClass.getSimpleName();
 }

 public String getMethodSeperator() {
  return "_";
 }

 /**
  * 返回视图保存的目录名称.
  * 
  * @return
  */
 public String getBaseViewDirName() {
  return "";
 }

 @RequestMapping(value = "/index")
 public String index() {
  return getView("index");
 }

 @RequestMapping(value = "/indexTreeGrid")
 public String indexTreeGrid() {
  return getView("indexTreeGrid");
 }

 @RequestMapping(value = "/debug")
 public String debug() {
  return getView("debug");
 }

 /**
  * 直接跳转到视图
  * 
  * @param viewNameSuffix
  *         视图名称后缀 ModelName_{viewNameSuffix}.jsp
  * @return
  */
 @RequestMapping(value = "/view/{viewNameSuffix}")
 public String showView(@PathVariable(value = "viewNameSuffix") String viewNameSuffix) {
  return getView(viewNameSuffix);
 }
 /**
  * 留给子类扩展.在查询之前实现一些业务逻辑.
  * 
  * @param result
  * @param entity
  * @param request
  */
 public void beforeList(T entity, HttpServletRequest request) {
 }

 @RequestMapping(value = "list")
 public void list(T entity, HttpServletRequest request, HttpServletResponse response) {
  this.list(entity, "query", "count", request, response);
 }

 /**
  * 留给子类扩展.在查询之前实现一些业务逻辑.
  * 
  * @param result
  * @param entity
  * @param request
  */
 public void beforeReport(T entity, HttpServletRequest request) {
 }

 @RequestMapping(value = "report")
 public void report(T entity, HttpServletRequest request, HttpServletResponse response) {
  this.list(entity, "queryReport", "countReport", request, response);
 }

 public void list(T entity, String queryName, String countName, HttpServletRequest request, HttpServletResponse response) {
  ListResultSupport result = new ListResultSupport();
  try {
   this.beforeList(entity, request);
   int pageNo = entity.getPageNum();
   int pageSize = entity.getPageSize();
   @SuppressWarnings("unchecked")
   Map<String, Object> params = BeanMap.create(entity);
   LogUtils.performance.info("params:{}", params);
   PaginationSupport page = this.service.query(queryName, countName,
    new QueryFilterBuilder().params(params).orderBy(entity.getOrderBy()).build(), pageNo, pageSize);
   result.setRows(page.getItems());
   result.setTotal((int) page.getTotalCount());
   result.onSuccess("操作成功");
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 /**
  * 留给子类扩展。在保存之前实现一些业务逻辑.
  * 
  * @param result
  * @param entity
  * @param request
  */
 public void beforeSave(FormResultSupport result, T entity, HttpServletRequest request) {
  // TODO:表单验证.
  // TODO:业务验证
 }

 public void onSave(T entity) {
  service.save(entity);
 }

 @RequestMapping(value = "/save")
 public void save(T entity, HttpServletRequest request, HttpServletResponse response) {
  FormResultSupport result = new FormResultSupport();
  try {
   this.beforeSave(result, entity, request);
   if (!result.getErrorMessages().isEmpty()) {
    result.onException("操作失败");
   }
   this.onSave(entity);
   result.onSuccess("操作成功");
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 /**
  * 留给子类扩展 .在返回数据之前实现一些业务逻辑.
  * 
  * @param entity
  */
 public void didGet(ItemResultSupport<T> entity, HttpServletRequest request, HttpServletResponse response) {

 }

 @RequestMapping(value = "/get")
 public void get(String id, HttpServletRequest request, HttpServletResponse response) {
  ItemResultSupport<T> result = new ItemResultSupport<T>();
  try {
   result = service.get(id);
   this.didGet(result, request, response);
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 @RequestMapping(value = "/del")
 public void del(String id, HttpServletRequest request, HttpServletResponse response) {
  ResultSupport result = new ResultSupport();
  try {
   String[] array = StringUtils.split(id, ",");
   if (array == null || array.length == 0) {
    result.onException("请指定删除的记录");
   } else {
    for (String item : array) {
     service.deleteByPK(item);
    }
    result.onSuccess("删除成功");
   }
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   result.onException("删除失败");
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 public String getUiIdField() {
  return "id";
 }

 public String getUiTextField() {
  return "text";
 }

 public String getUiParentIdField() {
  return "parentId";
 }

 public String getUiAttributesField() {
  return "";
 }

 public void renderTree(String parentId, QueryFilterBuilder filterBuilder, HttpServletRequest request, HttpServletResponse response) {
  JSONArray tree = new JSONArray();
  if (StringUtils.isBlank(parentId)) {
   parentId = "0";
   JSONObject node = new JSONObject();
   node.put("id", "0");
   node.put("text", "无上级");
   node.put("iconCls", "icon-ok");
   tree.add(node);
  }
  String[] attributesField = StringUtils.split(this.getUiAttributesField(), ",");
  List<T> list = service.query(filterBuilder.put(this.getUiParentIdField(), parentId).build());
  for (T item : list) {
   JSONObject node = new JSONObject();
   node.put("id", BeanUtil.getPropertySilently(item, this.getUiIdField()));
   node.put("text", BeanUtil.getPropertySilently(item, this.getUiTextField()));
   node.put("iconCls", getIconCls(item));
   if (attributesField != null && attributesField.length > 0) {
    JSONObject attributesNode = new JSONObject();
    for (String attributeField : attributesField) {
     attributesNode.put(attributeField, BeanUtil.getPropertySilently(item, attributeField));
    }
    node.put("attributes", attributesNode);
   }
   if (StringUtils.equals(parentId, "0"))
    node.put("state", "closed");
   tree.add(node);
  }
  ServletUtils.renderJsonAsText(response, tree);
 }

 @RequestMapping(value = "/tree")
 public void tree(@RequestParam(value = "id", required = false) String parentId, HttpServletRequest request, HttpServletResponse response) {
  this.renderTree(parentId, new QueryFilterBuilder(), request, response);
 }

 /**
  * 允许子类自定义图标.
  * 
  * @param item
  * @return
  */
 public String getIconCls(T item) {
  return "icon-folder";
 }

 public void renderTreeselect(String parentId, QueryFilterBuilder filterBuilder, HttpServletRequest request, HttpServletResponse response) {
  JSONArray tree = new JSONArray();
  if (StringUtils.isBlank(parentId)) {
   parentId = "0";
  }
  String[] attributesField = StringUtils.split(this.getUiAttributesField(), ",");
  List<T> list = service.query(filterBuilder.put(this.getUiParentIdField(), parentId).build());
  for (T item : list) {
   JSONObject node = new JSONObject();
   node.put("id", BeanUtil.getPropertySilently(item, this.getUiIdField()));
   node.put("text", BeanUtil.getPropertySilently(item, this.getUiTextField()));
   node.put("iconCls", this.getIconCls(item));
   if (attributesField != null && attributesField.length > 0) {
    JSONObject attributesNode = new JSONObject();
    for (String attributeField : attributesField) {
     attributesNode.put(attributeField, BeanUtil.getPropertySilently(item, attributeField));
    }
    node.put("attributes", attributesNode);
   }
   if (StringUtils.equals(parentId, "0"))
    node.put("state", "closed");
   tree.add(node);
  }
  ServletUtils.renderJsonAsText(response, tree);
 }

 /**
  * 选择树(不显示无上级)
  * 
  * @param parentId
  * @param request
  * @param response
  */
 @RequestMapping(value = "/treeselect")
 public void treeselect(@RequestParam(value = "id", required = false) String parentId, HttpServletRequest request,
  HttpServletResponse response) {
  this.renderTreeselect(parentId, new QueryFilterBuilder(), request, response);
 }
/*
下拉列表
 */
 @RequestMapping(value = "/combobox")
 public void combobox(@RequestParam(value = "type", required = false) String type, HttpServletRequest request,
  HttpServletResponse response) {
  JSONArray combobox = new JSONArray();
  if (StringUtils.equalsIgnoreCase("query", type)) {
   JSONObject node = new JSONObject();
   node.put("id", "");
   node.put("text", "全部");
   combobox.add(node);
  }
  String[] attributesField = StringUtils.split(this.getUiAttributesField(), ",");
  List<T> list = service.query(new QueryFilterBuilder().build());
  for (T item : list) {
   JSONObject node = new JSONObject();
   node.put("id", BeanUtil.getPropertySilently(item, this.getUiIdField()));
   node.put("text", BeanUtil.getPropertySilently(item, this.getUiTextField()));
   if (attributesField != null && attributesField.length > 0) {
    JSONObject attributesNode = new JSONObject();
    for (String attributeField : attributesField) {
     attributesNode.put(attributeField, BeanUtil.getPropertySilently(item, attributeField));
    }
    node.put("attributes", attributesNode);
   }
   combobox.add(node);
  }
  ServletUtils.renderJsonAsText(response, combobox);
 }

 public void willRead(DataSourceRequest request) {
  LogUtils.performance.debug("willRead:{}", FastjsonUtils.string(request));
 }

 @RequestMapping(value = "read", method = RequestMethod.POST)
 public @ResponseBody DataSourceResponse read(@RequestBody DataSourceRequest request) {
  this.willRead(request);
  return this.service.getDataSource(request);
 }

 @RequestMapping(value = "update", method = RequestMethod.POST)
 public @ResponseBody T update(@RequestBody T model) {
  this.service.save(model);
  return model;
 }

 @RequestMapping(value = "create", method = RequestMethod.POST)
 public @ResponseBody T create(@RequestBody T model) {
  this.service.save(model);
  return model;
 }

 @RequestMapping(value = "destroy", method = RequestMethod.POST)
 public @ResponseBody T destroy(@RequestBody T model) {
  this.service.delete(model);
  return model;
 }

 /**
  * 返回查询结果之前调用.
  *
  */
 public void willQuery(QueryFilterBuilder builder) {

 }

 /**
  * 默认swgrid网格适配.
  * 
  * @param gridPager
  * @param request
  * @param response
  */
 @RequestMapping(value = "swgrid", method = RequestMethod.POST)
 public void swgrid(String gridPager, HttpServletRequest request, HttpServletResponse response) {
  QueryFilterBuilder builder = new QueryFilterBuilder();
  this.willQuery(builder);
  this.swgrid(gridPager, builder, this.service, request, response);
 }

 /**
  * 自定义swgrid网格适配.
  * 
  * @param gridPager
  * @param queryName
  *         查询方法.
  * @param countName
  *         统计数量方法.
  * @param request
  * @param response
  */
 public void swgrid(String gridPager, String queryName, String countName, HttpServletRequest request, HttpServletResponse response) {
  QueryFilterBuilder builder = new QueryFilterBuilder();
  this.willQuery(builder);
  this.swgrid(gridPager, builder, this.service, queryName, countName, request, response);
 }

 /**
  * 默认网格适配.
  * 
  * @param gridPager
  * @param builder
  *         查询条件构造器
  * @param service
  *         服务实现
  * @param request
  * @param response
  */
 public void swgrid(String gridPager, QueryFilterBuilder builder, GenericService<?> service, HttpServletRequest request,
  HttpServletResponse response) {
  this.swgrid(gridPager, builder, service, "query", "count", request, response);
 }

 /**
  * 自定义swgrid网格适配.
  * 
  * @param gridPager
  * @param builder
  *         查询条件构造器
  * @param service
  *         服务实现
  * @param queryName
  *         服务查询的方法
  * @param countName
  *         服务统计数量的方法.
  * @param request
  * @param response
  */
 public void swgrid(String gridPager, QueryFilterBuilder builder, GenericService<?> service, String queryName, String countName,
  HttpServletRequest request, HttpServletResponse response) {
  Pager pager = new Pager();
  try {
   Map<String, Object> overrideParams = new HashMap<String, Object>();
   overrideParams.putAll(builder.build().getParams());
   pager = FastjsonUtils.object(gridPager, Pager.class);
   int pageNum = pager.getNowPage();
   int pageSize = pager.getPageSize();
   builder.putAll(pager.getFastQueryParameters());
   builder.putAll(pager.getParameters());
   builder.putAll(overrideParams);
   PaginationSupport ps = service.query(queryName, countName, builder.build(), pageNum, pageSize);
   pager.setRecordCount(ps.getTotalCount());
   pager.setPageCount(ps.getPageCount());
   pager.setNowPage(ps.getCurrentPageNo());
   pager.setStartRecord(ps.getStartOfPage());
   pager.setExhibitDatas(ps.getItems());
   pager.setIsSuccess(true);
  } catch (Throwable e) {
   LogUtils.performance.error("swgrid:gridPager:{} exception:{}", gridPager, ExceptionUtils.getFullStackTrace(e));
   pager.setIsSuccess(false);
  }
  ServletUtils.renderJsonAsText(response, pager);
 }

 /**
  * 自定义swgrid网格适配.
  * 
  * @param gridPager
  * @param builder
  * @param pagerSupport
  * @param request
  * @param response
  */
 public void swgrid(String gridPager, QueryFilterBuilder builder, PagerSupport pagerSupport, HttpServletRequest request,
  HttpServletResponse response) {
  Pager pager = new Pager();
  try {
   Map<String, Object> overrideParams = new HashMap<String, Object>();
   overrideParams.putAll(builder.build().getParams());
   pager = FastjsonUtils.object(gridPager, Pager.class);
   int pageNum = pager.getNowPage();
   int pageSize = pager.getPageSize();
   builder.putAll(pager.getFastQueryParameters());
   builder.putAll(pager.getParameters());
   builder.putAll(overrideParams);
   PaginationSupport ps = pagerSupport.query(builder.build(), pageNum, pageSize);
   pager.setRecordCount(ps.getTotalCount());
   pager.setPageCount(ps.getPageCount());
   pager.setNowPage(ps.getCurrentPageNo());
   pager.setStartRecord(ps.getStartOfPage());
   pager.setExhibitDatas(ps.getItems());
   pager.setIsSuccess(true);
  } catch (Throwable e) {
   LogUtils.performance.error("swgrid:gridPager:{} exception:{}", gridPager, ExceptionUtils.getFullStackTrace(e));
   pager.setIsSuccess(false);
  }
  ServletUtils.renderJsonAsText(response, pager);
 }

 /**
  * 禁用/启用.
  * 
  * @param ids
  * @param state
  * @param request
  * @param response
  */
 @RequestMapping(value = "state")
 public void state(String ids, String state, HttpServletRequest request, HttpServletResponse response) {
  ResultSupport result = new ResultSupport();
  try {
   String[] pks = CommonSupport.split(ids, ",");
   this.service.updateByIds(pks, "state", state);
   result.onSuccess("操作成功");
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }
}
