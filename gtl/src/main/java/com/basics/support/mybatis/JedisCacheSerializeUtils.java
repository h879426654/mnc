package com.basics.support.mybatis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.cache.CacheException;

public final class JedisCacheSerializeUtils {

 public static byte[] serialize(Object object) {
  ObjectOutputStream oos = null;
  ByteArrayOutputStream baos = null;
  try {
   baos = new ByteArrayOutputStream();
   oos = new ObjectOutputStream(baos);
   oos.writeObject(object);
   byte[] bytes = baos.toByteArray();
   return bytes;
  } catch (Exception e) {
   throw new CacheException(e);
  } finally {
   IOUtils.closeQuietly(oos);
   IOUtils.closeQuietly(baos);
  }

 }

 public static Object unserialize(byte[] bytes) {
  if (bytes == null) {
   return null;
  }
  ByteArrayInputStream bais = null;
  ObjectInputStream ois = null;
  try {
   bais = new ByteArrayInputStream(bytes);
   ois = new ObjectInputStream(bais);
   return ois.readObject();
  } catch (Exception e) {
   throw new CacheException(e);
  } finally {
   IOUtils.closeQuietly(bais);
   IOUtils.closeQuietly(ois);
  }
 }

}
