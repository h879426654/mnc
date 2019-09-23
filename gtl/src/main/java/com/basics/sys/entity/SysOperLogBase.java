package com.basics.sys.entity;
    import java.util.Date;
public class SysOperLogBase extends BaseBean{
    /**
    * 日志主键
    */
    private String id;

    /**
    * 方法名称
    */
    private String operMethod;

    /**
    * 登录账号
    */
    private String loginName;

    /**
    * 主机地址
    */
    private String operIp;

    /**
    * 请求参数
    */
    private String operParam;

    /**
    * 操作状态 0正常 1异常
    */
    private Integer operStatus;

    /**
    * 错误消息
    */
    private String errorMsg;

    /**
    * 操作时间
    */
    private Date operTime;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getOperMethod(){
     return this.operMethod;
    }

    public void setOperMethod(String operMethod){
     this.operMethod=operMethod;
    }
    public String getLoginName(){
     return this.loginName;
    }

    public void setLoginName(String loginName){
     this.loginName=loginName;
    }
    public String getOperIp(){
     return this.operIp;
    }

    public void setOperIp(String operIp){
     this.operIp=operIp;
    }
    public String getOperParam(){
     return this.operParam;
    }

    public void setOperParam(String operParam){
     this.operParam=operParam;
    }
    public Integer getOperStatus(){
     return this.operStatus;
    }

    public void setOperStatus(Integer operStatus){
     this.operStatus=operStatus;
    }
    public String getErrorMsg(){
     return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg){
     this.errorMsg=errorMsg;
    }
    public Date getOperTime(){
     return this.operTime;
    }

    public void setOperTime(Date operTime){
     this.operTime=operTime;
    }
}