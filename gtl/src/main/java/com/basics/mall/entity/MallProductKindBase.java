package com.basics.mall.entity;
public class MallProductKindBase extends BaseBean{
    /**
    * 类别主键
    */
    private String id;

    /**
    * 类别名称
    */
    private String productKindName;

    /**
    * 描述
    */
    private String productKindDescription;

    /**
    * 类别拼接权重，值越小越靠前
    */
    private Integer productKindMosaicOrder;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getProductKindName(){
     return this.productKindName;
    }

    public void setProductKindName(String productKindName){
     this.productKindName=productKindName;
    }
    public String getProductKindDescription(){
     return this.productKindDescription;
    }

    public void setProductKindDescription(String productKindDescription){
     this.productKindDescription=productKindDescription;
    }
    public Integer getProductKindMosaicOrder(){
     return this.productKindMosaicOrder;
    }

    public void setProductKindMosaicOrder(Integer productKindMosaicOrder){
     this.productKindMosaicOrder=productKindMosaicOrder;
    }
}