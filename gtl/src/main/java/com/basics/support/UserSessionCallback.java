package com.basics.support;

/**
 * The Interface UserSessionCallback.
 * 
 * @param <T>
 *         the generic type
 */
public interface UserSessionCallback<T> {

 /**
  * Do in user session.
  * 
  * @param session
  *         the session
  * @return the t
  * @throws Exception
  *          the exception
  */
 public T doInUserSession(UserSession session) throws Exception;
}