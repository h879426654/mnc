package com.basics.app.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.basics.support.DefaultPasswordCryptor;
import com.basics.support.PasswordCryptor;

@Service
public class AppPasswordCryptor implements PasswordCryptor {

 protected String groupId = "com.basics.app";
 protected PasswordCryptor delegate;

 public PasswordCryptor getDelegate() {
  if (this.delegate == null) {
   this.delegate = this.onBuildDefaultPasswordCryptor();
  }
  return this.delegate;
 }

 public void setDelegate(PasswordCryptor delegate) {
  this.delegate = delegate;
 }

 public String getGroupId() {
  return this.groupId;
 }

 public void setGroupId(String groupId) {
  this.groupId = groupId;
 }

 public PasswordCryptor onBuildDefaultPasswordCryptor() {
  return new DefaultPasswordCryptor();
 }

 public String encrypt(String plainKey) throws Exception {
  return this.getDelegate().encrypt(this.getGroupId() + plainKey);
 }

 public String decrypt(String encryptedKey) throws Exception {
  String decrpted = this.getDelegate().decrypt(encryptedKey);
  return StringUtils.substring(decrpted, this.getGroupId().length());
 }

}
