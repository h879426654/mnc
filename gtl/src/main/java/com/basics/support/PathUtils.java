package com.basics.support;

import org.apache.commons.lang.StringUtils;

/**
 * PathUtils.
 * 
 * @author yuwenfeng
 */
public class PathUtils {

 /** The Constant PATH_SEPARATOR. */
 public static final String PATH_SEPARATOR = "/";

 /**
  * Builds the virtual path.
  *
  * @param parts
  *         the parts
  * @return the string
  */
 public static String buildVirtualPath(String... parts) {
  StringBuilder path = new StringBuilder();
  for (int i = 0; i < parts.length; i++) {
   if (i > 0 && !StringUtils.endsWith(path.toString(), PATH_SEPARATOR)) {
    path.append(PATH_SEPARATOR);
   }
   String part = parts[i];
   part = StringUtils.replace(part, "\\", PATH_SEPARATOR);
   if (StringUtils.startsWith(part, PATH_SEPARATOR)) {
    part = StringUtils.substringAfter(part, PATH_SEPARATOR);
   }
   path.append(part);
  }
  return path.toString();
 }
}
