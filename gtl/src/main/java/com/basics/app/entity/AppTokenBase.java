package com.basics.app.entity;
    import java.util.Date;
public class AppTokenBase extends BaseBean{
    /**
    * 令牌ID
    */
    private String id;

    /**
    * 令牌创建时间
    */
    private Date tokenCreateTime;

    /**
    * 令牌过期时间
    */
    private Date tokenExpirationTime;

    /**
    * 令牌所属用户ID
    */
    private String userId;

    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public Date getTokenCreateTime(){
     return this.tokenCreateTime;
    }

    public void setTokenCreateTime(Date tokenCreateTime){
     this.tokenCreateTime=tokenCreateTime;
    }
    public Date getTokenExpirationTime(){
     return this.tokenExpirationTime;
    }

    public void setTokenExpirationTime(Date tokenExpirationTime){
     this.tokenExpirationTime=tokenExpirationTime;
    }
    public String getUserId(){
     return this.userId;
    }

    public void setUserId(String userId){
     this.userId=userId;
    }
}