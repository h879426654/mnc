package com.basics.support.ztree;

import java.util.ArrayList;
import java.util.List;

/**
 * ZTreeBuilder.
 *
 * @param <T>
 *         the generic type
 */
public class ZTreeBuilder<T> {

 /**
  * Builds the ZTree.
  *
  * @param dataProvider
  *         the data provider
  * @return the list< z tree node>
  */
 public List<ZTreeNode> build(ZTreeDataProvider<T> dataProvider) {
  List<ZTreeNode> nodes = new ArrayList<ZTreeNode>();
  List<T> datas = dataProvider.getRoot();
  for (T data : datas) {
   ZTreeNode node = dataProvider.decorateNode(data);
   nodes.add(node);
   buildChildren(dataProvider, node, data);
  }
  return nodes;
 }

 /**
  * Builds the children.
  *
  * @param dataProvider
  *         the data provider
  * @param node
  *         the node
  * @param data
  *         the data
  */
 private void buildChildren(ZTreeDataProvider<T> dataProvider, ZTreeNode node, T data) {
  if (dataProvider.hasChildren(data)) {
   List<T> childrens = dataProvider.getChildren(data);
   if (childrens != null && !childrens.isEmpty()) {
    List<ZTreeNode> childrenNodes = new ArrayList<ZTreeNode>();
    node.setChildren(childrenNodes);
    for (T children : childrens) {
     ZTreeNode childrenNode = dataProvider.decorateNode(children);
     childrenNodes.add(childrenNode);
     buildChildren(dataProvider, childrenNode, children);
    }
   }
  }
 }
}
