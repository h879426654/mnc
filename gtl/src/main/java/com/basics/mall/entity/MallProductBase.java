package com.basics.mall.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class MallProductBase extends BaseBean{
    /**
    * 商家ID
    */
    private String id;

    /**
    * 店铺ID
    */
    private String shopId;
    
    /**
     * 国家ID
     */
    private String countryId;

    /**
    * 商品一级分类
    */
    private String productFirstClassify;

    /**
    * 商品二级分类
    */
    private String productSecondClassify;

    /**
    * 商品名称
    */
    private String productName;

    /**
    * 商品状态(1待上架 2上架中 3已下架)
    */
    private Integer productStatus;

    /**
    * 商品封面图
    */
    private String productImg;

    /**
    * 商品价格
    */
    private BigDecimal productPrice;

    /**
    * 商品成本价
    */
    private BigDecimal productCost;

    /**
    * 运费
    */
    private BigDecimal productFreight;

    /**
    * 商品详情描述
    */
    private String productContext;

    /**
    * 销量
    */
    private Integer productSale;

    /**
    * 库存
    */
    private Integer productStock;

    /**
    * 商品收藏数
    */
    private Integer productCoolection;

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
    
    private String shopName;
    private String productFirstClassifyName;
    private String productSecondClassifyName;
    
    public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getProductFirstClassifyName() {
		return productFirstClassifyName;
	}

	public void setProductFirstClassifyName(String productFirstClassifyName) {
		this.productFirstClassifyName = productFirstClassifyName;
	}

	public String getProductSecondClassifyName() {
		return productSecondClassifyName;
	}

	public void setProductSecondClassifyName(String productSecondClassifyName) {
		this.productSecondClassifyName = productSecondClassifyName;
	}

	public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getShopId(){
     return this.shopId;
    }

    public void setShopId(String shopId){
     this.shopId=shopId;
    }
    public String getProductFirstClassify(){
     return this.productFirstClassify;
    }

    public void setProductFirstClassify(String productFirstClassify){
     this.productFirstClassify=productFirstClassify;
    }
    public String getProductSecondClassify(){
     return this.productSecondClassify;
    }

    public void setProductSecondClassify(String productSecondClassify){
     this.productSecondClassify=productSecondClassify;
    }
    public String getProductName(){
     return this.productName;
    }

    public void setProductName(String productName){
     this.productName=productName;
    }
    public Integer getProductStatus(){
     return this.productStatus;
    }

    public void setProductStatus(Integer productStatus){
     this.productStatus=productStatus;
    }
    public String getProductImg(){
     return this.productImg;
    }

    public void setProductImg(String productImg){
     this.productImg=productImg;
    }
    public BigDecimal getProductPrice(){
     return this.productPrice;
    }

    public void setProductPrice(BigDecimal productPrice){
     this.productPrice=productPrice;
    }
    public BigDecimal getProductCost(){
     return this.productCost;
    }

    public void setProductCost(BigDecimal productCost){
     this.productCost=productCost;
    }
    public BigDecimal getProductFreight(){
     return this.productFreight;
    }

    public void setProductFreight(BigDecimal productFreight){
     this.productFreight=productFreight;
    }
    public String getProductContext(){
     return this.productContext;
    }

    public void setProductContext(String productContext){
     this.productContext=productContext;
    }
    public Integer getProductSale(){
     return this.productSale;
    }

    public void setProductSale(Integer productSale){
     this.productSale=productSale;
    }
    public Integer getProductStock(){
     return this.productStock;
    }

    public void setProductStock(Integer productStock){
     this.productStock=productStock;
    }
    public Integer getProductCoolection(){
     return this.productCoolection;
    }

    public void setProductCoolection(Integer productCoolection){
     this.productCoolection=productCoolection;
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

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
    
}