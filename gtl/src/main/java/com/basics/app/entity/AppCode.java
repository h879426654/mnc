package com.basics.app.entity;

import java.util.Date;
public class AppCode extends AppCodeBase{
 /**
 * 短信ID
 */
 public AppCode id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 验证码类型1:注册 2:忘记密码 3修改密码
 */
 public AppCode codeType(Integer codeType){
  this.setCodeType(codeType);
  return this;
 }
 /**
 * 手机号
 */
 public AppCode codeMobile(String codeMobile){
  this.setCodeMobile(codeMobile);
  return this;
 }
 /**
 * 短信验证码
 */
 public AppCode codeCode(String codeCode){
  this.setCodeCode(codeCode);
  return this;
 }
 /**
 * 验证码是否有效
 */
 public AppCode codeState(Integer codeState){
  this.setCodeState(codeState);
  return this;
 }
 /**
 * 验证码内容
 */
 public AppCode codeText(String codeText){
  this.setCodeText(codeText);
  return this;
 }
 /**
 * 验证码生成时间
 */
 public AppCode codeCreateTime(Date codeCreateTime){
  this.setCodeCreateTime(codeCreateTime);
  return this;
 }
 /**
 * 验证码过期时间
 */
 public AppCode codeExpirationTime(Date codeExpirationTime){
  this.setCodeExpirationTime(codeExpirationTime);
  return this;
 }
}