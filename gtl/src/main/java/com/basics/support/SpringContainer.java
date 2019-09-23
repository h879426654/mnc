package com.basics.support;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@SuppressWarnings("unchecked")
public class SpringContainer implements Container {
 private ApplicationContext applicationContext;

 @SuppressWarnings("rawtypes")
 public Object getComponent(Object key) {
  check(key);

  if ((key instanceof Class)) {
   Map<String, Object> beans = this.applicationContext.getBeansOfType((Class) key);
   if (beans == null) {
    throw new ComponentNotFoundException(
     "The container is unable to resolve single instance of " + ((Class) key).getName() + ", none instances found");
   }
   if ((beans.size() == 0) || (beans.size() > 1)) {
    throw new ComponentNotFoundException("The container is unable to resolve single instance of " + ((Class) key).getName()
     + ", number of instances found was: " + beans.size());
   }
   key = beans.keySet().iterator().next();
  }
  return this.applicationContext.getBean(key.toString());
 }

 public <T> Map<String, T> getComponents(Class<T> key) {
  check(key);
  Map<String, T> beans = this.applicationContext.getBeansOfType(key);
  return beans;
 }

 private void check(Object key) {
  if (this.applicationContext == null) {
   throw new IllegalStateException("Spring Application context has not been set");
  }
  if (key == null)
   throw new ComponentNotFoundException("The component key can not be null");
 }

 public void reload() {
  AbstractApplicationContext aac = (AbstractApplicationContext) this.applicationContext;
  aac.close();
  aac.refresh();
 }

 public void init(Object obj) {
  if (obj == null) {
   throw new RuntimeException("initialize parameter object cann't is null");
  }
  if ((obj instanceof ApplicationContext)) {
   this.applicationContext = ((ApplicationContext) obj);
   return;
  }
  if ((obj instanceof Properties)) {
   Properties props = (Properties) obj;
   String userContext = props.getProperty("userContext", "");
   String contextConfigLocation = props.getProperty("contextConfigLocation", "");
   String[] contexts = StringUtils.split(contextConfigLocation, ",");
   GenericXmlApplicationContext xmlContext = new GenericXmlApplicationContext();
   String defaultProfiles = props.getProperty("defaultProfiles", "");
   if (StringUtils.isNotBlank(defaultProfiles)) {
    LogUtils.performance.info("{} defaultProfiles {}", userContext, defaultProfiles);
    xmlContext.getEnvironment().setDefaultProfiles(StringUtils.split(defaultProfiles));
   }
   // 通过配置ProfileUtilsServletContextListener,读取了web.xml配置，优选级更高.
   if (ProfileUtils.getActiveProfiles() != null) {
    LogUtils.performance.info("{} activeProfiles {}", userContext, ProfileUtils.getActiveProfiles());
    xmlContext.getEnvironment().setActiveProfiles(ProfileUtils.getActiveProfiles());
   }
   if (ProfileUtils.getDefaultProfiles() != null) {
    LogUtils.performance.info("{} defaultProfiles {}", userContext, ProfileUtils.getDefaultProfiles());
    xmlContext.getEnvironment().setDefaultProfiles(ProfileUtils.getDefaultProfiles());
   }
   xmlContext.load(contexts);
   xmlContext.refresh();
   this.applicationContext = xmlContext;
   return;
  }
  throw new RuntimeException("initialize parameter object must's instance of ApplicationContext or Properties");
 }

 public void destroy() {
  ((AbstractApplicationContext) this.applicationContext).close();
 }
}