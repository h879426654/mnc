package com.basics.sys.entity;
    import java.math.BigDecimal;
    import java.util.Date;
public class SysRuleBase extends BaseBean{
    /**
    * 规则ID
    */
    private String id;

    /**
    * 每日释放比例
    */
    private BigDecimal releaseRateDay;

    /**
    * 余额转积分首次
    */
    private BigDecimal rateToIntegralFirst;

    /**
    * 余额转积分非首次
    */
    private BigDecimal rateToIntegralMore;

    /**
    * 余额转账个人奖励比例
    */
    private BigDecimal moneyOutRate;

    /**
    * 余额交易个人奖励比例
    */
    private BigDecimal moneyTradeRate;
    
    /**
     * 余额交易个人奖励比例
     */
    private BigDecimal moneyTradeRateSale;

    /**
    * 余额消费奖励比例
    */
    private BigDecimal moneySaleRate;

    /**
    * 转化比例
    */
    private BigDecimal convertRate;

    /**
    * 签到奖励积分数量
    */
    private Integer signRewardNum;

    /**
    * 交易手续费比例
    */
    private BigDecimal tradeRate;

    /**
    * 交易开始时间(24小时制)
    */
    private Integer tradeStartTime;

    /**
    * 交易结束时间(24小时制)
    */
    private Integer tradeEndTime;

    /**
    * 每人单次交易数
    */
    private Integer customerTradeNum;

    /**
    * 交易是否审核
    */
    private Integer tradeApplyFlag;

    /**
    * 交易最小值
    */
    private BigDecimal tradeMinNum;

    /**
    * 交易最大值(-1表示无穷大)
    */
    private BigDecimal tradeMaxNum;

    /**
    * 卖方最低单价比例(%)
    */
    private BigDecimal mallMinPrice;

    /**
    * 卖方最高单价比例(%)
    */
    private BigDecimal mallMaxPrice;

    /**
    * 买方最低单价比例(%)
    */
    private BigDecimal buyMinPrice;

    /**
    * 买方最高单价比例(%)
    */
    private BigDecimal buyMaxPrice;

    /**
     * 交易超过时间
     */
    private Integer tradeTimeOut;
    
    /**
     * 抽奖金额
     */
    private BigDecimal rewardNum;
    
    /**
     * 抽奖开关
     */
    private Integer rewardFlag;
    
    /**
     * 是否需要上传营业执照
     */
    private Integer needUploadLicence;
    
    /**
     * 安卓版本
     */
    private String androidVersion;
    
    /**
     * 安卓下载地址
     */
    private String androidDownload;
    
    /**
     * 苹果版本
     */
    private String appleVersion;
    
    /**
     * 苹果下载地址
     */
    private String appleDownload;
    
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
    /**
     * 商铺所需余额
     */
    private BigDecimal needShopMoney;
    /**
     * 余额交易折扣
     */
    private BigDecimal discountNum;
    
    


	public String getAndroidDownload() {
		return androidDownload;
	}

	public void setAndroidDownload(String androidDownload) {
		this.androidDownload = androidDownload;
	}


	public String getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}

	public String getAppleVersion() {
		return appleVersion;
	}

	public void setAppleVersion(String appleVersion) {
		this.appleVersion = appleVersion;
	}

	public String getAppleDownload() {
		return appleDownload;
	}

	public void setAppleDownload(String appleDownload) {
		this.appleDownload = appleDownload;
	}

	public BigDecimal getDiscountNum() {
		return discountNum;
	}

	public void setDiscountNum(BigDecimal discountNum) {
		this.discountNum = discountNum;
	}

	public BigDecimal getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}

	public Integer getRewardFlag() {
		return rewardFlag;
	}

	public void setRewardFlag(Integer rewardFlag) {
		this.rewardFlag = rewardFlag;
	}

	public Integer getTradeTimeOut() {
		return tradeTimeOut;
	}

	public void setTradeTimeOut(Integer tradeTimeOut) {
		this.tradeTimeOut = tradeTimeOut;
	}

	public BigDecimal getMoneyTradeRateSale() {
		return moneyTradeRateSale;
	}

	public void setMoneyTradeRateSale(BigDecimal moneyTradeRateSale) {
		this.moneyTradeRateSale = moneyTradeRateSale;
	}

	public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public BigDecimal getReleaseRateDay(){
     return this.releaseRateDay;
    }

    public void setReleaseRateDay(BigDecimal releaseRateDay){
     this.releaseRateDay=releaseRateDay;
    }
    public BigDecimal getRateToIntegralFirst(){
     return this.rateToIntegralFirst;
    }

    public void setRateToIntegralFirst(BigDecimal rateToIntegralFirst){
     this.rateToIntegralFirst=rateToIntegralFirst;
    }
    public BigDecimal getRateToIntegralMore(){
     return this.rateToIntegralMore;
    }

    public void setRateToIntegralMore(BigDecimal rateToIntegralMore){
     this.rateToIntegralMore=rateToIntegralMore;
    }
    public BigDecimal getMoneyOutRate(){
     return this.moneyOutRate;
    }

    public void setMoneyOutRate(BigDecimal moneyOutRate){
     this.moneyOutRate=moneyOutRate;
    }
    public BigDecimal getMoneyTradeRate(){
     return this.moneyTradeRate;
    }

    public void setMoneyTradeRate(BigDecimal moneyTradeRate){
     this.moneyTradeRate=moneyTradeRate;
    }
    public BigDecimal getMoneySaleRate(){
     return this.moneySaleRate;
    }

    public void setMoneySaleRate(BigDecimal moneySaleRate){
     this.moneySaleRate=moneySaleRate;
    }
    public BigDecimal getConvertRate(){
     return this.convertRate;
    }

    public void setConvertRate(BigDecimal convertRate){
     this.convertRate=convertRate;
    }
    public Integer getSignRewardNum(){
     return this.signRewardNum;
    }

    public void setSignRewardNum(Integer signRewardNum){
     this.signRewardNum=signRewardNum;
    }
    public BigDecimal getTradeRate(){
     return this.tradeRate;
    }

    public void setTradeRate(BigDecimal tradeRate){
     this.tradeRate=tradeRate;
    }
    public Integer getTradeStartTime(){
     return this.tradeStartTime;
    }

    public void setTradeStartTime(Integer tradeStartTime){
     this.tradeStartTime=tradeStartTime;
    }
    public Integer getTradeEndTime(){
     return this.tradeEndTime;
    }

    public void setTradeEndTime(Integer tradeEndTime){
     this.tradeEndTime=tradeEndTime;
    }
    public Integer getCustomerTradeNum(){
     return this.customerTradeNum;
    }

    public void setCustomerTradeNum(Integer customerTradeNum){
     this.customerTradeNum=customerTradeNum;
    }
    public Integer getTradeApplyFlag(){
     return this.tradeApplyFlag;
    }

    public void setTradeApplyFlag(Integer tradeApplyFlag){
     this.tradeApplyFlag=tradeApplyFlag;
    }
    public BigDecimal getTradeMinNum(){
     return this.tradeMinNum;
    }

    public void setTradeMinNum(BigDecimal tradeMinNum){
     this.tradeMinNum=tradeMinNum;
    }
    public BigDecimal getTradeMaxNum(){
     return this.tradeMaxNum;
    }

    public void setTradeMaxNum(BigDecimal tradeMaxNum){
     this.tradeMaxNum=tradeMaxNum;
    }
    public BigDecimal getMallMinPrice(){
     return this.mallMinPrice;
    }

    public void setMallMinPrice(BigDecimal mallMinPrice){
     this.mallMinPrice=mallMinPrice;
    }
    public BigDecimal getMallMaxPrice(){
     return this.mallMaxPrice;
    }

    public void setMallMaxPrice(BigDecimal mallMaxPrice){
     this.mallMaxPrice=mallMaxPrice;
    }
    public BigDecimal getBuyMinPrice(){
     return this.buyMinPrice;
    }

    public void setBuyMinPrice(BigDecimal buyMinPrice){
     this.buyMinPrice=buyMinPrice;
    }
    public BigDecimal getBuyMaxPrice(){
     return this.buyMaxPrice;
    }

    public void setBuyMaxPrice(BigDecimal buyMaxPrice){
     this.buyMaxPrice=buyMaxPrice;
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

    public BigDecimal getNeedShopMoney() {
        return needShopMoney;
    }

    public void setNeedShopMoney(BigDecimal needShopMoney) {
        this.needShopMoney = needShopMoney;
    }

	public Integer getNeedUploadLicence() {
		return needUploadLicence;
	}

	public void setNeedUploadLicence(Integer needUploadLicence) {
		this.needUploadLicence = needUploadLicence;
	}
    
    
}