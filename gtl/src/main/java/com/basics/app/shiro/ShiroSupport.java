package com.basics.app.shiro;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ShiroSupport implements ApplicationContextAware {

 @Value("${shiro.urpService}")
 protected String urpService;

 @Value("${shiro.rememberMeParam}")
 protected String rememberMeParam;

 protected ApplicationContext context;

 public String getUrpService() {
  return urpService;
 }

 public void setUrpService(String urpService) {
  this.urpService = urpService;
 }

 public String getRememberMeParam() {
  return rememberMeParam;
 }

 public void setRememberMeParam(String rememberMeParam) {
  this.rememberMeParam = rememberMeParam;
 }

 public void setApplicationContext(ApplicationContext conext) throws BeansException {
  this.context = conext;
 }

 public UserRolePermissionSupport getUserRolePermissionSupport() {
  return (UserRolePermissionSupport) this.context.getBean(this.urpService);
 }

}
