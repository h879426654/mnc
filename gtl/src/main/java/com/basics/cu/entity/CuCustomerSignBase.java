package com.basics.cu.entity;
    import java.util.Date;
public class CuCustomerSignBase extends BaseBean{
    /**
    * 签到ID
    */
    private String id;

    /**
    * 用户ID
    */
    private String customerId;

    /**
    * 每人奖励数量（积分）
    */
    private Integer signNum;

    /**
    * 签到时间
    */
    private Date signTime;

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
    public String getCustomerId(){
     return this.customerId;
    }

    public void setCustomerId(String customerId){
     this.customerId=customerId;
    }
    public Integer getSignNum(){
     return this.signNum;
    }

    public void setSignNum(Integer signNum){
     this.signNum=signNum;
    }
    public Date getSignTime(){
     return this.signTime;
    }

    public void setSignTime(Date signTime){
     this.signTime=signTime;
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