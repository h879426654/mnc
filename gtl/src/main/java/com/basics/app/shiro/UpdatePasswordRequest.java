package com.basics.app.shiro;

public class UpdatePasswordRequest {

 private String passwordOld;
 private String passwordNew;
 private String passwordConfirmed;

 public String getPasswordOld() {
  return passwordOld;
 }

 public void setPasswordOld(String passwordOld) {
  this.passwordOld = passwordOld;
 }

 public String getPasswordNew() {
  return passwordNew;
 }

 public void setPasswordNew(String passwordNew) {
  this.passwordNew = passwordNew;
 }

 public String getPasswordConfirmed() {
  return passwordConfirmed;
 }

 public void setPasswordConfirmed(String passwordConfirmed) {
  this.passwordConfirmed = passwordConfirmed;
 }
}
