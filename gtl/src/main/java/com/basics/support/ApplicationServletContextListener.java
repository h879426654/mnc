package com.basics.support;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationServletContextListener implements ServletContextListener {

 public void contextDestroyed(ServletContextEvent event) {
  Application.getInstance().getContainer().destroy();
 }

 public void contextInitialized(ServletContextEvent event) {
  WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
  String[] activeProfiles = context.getEnvironment().getActiveProfiles();
  String[] defaultProfiles = context.getEnvironment().getDefaultProfiles();
  LogUtils.performance.info("defaultProfiles:{}", new Object[] { defaultProfiles });
  LogUtils.performance.info("activeProfile:{}", new Object[] { activeProfiles });
  ProfileUtils.setActiveProfiles(activeProfiles);
  ProfileUtils.setDefaultProfiles(defaultProfiles);
  SpringContainer container = new SpringContainer();
  container.init(context);
  Application.getInstance().setContainer(container);
 }

}
