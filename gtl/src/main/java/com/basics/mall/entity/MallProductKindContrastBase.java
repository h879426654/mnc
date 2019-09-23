package com.basics.mall.entity;
public class MallProductKindContrastBase extends BaseBean{
    /**
    * 商品维度对照ID
    */
    private String id;

    /**
    * 商品主维度ID
    */
    private String kindId;

    /**
    * 商品子维度ID
    */
    private String kindDetailId;

    /**
    * 商品ID
    */
    private String productId;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getKindId(){
     return this.kindId;
    }

    public void setKindId(String kindId){
     this.kindId=kindId;
    }
    public String getKindDetailId(){
     return this.kindDetailId;
    }

    public void setKindDetailId(String kindDetailId){
     this.kindDetailId=kindDetailId;
    }
    public String getProductId(){
     return this.productId;
    }

    public void setProductId(String productId){
     this.productId=productId;
    }
}