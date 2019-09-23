package com.basics.mall.entity;
    import java.util.Date;
public class MallIndexProductBase extends BaseBean{
    /**
    * 主键
    */
    private String id;

    /**
    * 首页模块
    */
    private String typeId;

    /**
    * 商品ID
    */
    private String productId;

    /**
    * 图片地址
    */
    private String productImage;

    /**
    * 权重
    */
    private Integer indexSort;

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
    
    private String typeTitle;
    private String productName;
    private String productImg;
    private String indexImg;
    
    public String getTypeTitle() {
		return typeTitle;
	}

	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getIndexImg() {
		return indexImg;
	}

	public void setIndexImg(String indexImg) {
		this.indexImg = indexImg;
	}

	public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getTypeId(){
     return this.typeId;
    }

    public void setTypeId(String typeId){
     this.typeId=typeId;
    }
    public String getProductId(){
     return this.productId;
    }

    public void setProductId(String productId){
     this.productId=productId;
    }
    public String getProductImage(){
     return this.productImage;
    }

    public void setProductImage(String productImage){
     this.productImage=productImage;
    }
    public Integer getIndexSort(){
     return this.indexSort;
    }

    public void setIndexSort(Integer indexSort){
     this.indexSort=indexSort;
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