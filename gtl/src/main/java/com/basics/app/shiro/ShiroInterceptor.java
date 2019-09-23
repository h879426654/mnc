package com.basics.app.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.basics.app.support.AppSystemAppTypeEnum;
import com.basics.app.support.AppSystemEnum;
import com.basics.app.support.AppSystemTypeEnum;
import com.basics.support.CommonSupport;
import com.basics.support.FuncSupport;
import com.basics.support.LogUtils;
import com.basics.support.ProfileUtils;
import com.basics.support.QueryFilterBuilder;

/**
 * 支持在控制器类上增加注解来控制权限.
 * 
 * @author yuwenfeng
 *
 */
@Component
public class ShiroInterceptor implements HandlerInterceptor {

 @Autowired
 protected ShiroSupport shiroSupport;

 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
  throws Exception {
  if (ProfileUtils.isDev()) {
   request.setAttribute("_tm", System.currentTimeMillis());
  } else {
   request.setAttribute("_tm", "");
  }

 }

 public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object args, Exception e) throws Exception {

 }

 public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object args) throws Exception {
  if (args instanceof HandlerMethod) {
   HandlerMethod method = (HandlerMethod) args;
   Class<? extends Object> clasz = method.getBeanType();
   if (clasz.isAnnotationPresent(RequiresPermissions.class)) {
    RequiresPermissions requiresPermissions = clasz.getAnnotation(RequiresPermissions.class);
    ShiroUtils.assertAuthorized(requiresPermissions);
   }
   if (clasz.isAnnotationPresent(RequiresRoles.class)) {
    RequiresRoles requiresRoles = clasz.getAnnotation(RequiresRoles.class);
    ShiroUtils.assertAuthorized(requiresRoles);
   }
   addBackendMenus(req);
  }
  return true;
 }

 public void addBackendMenus(final HttpServletRequest req) {
  try {
   // requestURI:/backend/video/videoRole/index.do
   String requestURI = req.getRequestURI();
   String url = StringUtils.substringAfterLast(requestURI, "/backend/");
   if (StringUtils.isBlank(url)) {
    return;
   }
   UserRolePermissionSupport urpSupport = shiroSupport.getUserRolePermissionSupport();
   // 增加系统
   UserSupport userSupport = AppUserUtils.getCurrentUser(UserSupport.class);
   List<PermissionSupport> systems = this.filterBackendWeb(urpSupport.getUserSystems(userSupport.getId()));
   req.setAttribute("_systems", systems);
   // 当前菜单
   PermissionSupport urlMenu = null;
   List<PermissionSupport> urlMenus = urpSupport.getPermissions(new QueryFilterBuilder().put("url", url).limit(1).build());
   if (urlMenus.size() > 0) {
    urlMenu = urlMenus.get(0);
   }

   for (PermissionSupport system : systems) {

    this.loadChildren(urpSupport, userSupport, system, urlMenu, new FuncSupport.Accept<PermissionSupport>() {

     public boolean accept(PermissionSupport menu) {
      if (menu.isActive()) {
       String comment = menu.getComment();
       if (StringUtils.isBlank(comment)) {
        comment = menu.getName();
       }
       req.setAttribute("_urlMenuComment", comment);
      }
      return false;
     }

    });

   }
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
  }
 }

 public void loadChildren(UserRolePermissionSupport urpSupport, UserSupport userSupport, PermissionSupport node, PermissionSupport urlMenu,
  FuncSupport.Accept<PermissionSupport> accept) {
  boolean hasActiveMenu = (urlMenu != null);
  boolean loadSystems = false;
  // systems
  if (loadSystems) {
   List<PermissionSupport> systems = urpSupport.getUserPermissions(userSupport.getId(), node.getId(), PermissionSupport.TYPE_SYSTEM);
   node.setSystems(systems);
   for (PermissionSupport system : systems) {
    loadChildren(urpSupport, userSupport, system, urlMenu, accept);
    if (hasActiveMenu) {
     if (CommonSupport.equals(urlMenu.getParentId(), system.getId())) {
      system.setActive(true);
     }
    }
   }
  }
  // dirs
  List<PermissionSupport> dirs = urpSupport.getUserPermissions(userSupport.getId(), node.getId(), PermissionSupport.TYPE_DIR);
  node.setDirs(dirs);
  for (PermissionSupport dir : dirs) {
   loadChildren(urpSupport, userSupport, dir, urlMenu, accept);
   if (hasActiveMenu) {
    if (CommonSupport.equals(urlMenu.getParentId(), dir.getId())) {
     dir.setActive(true);
    }
   }
  }
  // menus
  List<PermissionSupport> menus = urpSupport.getUserPermissions(userSupport.getId(), node.getId(), PermissionSupport.TYPE_MENU);
  node.setMenus(menus);
  for (PermissionSupport menu : menus) {
   if (hasActiveMenu) {
    if (CommonSupport.equals(urlMenu.getId(), menu.getId())) {
     menu.setActive(true);
     if (accept != null) {
      accept.accept(menu);
     }
    }
   }
  }
 }

 public List<PermissionSupport> filterBackendWeb(List<PermissionSupport> systems) {
  List<PermissionSupport> items = new ArrayList<PermissionSupport>();
  for (PermissionSupport system : systems) {
   AppSystemEnum appSystem = AppSystemEnum.from(system.getCode());
   if (appSystem != null && appSystem.getType().equals(AppSystemTypeEnum.BACKEND)
    && appSystem.getAppType().equals(AppSystemAppTypeEnum.WEB)) {
    items.add(system);
   }
  }
  return items;
 }
}
