package com.basics.app.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class AppUserUtils {

 @SuppressWarnings("unchecked")
 public static <T> T getCurrentUser(Class<T> user) {
  try {
   @SuppressWarnings("rawtypes")
   List principals = SecurityUtils.getSubject().getPrincipals().asList();
   for (Object principal : principals) {
    if (user.isInstance(principal)) {
     return (T) principal;
    }
   }
  } catch (Throwable e) {
  }
  return null;
 }

 public static UserSupport getCurrentUserSupport() {
  return getCurrentUser(UserSupport.class);
 }

 /**
  * 获取当前用户session
  * 
  * @return session
  */
 public static Session getSession() {
  Session session = SecurityUtils.getSubject().getSession();
  return session;
 }

 /**
  * 获取当前用户httpsession
  * 
  * @return httpsession
  */
 public static Session getHttpSession() {
  Session session = SecurityUtils.getSubject().getSession();
  return session;
 }

 /**
  * 
  * @return
  */
 public static boolean isCurrentUserAuthenticated() {
  Subject currentUser = SecurityUtils.getSubject();
  return currentUser.isAuthenticated();
 }
}
