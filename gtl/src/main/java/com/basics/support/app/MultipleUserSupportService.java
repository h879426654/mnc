package com.basics.support.app;

import com.basics.app.shiro.UserSupport;

/**
 * MultipleUserSupportService.
 * 
 * @author yuwenfeng
 */
public interface MultipleUserSupportService {

 /**
  * 通过用户ID和用户类型获取用户信息..
  *
  * @param userId
  *         the user id
  * @param userType
  *         the user type
  * @return UserSupport
  */
 public UserSupport getByUserIdAndType(String userId, String userType);
}
