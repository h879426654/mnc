
package com.basics.support.ztree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ZFileTreeDataProvider.
 */
public class ZFileTreeDataProvider implements ZTreeDataProvider<File> {

 /** The root. */
 List<File> root = new ArrayList<File>();

 /**
  * The Constructor.
  *
  * @param files
  *         the roots
  */
 public ZFileTreeDataProvider(File... files) {
  super();
  for (File file : files) {
   this.root.add(file);
  }
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.ztree.ZTreeDataProvider#getRoot()
  */
 public List<File> getRoot() {
  return root;
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.ztree.ZTreeDataProvider#hasChildren(java.lang.Object)
  */
 public boolean hasChildren(File data) {
  return data != null && data.isDirectory();
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.ztree.ZTreeDataProvider#getChildren(java.lang.Object)
  */
 public List<File> getChildren(File data) {
  List<File> children = new ArrayList<File>();
  File[] files = data.listFiles();
  if (files != null) {
   for (File file : files) {
    children.add(file);
   }
  }
  return children;
 }

 /*
  * (non-Javadoc)
  * 
  * @see com.basics.support.ztree.ZTreeDataProvider#decorateNode(java.lang.Object)
  */
 public ZTreeNode decorateNode(File data) {
  ZTreeNode node = new ZTreeNode();
  node.setId(data.hashCode() + "");
  node.setName(data.getName());
  node.setPath(data.getAbsolutePath());
  return node;
 }

}
