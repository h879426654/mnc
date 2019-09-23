package com.basics.support;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * MultipartFileSupport.
 * 
 * @author yuwenfeng.
 */
public class MultipartFileSupport implements MultipartFile {

 /** The bytes. */
 protected byte[] bytes;

 /** The content type. */
 protected String contentType;

 /** The name. */
 protected String name;

 /** The ok. */
 protected boolean ok;

 /**
  * Instantiates a new multipart file support.
  *
  * @param bytes
  *         the bytes
  */
 public MultipartFileSupport(byte[] bytes) {
  this.bytes = bytes;
  this.ok = this.bytes != null;
 }

 /**
  * Instantiates a new multipart file support.
  *
  * @param file
  *         the file
  */
 public MultipartFileSupport(File file) {
  try {
   this.name = FilenameUtils.getName(file.getName());
   this.bytes = FileUtils.readFileToByteArray(file);
  } catch (Throwable e) {
   ok = false;
  }
 }

 /**
  * Gets the bytes.
  *
  * @return the bytes
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public byte[] getBytes() throws IOException {
  return this.bytes;
 }

 /**
  * Gets the content type.
  *
  * @return the content type
  */
 public String getContentType() {
  return this.contentType;
 }

 /**
  * Gets the input stream.
  *
  * @return the input stream
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  */
 public InputStream getInputStream() throws IOException {
  return new ByteArrayInputStream(this.bytes);
 }

 /**
  * Gets the name.
  *
  * @return the name
  */
 public String getName() {
  return this.name;
 }

 /**
  * Gets the original filename.
  *
  * @return the original filename
  */
 public String getOriginalFilename() {
  return this.name;
 }

 /**
  * Gets the size.
  *
  * @return the size
  */
 public long getSize() {
  return bytes.length;
 }

 /**
  * Checks if is empty.
  *
  * @return true, if is empty
  */
 public boolean isEmpty() {
  return bytes == null;
 }

 /**
  * Transfer to.
  *
  * @param file
  *         the file
  * @throws IOException
  *          Signals that an I/O exception has occurred.
  * @throws IllegalStateException
  *          the illegal state exception
  */
 public void transferTo(File file) throws IOException, IllegalStateException {
  FileUtils.writeByteArrayToFile(file, bytes);
 }

 /**
  * Checks if is ok.
  *
  * @return true, if is ok
  */
 public boolean isOk() {
  return this.ok;
 }

}
