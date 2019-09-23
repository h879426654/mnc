package com.basics.mall.entity;
    import java.util.Date;
public class MallShopAdvertBase extends BaseBean{
    /**
    * 商家ID
    */
    private String id;
    
    /**
     * 用户ID
     */
    private String customerId;
    
    /**
     * 国家ID
     */
    private String countryId;

    /**
    * 商家名称
    */
    private String advertName;

    /**
    * 商家介绍
    */
    private String advertContext;

    /**
    * 商家封面图片
    */
    private String advertImage;
    
    /**
     * 商家营业执照
     */
    private String shopLicence;
    
    /**
     * 商家视频
     */
    private String shopVideo;

    /**
    * 商家分类ID
    */
    private String advertClassifyId;

    /**
    * 联系方式
    */
    private String advertPhone;

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
    * 地址
    */
    private String advertAddress;

    /**
    * 经度
    */
    private String advertLongitude;

    /**
    * 纬度
    */
    private String advertLatitude;
    
    private Integer applyStatus;
    private String applyContext;

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
    private String customerPhone;
    
    private String advertClassifyName;
    private String addressProvinceName;
    private String addressCityName;
    private String addressAreaName;
    
    public String getShopLicence() {
		return shopLicence;
	}

	public void setShopLicence(String shopLicence) {
		this.shopLicence = shopLicence;
	}

	public String getAddressProvinceName() {
		return addressProvinceName;
	}

	public void setAddressProvinceName(String addressProvinceName) {
		this.addressProvinceName = addressProvinceName;
	}

	public String getAddressCityName() {
		return addressCityName;
	}

	public void setAddressCityName(String addressCityName) {
		this.addressCityName = addressCityName;
	}

	public String getAddressAreaName() {
		return addressAreaName;
	}

	public void setAddressAreaName(String addressAreaName) {
		this.addressAreaName = addressAreaName;
	}

	public String getAdvertClassifyName() {
		return advertClassifyName;
	}

	public void setAdvertClassifyName(String advertClassifyName) {
		this.advertClassifyName = advertClassifyName;
	}

	public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getAdvertName(){
     return this.advertName;
    }

    public void setAdvertName(String advertName){
     this.advertName=advertName;
    }
    public String getAdvertContext(){
     return this.advertContext;
    }

    public void setAdvertContext(String advertContext){
     this.advertContext=advertContext;
    }
    public String getAdvertImage(){
     return this.advertImage;
    }

    public void setAdvertImage(String advertImage){
     this.advertImage=advertImage;
    }
    public String getAdvertClassifyId(){
     return this.advertClassifyId;
    }

    public void setAdvertClassifyId(String advertClassifyId){
     this.advertClassifyId=advertClassifyId;
    }
    public String getAdvertPhone(){
     return this.advertPhone;
    }

    public void setAdvertPhone(String advertPhone){
     this.advertPhone=advertPhone;
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
    public String getAdvertAddress(){
     return this.advertAddress;
    }

    public void setAdvertAddress(String advertAddress){
     this.advertAddress=advertAddress;
    }
    public String getAdvertLongitude(){
     return this.advertLongitude;
    }

    public void setAdvertLongitude(String advertLongitude){
     this.advertLongitude=advertLongitude;
    }
    public String getAdvertLatitude(){
     return this.advertLatitude;
    }

    public void setAdvertLatitude(String advertLatitude){
     this.advertLatitude=advertLatitude;
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

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplyContext() {
		return applyContext;
	}

	public void setApplyContext(String applyContext) {
		this.applyContext = applyContext;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getShopVideo() {
		return shopVideo;
	}

	public void setShopVideo(String shopVideo) {
		this.shopVideo = shopVideo;
	}
	
    
}