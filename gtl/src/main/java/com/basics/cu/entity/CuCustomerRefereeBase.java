package com.basics.cu.entity;

public class CuCustomerRefereeBase extends BaseBean{
    /**
    * 用户ID
    */
    private String id;

    /**
    * 推荐人ID
    */
    private String refereeId;

    private String customerPhone;
    private String customerName;
    private String realName;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getRefereeId(){
     return this.refereeId;
    }

    public void setRefereeId(String refereeId){
     this.refereeId=refereeId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}