package com.basics.support;

import java.util.Date;

/**
 * UserAwareSupport.
 *
 * @param <T>
 *         the generic type.用户ID类型.
 */
public interface UserAwareSupport<T> {

 /**
  * 创建时间.
  * 
  * @return 创建时间.
  */
 java.util.Date getCreateTime();

 /**
  * 创建时间.
  *
  * @param value
  *         the creates the time
  */
 public void setCreateTime(Date value);

 /**
  * 创建人.
  * 
  * @return 创建人.
  */
 T getCreateUser();

 /**
  * 创建人.
  * 
  * @param createUser
  *         创建人.
  */
 public void setCreateUser(T createUser);

 /**
  * 更新时间.
  * 
  * @return 更新时间.
  */
 java.util.Date getUpdateTime();

 /**
  * 更新时间.
  * 
  * @param updateTime
  *         更新时间.
  */
 void setUpdateTime(java.util.Date updateTime);

 /**
  * 更新人.
  * 
  * @return 更新人.
  */
 T getUpdateUser();

 /**
  * 更新人.
  * 
  * @param updateUser
  *         更新人.
  */
 void setUpdateUser(T updateUser);

}