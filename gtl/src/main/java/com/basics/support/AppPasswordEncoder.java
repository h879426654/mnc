package com.basics.support;

import org.jasypt.util.digest.Digester;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AppPasswordEncoder. (默认的最简单的MD5加密)
 * 
 * @author yuwenfeng.
 */
public class AppPasswordEncoder implements PasswordEncoder {

 /** The algorithm. */
 private String algorithm = "MD5";

 /** iterations. */
 private int iterations = 1;

 /**
  * Gets the iterations.
  *
  * @return the iterations
  */
 public int getIterations() {
  return iterations;
 }

 /**
  * Sets the iterations.
  *
  * @param iterations
  *         the iterations
  */
 public void setIterations(int iterations) {
  this.iterations = iterations;
 }

 /** The digester. */
 Digester digester;

 /**
  * Gets the digester.
  *
  * @return the digester
  */
 public Digester getDigester() {
  if (this.digester == null) {
   this.digester = new Digester(this.algorithm);
  }
  return digester;
 }

 /**
  * Sets the digester.
  *
  * @param digester
  *         the digester
  */
 public void setDigester(Digester digester) {
  this.digester = digester;
 }

 /**
  * Gets the algorithm.
  *
  * @return the algorithm
  */
 public String getAlgorithm() {
  return algorithm;
 }

 /**
  * Sets the algorithm.
  *
  * @param algorithm
  *         the algorithm
  */
 public void setAlgorithm(String algorithm) {
  this.algorithm = algorithm;
 }

 /*
  * (non-Javadoc)
  * 
  * @see
  * org.springframework.security.crypto.password.PasswordEncoder#encode(java.
  * lang.CharSequence)
  */
 public String encode(CharSequence rawPassword) {
  String result = rawPassword.toString();
  for (int i = 0; i < this.iterations; i++) {
   byte[] digest = this.getDigester().digest(Utf8.encode(result));
   result = new String(Hex.encode(digest));
  }
  return result;
 }

 /*
  * (non-Javadoc)
  * 
  * @see
  * org.springframework.security.crypto.password.PasswordEncoder#matches(java.
  * lang.CharSequence, java.lang.String)
  */
 public boolean matches(CharSequence rawPassword, String encodedPassword) {
  return CommonSupport.equals(this.encode(rawPassword), encodedPassword);
 }
}
