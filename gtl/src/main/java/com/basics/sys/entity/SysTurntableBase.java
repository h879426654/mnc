package com.basics.sys.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class SysTurntableBase extends BaseBean{
    /**
    * ID
    */
    private String id;

    /**
    * 奖励类型(1余额 2积分 3链)
    */
    private Integer rewardType;

    /**
    * 奖励数目
    */
    private Integer rewardNum;

    /**
    * 中奖比例
    */
    private BigDecimal rewardRate;

    /**
    * 排序
    */
    private Integer rewardSort;
    
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
    
    private String rewardTypeName;
    
    public Integer getRewardSort() {
		return rewardSort;
	}

	public void setRewardSort(Integer rewardSort) {
		this.rewardSort = rewardSort;
	}

	public String getRewardTypeName() {
		return rewardTypeName;
	}

	public void setRewardTypeName(String rewardTypeName) {
		this.rewardTypeName = rewardTypeName;
	}

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
    public Integer getRewardNum(){
     return this.rewardNum;
    }

    public void setRewardNum(Integer rewardNum){
     this.rewardNum=rewardNum;
    }
    public BigDecimal getRewardRate(){
     return this.rewardRate;
    }

    public void setRewardRate(BigDecimal rewardRate){
     this.rewardRate=rewardRate;
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
}