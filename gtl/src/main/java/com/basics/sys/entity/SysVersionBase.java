package com.basics.sys.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class SysVersionBase extends BaseBean {
	/**
	 * 版本ID
	 */
	private String id;

	/**
	 * 版本名
	 */
	private String versionName;

	/**
	 * 版本类型
	 */
	private Integer versionType;

	/**
	 * 更新内容
	 */
	private String versionContext;

	/**
	 * 版本号
	 */
	private String versionCode;

	/**
	 * 版本
	 */
	private String versionNum;

	/**
	 * 下载地址
	 */
	private String versionUrl;

	/**
	 * 是否删除（1是 0否）
	 */
	private Integer flagDel;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Integer getVersionType() {
		return this.versionType;
	}

	public void setVersionType(Integer versionType) {
		this.versionType = versionType;
	}

	public String getVersionContext() {
		return this.versionContext;
	}

	public void setVersionContext(String versionContext) {
		this.versionContext = versionContext;
	}

	public String getVersionCode() {
		return this.versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getVersionUrl() {
		return this.versionUrl;
	}

	public void setVersionUrl(String versionUrl) {
		this.versionUrl = versionUrl;
	}

	public Integer getFlagDel() {
		return this.flagDel;
	}

	public void setFlagDel(Integer flagDel) {
		this.flagDel = flagDel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}