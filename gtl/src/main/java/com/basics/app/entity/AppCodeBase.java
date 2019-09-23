package com.basics.app.entity;
    import java.util.Date;
public class AppCodeBase extends BaseBean{
    /**
    * 短信ID
    */
    private String id;

    /**
    * 验证码类型1:注册 2:忘记密码 3修改密码
    */
    private Integer codeType;

    /**
    * 手机号
    */
    private String codeMobile;

    /**
    * 短信验证码
    */
    private String codeCode;

    /**
    * 验证码是否有效
    */
    private Integer codeState;

    /**
    * 验证码内容
    */
    private String codeText;

    /**
    * 验证码生成时间
    */
    private Date codeCreateTime;

    /**
    * 验证码过期时间
    */
    private Date codeExpirationTime;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public Integer getCodeType(){
     return this.codeType;
    }

    public void setCodeType(Integer codeType){
     this.codeType=codeType;
    }
    public String getCodeMobile(){
     return this.codeMobile;
    }

    public void setCodeMobile(String codeMobile){
     this.codeMobile=codeMobile;
    }
    public String getCodeCode(){
     return this.codeCode;
    }

    public void setCodeCode(String codeCode){
     this.codeCode=codeCode;
    }
    public Integer getCodeState(){
     return this.codeState;
    }

    public void setCodeState(Integer codeState){
     this.codeState=codeState;
    }
    public String getCodeText(){
     return this.codeText;
    }

    public void setCodeText(String codeText){
     this.codeText=codeText;
    }
    public Date getCodeCreateTime(){
     return this.codeCreateTime;
    }

    public void setCodeCreateTime(Date codeCreateTime){
     this.codeCreateTime=codeCreateTime;
    }
    public Date getCodeExpirationTime(){
     return this.codeExpirationTime;
    }

    public void setCodeExpirationTime(Date codeExpirationTime){
     this.codeExpirationTime=codeExpirationTime;
    }
}