package com.basics.support;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class ServletUtils {
 public static final String TEXT_TYPE = "text/plain";
 public static final String JSON_TYPE = "application/json";
 public static final String XML_TYPE = "text/xml";
 public static final String HTML_TYPE = "text/html";
 public static final String JS_TYPE = "text/javascript";
 public static final String EXCEL_TYPE = "application/vnd.ms-excel";
 public static final long ONE_YEAR_SECONDS = 31536000L;

 public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
  response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000L);
  response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
 }

 public static void setNoCacheHeader(HttpServletResponse response) {
  response.setDateHeader("Expires", 0L);
  response.addHeader("Pragma", "no-cache");

  response.setHeader("Cache-Control", "no-cache");
 }

 public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
  response.setDateHeader("Last-Modified", lastModifiedDate);
 }

 public static void setEtag(HttpServletResponse response, String etag) {
  response.setHeader("ETag", etag);
 }

 public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response, long lastModified) {
  long ifModifiedSince = request.getDateHeader("If-Modified-Since");
  if ((ifModifiedSince != -1L) && (lastModified < ifModifiedSince + 1000L)) {
   response.setStatus(304);
   return false;
  }
  return true;
 }

 public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
  String headerValue = request.getHeader("If-None-Match");
  if (headerValue != null) {
   boolean conditionSatisfied = false;
   if (!"*".equals(headerValue)) {
    StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");
    do {
     String currentToken = commaTokenizer.nextToken();
     if (currentToken.trim().equals(etag))
      conditionSatisfied = true;
     if (conditionSatisfied)
      break;
    } while (commaTokenizer.hasMoreTokens());
   } else {
    conditionSatisfied = true;
   }

   if (conditionSatisfied) {
    response.setStatus(304);
    response.setHeader("ETag", etag);
    return false;
   }
  }
  return true;
 }

 public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
  try {
   String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
   response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
  } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
  }
 }

 public static void render(HttpServletResponse response, String encoding, String contentType, String content, String[] headers) {
  initResponseHeader(response, encoding, contentType, headers);
  PrintWriter writer = null;
  try {
   writer = response.getWriter();
   writer.write(content);
   writer.flush();
  } catch (IOException e) {
   throw new RuntimeException(e.getMessage(), e);
  } finally {
   IOUtils.closeQuietly(writer);
  }
 }

 public static void renderText(HttpServletResponse response, String encoding, String text, String[] headers) {
  render(response, encoding, "text/plain", text, headers);
 }

 public static void renderJsonAsText(HttpServletResponse response, Object object) {
  String string = FastjsonUtils.string(object);
  LogUtils.performance.debug("renderJsonAsText:{}", string);
  render(response, "UTF-8", "text/plain", string, new String[] {});
 }

 public static void renderHtml(HttpServletResponse response, String encoding, String html, String[] headers) {
  render(response, encoding, "text/html", html, headers);
 }

 public static void renderXml(HttpServletResponse response, String encoding, String xml, String[] headers) {
  render(response, encoding, "text/xml", xml, headers);
 }

 public static void renderJson(HttpServletResponse response, String encoding, String jsonString, String[] headers) {
  render(response, encoding, "application/json", jsonString, headers);
 }

 public static void renderJson(HttpServletResponse response, String encoding, Object data, String[] headers) {
  initResponseHeader(response, encoding, "application/json", headers);
  try {
   response.getWriter().print(FastjsonUtils.string(data));
  } catch (IOException e) {
   throw new IllegalArgumentException(e);
  }
 }

 public static void renderJsonp(HttpServletResponse response, String encoding, String callbackName, Object object, String[] headers) {
  String jsonString = null;
  try {
   jsonString = FastjsonUtils.string(object);
  } catch (Throwable e) {
   throw new IllegalArgumentException(e);
  }
  String result = callbackName + "(" + jsonString + ");";
  render(response, encoding, "text/javascript", result, headers);
 }

 /**
  * Inits the response header.
  *
  * @param response
  *         the response
  * @param encoding
  *         the encoding
  * @param contentType
  *         the content type
  * @param headers
  *         the headers
  */
 private static void initResponseHeader(HttpServletResponse response, String encoding, String contentType, String[] headers) {
  boolean noCache = true;
  for (String header : headers) {
   String headerName = StringUtils.substringBefore(header, ":");
   String headerValue = StringUtils.substringAfter(header, ":");

   if (StringUtils.equalsIgnoreCase(headerName, "encoding"))
    encoding = headerValue;
   else if (StringUtils.equalsIgnoreCase(headerName, "no-cache"))
    noCache = Boolean.parseBoolean(headerValue);
   else {
    throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
   }
  }

  String fullContentType = contentType + ";charset=" + encoding;
  response.setContentType(fullContentType);
  if (noCache)
   setNoCacheHeader(response);
 }
}