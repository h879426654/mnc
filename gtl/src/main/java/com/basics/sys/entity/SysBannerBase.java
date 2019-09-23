package com.basics.sys.entity;
    import java.util.Date;
public class SysBannerBase extends BaseBean{
    /**
    * 横幅ID
    */
    private String id;

    private String countryId;

    /**
    * 横幅标题
    */
    private String bannerTitle;

    /**
    * 横幅图片地址
    */
    private String bannerImage;

    /**
     * 横幅链接
     */
    private String bannerType;
    
    /**
    * 横幅链接
    */
    private String bannerUrl;

    /**
    * 横幅权重
    */
    private Integer bannerSort;

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
    
    private String bannerTypeName;
    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getBannerTitle(){
     return this.bannerTitle;
    }

    public void setBannerTitle(String bannerTitle){
     this.bannerTitle=bannerTitle;
    }
    public String getBannerImage(){
     return this.bannerImage;
    }

    public void setBannerImage(String bannerImage){
     this.bannerImage=bannerImage;
    }
    public String getBannerUrl(){
     return this.bannerUrl;
    }

    public void setBannerUrl(String bannerUrl){
     this.bannerUrl=bannerUrl;
    }
    public Integer getBannerSort(){
     return this.bannerSort;
    }

    public void setBannerSort(Integer bannerSort){
     this.bannerSort=bannerSort;
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

	public String getBannerTypeName() {
		return bannerTypeName;
	}

	public void setBannerTypeName(String bannerTypeName) {
		this.bannerTypeName = bannerTypeName;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}