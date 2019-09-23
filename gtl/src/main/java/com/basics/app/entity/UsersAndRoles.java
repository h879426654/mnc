package com.basics.app.entity;

import org.apache.commons.lang.StringUtils;

public class UsersAndRoles {

 private String users;
 private String roles;

 public String getUsers() {
  return this.users;
 }

 public void setUsers(String users) {
  this.users = users;
 }

 public String getRoles() {
  return this.roles;
 }

 public void setRoles(String roles) {
  this.roles = roles;
 }

 public UsersAndRoles user(String user) {
  this.setUsers(user);
  return this;
 }

 public UsersAndRoles users(String... users) {
  if (users != null) {
   this.setUsers(StringUtils.join(users, ","));
  } else {
   this.setUsers("");
  }
  return this;
 }

 public UsersAndRoles role(String role) {
  this.setRoles(role);
  return this;
 }

 public UsersAndRoles roles(String... roles) {
  if (roles != null) {
   this.setRoles(StringUtils.join(roles, ","));
  } else {
   this.setRoles("");
  }
  return this;
 }

}
