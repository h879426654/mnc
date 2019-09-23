package com.basics.support;

import java.util.ArrayList;
import java.util.List;

public class Application {
 private Container container;
 private static final Application instance = new Application();

 public static Application getInstance() {
  return instance;
 }

 public Container getContainer() {
  return this.container;
 }

 public void setContainer(Container container) {
  this.container = container;
 }

 /**
  * Gets the bean.
  * 
  * @param key
  *         the key
  * @return the bean
  */
 public Object getBean(Object key) {
  return getContainer().getComponent(key);
 }

 @SuppressWarnings("unchecked")
 public <T> T getService(Class<T> key) {
  return (T) getBean(key);
 }

 public <T> List<T> getServices(Class<T> key) {
  List<T> beans = new ArrayList<T>();
  beans.addAll(this.getContainer().getComponents(key).values());
  return beans;
 }

 /**
  * Checks if is container available.
  *
  * @return true, if checks if is container available
  */
 public static boolean isContainerAvailable() {
  return Application.getInstance().getContainer() != null;
 }
}