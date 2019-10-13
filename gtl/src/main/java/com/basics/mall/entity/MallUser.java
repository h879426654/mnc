package com.basics.mall.entity;


import java.util.Date;

public class MallUser extends MallUserBase{
 public MallUser id(String id){
  this.setId(id);
  return this;
 }
 public MallUser customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 public MallUser userName(String userName){
  this.setUserName(userName);
  return this;
 }
 public MallUser passWord(String passWord){
  this.setPassWord(passWord);
  return this;
 }

}