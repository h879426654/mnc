package com.basics.app.ui;

import java.util.List;

import com.basics.app.entity.AppRole;
import com.basics.app.service.AppRoleService;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ztree.ZTreeDataProvider;
import com.basics.support.ztree.ZTreeNode;

public class AppRoleZTreeDataProvider implements ZTreeDataProvider<AppRole> {

 AppRoleService service;

 public AppRoleZTreeDataProvider(AppRoleService service) {
  super();
  this.service = service;
 }

 public List<AppRole> getRoot() {
  QueryFilterBuilder builder = new QueryFilterBuilder();
  builder.put("state", "1");
  return this.service.query(builder.build());
 }

 public boolean hasChildren(AppRole data) {
  return false;
 }

 public List<AppRole> getChildren(AppRole data) {
  return null;
 }

 public ZTreeNode decorateNode(AppRole data) {
  ZTreeNode node = new ZTreeNode();
  node.setId(data.getId());
  node.setName(data.getName());
  return node;
 }

}
