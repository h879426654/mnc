package com.basics.app.entity;

import java.util.Date;
public class AppToken extends AppTokenBase{
 /**
 * 令牌ID
 */
 public AppToken id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 令牌创建时间
 */
 public AppToken tokenCreateTime(Date tokenCreateTime){
  this.setTokenCreateTime(tokenCreateTime);
  return this;
 }
 /**
 * 令牌过期时间
 */
 public AppToken tokenExpirationTime(Date tokenExpirationTime){
  this.setTokenExpirationTime(tokenExpirationTime);
  return this;
 }
 /**
 * 令牌所属用户ID
 */
 public AppToken userId(String userId){
  this.setUserId(userId);
  return this;
 }
}