package com.basics.sys.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class SysProfitBase extends BaseBean{
    /**
    * 收益ID
    */
    private String id;

    /**
    * 收益来源ID
    */
    private String profitSourceId;

    /**
    * 收益来源(1商家申请 2商家升级 3会员消费)
    */
    private Integer profitSource;

    /**
    * 收益值
    */
    private BigDecimal profitNum;

    /**
    * 收益说明
    */
    private String profitRemark;

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
    public String getProfitSourceId(){
     return this.profitSourceId;
    }

    public void setProfitSourceId(String profitSourceId){
     this.profitSourceId=profitSourceId;
    }
    public Integer getProfitSource(){
     return this.profitSource;
    }

    public void setProfitSource(Integer profitSource){
     this.profitSource=profitSource;
    }
    public BigDecimal getProfitNum(){
     return this.profitNum;
    }

    public void setProfitNum(BigDecimal profitNum){
     this.profitNum=profitNum;
    }
    public String getProfitRemark(){
     return this.profitRemark;
    }

    public void setProfitRemark(String profitRemark){
     this.profitRemark=profitRemark;
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