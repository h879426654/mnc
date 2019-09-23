package com.basics.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
public class SysConfig extends SysConfigBase{
 /**
 * 配置ID
 */
 public SysConfig id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 配置
 */
 public SysConfig configCode(String configCode){
  this.setConfigCode(configCode);
  return this;
 }
 /**
 * 配置名
 */
 public SysConfig configName(String configName){
  this.setConfigName(configName);
  return this;
 }
 /**
 * 配置值
 */
 public SysConfig configValue(BigDecimal configValue){
  this.setConfigValue(configValue);
  return this;
 }
 /**
 * 是否启用
 */
 public SysConfig configFlag(Integer configFlag){
  this.setConfigFlag(configFlag);
  return this;
 }
 /**
 * 版本号
 */
 public SysConfig versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysConfig flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysConfig createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysConfig createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysConfig modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysConfig modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}