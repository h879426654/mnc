package com.basics.app.controller.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.support.Application;
import com.basics.support.GenericMybatisDao;
import com.basics.support.GenericRunnable;
import com.basics.support.GenericService;
import com.basics.support.ListResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ResultSupport;
import com.basics.support.ServletUtils;
import com.basics.support.mybatis.CacheAdapter;
import com.basics.support.mybatis.CacheUsed;

@Controller
@RequestMapping("/console")
public class ConsoleFrontendController extends BaseFrontendController {

 @Named("app.executor")
 Executor executor;

 @RequestMapping(value = "cacheList")
 public void cacheList(HttpServletRequest request, HttpServletResponse response) {
  ListResultSupport result = new ListResultSupport();
  try {
   List<String> cacheNames = new ArrayList<String>(CacheUsed.getCaches().keySet());
   result.setRows(cacheNames);
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 @RequestMapping(value = "cacheClear")
 public void cacheClear(String cacheName, HttpServletRequest request, HttpServletResponse response) {
  ListResultSupport result = new ListResultSupport();
  List<String> cacheNames = new ArrayList<String>();
  try {
   if (StringUtils.isBlank(cacheName)) {
    cacheNames = new ArrayList<String>(CacheUsed.getCaches().keySet());
   } else {
    if (!CacheUsed.getCaches().containsKey(cacheName)) {
     throw new RuntimeException("cacheName:" + cacheName + " 不存在.");
    }
    cacheNames.add(cacheName);
   }
   for (String name : cacheNames) {
    CacheUsed.clearCache(name);
   }
   result.setRows(cacheNames);
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 @RequestMapping(value = "cacheClearAll")
 public void cacheClearAll(HttpServletRequest request, HttpServletResponse response) {
  ListResultSupport result = new ListResultSupport();
  try {
   List<String> cacheNames = new ArrayList<String>(CacheUsed.getCaches().keySet());
   CacheUsed.clearAllCache();
   result.setRows(cacheNames);
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 @RequestMapping(value = "cacheBurn")
 public void cacheBurn(HttpServletRequest request, HttpServletResponse response) {
  ResultSupport result = new ResultSupport();
  try {
   executor.execute(new GenericRunnable<ConsoleFrontendController>(this) {

    @Override
    public void run(ConsoleFrontendController owner) {
     owner.burn();
    }
   });
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 public void burn() {
  Map<String, CacheAdapter> caches = CacheUsed.getCaches();
  List<String> cacheNames = new ArrayList<String>(caches.keySet());
  LogUtils.performance.info("cacheNames:{}", cacheNames.size());
  for (String cacheName : cacheNames) {
   this.burn(cacheName);
  }
 }

 public void burn(String cacheName) {
  if (!Application.isContainerAvailable()) {
   return;
  }
  try {
   String entityClass = StringUtils.substringAfterLast(cacheName, ".");
   String serviceName = String.format("%sMybatisService", StringUtils.uncapitalize(entityClass));
   String daoName = String.format("%sMybatisDao", StringUtils.uncapitalize(entityClass));
   GenericService service = (GenericService) Application.getInstance().getBean(serviceName);
   GenericMybatisDao dao = (GenericMybatisDao) Application.getInstance().getBean(daoName);
   int pageSize = 200;
   PaginationSupport ps = service.query(new QueryFilter(), 1, 200);
   for (int pageIndex = 1; pageIndex <= ps.getPageCount(); pageIndex++) {
    PaginationSupport thatPage = service.query(new QueryFilter(), pageIndex, pageSize);
    LogUtils.performance.info("burn:cacheName:{} pageIndex:{} pageSize:{}", cacheName, pageIndex, pageSize);
    // LogUtils.performance.info("burn:cacheName:{} json:{}", cacheName,
    // FastjsonUtils.string(ps));
    for (Object item : thatPage.getItems()) {
     Map<String, Object> params = dao.getPrimaryKey(item);
     dao.queryOne(new QueryFilterBuilder().params(params).build());
     LogUtils.performance.info("burn:cacheName:{} queryOne:{}", cacheName, params);
    }
   }
  } catch (Throwable e) {
   LogUtils.performance.error("burn:cacheName:{} error:{}", cacheName, e);
  }
 }
}
