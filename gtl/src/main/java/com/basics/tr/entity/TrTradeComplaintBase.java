package com.basics.tr.entity;
    import java.util.Date;
public class TrTradeComplaintBase extends BaseBean{
    /**
    * 投诉ID
    */
    private String id;

    /**
    * 交易ID
    */
    private String tradeId;

    /**
    * 交易类型(1余额 2链)
    */
    private Integer tradeType;

    /**
    * 用户ID
    */
    private String customerId;

    /**
    * 投诉内容
    */
    private String complaintContext;

    /**
    * 投诉状态(1待处理 2交易完成 3交易取消)
    */
    private Integer complaintStatus;

    /**
    * 处理说明
    */
    private String complaintRemark;

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
    public String getTradeId(){
     return this.tradeId;
    }

    public void setTradeId(String tradeId){
     this.tradeId=tradeId;
    }
    public Integer getTradeType(){
     return this.tradeType;
    }

    public void setTradeType(Integer tradeType){
     this.tradeType=tradeType;
    }
    public String getCustomerId(){
     return this.customerId;
    }

    public void setCustomerId(String customerId){
     this.customerId=customerId;
    }
    public String getComplaintContext(){
     return this.complaintContext;
    }

    public void setComplaintContext(String complaintContext){
     this.complaintContext=complaintContext;
    }
    public Integer getComplaintStatus(){
     return this.complaintStatus;
    }

    public void setComplaintStatus(Integer complaintStatus){
     this.complaintStatus=complaintStatus;
    }
    public String getComplaintRemark(){
     return this.complaintRemark;
    }

    public void setComplaintRemark(String complaintRemark){
     this.complaintRemark=complaintRemark;
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