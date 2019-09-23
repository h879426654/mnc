package com.basics.app.entity;
    import java.math.BigDecimal;
public class AppAreaBase extends BaseBean{
    /**
    * 地区ID
    */
    private String id;

    /**
    * 上级地区
    */
    private String areaParentId;

    /**
    * 地区标识
    */
    private String areaCode;

    /**
    * 地区名称
    */
    private String areaName;

    /**
    * 地区描述
    */
    private String areaComment;

    /**
    * 地区排序
    */
    private BigDecimal areaOrder;

    /**
    * 地区图标
    */
    private String areaIcon;

    /**
    * 地区URL
    */
    private String areaUrl;

    /**
    * 地区类型 0省 10市 100区
    */
    private Integer areaType;

    /**
    * 地区状态
    */
    private Integer areaFlag;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getAreaParentId(){
     return this.areaParentId;
    }

    public void setAreaParentId(String areaParentId){
     this.areaParentId=areaParentId;
    }
    public String getAreaCode(){
     return this.areaCode;
    }

    public void setAreaCode(String areaCode){
     this.areaCode=areaCode;
    }
    public String getAreaName(){
     return this.areaName;
    }

    public void setAreaName(String areaName){
     this.areaName=areaName;
    }
    public String getAreaComment(){
     return this.areaComment;
    }

    public void setAreaComment(String areaComment){
     this.areaComment=areaComment;
    }
    public BigDecimal getAreaOrder(){
     return this.areaOrder;
    }

    public void setAreaOrder(BigDecimal areaOrder){
     this.areaOrder=areaOrder;
    }
    public String getAreaIcon(){
     return this.areaIcon;
    }

    public void setAreaIcon(String areaIcon){
     this.areaIcon=areaIcon;
    }
    public String getAreaUrl(){
     return this.areaUrl;
    }

    public void setAreaUrl(String areaUrl){
     this.areaUrl=areaUrl;
    }
    public Integer getAreaType(){
     return this.areaType;
    }

    public void setAreaType(Integer areaType){
     this.areaType=areaType;
    }
    public Integer getAreaFlag(){
     return this.areaFlag;
    }

    public void setAreaFlag(Integer areaFlag){
     this.areaFlag=areaFlag;
    }
}