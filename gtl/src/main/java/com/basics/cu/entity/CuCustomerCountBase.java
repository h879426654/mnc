package com.basics.cu.entity;
    import java.util.Date;
public class CuCustomerCountBase extends BaseBean{
    /**
    * 统计ID
    */
    private String id;

    /**
    * 会员级别
    */
    private String customerLevelId;

    /**
     * 等级是否自动变化(1.是 0.否)
     */
    private Integer flagLevelAuto;
    
    /**
    * 直推人数
    */
    private Integer salfNum;

    /**
    * 团队人数
    */
    private Integer teamNum;

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
    private Date modifyTime;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getCustomerLevelId(){
     return this.customerLevelId;
    }

    public void setCustomerLevelId(String customerLevelId){
     this.customerLevelId=customerLevelId;
    }
    public Integer getSalfNum(){
     return this.salfNum;
    }

    public void setSalfNum(Integer salfNum){
     this.salfNum=salfNum;
    }
    public Integer getTeamNum(){
     return this.teamNum;
    }

    public void setTeamNum(Integer teamNum){
     this.teamNum=teamNum;
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
    public Date getModifyTime(){
     return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime){
     this.modifyTime=modifyTime;
    }

	public Integer getFlagLevelAuto() {
		return flagLevelAuto;
	}

	public void setFlagLevelAuto(Integer flagLevelAuto) {
		this.flagLevelAuto = flagLevelAuto;
	}
    
}