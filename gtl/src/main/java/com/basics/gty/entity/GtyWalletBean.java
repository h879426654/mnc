package com.basics.gty.entity;

import java.math.BigDecimal;

public class GtyWalletBean extends GtyBaseBean {
 private String userId;
 private BigDecimal mncNum;
 private BigDecimal moveNum;

 private BigDecimal superNum;
 private BigDecimal recordNum;
 private BigDecimal mTokenNum;
 private BigDecimal scoreNum;
 private BigDecimal releasedSuperNum;
 private String walletAddress;

 private BigDecimal superReleaseNum;
 private BigDecimal mTokenRelease;
 private BigDecimal scoreRelease;


 public BigDecimal getSuperReleaseNum() {
  return superReleaseNum;
 }

 public void setSuperReleaseNum(BigDecimal superReleaseNum) {
  this.superReleaseNum = superReleaseNum;
 }

 public BigDecimal getmTokenRelease() {
  return mTokenRelease;
 }

 public void setmTokenRelease(BigDecimal mTokenRelease) {
  this.mTokenRelease = mTokenRelease;
 }

 public BigDecimal getScoreRelease() {
  return scoreRelease;
 }

 public void setScoreRelease(BigDecimal scoreRelease) {
  this.scoreRelease = scoreRelease;
 }

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