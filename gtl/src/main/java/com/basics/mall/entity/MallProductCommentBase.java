package com.basics.mall.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class MallProductCommentBase extends BaseBean{
    /**
    * 商品评价ID
    */
    private String id;

    /**
    * 商品ID
    */
    private String productId;

    /**
    * 会员ID
    */
    private String customerId;

    /**
    * 评价内容
    */
    private String commentContext;

    /**
    * 评价类型(1好评 2中评 3差评)
    */
    private Integer commentType;

    /**
    * 描述星级评价
    */
    private BigDecimal commentDescribeSart;

    /**
    * 服务星级评价
    */
    private BigDecimal commentServiceSart;

    /**
    * 物流星级评价
    */
    private BigDecimal commentLogisticsSart;

    /**
    * 是否匿名(1是 0否)
    */
    private Integer flagAnonymous;

    /**
    * 回复内容
    */
    private String replyContext;

    /**
    * 回复时间
    */
    private Date replyTime;

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
    
    private String productName;
    private String customerName;
    
    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getProductId(){
     return this.productId;
    }

    public void setProductId(String productId){
     this.productId=productId;
    }
    public String getCustomerId(){
     return this.customerId;
    }

    public void setCustomerId(String customerId){
     this.customerId=customerId;
    }
    public String getCommentContext(){
     return this.commentContext;
    }

    public void setCommentContext(String commentContext){
     this.commentContext=commentContext;
    }
    public Integer getCommentType(){
     return this.commentType;
    }

    public void setCommentType(Integer commentType){
     this.commentType=commentType;
    }
    public BigDecimal getCommentDescribeSart(){
     return this.commentDescribeSart;
    }

    public void setCommentDescribeSart(BigDecimal commentDescribeSart){
     this.commentDescribeSart=commentDescribeSart;
    }
    public BigDecimal getCommentServiceSart(){
     return this.commentServiceSart;
    }

    public void setCommentServiceSart(BigDecimal commentServiceSart){
     this.commentServiceSart=commentServiceSart;
    }
    public BigDecimal getCommentLogisticsSart(){
     return this.commentLogisticsSart;
    }

    public void setCommentLogisticsSart(BigDecimal commentLogisticsSart){
     this.commentLogisticsSart=commentLogisticsSart;
    }
    public Integer getFlagAnonymous(){
     return this.flagAnonymous;
    }

    public void setFlagAnonymous(Integer flagAnonymous){
     this.flagAnonymous=flagAnonymous;
    }
    public String getReplyContext(){
     return this.replyContext;
    }

    public void setReplyContext(String replyContext){
     this.replyContext=replyContext;
    }
    public Date getReplyTime(){
     return this.replyTime;
    }

    public void setReplyTime(Date replyTime){
     this.replyTime=replyTime;
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