package com.basics.sys.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class SysMarketValueBase extends BaseBean{
    /**
    * 主键
    */
    private String id;

    /**
    * 平台币与美元间的汇率
    */
    private BigDecimal dollarRate;

    /**
    * 人民币转BLC
    */
    private BigDecimal rmbRate;

    /**
    * 时间
    */
    private Date rateTime;

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
    public BigDecimal getDollarRate(){
     return this.dollarRate;
    }

    public void setDollarRate(BigDecimal dollarRate){
     this.dollarRate=dollarRate;
    }
    public BigDecimal getRmbRate(){
     return this.rmbRate;
    }

    public void setRmbRate(BigDecimal rmbRate){
     this.rmbRate=rmbRate;
    }
    public Date getRateTime(){
     return this.rateTime;
    }

    public void setRateTime(Date rateTime){
     this.rateTime=rateTime;
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