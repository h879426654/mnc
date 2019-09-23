package com.basics.mall.entity;
    import java.util.Date;
public class MallCustomerCarBase extends BaseBean{
    /**
    * 购物车ID
    */
    private String id;

    /**
    * 客户ID
    */
    private String customerId;

    /**
    * 商品ID
    */
    private String productId;

    /**
    * 商品数量
    */
    private Integer productNum;

    /**
    * 规格ID
    */
    private String combinationId;

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

    public void setId(String id){
     this.id=id;
    }
    public String getCustomerId(){
     return this.customerId;
    }

    public void setCustomerId(String customerId){
     this.customerId=customerId;
    }
    public String getProductId(){
     return this.productId;
    }

    public void setProductId(String productId){
     this.productId=productId;
    }
    public Integer getProductNum(){
     return this.productNum;
    }

    public void setProductNum(Integer productNum){
     this.productNum=productNum;
    }
    public String getCombinationId(){
     return this.combinationId;
    }

    public void setCombinationId(String combinationId){
     this.combinationId=combinationId;
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