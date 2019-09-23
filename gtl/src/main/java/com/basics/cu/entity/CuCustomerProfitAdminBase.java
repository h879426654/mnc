package com.basics.cu.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class CuCustomerProfitAdminBase extends BaseBean{
    /**
    * 收益ID
    */
    private String id;

    /**
    * 用户ID
    */
    private String customerId;

    /**
    * 领取收益
    */
    private BigDecimal profitNum;

    /**
    * 收益类型
    */
    private Integer profitType;

    /**
    * 收益状态(1收入 2支出)
    */
    private Integer profitStatus;

    /**
    * 领取时间
    */
    private Date profitHavedTime;

    /**
    * 收益来源
    */
    private String profitSource;

    /**
    * 收益说明
    */
    private String profitRemark;

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
    
    
    private String customerName;
    private String realName;
    private String customerPhone;
    private String userName;
    
    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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
    public BigDecimal getProfitNum(){
     return this.profitNum;
    }

    public void setProfitNum(BigDecimal profitNum){
     this.profitNum=profitNum;
    }
    public Integer getProfitType(){
     return this.profitType;
    }

    public void setProfitType(Integer profitType){
     this.profitType=profitType;
    }
    public Integer getProfitStatus(){
     return this.profitStatus;
    }

    public void setProfitStatus(Integer profitStatus){
     this.profitStatus=profitStatus;
    }
    public Date getProfitHavedTime(){
     return this.profitHavedTime;
    }

    public void setProfitHavedTime(Date profitHavedTime){
     this.profitHavedTime=profitHavedTime;
    }
    public String getProfitSource(){
     return this.profitSource;
    }

    public void setProfitSource(String profitSource){
     this.profitSource=profitSource;
    }
    public String getProfitRemark(){
     return this.profitRemark;
    }

    public void setProfitRemark(String profitRemark){
     this.profitRemark=profitRemark;
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