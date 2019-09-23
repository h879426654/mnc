/**

 * 
 */
package com.basics.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

public class DownloadUtils {
 public static final String DOWNLOAD_MIME = "application/octet-stream;charset=UTF-8";

 private static final String DISPOSITION = "Content-Disposition";

 private static final String CONTENT_LENGTH = "Content-Length";

 private static final String ATTACH_FILE = "attachment;filename=";

 private static final String INLINE_FILE = "inline;filename=";

 public static String encodeFilename(String filename, HttpServletRequest request) {
  if (null == filename)
   return "";
  try {
   String extName = "";
   int i = filename.lastIndexOf(".");
   if (i != -1) {
    extName = filename.substring(i);
    filename = filename.substring(0, i);
   }
   // 文件名不能有\/:*?"<>|
   filename = filename.replace("\\", "");
   filename = filename.replace("/", "");
   filename = filename.replace(":", "");
   filename = filename.replace("*", "");
   filename = filename.replace("?", "");
   filename = filename.replace("\"", "");
   filename = filename.replace("<", "");
   filename = filename.replace(">", "");
   filename = filename.replace("|", "");
   filename = filename.replace("&", "");
   filename = filename.replace("#", "");
   filename = filename.replace("~", "");
   filename = filename.replace(";", "");
   filename = filename.replace("；", "");
   filename = filename.replace("。", "");
   filename = filename.replace(" ", "");
   filename = filename.replace(" ", "");

   return new String(filename.getBytes(), "ISO8859-1") + extName;
  } catch (Exception e) {
   e.printStackTrace();
   return filename;
  }
 }

 public static void download(HttpServletRequest request, HttpServletResponse response, String filename, File file) throws IOException {
  LogUtils.performance.info("download:" + file.getAbsolutePath());
  InputStream is = null;
  try {
   is = new FileInputStream(file);
   download(request, response, filename, is);
  } finally {
   IOUtils.closeQuietly(is);
  }
 }

 public static void download(HttpServletRequest request, HttpServletResponse response, String filename, InputStream is) throws IOException {
  setResponseHeader(request, response, filename);

  OutputStream out = null;
  try {
   out = response.getOutputStream();
   if (is != null && out != null) {
    IOUtils.copy(is, out);
   }
  } catch (Throwable ex) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(ex));
   throw new RuntimeException(ex);
  } finally {
   IOUtils.closeQuietly(is);
   IOUtils.closeQuietly(out);
  }
 }

 public static void download(HttpServletRequest request, HttpServletResponse response, String filename, byte[] datas) throws IOException {
  setResponseHeader(request, response, filename);
  response.addHeader(CONTENT_LENGTH, String.valueOf(datas.length));
  OutputStream out = null;
  try {
   out = response.getOutputStream();
   out.write(datas, 0, datas.length);
   out.flush();
  } catch (Throwable ex) {
   LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(ex));
   throw new RuntimeException(ex);
  } finally {
   IOUtils.closeQuietly(out);
  }
 }

 public static void downloadInline(HttpServletRequest request, HttpServletResponse response, String filename, InputStream is)
  throws IOException {
  setResponseHeaderInline(request, response, filename);
  OutputStream out = null;
  try {
   out = response.getOutputStream();
   if (is != null && out != null) {
    IOUtils.copy(is, out);
   }
  } catch (Throwable ex) {
   LogUtils.performance.error("{}", ex);
   throw new RuntimeException(ex);
  } finally {
   IOUtils.closeQuietly(is);
   IOUtils.closeQuietly(out);
  }
 }

 public static void downloadInline(HttpServletRequest request, HttpServletResponse response, String filename, byte[] datas)
  throws IOException {
  setResponseHeaderInline(request, response, filename);
  response.addHeader(CONTENT_LENGTH, String.valueOf(datas.length));
  OutputStream out = null;
  try {
   out = response.getOutputStream();
   out.write(datas, 0, datas.length);
   out.flush();
  } catch (Throwable ex) {
   LogUtils.performance.error("{}", ex);
   throw new RuntimeException(ex);
  } finally {
   IOUtils.closeQuietly(out);
  }
 }

 private static void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String filename) {
  response.setHeader("Pragma", "public");
  response.setHeader("Expires", "0");
  response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
  response.setHeader("Cache-Control", "public");
  response.setHeader("Content-Description", "File Transfer");
  String downfileName = encodeFilename(filename, request);
  response.setContentType(DOWNLOAD_MIME);
  response.setHeader(DISPOSITION, ATTACH_FILE + "\"" + downfileName + "\"");
 }

 private static void setResponseHeaderInline(HttpServletRequest request, HttpServletResponse response, String filename) {
  response.setHeader("Pragma", "public");
  response.setHeader("Expires", "0");
  response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
  response.setHeader("Cache-Control", "public");
  response.setHeader("Content-Description", "File Transfer");
  String downfileName = encodeFilename(filename, request);
  response.setContentType(DOWNLOAD_MIME);
  response.setHeader(DISPOSITION, INLINE_FILE + "\"" + downfileName + "\"");
 }

 /**
  * 将中文名称用真实的文件名替换,解决URL参递中文,无法以正常下载的问题.
  * 
  * @param params
  * @return
  */
 public static String fixAttachmentFilename(String params) {
  int indexOf = params.lastIndexOf("realpath");
  if (indexOf == -1)
   return params;
  String realpath = params.substring(indexOf);
  String filename = FilenameUtils.getName(realpath);
  return "filename=" + filename + "&" + realpath;
 }
}
