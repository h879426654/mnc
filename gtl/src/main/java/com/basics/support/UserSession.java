package com.basics.support;

/**
 * UserSession.
 */
public class UserSession {

 protected Object user = null;

 private UserContext context;

 public UserContext getContext() {
  return context;
 }

 public void setContext(UserContext context) {
  this.context = context;
 }

 public Object getUser() {
  return user;
 }

 public void setUser(Object user) {
  this.user = user;
 }

}
