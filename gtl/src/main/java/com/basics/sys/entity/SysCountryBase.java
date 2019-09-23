package com.basics.sys.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class SysCountryBase extends BaseBean{
    /**
    * 国家ID
    */
    private String countryId;

    
    private String countryNum;
    

    /**
    * 国家CODE
    */
    private String countryCode;

    /**
    * 国家名字
    */
    private String countryName;

    /**
    * 国家汇率
    */
    private BigDecimal countryRate;
    
    private String countrySymbol;
    
    /**
     * 权重
     */
    private Integer countrySort;

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
    public String getCountryCode(){
     return this.countryCode;
    }

    public void setCountryCode(String countryCode){
     this.countryCode=countryCode;
    }
    public String getCountryName(){
     return this.countryName;
    }

    public void setCountryName(String countryName){
     this.countryName=countryName;
    }
    public BigDecimal getCountryRate(){
     return this.countryRate;
    }

    public void setCountryRate(BigDecimal countryRate){
     this.countryRate=countryRate;
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

	public Integer getCountrySort() {
		return countrySort;
	}

	public void setCountrySort(Integer countrySort) {
		this.countrySort = countrySort;
	}

	public String getCountrySymbol() {
		return countrySymbol;
	}

	public void setCountrySymbol(String countrySymbol) {
		this.countrySymbol = countrySymbol;
	}

	public String getCountryNum() {
		return countryNum;
	}

	public void setCountryNum(String countryNum) {
		this.countryNum = countryNum;
	}

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}