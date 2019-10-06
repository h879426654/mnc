package com.basics.mall.entity;

import java.util.Date;

public class MallAdvertHotBase {
    private String id;

    private String advertId;

    private String imageUrl;

    private String advertName;

    private String count;

    private String distance;

    private String isHot;

    private Date createTime;

    private String advertLatitude;

    private String advertLongitude;

    private String advertAddress;

    private String hot;

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getAdvertLatitude() {
        return advertLatitude;
    }

    public void setAdvertLatitude(String advertLatitude) {
        this.advertLatitude = advertLatitude;
    }

    public String getAdvertLongitude() {
        return advertLongitude;
    }

    public void setAdvertLongitude(String advertLongitude) {
        this.advertLongitude = advertLongitude;
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

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
