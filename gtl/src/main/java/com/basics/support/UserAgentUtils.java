package com.basics.support;

import javax.servlet.http.HttpServletRequest;

/**
 * UserAgentUtils.
 */
public class UserAgentUtils {

 /**
  * From.
  *
  * @param request
  *         the request
  * @return the user agent support
  */
 public static UserAgentSupport from(HttpServletRequest request) {
  String userAgentString = request.getHeader("User-Agent");
  return new UserAgentSupport(userAgentString);
 }
}
