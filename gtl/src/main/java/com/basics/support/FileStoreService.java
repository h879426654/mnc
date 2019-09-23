package com.basics.support;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Interface FileStoreService.
 */
public interface FileStoreService {

 /**
  * 返回存储的id
  * 
  * @return
  */
 long getStoreId();

 /**
  * 返回存储的名称.
  * 
  * @return
  */
 public String getStoreName();

 /**
  * Read file to byte array.
  * 
  * @param path
  *         the path
  * @return the byte[]
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 byte[] readToByteArray(String path) throws IOException;

 /**
  * Read file to input stream.
  * 
  * @param path
  *         the path
  * @return the input stream
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 InputStream readToInputStream(String path) throws IOException;

 /**
  * Existed.
  * 
  * @param path
  *         the path
  * @return true, if successful
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 boolean existed(String path) throws IOException;

 /**
  * Write.
  * 
  * @param path
  *         the path
  * @param content
  *         the content
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 void write(String path, byte[] content) throws IOException;

 /**
  * Write.
  * 
  * @param path
  *         the path
  * @param content
  *         the content
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 void write(String path, InputStream content) throws IOException;

 /**
  * Write.
  * 
  * @param path
  *         the path
  * @param file
  *         the file
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 void write(String path, File file) throws IOException;

 /**
  * Delete.
  * 
  * @param path
  *         the path
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 void delete(String path) throws IOException;

 /**
  * 返回指定相对路径的外网访问地址.
  * 
  * @param filePath
  * @return
  */
 public String getInternetUrl(String filePath);

 /**
  * 
  * @Title: getPath
  * @Description: 返回域名外的相对路径
  * @param request
  * @return String
  * @throws @author
  *          yuwenfeng
  *
  * @date May 10, 2016 4:36:09 PM
  */
 public String getPath(String filePath);
}
