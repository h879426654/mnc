package com.basics.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * 用户上下文.
 * <p>
 * 通过Application.getInstance().getContainer()实例化用户上下文.
 * </p>
 * <p>
 * 从UserContext.properties 读取locationContainer配置,实例化用户上下文
 * </p>
 * 
 * @author yuwenfeng
 * 
 */
public class UserContext {

 /** The instance. */
 protected static UserContext INSTANCE = null;

 /**
  * 1.通过Application.getInstance().getContainer()实例化用户上下文.
  * 2.从UserContext.properties 读取contextConfigLocation配置,实例化用户上下文.
  * 
  * @return single instance of Context
  */
 public static UserContext getInstance() {
  if (INSTANCE == null) {
   if (Application.getInstance().getContainer() != null) {
    LogUtils.performance.info("通过Application.getInstance().getContainer()初始化UserContext");
    INSTANCE = new UserContext(Application.getInstance().getContainer());
    INSTANCE.setProps(loadProps(UserContext.class));
   }
  }
  if (INSTANCE == null) {
   INSTANCE = getInstance(UserContext.class);
  }
  return INSTANCE;
 }

 /**
  * 通过<指定类名>.properties 读取contextConfigLocation配置,创建上下文.
  * 
  * @param resourceClass
  *         the resource class
  * @return single instance of UserContext
  */
 public static UserContext getInstance(Class<?> resourceClass) {
  return getInstance(loadProps(resourceClass));
 }

 /**
  * 读取contextConfigLocation配置,创建上下文.
  * 
  * @param props
  *         the props
  * @return single instance of UserContext
  */
 public static UserContext getInstance(Properties props) {
  String locationContainer = props.getProperty("locationContainer", "");
  LogUtils.performance.info("get instance from {}", props);
  if (StringUtils.isBlank(locationContainer)) {
   throw new RuntimeException("locationContainer not found.");
  }
  Container container = null;
  Object locationContainerInstance;
  try {
   locationContainerInstance = Class.forName(locationContainer).newInstance();
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   throw new RuntimeException(e);
  }
  if (locationContainerInstance instanceof Container) {
   container = (Container) locationContainerInstance;
   container.init(props);
   Application.getInstance().setContainer(container);
  } else {
   throw new RuntimeException(locationContainer + " not instanceof Container");
  }
  UserContext userContextInstance = null;
  // 根据属性文件指定的类实例化上下文实例.
  String userContext = props.getProperty("userContext", "");
  if (StringUtils.isNotBlank(userContext)) {
   try {
    userContextInstance = (UserContext) Class.forName(userContext).newInstance();
   } catch (Throwable e) {
    LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   }
  }
  // 默认实例.
  if (userContextInstance == null) {
   userContextInstance = new UserContext(container);
  } else {
   userContextInstance.setContainer(container);
  }
  userContextInstance.setProps(props);
  return userContextInstance;
 }

 /**
  * Load props.
  * 
  * @param clasz
  *         the clasz
  * @return the properties
  */
 public static Properties loadProps(Class<?> clasz, String name) {
  Properties props = new java.util.Properties();
  String fileName = StringUtils.isBlank(name) ? clasz.getSimpleName() + ".properties" : name + ".properties";
  InputStream is = null;
  try {
   LogUtils.performance.info("load properties for {}  from file {}", clasz.getName(), fileName);
   is = clasz.getResourceAsStream(fileName);
   props.load(is);
   props.setProperty("userContext", props.getProperty("userContext", clasz.getName()));
  } catch (Throwable e) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
  } finally {
   IOUtils.closeQuietly(is);
  }
  return props;
 }

 public static Properties loadProps(Class<?> clasz) {
  return loadProps(clasz, "");
 }

 public static Properties loadProps(File file) {
  Properties props = new Properties();
  if (file == null) {
   LogUtils.performance.error("loadProps from null file.");
   return props;
  }
  if (file.isDirectory()) {
   return loadProps(new File(file, ".properties"));
  }
  if (!file.exists()) {
   LogUtils.performance.error("loadProps from file:{} not existed.", file);
   return props;
  }
  InputStream fis = null;
  try {
   fis = new FileInputStream(file);
   props.load(fis);
  } catch (Throwable e) {
   LogUtils.performance.error("loadProps from file {} exception {}", file, e);
  } finally {
   IOUtils.closeQuietly(fis);
  }
  return props;
 }

 /**
  * Sets the instance.
  * 
  * @param context
  *         the new instance
  */
 public static void setInstance(UserContext context) {
  INSTANCE = context;
 }

 public UserContext() {

 }

 public UserSession createSession() {
  return createSession("");
 }

 public Object findUserByUsername(String username) {
  LogUtils.performance.error("findUserByUsername not implement");
  return null;
 }

 public UserSession createSession(String username) {
  UserSession session = new UserSession();
  session.setContext(this);
  if (StringUtils.isNotBlank(username)) {
   session.setUser(findUserByUsername(username));
  }
  return session;
 }

 /**
  * Sets the instance.
  * 
  * @param container
  *         the new instance
  */
 public static void setInstance(Container container) {
  INSTANCE = new UserContext(container);
 }

 /** The container. */
 private Container container;

 /**
  * Gets the container.
  * 
  * @return the container
  */
 public Container getContainer() {
  return container;
 }

 /**
  * Sets the container.
  * 
  * @param container
  *         the new container
  */
 public void setContainer(Container container) {
  this.container = container;

 }

 /**
  * Instantiates a new context.
  * 
  * @param container
  *         the container
  */
 public UserContext(Container container) {
  super();
  this.setContainer(container);
  if (Application.getInstance().getContainer() == null) {
   Application.getInstance().setContainer(container);
  }
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

 /**
  * Gets the service.
  * 
  * @param <T>
  *         the generic type
  * @param key
  *         the key
  * @return the service
  */
 @SuppressWarnings("unchecked")
 public <T> T getService(Class<T> key) {
  return (T) getBean(key);
 }

 public <T> List<T> getServices(Class<T> key) {
  List<T> beans = new ArrayList<T>();
  beans.addAll(this.getContainer().getComponents(key).values());
  return beans;
 }

 Properties props = new Properties();

 public Properties getProps() {
  return props;
 }

 public void setProps(Properties props) {
  this.props = props;
 }

}
