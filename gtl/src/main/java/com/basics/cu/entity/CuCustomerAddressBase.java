package com.basics.cu.entity;
    import java.util.Date;

public class CuCustomerAddressBase extends BaseBean {
    /**
    * 地址ID
    */
    private String id;

    /**
    * 客户ID
    */
    private String customerId;

    /**
    * 联系电话
    */
    private String addressPhone;

    /**
    * 联系姓名
    */
    private String addressName;

    private String location;
    
    /**
    * 省ID
    */
    private String addressProvince;

    /**
    * 市ID
    */
    private String addressCity;

    /**
    * 区域ID
    */
    private String addressArea;

    /**
    * 详细地址
    */
    private String addressInfo;

    /**
    * 是否为默认地址(1是 0否)
    */
    private Integer addressFlag;

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


    public String getId(){
     return this.id;
    }


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
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
    public String getAddressPhone(){
     return this.addressPhone;
    }

    public void setAddressPhone(String addressPhone){
     this.addressPhone=addressPhone;
    }
    public String getAddressName(){
     return this.addressName;
    }

    public void setAddressName(String addressName){
     this.addressName=addressName;
    }
    public String getAddressProvince(){
     return this.addressProvince;
    }

    public void setAddressProvince(String addressProvince){
     this.addressProvince=addressProvince;
    }
    public String getAddressCity(){
     return this.addressCity;
    }

    public void setAddressCity(String addressCity){
     this.addressCity=addressCity;
    }
    public String getAddressArea(){
     return this.addressArea;
    }

    public void setAddressArea(String addressArea){
     this.addressArea=addressArea;
    }
    public String getAddressInfo(){
     return this.addressInfo;
    }

    public void setAddressInfo(String addressInfo){
     this.addressInfo=addressInfo;
    }
    public Integer getAddressFlag(){
     return this.addressFlag;
    }

    public void setAddressFlag(Integer addressFlag){
     this.addressFlag=addressFlag;
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
}