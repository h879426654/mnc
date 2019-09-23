package com.basics.support;

import org.apache.commons.lang3.ArrayUtils;

// TODO: Auto-generated Javadoc
/**
 * 全局共享的配置,方便各系统进行集成。.
 * 
 * @author yuwenfeng.
 */
public class ProfileUtils {

 /** The Constant PROFILE_DEV. */
 public static final String PROFILE_DEV = "dev";

 /** The Constant PROFILE_TEST. */
 public static final String PROFILE_TEST = "test";

 /** The Constant PROFILE_PRODUCTION. */
 public static final String PROFILE_PRODUCTION = "production";

 /** The active profiles. */
 private static String[] activeProfiles;

 /** The default profiles. */
 private static String[] defaultProfiles;

 /**
  * Gets the active profiles.
  * 
  * @return the active profiles
  */
 public static String[] getActiveProfiles() {
  return activeProfiles;
 }

 /**
  * Sets the active profiles.
  * 
  * @param activeProfiles
  *         the new active profiles
  */
 public synchronized static void setActiveProfiles(String[] activeProfiles) {
  ProfileUtils.activeProfiles = activeProfiles;
 }

 /**
  * Gets the default profiles.
  * 
  * @return the default profiles
  */
 public static String[] getDefaultProfiles() {
  return defaultProfiles;
 }

 /**
  * Sets the default profiles.
  * 
  * @param defaultProfiles
  *         the new default profiles
  */
 public synchronized static void setDefaultProfiles(String[] defaultProfiles) {
  ProfileUtils.defaultProfiles = defaultProfiles;
 }

 /**
  * Gets the profiles.
  *
  * @return activeProfiles or defaultProfiles or null.
  */
 public synchronized static String[] getProfiles() {
  if (ProfileUtils.activeProfiles != null && ProfileUtils.activeProfiles.length > 0) {
   return ProfileUtils.activeProfiles;
  }
  if (ProfileUtils.defaultProfiles != null && ProfileUtils.defaultProfiles.length > 0) {
   return ProfileUtils.defaultProfiles;
  }
  LogUtils.performance.warn("not profile found.");
  return null;
 }

 /**
  * Contain profile.
  *
  * @param profile
  *         the profile
  * @return true, if successful
  */
 public static boolean containProfile(String profile) {
  if (ProfileUtils.activeProfiles != null && ArrayUtils.contains(ProfileUtils.activeProfiles, profile)) {
   return true;
  }
  if (ProfileUtils.defaultProfiles != null && ArrayUtils.contains(ProfileUtils.defaultProfiles, profile)) {
   return true;
  }
  return false;
 }

 /**
  * Checks if is dev.
  *
  * @return true, if is dev
  */
 public static boolean isDev() {
  return ProfileUtils.containProfile(ProfileUtils.PROFILE_DEV);
 }

 /**
  * Checks if is test.
  *
  * @return true, if is test
  */
 public static boolean isTest() {
  return ProfileUtils.containProfile(ProfileUtils.PROFILE_TEST);
 }

 /**
  * Checks if is production.
  *
  * @return true, if is production
  */
 public static boolean isProduction() {
  return ProfileUtils.containProfile(ProfileUtils.PROFILE_PRODUCTION);
 }

 /**
  * Active dev.
  */
 public static void activeDev() {
  ProfileUtils.setActiveProfiles(new String[] { ProfileUtils.PROFILE_DEV });
 }

 /**
  * Active test.
  */
 public static void activeTest() {
  ProfileUtils.setActiveProfiles(new String[] { ProfileUtils.PROFILE_TEST });
 }

 /**
  * Active production.
  */
 public static void activeProduction() {
  ProfileUtils.setActiveProfiles(new String[] { ProfileUtils.PROFILE_PRODUCTION });
 }
}
