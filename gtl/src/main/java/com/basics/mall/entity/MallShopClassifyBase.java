package com.basics.mall.entity;
    import java.util.Date;
public class MallShopClassifyBase extends BaseBean{
    /**
    * 商品分类ID
    */
    private String id;

    private String countryId;

    /**
    * 分类名称
    */
    private String classifyName;

    /**
    * 分类图片
    */
    private String classifyImg;

    /**
    * 父级ID
    */
    private String classifyParentId;

    /**
    * 权重
    */
    private Integer classifySort;

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
    public String getClassifyName(){
     return this.classifyName;
    }

    public void setClassifyName(String classifyName){
     this.classifyName=classifyName;
    }
    public String getClassifyImg(){
     return this.classifyImg;
    }

    public void setClassifyImg(String classifyImg){
     this.classifyImg=classifyImg;
    }
    public String getClassifyParentId(){
     return this.classifyParentId;
    }

    public void setClassifyParentId(String classifyParentId){
     this.classifyParentId=classifyParentId;
    }
    public Integer getClassifySort(){
     return this.classifySort;
    }

    public void setClassifySort(Integer classifySort){
     this.classifySort=classifySort;
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
}