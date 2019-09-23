package com.basics.support;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;

public class VFSUtils {

 public static final String SEPARATOR = "/";

 public static String buildVirtualPath(String... parts) {
  StringBuilder path = new StringBuilder();
  for (int i = 0; i < parts.length; i++) {
   if (i > 0 && !StringUtils.endsWith(path.toString(), SEPARATOR)) {
    path.append(SEPARATOR);
   }
   String part = parts[i];
   part = StringUtils.replace(part, "\\", "/");
   if (StringUtils.startsWith(part, SEPARATOR)) {
    part = StringUtils.substringAfter(part, SEPARATOR);
   }
   path.append(part);
  }
  return path.toString();
 }

 public FileObject resolveFile(String virtualFilePath) throws IOException {
  return VFS.getManager().resolveFile(virtualFilePath);
 }

 public static byte[] readFileToByteArray(String virtualFilePath) throws IOException {
  try {
   LogUtils.performance.info("readFileToByteArray:{}", virtualFilePath);
   FileObject fileObject = VFS.getManager().resolveFile(virtualFilePath);
   InputStream input = null;
   input = fileObject.getContent().getInputStream();
   try {
    return IOUtils.toByteArray(input);
   } catch (IOException e) {
    throw new IOException(e);
   } finally {
    IOUtils.closeQuietly(input);
    fileObject.close();
   }
  } catch (FileSystemException e) {
   throw new IOException(e.getMessage());
  }
 }

 public static InputStream readFileToInputStream(String virtualFilePath) throws IOException {
  try {
   LogUtils.performance.info("readFileToInputStream:{}", virtualFilePath);
   FileObject fileObject = VFS.getManager().resolveFile(virtualFilePath);
   return fileObject.getContent().getInputStream();
  } catch (FileSystemException e) {
   throw e;
  }
 }

 public static void writeFileContentToVFS(String virtualFilePath, byte[] body) throws IOException {
  writeFileContentToVFS(virtualFilePath, new ByteArrayInputStream(body));
 }

 public static void writeFileContentToVFS(String virtualFilePath, InputStream input) throws IOException {
  try {
   LogUtils.performance.info("writeFileContentToVFS:{}", virtualFilePath);
   FileObject fileObject = VFS.getManager().resolveFile(virtualFilePath);
   OutputStream output = null;
   output = fileObject.getContent().getOutputStream();
   try {
    IOUtils.copyLarge(input, output);
   } catch (IOException e) {
    throw e;
   } finally {
    IOUtils.closeQuietly(input);
    IOUtils.closeQuietly(output);
    fileObject.close();
   }
  } catch (FileSystemException e) {
   throw e;
  }
 }

 public static void deleteFileContentFromVFS(String virtualFilePath) throws IOException {
  try {
   FileObject fileObject = VFS.getManager().resolveFile(virtualFilePath);
   if (fileObject.exists()) {
    LogUtils.performance.info("deleteFileContentFromVFS:" + virtualFilePath);
    fileObject.delete();
   }
  } catch (FileSystemException e) {
   throw e;
  }
 }
}
