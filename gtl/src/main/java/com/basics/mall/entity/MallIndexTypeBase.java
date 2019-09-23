package com.basics.mall.entity;
    import java.util.Date;
public class MallIndexTypeBase extends BaseBean{
    /**
    * 主键
    */
    private String id;

    /**
    * 模块说明
    */
    private String typeTitle;

    /**
    * 图片地址
    */
    private String typeImg;

    /**
    * 链接地址
    */
    private String typeUrl;

    /**
    * 权重
    */
    private Integer typeSort;

    /**
    * 版本号
    */
    private Integer versionNum;

    /**
    * 是否删除（1是 0否）
    */
    private Integer flagDel;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建人
    */
    private String createUser;

    /**
    * 修改人
    */
    private String modifyUser;

    /**
    * 修改时间
    */
    private Date modifyDate;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getTypeTitle(){
     return this.typeTitle;
    }

    public void setTypeTitle(String typeTitle){
     this.typeTitle=typeTitle;
    }
    public String getTypeImg(){
     return this.typeImg;
    }

    public void setTypeImg(String typeImg){
     this.typeImg=typeImg;
    }
    public String getTypeUrl(){
     return this.typeUrl;
    }

    public void setTypeUrl(String typeUrl){
     this.typeUrl=typeUrl;
    }
    public Integer getTypeSort(){
     return this.typeSort;
    }

    public void setTypeSort(Integer typeSort){
     this.typeSort=typeSort;
    }
    public Integer getVersionNum(){
     return this.versionNum;
    }

    public void setVersionNum(Integer versionNum){
     this.versionNum=versionNum;
    }
    public Integer getFlagDel(){
     return this.flagDel;
    }

    public void setFlagDel(Integer flagDel){
     this.flagDel=flagDel;
    }
    public Date getCreateTime(){
     return this.createTime;
    }

    public void setCreateTime(Date createTime){
     this.createTime=createTime;
    }
    public String getCreateUser(){
     return this.createUser;
    }

    public void setCreateUser(String createUser){
     this.createUser=createUser;
    }
    public String getModifyUser(){
     return this.modifyUser;
    }

    public void setModifyUser(String modifyUser){
     this.modifyUser=modifyUser;
    }
    public Date getModifyDate(){
     return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate){
     this.modifyDate=modifyDate;
    }
}