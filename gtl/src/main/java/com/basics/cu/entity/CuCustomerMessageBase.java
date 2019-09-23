package com.basics.cu.entity;
    import java.util.Date;

public class CuCustomerMessageBase extends BaseBean {
    /**
    * 消息ID
    */
    private String id;

    /**
    * 用户ID
    */
    private String customerId;

    /**
    * 系统公告ID
    */
    private String appMessageId;

    /**
    * 消息标题
    */
    private String messageTitle;

    /**
    * 消息内容
    */
    private String messageContext;

    /**
    * 是否已读(0未读 1已读)
    */
    private Integer flagRead;

    /**
     * 消息类型(1.系统消息 2.交易消息)
     */
    private Integer messageType;
    
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
    public String getAppMessageId(){
     return this.appMessageId;
    }

    public void setAppMessageId(String appMessageId){
     this.appMessageId=appMessageId;
    }
    public String getMessageTitle(){
     return this.messageTitle;
    }

    public void setMessageTitle(String messageTitle){
     this.messageTitle=messageTitle;
    }
    public String getMessageContext(){
     return this.messageContext;
    }

    public void setMessageContext(String messageContext){
     this.messageContext=messageContext;
    }
    public Integer getFlagRead(){
     return this.flagRead;
    }

    public void setFlagRead(Integer flagRead){
     this.flagRead=flagRead;
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

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
}