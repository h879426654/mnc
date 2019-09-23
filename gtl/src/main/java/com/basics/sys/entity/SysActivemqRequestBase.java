package com.basics.sys.entity;
    import java.util.Date;
public class SysActivemqRequestBase extends BaseBean{
    /**
    * 消息ID
    */
    private String id;

    /**
    * 会员ID
    */
    private String customerId;

    /**
    * 消息名称
    */
    private String activemqType;

    /**
    * 消息内容
    */
    private String activemqContext;

    /**
    * 消息描述
    */
    private String activemqRemark;

    /**
    * 创建时间
    */
    private Date createDate;


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
    public String getActivemqType(){
     return this.activemqType;
    }

    public void setActivemqType(String activemqType){
     this.activemqType=activemqType;
    }
    public String getActivemqContext(){
     return this.activemqContext;
    }

    public void setActivemqContext(String activemqContext){
     this.activemqContext=activemqContext;
    }
    public String getActivemqRemark(){
     return this.activemqRemark;
    }

    public void setActivemqRemark(String activemqRemark){
     this.activemqRemark=activemqRemark;
    }
    public Date getCreateDate(){
     return this.createDate;
    }

    public void setCreateDate(Date createDate){
     this.createDate=createDate;
    }
}