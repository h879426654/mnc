package com.basics.support.ztree;

import java.util.List;

/**
 * ZTreeDataProvider.
 *
 * @param <Data>
 *         the generic type
 */
public interface ZTreeDataProvider<Data> {

 /**
  * Gets the root.
  *
  * @return the root
  */
 public List<Data> getRoot();

 /**
  * Checks for children.
  *
  * @param data
  *         the data
  * @return true, if checks for children
  */
 public boolean hasChildren(Data data);

 /**
  * Gets the children.
  *
  * @param data
  *         the data
  * @return the children
  */
 public List<Data> getChildren(Data data);

 /**
  * Decorate node.
  *
  * @param data
  *         the data
  * @return the z tree node
  */
 public ZTreeNode decorateNode(Data data);

}
