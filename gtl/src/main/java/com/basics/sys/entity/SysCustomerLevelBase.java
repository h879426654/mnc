package com.basics.sys.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class SysCustomerLevelBase extends BaseBean{
    /**
    * 等级ID
    */
    private String id;

    /**
    * 等级名称
    */
    private String levelName;
    private String levelEnglishName;
    private String levelKoreanName;
    private String levelJapaneseName;

    /**
    * 等级最小值
    */
    private BigDecimal levelMinIntegral;

    /**
    * 等级最大值
    */
    private BigDecimal levelMaxIntegral;

    /**
    * 直推人数
    */
    private Integer salfNum;

    /**
    * 直推奖励比例
    */
    private BigDecimal salfRewardRate;
    
    /**
     * 复投奖励比例
     */
    private BigDecimal recastRewardRate;

    /**
    * 团队支出奖励比例
    */
    private BigDecimal teamOutRewardRate;

    /**
    * 团队支入奖励比例
    */
    private BigDecimal teamInRewardRate;
    
    /**
     * 平级奖
     */
    private BigDecimal flatRewardRate;
    
    /**
     * 兑换奖
     */
    private BigDecimal exchangeRate;

    /**
     * 代数
     */
    private Integer floorNum;
    private Integer limitCoin;

    /**
    * 等级权重
    */
    private Integer levelSort;

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
    
    public BigDecimal getFlatRewardRate() {
		return flatRewardRate;
	}

	public void setFlatRewardRate(BigDecimal flatRewardRate) {
		this.flatRewardRate = flatRewardRate;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getLevelName(){
     return this.levelName;
    }

    public void setLevelName(String levelName){
     this.levelName=levelName;
    }
    public BigDecimal getLevelMinIntegral(){
     return this.levelMinIntegral;
    }

    public void setLevelMinIntegral(BigDecimal levelMinIntegral){
     this.levelMinIntegral=levelMinIntegral;
    }
    public BigDecimal getLevelMaxIntegral(){
     return this.levelMaxIntegral;
    }

    public void setLevelMaxIntegral(BigDecimal levelMaxIntegral){
     this.levelMaxIntegral=levelMaxIntegral;
    }
    public Integer getSalfNum(){
     return this.salfNum;
    }

    public void setSalfNum(Integer salfNum){
     this.salfNum=salfNum;
    }
    public BigDecimal getSalfRewardRate(){
     return this.salfRewardRate;
    }

    public void setSalfRewardRate(BigDecimal salfRewardRate){
     this.salfRewardRate=salfRewardRate;
    }
    public BigDecimal getTeamOutRewardRate(){
     return this.teamOutRewardRate;
    }

    public void setTeamOutRewardRate(BigDecimal teamOutRewardRate){
     this.teamOutRewardRate=teamOutRewardRate;
    }
    public BigDecimal getTeamInRewardRate(){
     return this.teamInRewardRate;
    }

    public void setTeamInRewardRate(BigDecimal teamInRewardRate){
     this.teamInRewardRate=teamInRewardRate;
    }
    public Integer getLevelSort(){
     return this.levelSort;
    }

    public void setLevelSort(Integer levelSort){
     this.levelSort=levelSort;
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

	public BigDecimal getRecastRewardRate() {
		return recastRewardRate;
	}

	public void setRecastRewardRate(BigDecimal recastRewardRate) {
		this.recastRewardRate = recastRewardRate;
	}

    public String getLevelEnglishName() {
        return levelEnglishName;
    }

    public void setLevelEnglishName(String levelEnglishName) {
        this.levelEnglishName = levelEnglishName;
    }

    public String getLevelKoreanName() {
        return levelKoreanName;
    }

    public void setLevelKoreanName(String levelKoreanName) {
        this.levelKoreanName = levelKoreanName;
    }

    public String getLevelJapaneseName() {
        return levelJapaneseName;
    }

    public void setLevelJapaneseName(String levelJapaneseName) {
        this.levelJapaneseName = levelJapaneseName;
    }

    public Integer getLimitCoin() {
        return limitCoin;
    }

    public void setLimitCoin(Integer limitCoin) {
        this.limitCoin = limitCoin;
    }
}