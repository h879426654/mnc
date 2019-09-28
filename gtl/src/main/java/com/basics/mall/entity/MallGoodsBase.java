package com.basics.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MallGoodsBase {
    private String id;

    private String advertId;

    private String goodsName;

    private String goodsText;

    private BigDecimal money;

    private String image;

    private String state;

    private String delFlag;

    private Integer pageN;
    private Integer pageS;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPageN() {
        return pageN;
    }

    public void setPageN(Integer pageN) {
        this.pageN = pageN;
    }

    public Integer getPageS() {
        return pageS;
    }

    public void setPageS(Integer pageS) {
        this.pageS = pageS;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsText() {
        return goodsText;
    }

    public void setGoodsText(String goodsText) {
        this.goodsText = goodsText;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
