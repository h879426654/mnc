package com.basics.app.entity;
    import java.util.Date;
public class AppLogBase extends BaseBean{
    /**
    * 日志ID
    */
    private String id;

    /**
    * 日志说明
    */
    private String logName;

    /**
    * 日志详情
    */
    private String logContext;

    /**
    * 日志描述
    */
    private String logRemark;

    /**
    * 创建时间
    */
    private Date logCreateDate;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getLogName(){
     return this.logName;
    }

    public void setLogName(String logName){
     this.logName=logName;
    }
    public String getLogContext(){
     return this.logContext;
    }

    public void setLogContext(String logContext){
     this.logContext=logContext;
    }
    public String getLogRemark(){
     return this.logRemark;
    }

    public void setLogRemark(String logRemark){
     this.logRemark=logRemark;
    }
    public Date getLogCreateDate(){
     return this.logCreateDate;
    }

    public void setLogCreateDate(Date logCreateDate){
     this.logCreateDate=logCreateDate;
    }
}