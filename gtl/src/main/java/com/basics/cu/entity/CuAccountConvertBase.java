package com.basics.cu.entity;
    import java.math.BigDecimal;
import java.util.Date;
public class CuAccountConvertBase extends BaseBean{
    /**
    * 转换ID
    */
    private String id;

    /**
    * 用户ID
    */
    private String customerId;

    /**
    * 转换类型(1余额转积分 2余额出售 3余额转账)
    */
    private Integer convertType;


    /**
    * 转换余额
    */
    private BigDecimal convertMoney;

    /**
    * 转换值
    */
    private BigDecimal convertNum;

    /**
    * 对象ID
    */
    private String sourceId;

    /**
    * 说明
    */
    private String convertRemark;

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

    private String customerPhone;


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
    public Integer getConvertType(){
     return this.convertType;
    }

    public void setConvertType(Integer convertType){
     this.convertType=convertType;
    }
    public BigDecimal getConvertMoney(){
     return this.convertMoney;
    }

    public void setConvertMoney(BigDecimal convertMoney){
     this.convertMoney=convertMoney;
    }
    public BigDecimal getConvertNum(){
     return this.convertNum;
    }

    public void setConvertNum(BigDecimal convertNum){
     this.convertNum=convertNum;
    }
    public String getSourceId(){
     return this.sourceId;
    }

    public void setSourceId(String sourceId){
     this.sourceId=sourceId;
    }
    public String getConvertRemark(){
     return this.convertRemark;
    }

    public void setConvertRemark(String convertRemark){
     this.convertRemark=convertRemark;
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

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

}