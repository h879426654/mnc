package com.basics.mall.entity;
public class MallProductKindDetailBase extends BaseBean{
    /**
    * 主键
    */
    private String id;

    /**
    * 商品维度主表ID
    */
    private String detailKindId;

    /**
    * 名称
    */
    private String detailName;

    /**
    * 描述
    */
    private String detailDescription;

    /**
    * 维度值
    */
    private String detailKindValue;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getDetailKindId(){
     return this.detailKindId;
    }

    public void setDetailKindId(String detailKindId){
     this.detailKindId=detailKindId;
    }
    public String getDetailName(){
     return this.detailName;
    }

    public void setDetailName(String detailName){
     this.detailName=detailName;
    }
    public String getDetailDescription(){
     return this.detailDescription;
    }

    public void setDetailDescription(String detailDescription){
     this.detailDescription=detailDescription;
    }
    public String getDetailKindValue(){
     return this.detailKindValue;
    }

    public void setDetailKindValue(String detailKindValue){
     this.detailKindValue=detailKindValue;
    }
}