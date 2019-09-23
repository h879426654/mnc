package com.basics.support.ztree;

import java.util.List;

/**
 * ZTreeNode.
 */
public class ZTreeNode {

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The url. */
	private String url;

	/** The path. */
	private String path;

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
	 *         the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** The children. */
	private List<ZTreeNode> children = null;
	// 1)、如果需要初始化默认节点被勾选，请设置 treeNode.checked 属性，详细请参见 API 文档中的相关内容
	/** The checked. */
	private boolean checked;
	// 2)、如果某节点禁用 checkbox，请设置 treeNode.chkDisabled 属性，详细请参见 API 文档中的相关内容 和
	// 'chkDisabled 演示'
	/** The chk disabled. */
	private boolean chkDisabled;
	// 3)、如果某节点不显示 checkbox，请设置 treeNode.nocheck 属性，详细请参见 API 文档中的相关内容 和 'nocheck
	// 演示'
	/** The nocheck. */
	private boolean nocheck;

	// 4)、如果更换 checked 属性，请参考 API 文档中 setting.data.key.checked 的详细说明
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *         the id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *         the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<ZTreeNode> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 *
	 * @param children
	 *         the children
	 */
	public void setChildren(List<ZTreeNode> children) {
		this.children = children;
	}

	/**
	 * Checks if is checked.
	 *
	 * @return true, if checks if is checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the checked.
	 *
	 * @param checked
	 *         the checked
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * Checks if is chk disabled.
	 *
	 * @return true, if checks if is chk disabled
	 */
	public boolean isChkDisabled() {
		return chkDisabled;
	}

	/**
	 * Sets the chk disabled.
	 *
	 * @param chkDisabled
	 *         the chk disabled
	 */
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	/**
	 * Checks if is nocheck.
	 *
	 * @return true, if checks if is nocheck
	 */
	public boolean isNocheck() {
		return nocheck;
	}

	/**
	 * Sets the nocheck.
	 *
	 * @param nocheck
	 *         the nocheck
	 */
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path
	 *         the path
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
