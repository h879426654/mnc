package com.basics.app.entity;

import com.basics.app.shiro.PermissionSupport;

/**
 * AppPermission
 *
 * @author yuwenfeng.
 *
 */
public class AppPermissionBase extends PermissionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1L;

	/**
	 * 权限ID.
	 */
	private String id;

	/**
	 * 上级权限ID.
	 */
	private String parentId;

	/**
	 * 权限标识.
	 */
	private String code;

	/**
	 * 权限名称.
	 */
	private String name;

	/**
	 * 英文
	 */
	private String permissionEnglishName;

	/**
	* 韩语
	*/
	private String permissionKoreanName;

	/**
	* 日语
	*/
	private String permissionJapaneseName;

	/**
	 * 权限描述.
	 */
	private String comment;

	/**
	 * 权限级排序.
	 */
	private Long order;

	/**
	 * 菜单图标.
	 */
	private String icon;

	/**
	 * 菜单URL.
	 */
	private String url;

	/**
	 * 权限类型 0=系统 1=目录 2=菜单 3=按钮.
	 */
	private Integer type;

	/**
	 * 权限状态 0启用 1停用.
	 */
	private Integer state;

	/**
	 * 权限ID.
	 * 
	 * @return 权限ID.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 权限ID.
	 * 
	 * @param id
	 *         权限ID.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 上级权限ID.
	 * 
	 * @return 上级权限ID.
	 */
	public String getParentId() {
		return this.parentId;
	}

	/**
	 * 上级权限ID.
	 * 
	 * @param parentId
	 *         上级权限ID.
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 权限标识.
	 * 
	 * @return 权限标识.
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 权限标识.
	 * 
	 * @param code
	 *         权限标识.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 权限名称.
	 * 
	 * @return 权限名称.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 权限名称.
	 * 
	 * @param name
	 *         权限名称.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 权限描述.
	 * 
	 * @return 权限描述.
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * 权限描述.
	 * 
	 * @param comment
	 *         权限描述.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 权限级排序.
	 * 
	 * @return 权限级排序.
	 */
	public Long getOrder() {
		return this.order;
	}

	/**
	 * 权限级排序.
	 * 
	 * @param order
	 *         权限级排序.
	 */
	public void setOrder(Long order) {
		this.order = order;
	}

	/**
	 * 菜单图标.
	 * 
	 * @return 菜单图标.
	 */
	public String getIcon() {
		return this.icon;
	}

	/**
	 * 菜单图标.
	 * 
	 * @param icon
	 *         菜单图标.
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 菜单URL.
	 * 
	 * @return 菜单URL.
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 菜单URL.
	 * 
	 * @param url
	 *         菜单URL.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 权限类型 0=系统 1=目录 2=菜单 3=按钮.
	 * 
	 * @return 权限类型 0=系统 1=目录 2=菜单 3=按钮.
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 权限类型 0=系统 1=目录 2=菜单 3=按钮.
	 * 
	 * @param type
	 *         权限类型 0=系统 1=目录 2=菜单 3=按钮.
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 权限状态 0启用 1停用.
	 * 
	 * @return 权限状态 0启用 1停用.
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * 权限状态 0启用 1停用.
	 * 
	 * @param state
	 *         权限状态 0启用 1停用.
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	public String getPermissionEnglishName() {
		return permissionEnglishName;
	}

	public void setPermissionEnglishName(String permissionEnglishName) {
		this.permissionEnglishName = permissionEnglishName;
	}

	public String getPermissionKoreanName() {
		return permissionKoreanName;
	}

	public void setPermissionKoreanName(String permissionKoreanName) {
		this.permissionKoreanName = permissionKoreanName;
	}

	public String getPermissionJapaneseName() {
		return permissionJapaneseName;
	}

	public void setPermissionJapaneseName(String permissionJapaneseName) {
		this.permissionJapaneseName = permissionJapaneseName;
	}

}
