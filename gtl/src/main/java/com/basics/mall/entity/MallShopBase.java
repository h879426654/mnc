package com.basics.mall.entity;
    import java.math.BigDecimal;
import java.util.Date;
public class MallShopBase extends BaseBean{
    /**
    * 店铺ID
    */
    private String id;

    private String countryId;


    /**
    * 会员ID
    */
    private String customerId;

    /**
    * 店铺名称
    */
    private String shopName;

    /**
    * 店铺信誉
    */
    private BigDecimal shopStar;

    /**
    * 店铺LOGO
    */
    private String shopLogo;
    
    /**
     * 店铺LOGO
     */
    private String shopLicence;
    
    /**
     * 店铺密码
     */
    private String shopPass;

    /**
    * 店铺编号
    */
    private Integer shopNumber;

    /**
    * 店铺状态(1营业 2停业)
    */
    private Integer shopStatus;
    
    /**
     * 店铺审核
     */
    private Integer applyStatus;
    
    /**
     * 审核意见
     */
    private String applyContext;

    /**
    * 店铺折扣比例
    */
    private BigDecimal shopDiscount;
    
    /**
     * 店铺折扣比例
     */
    private BigDecimal needMoney;

    /**
    * 店铺说明
    */
    private String shopService;

    /**
    * 店铺地址
    */
    private String shopAddress;

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

    private String shopPhone;
    
    private String customerName;
    
    private String customerPhone;

    private String countryName;


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getShopLicence() {
		return shopLicence;
	}

	public void setShopLicence(String shopLicence) {
		this.shopLicence = shopLicence;
	}

	public BigDecimal getNeedMoney() {
		return needMoney;
	}

	public void setNeedMoney(BigDecimal needMoney) {
		this.needMoney = needMoney;
	}

	public String getShopPass() {
		return shopPass;
	}

	public void setShopPass(String shopPass) {
		this.shopPass = shopPass;
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

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
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
    public String getShopName(){
     return this.shopName;
    }

    public void setShopName(String shopName){
     this.shopName=shopName;
    }
    public BigDecimal getShopStar(){
     return this.shopStar;
    }

    public void setShopStar(BigDecimal shopStar){
     this.shopStar=shopStar;
    }
    public String getShopLogo(){
     return this.shopLogo;
    }

    public void setShopLogo(String shopLogo){
     this.shopLogo=shopLogo;
    }
    public Integer getShopNumber(){
     return this.shopNumber;
    }

    public void setShopNumber(Integer shopNumber){
     this.shopNumber=shopNumber;
    }
    public Integer getShopStatus(){
     return this.shopStatus;
    }

    public void setShopStatus(Integer shopStatus){
     this.shopStatus=shopStatus;
    }
    public BigDecimal getShopDiscount(){
     return this.shopDiscount;
    }

    public void setShopDiscount(BigDecimal shopDiscount){
     this.shopDiscount=shopDiscount;
    }
    public String getShopService(){
     return this.shopService;
    }

    public void setShopService(String shopService){
     this.shopService=shopService;
    }
    public String getShopAddress(){
     return this.shopAddress;
    }

    public void setShopAddress(String shopAddress){
     this.shopAddress=shopAddress;
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

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

	public String getApplyContext() {
		return applyContext;
	}

	public void setApplyContext(String applyContext) {
		this.applyContext = applyContext;
	}

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}