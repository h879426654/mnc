package com.basics.support;

import java.util.Date;

public class UserAware<T> {

 private T userId;

 public UserAware(T userId) {
  super();
  this.userId = userId;
 }

 public void attach(UserAwareSupport<T> userAware) {
  try {
   if (userAware.getCreateTime() == null) {
    userAware.setCreateTime(new Date());
   }
   if (userAware.getCreateUser() == null) {
    userAware.setCreateUser(userId);
   }
   userAware.setUpdateTime(new Date());
   userAware.setUpdateUser(userId);
  } catch (Throwable e) {
   LogUtils.performance.error("User.attach error:{}", e);
  }
 }

}
