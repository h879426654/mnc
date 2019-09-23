package com.basics.sys.entity;
    import java.util.Date;
public class SysNoticeBase extends BaseBean{
    /**
    * 公告ID
    */
    private String id;

    /**
    * 公告类型(1公告通知 2站内信)
    */
    private Integer bulletinType;

    /**
    * 公告标题
    */
    private String noticeTitle;

    /**
    * 公告内容
    */
    private String noticeContext;

    /**
    * 权重
    */
    private Integer noticeSort;

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
    
    private String bulletinTypeName;
    
    


    public String getBulletinTypeName() {
		return bulletinTypeName;
	}

	public void setBulletinTypeName(String bulletinTypeName) {
		this.bulletinTypeName = bulletinTypeName;
	}

	public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public Integer getBulletinType(){
     return this.bulletinType;
    }

    public void setBulletinType(Integer bulletinType){
     this.bulletinType=bulletinType;
    }
    public String getNoticeTitle(){
     return this.noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle){
     this.noticeTitle=noticeTitle;
    }
    public String getNoticeContext(){
     return this.noticeContext;
    }

    public void setNoticeContext(String noticeContext){
     this.noticeContext=noticeContext;
    }
    public Integer getNoticeSort(){
     return this.noticeSort;
    }

    public void setNoticeSort(Integer noticeSort){
     this.noticeSort=noticeSort;
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