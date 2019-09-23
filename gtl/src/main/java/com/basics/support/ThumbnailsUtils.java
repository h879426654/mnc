package com.basics.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import net.coobird.thumbnailator.Thumbnails;

// TODO: Auto-generated Javadoc
/**
 * ThumbnailsUtils.
 * 
 * @author yuwenfeng
 */
public class ThumbnailsUtils {

 public static void resize(File srcFile, File destFile, int boxWidth, int boxHeight) throws Exception {
  Thumbnails.of(srcFile).size(boxWidth, boxHeight).toFile(destFile);
 }

 public static void resize(File srcFile, OutputStream outputStream, int boxWidth, int boxHeight) throws Exception {
  Thumbnails.of(srcFile).size(boxWidth, boxHeight).toOutputStream(outputStream);
 }

 public static void resize(InputStream inputStream, OutputStream outputStream, int boxWidth, int boxHeight) throws Exception {
  Thumbnails.of(inputStream).size(boxWidth, boxHeight).toOutputStream(outputStream);
 }

 public static void resize(File srcFile, File destFile, int boxWidth, int boxHeight, boolean ignoreSmall) throws Exception {
  if (ignoreSmall) {
   InputStream fis = null;
   try {
    ImageInfo info = new ImageInfo();
    fis = new FileInputStream(srcFile);
    info.setInput(fis);
    if (info.check()) {
     if (info.getWidth() < boxWidth) {
      boxWidth = info.getWidth();
     }
     if (info.getHeight() < boxHeight) {
      boxHeight = info.getHeight();
     }
    }
   } catch (Throwable e) {
    LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   } finally {
    IOUtils.closeQuietly(fis);
   }
  }
  Thumbnails.of(srcFile).size(boxWidth, boxHeight).toFile(destFile);
 }

 public static void resize(File srcFile, OutputStream outputStream, int boxWidth, int boxHeight, boolean ignoreSmall) throws Exception {
  if (ignoreSmall) {
   InputStream fis = null;
   try {
    ImageInfo info = new ImageInfo();
    fis = new FileInputStream(srcFile);
    info.setInput(fis);
    if (info.check()) {
     if (info.getWidth() < boxWidth) {
      boxWidth = info.getWidth();
     }
     if (info.getHeight() < boxHeight) {
      boxHeight = info.getHeight();
     }
    }
   } catch (Throwable e) {
    LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
   } finally {
    IOUtils.closeQuietly(fis);
   }
  }
  Thumbnails.of(srcFile).size(boxWidth, boxHeight).toOutputStream(outputStream);
 }

 public static void scale(File srcFile, File destFile, double scale) throws Exception {
  Thumbnails.of(srcFile).scale(scale).toFile(destFile);
 }

 public static void scale(File srcFile, OutputStream outputStream, double scale) throws Exception {
  Thumbnails.of(srcFile).scale(scale).toOutputStream(outputStream);
 }

 public static boolean support(File srcFile) {
  ByteArrayOutputStream bos = new ByteArrayOutputStream();
  try {
   resize(srcFile, bos, 1, 1);
   bos.close();
   return true;
  } catch (Throwable e) {
   return false;
  }
 }

 /**
  * Support.
  *
  * @param bytes
  *         the bytes
  * @return true, if successful
  */
 public static boolean support(byte[] bytes) {
  ByteArrayOutputStream bos = new ByteArrayOutputStream();
  ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
  try {
   resize(bis, bos, 1, 1);
   bos.close();
   return true;
  } catch (Throwable e) {
   return false;
  }
 }

 /**
  * The main method.
  *
  * @param args
  *         the arguments
  * @throws Exception
  *          the exception
  */
 public static void main(String[] args) throws Exception {
  File srcFile = new File("d:/IMG_0215.JPG");
  File destFile = new File("d:/IMG_0215_150x150.JPG");
  ThumbnailsUtils.resize(srcFile, destFile, 150, 150);
  destFile = new File("d:/IMG_0215_300x300.JPG");
  ThumbnailsUtils.resize(srcFile, destFile, 300, 300);
  destFile = new File("d:/IMG_0215_1024x1024.JPG");
  ThumbnailsUtils.resize(srcFile, destFile, 1024, 1024);
  destFile = new File("d:/IMG_0215_640x960.JPG");
  ThumbnailsUtils.resize(srcFile, destFile, 640, 960);
  destFile = new File("d:/IMG_0215_64x64.JPG");
  ThumbnailsUtils.resize(srcFile, destFile, 64, 64);
  destFile = new File("d:/IMG_0215_320x240.JPG");
  ThumbnailsUtils.resize(srcFile, destFile, 320, 240);
  LogUtils.performance.info("isSupport {}? {}", destFile, ThumbnailsUtils.support(destFile));
  destFile = new File("d:/研发中心工作周报.xlsx");
  LogUtils.performance.info("isSupport {}? {}", destFile, ThumbnailsUtils.support(destFile));

  FileInputStream fis = new FileInputStream(srcFile);
  FileOutputStream fos = new FileOutputStream(new File("d:/IMG_0215_640x960.stream.JPG"));
  LogUtils.performance.info("isSupport {}? {}", srcFile, ThumbnailsUtils.support(FileUtils.readFileToByteArray(srcFile)));
  ThumbnailsUtils.resize(fis, fos, 640, 960);

 }
}
