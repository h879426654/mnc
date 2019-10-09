package com.basics.gty.entity;

import com.basics.sys.entity.BaseBean;

import java.math.BigDecimal;
import java.util.Date;

public class GtyWallet extends BaseBean {
 private String userId;
 private BigDecimal mncNum;
 private BigDecimal moveNum;
 private int walletFrozen;

 private BigDecimal superNum;
 private BigDecimal recordNum;
 private BigDecimal mTokenNum;
 private BigDecimal scoreNum;
 private BigDecimal releasedSuperNum;
 private BigDecimal releasedTokenNum;
 private BigDecimal releasedScoreNum;

 private BigDecimal mncPrice;
 private String point;

 private BigDecimal releasedMnc;

 private BigDecimal blockNum;

 private String nickName;

 private boolean isMerchant;

 public boolean isMerchant() {
  return isMerchant;
 }

 public void setMerchant(boolean merchant) {
  isMerchant = merchant;
 }

 public String getNickName() {
  return nickName;
 }

 public void setNickName(String nickName) {
  this.nickName = nickName;
 }



 public int getWalletFrozen() {
  return walletFrozen;
 }

 public void setWalletFrozen(int walletFrozen) {
  this.walletFrozen = walletFrozen;
 }

 public BigDecimal getBlockNum() {
  return blockNum;
 }

 public void setBlockNum(BigDecimal blockNum) {
  this.blockNum = blockNum;
 }

 public BigDecimal getReleasedMnc() {
  return releasedMnc;
 }

 public void setReleasedMnc(BigDecimal releasedMnc) {
  this.releasedMnc = releasedMnc;
 }

 public BigDecimal getMncPrice() {
  return mncPrice;
 }

 public void setMncPrice(BigDecimal mncPrice) {
  this.mncPrice = mncPrice;
 }

 public String getPoint() {
  return point;
 }

 public void setPoint(String point) {
  this.point = point;
 }

 public BigDecimal getReleasedTokenNum() {
  return releasedTokenNum;
 }

 public void setReleasedTokenNum(BigDecimal releasedTokenNum) {
  this.releasedTokenNum = releasedTokenNum;
 }

 public BigDecimal getReleasedScoreNum() {
  return releasedScoreNum;
 }

 public void setReleasedScoreNum(BigDecimal releasedScoreNum) {
  this.releasedScoreNum = releasedScoreNum;
 }

 private String walletAddress;

 public String getWalletAddress() {
  return walletAddress;
 }

 public void setWalletAddress(String walletAddress) {
  this.walletAddress = walletAddress;
 }

 public BigDecimal getSuperNum() {
  return superNum;
 }

 public void setSuperNum(BigDecimal superNum) {
  this.superNum = superNum;
 }

 public BigDecimal getRecordNum() {
  return recordNum;
 }

 public void setRecordNum(BigDecimal recordNum) {
  this.recordNum = recordNum;
 }

 public BigDecimal getmTokenNum() {
  return mTokenNum;
 }

 public void setmTokenNum(BigDecimal mTokenNum) {
  this.mTokenNum = mTokenNum;
 }

 public BigDecimal getScoreNum() {
  return scoreNum;
 }

 public void setScoreNum(BigDecimal scoreNum) {
  this.scoreNum = scoreNum;
 }

 public BigDecimal getReleasedSuperNum() {
  return releasedSuperNum;
 }

 public void setReleasedSuperNum(BigDecimal releasedSuperNum) {
  this.releasedSuperNum = releasedSuperNum;
 }

 public String getUserId() {
  return userId;
 }

 public void setUserId(String userId) {
  this.userId = userId;
 }

 public BigDecimal getMncNum() {
  return mncNum;
 }

 public void setMncNum(BigDecimal mncNum) {
  this.mncNum = mncNum;
 }

 public BigDecimal getMoveNum() {
  return moveNum;
 }

 public void setMoveNum(BigDecimal moveNum) {
  this.moveNum = moveNum;
 }
}