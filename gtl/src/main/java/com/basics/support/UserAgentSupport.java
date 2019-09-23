package com.basics.support;

import org.apache.commons.lang3.StringUtils;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * UserAgentSupport.
 */
public class UserAgentSupport {

 /** The user agent string. */
 protected String userAgentString;

 /**
  * The Constructor.
  */
 public UserAgentSupport() {
 }

 /**
  * The Constructor.
  *
  * @param userAgentString
  *         the user agent string
  */
 public UserAgentSupport(String userAgentString) {
  super();
  this.userAgentString = userAgentString;
  this.init(userAgentString);
 }

 /**
  * Inits the.
  *
  * @param userAgentString
  *         the user agent string
  * @return the user agent support
  */
 public UserAgentSupport init(String userAgentString) {
  try {
   UserAgent userAgent = new UserAgent(userAgentString);
   String osName = userAgent.getOperatingSystem().getName();
   android = StringUtils.containsIgnoreCase(osName, "android");
   mac = StringUtils.containsIgnoreCase(osName, "mac");
   mobileDevice = DeviceType.MOBILE.equals(userAgent.getOperatingSystem().getDeviceType());
   computerDevice = DeviceType.COMPUTER.equals(userAgent.getOperatingSystem().getDeviceType());
   ios = StringUtils.containsIgnoreCase(osName, "ios");
   weixin = StringUtils.containsIgnoreCase(userAgentString, "MicroMessenger");
  } catch (Throwable e) {
   LogUtils.performance.error("init:{} exception:{}", userAgentString, e);
  }
  return this;
 }

 /** The android. */
 protected boolean android;

 /** The mac. */
 protected boolean mac;

 /** The ios. */
 protected boolean ios;

 /** The mobile device. */
 protected boolean mobileDevice;

 /** The computer device. */
 protected boolean computerDevice;

 /** The weixin. */
 protected boolean weixin;

 /**
  * Checks if is android.
  *
  * @return true, if checks if is android
  */
 public boolean isAndroid() {
  return android;
 }

 /**
  * Sets the android.
  *
  * @param android
  *         the android
  */
 public void setAndroid(boolean android) {
  this.android = android;
 }

 /**
  * Checks if is mac.
  *
  * @return true, if checks if is mac
  */
 public boolean isMac() {
  return mac;
 }

 /**
  * Sets the mac.
  *
  * @param mac
  *         the mac
  */
 public void setMac(boolean mac) {
  this.mac = mac;
 }

 /**
  * Checks if is ios.
  *
  * @return true, if checks if is ios
  */
 public boolean isIos() {
  return ios;
 }

 /**
  * Sets the ios.
  *
  * @param ios
  *         the ios
  */
 public void setIos(boolean ios) {
  this.ios = ios;
 }

 /**
  * Checks if is mobile device.
  *
  * @return true, if checks if is mobile device
  */
 public boolean isMobileDevice() {
  return mobileDevice;
 }

 /**
  * Sets the mobile device.
  *
  * @param mobileDevice
  *         the mobile device
  */
 public void setMobileDevice(boolean mobileDevice) {
  this.mobileDevice = mobileDevice;
 }

 /**
  * Checks if is computer device.
  *
  * @return true, if checks if is computer device
  */
 public boolean isComputerDevice() {
  return computerDevice;
 }

 /**
  * Sets the computer device.
  *
  * @param computerDevice
  *         the computer device
  */
 public void setComputerDevice(boolean computerDevice) {
  this.computerDevice = computerDevice;
 }

 /**
  * Gets the user agent string.
  *
  * @return the user agent string
  */
 public String getUserAgentString() {
  return userAgentString;
 }

 /**
  * Sets the user agent string.
  *
  * @param userAgentString
  *         the user agent string
  */
 public void setUserAgentString(String userAgentString) {
  this.userAgentString = userAgentString;
 }

 /**
  * Checks if is weixin.
  *
  * @return true, if checks if is weixin
  */
 public boolean isWeixin() {
  return weixin;
 }

 /**
  * Sets the weixin.
  *
  * @param weixin
  *         the weixin
  */
 public void setWeixin(boolean weixin) {
  this.weixin = weixin;
 }

 /*
  * (non-Javadoc)
  * 
  * @see java.lang.Object#toString()
  */
 @Override
 public String toString() {
  return "UserAgentSupport [userAgentString=" + userAgentString + ", android=" + android + ", mac=" + mac + ", ios=" + ios
   + ", mobileDevice=" + mobileDevice + ", computerDevice=" + computerDevice + ", weixin=" + weixin + "]";
 }

}
