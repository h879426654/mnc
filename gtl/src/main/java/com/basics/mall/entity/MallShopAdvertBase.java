package com.basics.mall.entity;

import java.util.Date;

public class MallShopAdvertBase extends BaseBean{
    private String id;
    private String customerId;
    private String advertName;
    private String advertContext;
    private String advertImage;
    private String shopLicence;
    private String shopVideo;
    private String advertPhone;
    private String advertAddress;
    private String advertLongitude;
    private String advertLatitude;
    private String applyStatus;
    private String applyContext;
    private String flagDel;
    private Date createTime;
    private int advertSale;
    private String city;
    private String region;
    private String customerPhone;

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getAdvertAddress() {
        return advertAddress;
    }

    public void setAdvertAddress(String advertAddress) {
        this.advertAddress = advertAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getAdvertContext() {
        return advertContext;
    }

    public void setAdvertContext(String advertContext) {
        this.advertContext = advertContext;
    }

    public String getAdvertImage() {
        return advertImage;
    }

    public void setAdvertImage(String advertImage) {
        this.advertImage = advertImage;
    }

    public String getShopLicence() {
        return shopLicence;
    }

    public void setShopLicence(String shopLicence) {
        this.shopLicence = shopLicence;
    }

    public String getShopVideo() {
        return shopVideo;
    }

    public void setShopVideo(String shopVideo) {
        this.shopVideo = shopVideo;
    }

    public String getAdvertPhone() {
        return advertPhone;
    }

    public void setAdvertPhone(String advertPhone) {
        this.advertPhone = advertPhone;
    }

    public String getAdvertLongitude() {
        return advertLongitude;
    }

    public void setAdvertLongitude(String advertLongitude) {
        this.advertLongitude = advertLongitude;
    }

    public String getAdvertLatitude() {
        return advertLatitude;
    }

    public void setAdvertLatitude(String advertLatitude) {
        this.advertLatitude = advertLatitude;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyContext() {
        return applyContext;
    }

    public void setApplyContext(String applyContext) {
        this.applyContext = applyContext;
    }

    public String getFlagDel() {
        return flagDel;
    }

    public void setFlagDel(String flagDel) {
        this.flagDel = flagDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getAdvertSale() {
        return advertSale;
    }

    public void setAdvertSale(int advertSale) {
        this.advertSale = advertSale;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}