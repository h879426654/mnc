package com.basics.support;

/**
 * The Class PlaceholderUtils.
 */
public class PlaceholderUtils {

 /**
  * Image placeholder.
  *
  * @param width
  *         the width
  * @param height
  *         the height
  * @return the multipart file
  */
 public static String imagePlaceholderUrl(int width, int height) {
  // http://imageplaceholder.com/?size=350x150
  String url = String.format("http://imageplaceholder.com/?size=%sx%s", width, height);
  return url;
 }
}
