package com.basics.cu.controller.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.basics.sys.entity.SysNotice;

public class IndexViewResponse implements Serializable {

	private static final long serialVersionUID = 6067619838015837845L;
	
	private List<BaseImgResponse> banners = new ArrayList<>();
	
	private List<SysNotice> notices = new ArrayList<>();
	
	private Map<String,Object> tables = new HashMap<>();

	public List<BaseImgResponse> getBanners() {
		return banners;
	}

	public void setBanners(List<BaseImgResponse> banners) {
		this.banners = banners;
	}

	public List<SysNotice> getNotices() {
		return notices;
	}

	public void setNotices(List<SysNotice> notices) {
		this.notices = notices;
	}

	public Map<String, Object> getTables() {
		return tables;
	}

	public void setTables(Map<String, Object> tables) {
		this.tables = tables;
	}
	
	
	
	
	

}
