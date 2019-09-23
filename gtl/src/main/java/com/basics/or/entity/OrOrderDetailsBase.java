package com.basics.or.entity;
    import java.util.Date;
public class OrOrderDetailsBase extends BaseBean{
    /**
    * 订单详情ID
    */
    private String id;

    /**
    * 订单ID
    */
    private String orderId;

    /**
    * 修改时间
    */
    private Date modifyDate;

    /**
    * 规格ID
    */
    private String combinationId;

    /**
    * 商品ID
    */
    private String productId;

    /**
    * 是否删除（1是 0否）
    */
    private Integer flagDel;

    /**
    * 商品数量
    */
    private Integer productNum;

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


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getOrderId(){
     return this.orderId;
    }

    public void setOrderId(String orderId){
     this.orderId=orderId;
    }
    public Date getModifyDate(){
     return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate){
     this.modifyDate=modifyDate;
    }
    public String getCombinationId(){
     return this.combinationId;
    }

    public void setCombinationId(String combinationId){
     this.combinationId=combinationId;
    }
    public String getProductId(){
     return this.productId;
    }

    public void setProductId(String productId){
     this.productId=productId;
    }
    public Integer getFlagDel(){
     return this.flagDel;
    }

    public void setFlagDel(Integer flagDel){
     this.flagDel=flagDel;
    }
    public Integer getProductNum(){
     return this.productNum;
    }

    public void setProductNum(Integer productNum){
     this.productNum=productNum;
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
}