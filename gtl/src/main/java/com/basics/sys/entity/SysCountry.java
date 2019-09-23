package com.basics.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
public class SysCountry extends SysCountryBase{
 /**
 * 国家ID
 */
 public SysCountry countryId(String countryId){
  this.setCountryId(countryId);
  return this;
 }
 /**
 * 国家CODE
 */
 public SysCountry countryCode(String countryCode){
  this.setCountryCode(countryCode);
  return this;
 }
 /**
 * 国家名字
 */
 public SysCountry countryName(String countryName){
  this.setCountryName(countryName);
  return this;
 }
 /**
 * 国家汇率
 */
 public SysCountry countryRate(BigDecimal countryRate){
  this.setCountryRate(countryRate);
  return this;
 }
 /**
  * 国家货币符号
  */
 public SysCountry countrySymbol(String countrySymbol){
	 this.setCountrySymbol(countrySymbol);
	 return this;
 }
 /**
 * 版本号
 */
 public SysCountry versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysCountry flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysCountry createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysCountry createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysCountry modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysCountry modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}