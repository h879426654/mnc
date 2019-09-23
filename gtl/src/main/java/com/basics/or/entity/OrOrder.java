package com.basics.or.entity;

import java.math.BigDecimal;
import java.util.Date;
public class OrOrder extends OrOrderBase{
 /**
 * 订单ID
 */
 public OrOrder id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 店铺ID
 */
 public OrOrder shopId(String shopId){
  this.setShopId(shopId);
  return this;
 }
 /**
 * 客户ID
 */
 public OrOrder customerId(String customerId){
  this.setCustomerId(customerId);
  return this;
 }
 /**
 * 订单总价
 */
 public OrOrder orderTotalPrice(BigDecimal orderTotalPrice){
  this.setOrderTotalPrice(orderTotalPrice);
  return this;
 }
 /**
 * 支付金额
 */
 public OrOrder orderPayPrice(BigDecimal orderPayPrice){
  this.setOrderPayPrice(orderPayPrice);
  return this;
 }
 /**
 * 支付方式(1支付宝 2微信 3银行卡)
 */
 public OrOrder orderPayType(Integer orderPayType){
  this.setOrderPayType(orderPayType);
  return this;
 }
 /**
 * 交易流水号
 */
 public OrOrder orderNumber(String orderNumber){
  this.setOrderNumber(orderNumber);
  return this;
 }
 /**
 * 订单支付时间
 */
 public OrOrder orderPayTime(Date orderPayTime){
  this.setOrderPayTime(orderPayTime);
  return this;
 }
 /**
 * 订单状态（1待支付 2待发货 3待签收 4待评价 5订单完成 6订单取消 7订单退款）
 */
 public OrOrder orderStatus(Integer orderStatus){
  this.setOrderStatus(orderStatus);
  return this;
 }
 /**
 * 物流公司编号
 */
 public OrOrder orderLogisticsCode(String orderLogisticsCode){
  this.setOrderLogisticsCode(orderLogisticsCode);
  return this;
 }
 /**
 * 物流公司名称
 */
 public OrOrder orderLogisticsName(String orderLogisticsName){
  this.setOrderLogisticsName(orderLogisticsName);
  return this;
 }
 /**
 * 物流单号
 */
 public OrOrder orderLogisticsNum(String orderLogisticsNum){
  this.setOrderLogisticsNum(orderLogisticsNum);
  return this;
 }
 /**
 * 收货人姓名
 */
 public OrOrder orderReceiver(String orderReceiver){
  this.setOrderReceiver(orderReceiver);
  return this;
 }
 /**
 * 收货人联系方式
 */
 public OrOrder orderReceiverPhone(String orderReceiverPhone){
  this.setOrderReceiverPhone(orderReceiverPhone);
  return this;
 }
 /**
 * 收货地址省ID
 */
 public OrOrder addressProvince(String addressProvince){
  this.setAddressProvince(addressProvince);
  return this;
 }
 /**
 * 收货地址市ID
 */
 public OrOrder addressCity(String addressCity){
  this.setAddressCity(addressCity);
  return this;
 }
 /**
 * 收货地址区域ID
 */
 public OrOrder addressArea(String addressArea){
  this.setAddressArea(addressArea);
  return this;
 }
 /**
 * 收货详细地址
 */
 public OrOrder addressInfo(String addressInfo){
  this.setAddressInfo(addressInfo);
  return this;
 }
 /**
 * 订单确认时间
 */
 public OrOrder orderFinishTime(Date orderFinishTime){
  this.setOrderFinishTime(orderFinishTime);
  return this;
 }
 /**
 * 买家备注
 */
 public OrOrder orderRemark(String orderRemark){
  this.setOrderRemark(orderRemark);
  return this;
 }
 /**
 * 版本号
 */
 public OrOrder versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public OrOrder flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public OrOrder createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public OrOrder createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public OrOrder modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public OrOrder modifyDate(Date modifyDate){
  this.setModifyDate(modifyDate);
  return this;
 }
}