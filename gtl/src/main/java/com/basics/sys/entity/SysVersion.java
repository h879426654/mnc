package com.basics.sys.entity;

import java.util.Date;

public class SysVersion extends SysVersionBase {
	/**
	 * 版本ID
	 */
	public SysVersion id(String id) {
		this.setId(id);
		return this;
	}

	/**
	 * 版本名
	 */
	public SysVersion versionName(String versionName) {
		this.setVersionName(versionName);
		return this;
	}

	/**
	 * 版本类型
	 */
	public SysVersion versionType(Integer versionType) {
		this.setVersionType(versionType);
		return this;
	}

	/**
	 * 更新内容
	 */
	public SysVersion versionContext(String versionContext) {
		this.setVersionContext(versionContext);
		return this;
	}

	/**
	 * 版本号
	 */
	public SysVersion versionCode(String versionCode) {
		this.setVersionCode(versionCode);
		return this;
	}

	/**
	 * 版本
	 */
	public SysVersion versionNum(String versionNum) {
		this.setVersionNum(versionNum);
		return this;
	}

	/**
	 * 下载地址
	 */
	public SysVersion versionUrl(String versionUrl) {
		this.setVersionUrl(versionUrl);
		return this;
	}

	/**
	 * 是否删除（1是 0否）
	 */
	public SysVersion flagDel(Integer flagDel) {
		this.setFlagDel(flagDel);
		return this;
	}

	/**
	 * 创建时间
	 */
	public SysVersion createTime(Date createTime) {
		this.setCreateTime(createTime);
		return this;
	}
}