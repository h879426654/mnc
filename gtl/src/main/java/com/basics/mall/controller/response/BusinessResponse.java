package com.basics.mall.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class BusinessResponse implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8578801641340429494L;

	/**
    * 商家名称
    */
    private String businessName;

    /**
    * 商家等级
    */
    private String levelName;

    /**
    * 商家信誉
    */
    private BigDecimal businessStar;

    /**
    * 商家图片
    */
    private String businessImg;

    /**
    * 商家编号
    */
    private Integer businessNumber;

    /**
    * 商家状态(1营业 2停业)
    */
    private Integer businessStatus;

    /**
    * 商家折扣比例
    */
    private BigDecimal businessDiscount;


    /**
    * 经营范围
    */
    private String businessService;


    /**
    * 市ID
    */
    private String areaName;

 
    /**
    * 商家详细地址
    */
    private String detailAddress;


	public String getBusinessName() {
		return businessName;
	}


	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	public String getLevelName() {
		return levelName;
	}


	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	public BigDecimal getBusinessStar() {
		return businessStar;
	}


	public void setBusinessStar(BigDecimal businessStar) {
		this.businessStar = businessStar;
	}


	public String getBusinessImg() {
		return businessImg;
	}


	public void setBusinessImg(String businessImg) {
		this.businessImg = businessImg;
	}


	public Integer getBusinessNumber() {
		return businessNumber;
	}


	public void setBusinessNumber(Integer businessNumber) {
		this.businessNumber = businessNumber;
	}


	public Integer getBusinessStatus() {
		return businessStatus;
	}


	public void setBusinessStatus(Integer businessStatus) {
		this.businessStatus = businessStatus;
	}


	public BigDecimal getBusinessDiscount() {
		return businessDiscount;
	}


	public void setBusinessDiscount(BigDecimal businessDiscount) {
		this.businessDiscount = businessDiscount;
	}


	public String getBusinessService() {
		return businessService;
	}


	public void setBusinessService(String businessService) {
		this.businessService = businessService;
	}


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public String getDetailAddress() {
		return detailAddress;
	}


	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}


}
