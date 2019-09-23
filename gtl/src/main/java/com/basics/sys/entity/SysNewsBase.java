package com.basics.sys.entity;
    import java.util.Date;
public class SysNewsBase extends BaseBean{
    /**
    * 规则ID
    */
    private String id;

    /**
    * 新闻标题
    */
    private String newsTitle;

    /**
    * 新闻图片
    */
    private String newsImg;

    /**
    * 新闻内容
    */
    private String newsContext;

    /**
    * 新闻权重
    */
    private Integer newsSort;

    /**
    * 新闻状态(1显示 0不显示)
    */
    private Integer newsStatus;

    /**
    * 浏览量
    */
    private Integer newsReadNum;

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


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getNewsTitle(){
     return this.newsTitle;
    }

    public void setNewsTitle(String newsTitle){
     this.newsTitle=newsTitle;
    }
    public String getNewsImg(){
     return this.newsImg;
    }

    public void setNewsImg(String newsImg){
     this.newsImg=newsImg;
    }
    public String getNewsContext(){
     return this.newsContext;
    }

    public void setNewsContext(String newsContext){
     this.newsContext=newsContext;
    }
    public Integer getNewsSort(){
     return this.newsSort;
    }

    public void setNewsSort(Integer newsSort){
     this.newsSort=newsSort;
    }
    public Integer getNewsStatus(){
     return this.newsStatus;
    }

    public void setNewsStatus(Integer newsStatus){
     this.newsStatus=newsStatus;
    }
    public Integer getNewsReadNum(){
     return this.newsReadNum;
    }

    public void setNewsReadNum(Integer newsReadNum){
     this.newsReadNum=newsReadNum;
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
}