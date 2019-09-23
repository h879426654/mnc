package com.basics.sys.entity;
    import java.util.Date;
public class SysActivemqResponseBase extends BaseBean{
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
    * 消息结果
    */
    private String activemqResponse;

    /**
    * 完成时间
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
    public String getActivemqResponse(){
     return this.activemqResponse;
    }

    public void setActivemqResponse(String activemqResponse){
     this.activemqResponse=activemqResponse;
    }
    public Date getCreateDate(){
     return this.createDate;
    }

    public void setCreateDate(Date createDate){
     this.createDate=createDate;
    }
}