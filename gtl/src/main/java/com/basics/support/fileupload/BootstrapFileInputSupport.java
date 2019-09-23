package com.basics.support.fileupload;

import java.util.HashMap;
import java.util.Map;

/**
 * BootstrapFileInputSupport.
 *
 * @author yuwenfeng
 */
public class BootstrapFileInputSupport {

 /**
  * The Class PreviewConfigItem.
  */
 public static class PreviewConfigItem {
  // {
  // caption: 'desert.jpg',
  // width: '120px',
  // url: 'http://localhost/avatar/delete', // server delete action
  // key: 100,
  // extra: {id: 100}
  // }
  /** The caption. */
  private String caption;

  /** The width. */
  private String width = "120px";

  /** The url. */
  private String url;

  /** The size. */
  private Long size;

  /** The key. */
  private String key = "";

  /** The extra. */
  private Map<String, Object> extra = new HashMap<String, Object>();

  /** The show drag. */
  private boolean showDrag;

  /** The show zoom. */
  private boolean showZoom;

  private boolean showDelete;

  /**
   * Gets the caption.
   *
   * @return the caption
   */
  public String getCaption() {
   return caption;
  }

  /**
   * Sets the caption.
   *
   * @param caption
   *         the new caption
   */
  public void setCaption(String caption) {
   this.caption = caption;
  }

  /**
   * Gets the width.
   *
   * @return the width
   */
  public String getWidth() {
   return width;
  }

  /**
   * Sets the width.
   *
   * @param width
   *         the new width
   */
  public void setWidth(String width) {
   this.width = width;
  }

  /**
   * Gets the url.
   *
   * @return the url
   */
  public String getUrl() {
   return url;
  }

  /**
   * Sets the url.
   *
   * @param url
   *         the new url
   */
  public void setUrl(String url) {
   this.url = url;
  }

  /**
   * Gets the size.
   *
   * @return the size
   */
  public Long getSize() {
   return size;
  }

  /**
   * Sets the size.
   *
   * @param size
   *         the new size
   */
  public void setSize(Long size) {
   this.size = size;
  }

  /**
   * Gets the key.
   *
   * @return the key
   */
  public String getKey() {
   return key;
  }

  /**
   * Sets the key.
   *
   * @param key
   *         the new key
   */
  public void setKey(String key) {
   this.key = key;
  }

  /**
   * Gets the extra.
   *
   * @return the extra
   */
  public Map<String, Object> getExtra() {
   return extra;
  }

  /**
   * Sets the extra.
   *
   * @param extra
   *         the extra
   */
  public void setExtra(Map<String, Object> extra) {
   this.extra = extra;
  }

  /**
   * Checks if is show drag.
   *
   * @return true, if is show drag
   */
  public boolean isShowDrag() {
   return showDrag;
  }

  /**
   * Sets the show drag.
   *
   * @param showDrag
   *         the new show drag
   */
  public void setShowDrag(boolean showDrag) {
   this.showDrag = showDrag;
  }

  public boolean isShowZoom() {
   return showZoom;
  }

  public void setShowZoom(boolean showZoom) {
   this.showZoom = showZoom;
  }

  public boolean isShowDelete() {
   return showDelete;
  }

  public void setShowDelete(boolean showDelete) {
   this.showDelete = showDelete;
  }

 }
}
