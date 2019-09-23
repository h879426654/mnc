package com.basics.cu.entity;

public class CuCustomerReferee extends CuCustomerRefereeBase{
 /**
 * 用户ID
 */
 public CuCustomerReferee id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 推荐人ID
 */
 public CuCustomerReferee refereeId(String refereeId){
  this.setRefereeId(refereeId);
  return this;
 }
}