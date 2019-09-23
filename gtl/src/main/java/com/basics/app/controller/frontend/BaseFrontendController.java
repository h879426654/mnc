package com.basics.app.controller.frontend;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BaseFrontendController implements ApplicationContextAware {

 public String getBaseViewDirName() {
        return "com.basics.app.frontend";
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
  sb.append(name);
  return sb.toString();
 }
}
