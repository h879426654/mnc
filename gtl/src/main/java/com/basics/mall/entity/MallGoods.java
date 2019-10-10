package com.basics.mall.entity;


import java.math.BigDecimal;
import java.util.Date;


public class MallGoods extends MallGoodsBase{
    public MallGoods id(String id){
        this.setId(id);
        return this;
    }
    public MallGoods advertId(String advertId){
        this.setAdvertId(advertId);
        return this;
    }
    public MallGoods goodsName(String goodsName){
        this.setGoodsName(goodsName);
        return this;
    }
    public MallGoods goodText(String goodText){
        this.setGoodsText(goodText);
        return this;
    }
    public MallGoods money(BigDecimal money){
        this.setMoney(money);
        return this;
    }
    public MallGoods image(String image){
        this.setImage(image);
        return this;
    }
    public MallGoods state(String state){
        this.setState(state);
        return this;
    }
    public MallGoods deFlag(String deFlag){
        this.setDelFlag(deFlag);
        return this;
    }
    public MallGoods pageN(Integer pageN) {
        this.setPageN(pageN);
        return this;
    }
    public MallGoods pageS(Integer pageS) {
        this.setPageS(pageS);
        return this;
    }
    public MallGoods createTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }
    public MallGoods status(String status) {
        this.setStatus(status);
        return this;
    }

}