package com.basics.app.ui;

import java.util.List;

import com.basics.app.entity.AppUser;
import com.basics.app.service.AppUserService;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ztree.ZTreeDataProvider;
import com.basics.support.ztree.ZTreeNode;

public class AppUserZTreeDataProvider implements ZTreeDataProvider<AppUser> {

 QueryFilterBuilder builder;

 AppUserService service;

 public AppUserZTreeDataProvider(AppUserService service, QueryFilterBuilder builder) {
  super();
  this.builder = builder;
  this.service = service;
 }

 public List<AppUser> getRoot() {
  return service.query(builder.build());
 }

 public boolean hasChildren(AppUser data) {
  return false;
 }

 public List<AppUser> getChildren(AppUser data) {
  return null;
 }

 public ZTreeNode decorateNode(AppUser data) {
  ZTreeNode node = new ZTreeNode();
  node.setId(data.getId());
  node.setName(data.getName());
  return node;
 }
}
