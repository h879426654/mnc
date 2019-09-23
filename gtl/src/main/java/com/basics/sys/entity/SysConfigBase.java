package com.basics.sys.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class SysConfigBase extends BaseBean{
    /**
    * 配置ID
    */
    private String id;

    /**
    * 配置
    */
    private String configCode;

    /**
    * 配置名
    */
    private String configName;

    /**
    * 配置值
    */
    private BigDecimal configValue;

    /**
    * 是否启用
    */
    private Integer configFlag;

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
    public String getConfigCode(){
     return this.configCode;
    }

    public void setConfigCode(String configCode){
     this.configCode=configCode;
    }
    public String getConfigName(){
     return this.configName;
    }

    public void setConfigName(String configName){
     this.configName=configName;
    }
    public BigDecimal getConfigValue(){
     return this.configValue;
    }

    public void setConfigValue(BigDecimal configValue){
     this.configValue=configValue;
    }
    public Integer getConfigFlag(){
     return this.configFlag;
    }

    public void setConfigFlag(Integer configFlag){
     this.configFlag=configFlag;
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