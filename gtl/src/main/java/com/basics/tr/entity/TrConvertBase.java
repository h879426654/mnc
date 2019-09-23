package com.basics.tr.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class TrConvertBase extends BaseBean{
    /**
    * 兑换ID
    */
    private String id;

    /**
    * 兑换流水号
    */
    private String convertSerial;

    /**
    * 兑换主题
    */
    private String convertName;

    /**
    * 兑换说明
    */
    private String convertRemark;

    /**
    * 兑换数量
    */
    private BigDecimal convertNum;

    /**
    * 总量
    */
    private BigDecimal convertTotalNum;

    /**
    * 开始时间
    */
    private Date convertStartTime;

    /**
    * 结束时间
    */
    private Date convertEndTime;

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
    public String getConvertSerial(){
     return this.convertSerial;
    }

    public void setConvertSerial(String convertSerial){
     this.convertSerial=convertSerial;
    }
    public String getConvertName(){
     return this.convertName;
    }

    public void setConvertName(String convertName){
     this.convertName=convertName;
    }
    public String getConvertRemark(){
     return this.convertRemark;
    }

    public void setConvertRemark(String convertRemark){
     this.convertRemark=convertRemark;
    }
    public BigDecimal getConvertNum(){
     return this.convertNum;
    }

    public void setConvertNum(BigDecimal convertNum){
     this.convertNum=convertNum;
    }
    public BigDecimal getConvertTotalNum(){
     return this.convertTotalNum;
    }

    public void setConvertTotalNum(BigDecimal convertTotalNum){
     this.convertTotalNum=convertTotalNum;
    }
    public Date getConvertStartTime(){
     return this.convertStartTime;
    }

    public void setConvertStartTime(Date convertStartTime){
     this.convertStartTime=convertStartTime;
    }
    public Date getConvertEndTime(){
     return this.convertEndTime;
    }

    public void setConvertEndTime(Date convertEndTime){
     this.convertEndTime=convertEndTime;
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