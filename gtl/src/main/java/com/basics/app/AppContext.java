package com.basics.app;

import org.apache.commons.lang.StringUtils;

import com.basics.support.UserContext;

public class AppContext extends UserContext {

 /** The instance. */
 protected static UserContext INSTANCE = null;

 /**
  * 1.通过Application.getInstance().getContainer()实例化用户上下文.
  * 2.从UserContext.properties 读取contextConfigLocation配置,实例化用户上下文.
  * 
  * @return single instance of Context
  */
 public static synchronized UserContext getInstance() {
  if (AppContext.INSTANCE == null) {
   AppContext.INSTANCE = UserContext.getInstance(AppContext.class);
  }
  return AppContext.INSTANCE;
 }

 @Override
 public Object findUserByUsername(String username) {
  if (StringUtils.isBlank(username)) {
   return null;
  }
  return null;
 }

}
