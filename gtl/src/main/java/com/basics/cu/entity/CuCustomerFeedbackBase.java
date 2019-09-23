package com.basics.cu.entity;
    import java.util.Date;
public class CuCustomerFeedbackBase extends BaseBean{
    /**
    * 反馈ID
    */
    private String id;

    /**
    * 反馈类型(1用户反馈 3商家反馈)
    */
    private Integer feedbackType;

    /**
    * 反馈用户ID
    */
    private String customerId;

    /**
    * 反馈内容
    */
    private String feedbackContext;

    /**
    * 反馈状态（1待处理 2已处理 3拒绝处理）
    */
    private Integer feedbackStatus;

    /**
    * 反馈处理说明
    */
    private String feedbackRemark;

    /**
    * 创建时间
    */
    private Date createTime;
    
    private String feedbackTypeName;
    private String customerName;
    private String customerPhone;

    public String getFeedbackTypeName() {
		return feedbackTypeName;
	}

	public void setFeedbackTypeName(String feedbackTypeName) {
		this.feedbackTypeName = feedbackTypeName;
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

	public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public Integer getFeedbackType(){
     return this.feedbackType;
    }

    public void setFeedbackType(Integer feedbackType){
     this.feedbackType=feedbackType;
    }
    public String getCustomerId(){
     return this.customerId;
    }

    public void setCustomerId(String customerId){
     this.customerId=customerId;
    }
    public String getFeedbackContext(){
     return this.feedbackContext;
    }

    public void setFeedbackContext(String feedbackContext){
     this.feedbackContext=feedbackContext;
    }
    public Integer getFeedbackStatus(){
     return this.feedbackStatus;
    }

    public void setFeedbackStatus(Integer feedbackStatus){
     this.feedbackStatus=feedbackStatus;
    }
    public String getFeedbackRemark(){
     return this.feedbackRemark;
    }

    public void setFeedbackRemark(String feedbackRemark){
     this.feedbackRemark=feedbackRemark;
    }
    public Date getCreateTime(){
     return this.createTime;
    }

    public void setCreateTime(Date createTime){
     this.createTime=createTime;
    }
}