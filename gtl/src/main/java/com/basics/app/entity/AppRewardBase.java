package com.basics.app.entity;
    import java.util.Date;
public class AppRewardBase extends BaseBean{
    /**
    * 奖励ID
    */
    private String id;

    /**
    * 奖励类型（1实名认证 2商家奖励 3晋升奖励）
    */
    private Integer rewardType;

    /**
    * 矿机ID
    */
    private String machineId;

    /**
    * 矿机数量
    */
    private Integer machineNum;

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
    public Integer getRewardType(){
     return this.rewardType;
    }

    public void setRewardType(Integer rewardType){
     this.rewardType=rewardType;
    }
    public String getMachineId(){
     return this.machineId;
    }

    public void setMachineId(String machineId){
     this.machineId=machineId;
    }
    public Integer getMachineNum(){
     return this.machineNum;
    }

    public void setMachineNum(Integer machineNum){
     this.machineNum=machineNum;
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