package com.basics.support;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class HttpMultipartFile implements MultipartFile {

 protected String httpUrl;

 protected byte[] bytes;

 protected String contentType;

 protected String name;

 protected boolean ok;

 public HttpMultipartFile(String httpUrl) {
  super();
  this.httpUrl = httpUrl;
  this.init();
 }

 protected void init() {
  HttpResponse response = HttpRequest.get(this.httpUrl).send();
  bytes = response.bodyBytes();
  this.contentType = response.contentType();
  this.name = FilenameUtils.getName(this.httpUrl);
  LogUtils.performance.info("{}({})", response.statusPhrase(), response.statusCode());
  this.ok = 200 == response.statusCode();
 }

 public byte[] getBytes() throws IOException {
  return this.bytes;
 }

 public String getContentType() {
  return this.contentType;
 }

 public InputStream getInputStream() throws IOException {
  return new ByteArrayInputStream(this.bytes);
 }

 public String getName() {
  return this.name;
 }

 public String getOriginalFilename() {
  return this.name;
 }

 public long getSize() {
  return bytes.length;
 }

 public boolean isEmpty() {
  return bytes == null;
 }

 public void transferTo(File file) throws IOException, IllegalStateException {
  FileUtils.writeByteArrayToFile(file, bytes);
 }

 public boolean isOk() {
  return ok;
 }

}
