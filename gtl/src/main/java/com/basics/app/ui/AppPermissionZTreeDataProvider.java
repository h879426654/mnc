package com.basics.app.ui;

import java.util.List;

import com.basics.app.entity.AppPermission;
import com.basics.app.service.AppPermissionService;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ztree.ZTreeDataProvider;
import com.basics.support.ztree.ZTreeNode;

public class AppPermissionZTreeDataProvider implements ZTreeDataProvider<AppPermission> {

	AppPermissionService service;

	public AppPermissionZTreeDataProvider(AppPermissionService service) {
		super();
		this.service = service;
	}

	public List<AppPermission> getRoot() {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("state", "1");
		builder.put("parentId", "0");
		return this.service.query(builder.build());
	}

	public boolean hasChildren(AppPermission data) {
		return true;
	}

	public List<AppPermission> getChildren(AppPermission data) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("state", "1");
		builder.put("parentId", data.getId());
		return this.service.query(builder.build());
	}

	public ZTreeNode decorateNode(AppPermission data) {
		ZTreeNode node = new ZTreeNode();
		node.setId(data.getId());
		node.setName(data.getName());
		return node;
	}

}
