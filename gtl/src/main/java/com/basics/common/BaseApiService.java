package com.basics.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;

import com.basics.cu.dao.*;
import com.basics.gty.dao.GtyWalletDao;
import com.basics.gty.entity.GtyWallet;
import com.basics.mall.dao.*;
import com.basics.wallet.dao.WalletEntityDao;
import com.basics.wallet.entity.WalletEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContext;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.dao.AppAreaDao;
import com.basics.app.dao.AppCodeDao;
import com.basics.app.dao.AppImageDao;
import com.basics.app.dao.AppLogDao;
import com.basics.app.dao.AppOptionDao;
import com.basics.app.dao.AppRewardDao;
import com.basics.app.dao.AppRoleDao;
import com.basics.app.dao.AppTokenDao;
import com.basics.app.dao.AppUserDao;
import com.basics.app.dao.AppUserRoleDao;
import com.basics.app.entity.AppCode;
import com.basics.app.entity.AppToken;
import com.basics.cu.entity.CuAccountConvert;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerCount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.cu.entity.CuCustomerMessage;
import com.basics.cu.entity.CuCustomerProfit;
import com.basics.cu.entity.CuCustomerProfitAdmin;
import com.basics.cu.entity.CuCustomerReferee;
import com.basics.cu.vo.CustomerCountLevelVo;
import com.basics.mall.entity.MallShop;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.or.dao.OrOrderDao;
import com.basics.or.dao.OrOrderDetailsDao;
import com.basics.support.CommonSupport;
import com.basics.support.ConfigReader;
import com.basics.support.DateUtils;
import com.basics.support.FileStoreService;
import com.basics.support.JuheClient;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.activemq.QueueSender;
import com.basics.sys.dao.SysActivemqRequestDao;
import com.basics.sys.dao.SysActivemqResponseDao;
import com.basics.sys.dao.SysBannerDao;
import com.basics.sys.dao.SysConfigDao;
import com.basics.sys.dao.SysCountryDao;
import com.basics.sys.dao.SysCustomerLevelDao;
import com.basics.sys.dao.SysMarketValueDao;
import com.basics.sys.dao.SysNoticeDao;
import com.basics.sys.dao.SysProfitDao;
import com.basics.sys.dao.SysRuleDao;
import com.basics.sys.dao.SysTurntableDao;
import com.basics.sys.dao.SysTurntableRewardDao;
import com.basics.sys.dao.SysVersionDao;
import com.basics.sys.entity.SysActivemqRequest;
import com.basics.sys.entity.SysActivemqResponse;
import com.basics.sys.entity.SysConfig;
import com.basics.sys.entity.SysCountry;
import com.basics.sys.entity.SysCustomerLevel;
import com.basics.sys.entity.SysMarketValue;
import com.basics.sys.entity.SysProfit;
import com.basics.sys.entity.SysRule;
import com.basics.tr.dao.TrConvertDao;
import com.basics.tr.dao.TrTradeCoinDao;
import com.basics.tr.dao.TrTradeConvertDao;
import com.basics.tr.dao.TrTradeMoneyDao;
import com.basics.tr.entity.TrConvert;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.entity.TrTradeMoney;

/**
 * BaseApiService.
 * <p>
 * Api服务基类,注入通用的Dao,提供通用的方法.
 * </p>
 */
@Service
@Transactional
public class BaseApiService {

	@Autowired
	protected AppTokenDao appTokenDao;
	@Autowired
	public JuheClient juheClient;
	@Autowired
	public AppCodeDao appCodeDao;
	@Autowired
	public AppLogDao appLogDao;
	@Autowired
	public AppAreaDao appAreaDao;
	@Autowired
	public AppRewardDao appRewardDao;
	@Autowired
	public AppOptionDao appOptionDao;
	@Autowired
	public AppImageDao appImageDao;
	@Autowired
	protected AppRoleDao appRoleDao;
	@Autowired
	protected AppUserRoleDao appUserRoleDao;

	@Autowired
	public SysCustomerLevelDao sysCustomerLevelDao;
	@Autowired
	public SysBannerDao sysBannerDao;
	@Autowired
	public SysNoticeDao sysNoticeDao;
	@Autowired
	public SysMarketValueDao sysMarketValueDao;
	@Autowired
	public SysRuleDao sysRuleDao;
	@Autowired
	public SysProfitDao sysProfitDao;
	@Autowired
	public SysTurntableDao sysTurntableDao;
	@Autowired
	public SysTurntableRewardDao sysTurntableRewardDao;
	@Autowired
	public SysConfigDao sysConfigDao;
	@Autowired
	public SysVersionDao sysVersionDao;
	@Autowired
	public SysCountryDao sysCountryDao;

	@Autowired
	public MallProductKindCombinationDao mallProductKindCombinationDao;
	@Autowired
	public MallProductKindContrastDao mallProductKindContrastDao;
	@Autowired
	public MallProductKindDao mallProductKindDao;
	@Autowired
	public MallProductKindDetailDao mallProductKindDetailDao;
	@Autowired
	public MallCustomerCarDao mallCustomerCarDao;
	@Autowired
	public MallProductClassifyDao mallProductClassifyDao;
	@Autowired
	public MallIndexTypeDao mallIndexTypeDao;
	@Autowired
	public MallIndexProductDao mallIndexProductDao;
	@Autowired
	public MallProductDao mallProductDao;
	@Autowired
	public MallProductCommentDao mallProductCommentDao;
	@Autowired
	public MallShopDao mallShopDao;
	@Autowired
	public MallShopAdvertDao mallShopAdvertDao;
	@Autowired
	public MallShopClassifyDao mallShopClassifyDao;
	@Autowired
	public OrOrderDetailsDao orOrderDetailsDao;
	@Autowired
	public OrOrderDao orOrderDao;
	@Autowired
	public AppUserDao appUserDao;
	@Autowired
	public WalletEntityDao walletEntityDao;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Autowired
	public CuCustomerLoginDao cuCustomerLoginDao;
	@Autowired
	public CuCustomerInfoDao cuCustomerInfoDao;
	@Autowired
	public CuCustomerAccountDao cuCustomerAccountDao;
	@Autowired
	public CuCustomerCountDao cuCustomerCountDao;
	@Autowired
	public CuCustomerRefereeDao cuCustomerRefereeDao;
	@Autowired
	public CuCustomerProfitDao cuCustomerProfitDao;
	@Autowired
	public CuCustomerProfitAdminDao cuCustomerProfitAdminDao;
	@Autowired
	public CuAccountConvertDao cuAccountConvertDao;
	@Autowired
	public CuCustomerMessageDao cuCustomerMessageDao;
	@Autowired
	protected CuCustomerCollectionDao cuCustomerCollectionDao;
	@Autowired
	protected CuCustomerAddressDao cuCustomerAddressDao;
	@Autowired
	protected CuCustomerSignDao cuCustomerSignDao;
	@Autowired
	protected CuCustomerFeedbackDao cuCustomerFeedbackDao;
	@Autowired
	protected CuDrawRewardDao cuDrawRewardDao;
	@Autowired
	public TrConvertDao trConvertDao;

	@Autowired
	public GtyWalletDao gtyWalletDao;

	@Autowired
	public TrTradeMoneyDao trTradeMoneyDao;
	@Autowired
	public TrTradeCoinDao trTradeCoinDao;
	@Autowired
	public TrTradeConvertDao trTradeConvertDao;

	@Autowired
	public FileStoreService baseFileStoreService;
	@Autowired
	@Qualifier("queueDestination")
	public Destination queueDestination;
	@Autowired
	public QueueSender queueSender;
	@Autowired
	public SysActivemqRequestDao sysActivemqRequestDao;
	@Autowired
	public SysActivemqResponseDao sysActivemqResponseDao;
	@Autowired
	public MallAdvertHotDao mallAdvertHotDao;
	@Autowired
	public CuReatil2Dao cuReatil2Dao;
	@Autowired
	public CuReatil3Dao cuReatil3Dao;
	@Autowired
	public CuCustomerCollectDao cuCustomerCollectDao;
	@Autowired
	public CuHttpUrlDao cuHttpUrlDao;
	@Autowired
	public CuConsumeDao cuConsumeDao;
	@Autowired
	public CuHistoryDao cuHistoryDao;
	@Autowired
	public CuDiscussDao cuDiscussDao;
	@Autowired
	public MallGoodsDao mallGoodsDao;
	@Autowired
	public CuReatil1Dao cuReatil1Dao;
	// 计算产量（根据毫秒秒）
	public BigDecimal calculationRate(long date, BigDecimal yidldNum) {
		return keepFiveNum(yidldNum.multiply(new BigDecimal(date)).divide(new BigDecimal(1000 * 60 * 60), 5, RoundingMode.FLOOR));
	}

	public static void main(String[] args) {
//		System.out.println(new BigDecimal("0.0154563").multiply(new BigDecimal(3453334)).divide(new BigDecimal(1000 * 60 * 60)).doubleValue());
	}

	// 保留5位小数，取下
	public BigDecimal keepFiveNum(BigDecimal num) {
		return num.setScale(8, RoundingMode.FLOOR);
	}

	/**
	 * 接口返回信息统一放在errorCode.properties里面统一配置
	 *
	 * @param codeString
	 *            错误信息对应的Code值
	 * @return
	 */
	public String getErrorMsgByCode(String codeString) {
		return ConfigReader.getInstance().get(codeString);
	}

	/**
	 * 判断是否激活状态
	 */
	public boolean checkCustomerStatus(CuCustomerLogin user) {
		if (Constant.STATE_YES == user.getCustomerStatus().intValue()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断用户信息是否完善
	 */
	public boolean checkCustomerInfo(String customerId) {
		CuCustomerInfo info = cuCustomerInfoDao.get(customerId);
		CommonSupport.checkNotNull(info, "用户信息错误, id:" + customerId);
		if (null == info.getCustomerAlipay() || null == info.getRealName() || null == info.getBankCard()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否存在上下级关系
	 *
	 * @param refereeId
	 * @param customerId
	 * @return
	 */
	public boolean customerIsReferee(String refereeId, String customerId) {
		CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
		if (null != referee) {
			if (null != referee.getRefereeId() && !Constant.DEFAULT_REFEREE.equals(referee.getRefereeId())) {
				if (referee.getRefereeId().equals(refereeId)) {
					return true;
				}
				customerIsReferee(refereeId, referee.getRefereeId());
			}
		}
		return false;
	}

	/**
	 * 根据token获取用户
	 */
	public CuCustomerLogin checkToken(String token) {
		AppToken appToken = appTokenDao.get(token);
		Date now = new Date();
		if (null != appToken && appToken.getTokenExpirationTime().getTime() >= now.getTime()) {
			appToken.setTokenExpirationTime(DateUtils.addDateMinut(now, 1));
			appTokenDao.save(appToken);
			return cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("id", appToken.getUserId()).put("customerStatus", Constant.STATE_YES).build());
		}
		return null;
	}

	/**
	 * 更新团队人数
	 */
	public void doUpdateTeamNum(String refereeId, boolean flag) {
		if (null != refereeId && !"0".equals(refereeId)) {
			CuCustomerCount count = cuCustomerCountDao.get(refereeId);
			CommonSupport.checkNotNull(count, "推荐人count出错了, id:" + refereeId);
			if (flag) {
				count.setSalfNum(count.getSalfNum() + 1);
			}
			count.setTeamNum(count.getTeamNum() + 1);
			cuCustomerCountDao.save(count);
			CuCustomerReferee referee = cuCustomerRefereeDao.get(refereeId);
			if (null != referee) {
				doUpdateTeamNum(referee.getRefereeId(), false);
			}
		}
	}

	/**
	 * 更新团队人数
	 *
	 * @param refereeId
	 *            推荐人id
	 * @param num
	 *            数量
	 * @param isDirect
	 *            是否直推
	 * @param isAdd
	 *            是否添加
	 */
	public void doUpdateTeamNum(String refereeId, Integer num, boolean isDirect, boolean isAdd) {
		if (null != refereeId && !"0".equals(refereeId)) {
			CuCustomerCount count = cuCustomerCountDao.get(refereeId);
			CommonSupport.checkNotNull(count, "推荐人count出错了, id:" + refereeId);
			if (isDirect) {
				count.setSalfNum(isAdd ? count.getSalfNum() + 1 : count.getSalfNum() - 1);
			}
			count.setTeamNum(isAdd ? count.getTeamNum() + num : count.getTeamNum() - num);
			cuCustomerCountDao.save(count);
			CuCustomerReferee referee = cuCustomerRefereeDao.get(refereeId);
			if (null != referee) {
				doUpdateTeamNum(referee.getRefereeId(), num, false, isAdd);
			}
		}
	}

	/**
	 * 增加收益记录
	 */
	public void doAddCustomerProfit(String customerId, String profitSource, BigDecimal rate, int earningStatus, int earningsType, String profitRemark) {
		if (0 < rate.doubleValue()) {
			CuCustomerProfit profit = new CuCustomerProfit();
			profit.setCustomerId(customerId);
			if (StringUtils.isNotBlank(profitSource)) {
				profit.setProfitSource(profitSource);
			}
			profit.setProfitNum(rate);
			profit.setProfitHavedTime(new Date());
			profit.setProfitStatus(earningStatus);
			profit.setProfitType(earningsType);
			profit.setCreateTime(new Date());
			profit.setProfitRemark(profitRemark);
			cuCustomerProfitDao.save(profit);
		}
	}

	/**
	 * 增加后台奖励记录
	 */
	public void doAddCustomerProfitAdmin(String customerId, String profitSource, BigDecimal rate, int earningStatus, int earningsType, String profitRemark, String createUser) {
		if (0 < rate.doubleValue()) {
			CuCustomerProfitAdmin profit = new CuCustomerProfitAdmin();
			profit.setCustomerId(customerId);
			if (StringUtils.isNotBlank(profitSource)) {
				profit.setProfitSource(profitSource);
			}
			if (StringUtils.isNotBlank(createUser)) {
				profit.setCreateUser(createUser);
			}
			profit.setProfitNum(rate);
			profit.setProfitHavedTime(new Date());
			profit.setProfitStatus(earningStatus);
			profit.setProfitType(earningsType);
			profit.setCreateTime(new Date());
			profit.setProfitRemark(profitRemark);
			cuCustomerProfitAdminDao.save(profit);
		}
	}

	/**
	 * 判断是否晋级
	 */
	public void checkCustomerLevel(String customerId, BigDecimal integral) {
		SysCustomerLevel level = sysCustomerLevelDao.queryOne(new QueryFilterBuilder().put("tallyWithLevel", integral).build());
		if (null != level) {
			CuCustomerCount count = cuCustomerCountDao.get(customerId);
			// 判断是否升级
			if (Constant.STATE_YES == count.getFlagLevelAuto()) {
				if (!count.getCustomerLevelId().equals(level.getId())) {
					count.setCustomerLevelId(level.getId());
					cuCustomerCountDao.save(count);
				}
			}
		}
	}

	/**
	 * 转换记录表
	 */
	public String createAccountConvert(String customerId, Integer convertType, BigDecimal convertMoney, BigDecimal convertNum, String sourceId, String convertRemark) {
		CuAccountConvert convert = new CuAccountConvert();
		convert.setCustomerId(customerId);
		convert.setConvertType(convertType);
		convert.setConvertMoney(convertMoney);
		convert.setConvertNum(convertNum);
		convert.setSourceId(sourceId);
		convert.setConvertRemark(convertRemark);
		convert.setCreateTime(new Date());
		cuAccountConvertDao.save(convert);
		return convert.getId();
	}

	/**
	 * 添加平台收益
	 */
	public void addSysProfit(String sourceId, Integer sourceType, BigDecimal num, String remark) {
		SysProfit profit = new SysProfit();
		profit.setProfitSourceId(sourceId);
		profit.setProfitSource(sourceType);
		profit.setProfitNum(num);
		profit.setProfitRemark(remark);
		profit.setCreateTime(new Date());
		sysProfitDao.save(profit);
	}

	/**
	 * 释放积分到余额
	 */
	public boolean IntegralToMoney(String customerId, BigDecimal num) {
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "获取会员账户失败");
		if (num.doubleValue() > 0 && account.getCustomerIntegral().doubleValue() >= num.doubleValue()) {
			account.setUseMoney(account.getUseMoney().add(num));
			account.setTotalMoney(account.getTotalMoney().add(num));
			account.setCustomerIntegral(account.getCustomerIntegral().subtract(num));
			cuCustomerAccountDao.save(account);
			checkCustomerLevel(account.getId(), account.getCustomerIntegral());
			return true;
		}
		return false;
	}

	/**
	 * 上级获取奖励
	 */
	public void rewardParent(String customerId, String parentId, BigDecimal money, Integer status, Integer floor) {
		CuCustomerAccount account = cuCustomerAccountDao.get(parentId);
		CommonSupport.checkNotNull(account, "获取会员账户失败");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", parentId);
		CuCustomerCount count = cuCustomerCountDao.get(parentId);
		CommonSupport.checkNotNull(count, "用户count错误");
		if (StringUtils.isNotBlank(count.getCustomerLevelId()) && !"-1".equals(count.getCustomerLevelId())) {
			SysCustomerLevel level = sysCustomerLevelDao.get(count.getCustomerLevelId());
			CommonSupport.checkNotNull(level, "会员等级错误");
			// 是否未后台设置等级
			if (Constant.STATE_NO == count.getFlagLevelAuto()) {
				if (floor <= level.getFloorNum()) {
					BigDecimal rate = BigDecimal.ZERO;
					if (Constant.PROFIT_STATUS_2 == status.intValue()) {
						if (account.getCustomerIntegral().doubleValue() >= money.multiply(level.getTeamOutRewardRate()).doubleValue()) {
							rate = money.multiply(level.getTeamOutRewardRate());
						} else {
							if (0 < account.getCustomerIntegral().doubleValue()) {
								rate = account.getCustomerIntegral();
							}
						}
						boolean flag = IntegralToMoney(parentId, rate);
						if (flag) {
							doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "团队转出收益");
							doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_3, "团队转出收益");
						}
					}
				}
			} else {
				if (floor <= level.getFloorNum() && count.getSalfNum().intValue() >= level.getSalfNum().intValue()) {
					BigDecimal rate = BigDecimal.ZERO;
					if (Constant.PROFIT_STATUS_1 == status.intValue()) {
						/*
						 * if (account.getCustomerIntegral().doubleValue() >=
						 * money.multiply(level.getTeamInRewardRate()).doubleValue()) { rate =
						 * money.multiply(level.getTeamInRewardRate()); } else { if (0 <
						 * account.getCustomerIntegral().doubleValue()) { rate =
						 * account.getCustomerIntegral(); } } boolean flag = IntegralToMoney(parentId,
						 * rate); if (flag) { doAddCustomerProfit(parentId, customerId, rate,
						 * Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "团队转入收益");
						 * doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_2,
						 * Constant.MONEY_TYPE_3, "团队转入收益"); }
						 */
					} else {
						if (account.getCustomerIntegral().doubleValue() >= money.multiply(level.getTeamOutRewardRate()).doubleValue()) {
							rate = money.multiply(level.getTeamOutRewardRate());
						} else {
							if (0 < account.getCustomerIntegral().doubleValue()) {
								rate = account.getCustomerIntegral();
							}
						}
						boolean flag = IntegralToMoney(parentId, rate);
						if (flag) {
							doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "团队转出收益");
							doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_3, "团队转出收益");
						}
					}
				}
			}
		}
		// 获取推荐人
		CuCustomerReferee referee = cuCustomerRefereeDao.get(parentId);
		if (null != referee && !"0".equals(referee.getRefereeId())) {
			floor = floor + 1;
			rewardParent(customerId, referee.getRefereeId(), money, status, floor);
		}
	}

	/**
	 * 上级获取奖励
	 *
	 * @param isMoneyToIntegral
	 *            是否为余额转积分
	 */
	public void rewardParent(String customerId, String parentId, BigDecimal money, Integer status, Integer floor, boolean isMoneyToIntegral) {
		CuCustomerAccount account = cuCustomerAccountDao.get(parentId);
		CommonSupport.checkNotNull(account, "获取会员账户失败");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", parentId);
		CuCustomerCount count = cuCustomerCountDao.get(parentId);
		CommonSupport.checkNotNull(count, "用户count错误");
		if (StringUtils.isNotBlank(count.getCustomerLevelId()) && !"-1".equals(count.getCustomerLevelId())) {
			SysCustomerLevel level = sysCustomerLevelDao.get(count.getCustomerLevelId());
			CommonSupport.checkNotNull(level, "会员等级错误");
			SysConfig code = sysConfigDao.getConfigByCode("SYS_EXCHANGE_FLOOR_NUM");
			// 是否未后台设置等级
			if (Constant.STATE_NO == count.getFlagLevelAuto()) {
				if (floor > code.getConfigValue().intValue()) {
					if (isMoneyToIntegral && floor <= level.getFloorNum() && 0 < level.getExchangeRate().doubleValue()) {
						BigDecimal integral = money.multiply(level.getExchangeRate());
						account.setCustomerIntegral(account.getCustomerIntegral().add(integral));
						cuCustomerAccountDao.save(account);
						doAddCustomerProfit(account.getId(), customerId, integral, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "兑换奖励");
						if (0 < level.getFlatRewardRate().doubleValue()) {
							rewardFlatCustomer(account.getId(), integral, level, account.getId());
						}
					}
				} else {
					if (floor <= level.getFloorNum()) {
						BigDecimal rate = BigDecimal.ZERO;
						if (Constant.PROFIT_STATUS_1 == status.intValue()) {
							// 团队收入奖
						} else {
							BigDecimal reward = money.multiply(isMoneyToIntegral ? level.getRecastRewardRate() : level.getTeamOutRewardRate());
							if (account.getCustomerIntegral().doubleValue() >= reward.doubleValue()) {
								rate = reward;
							} else {
								if (0 < account.getCustomerIntegral().doubleValue()) {
									rate = account.getCustomerIntegral();
								}
							}
							boolean flag = IntegralToMoney(parentId, rate);
							if (flag) {
								doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, isMoneyToIntegral ? "复投收益" : "团队转出收益");
								doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_3, isMoneyToIntegral ? "复投收益" : "团队转出收益");
							}
						}
					}
				}
			} else {
				if (floor > code.getConfigValue().intValue()) {
					if (isMoneyToIntegral && floor <= level.getFloorNum() && count.getSalfNum().intValue() >= level.getSalfNum().intValue() && 0 < level.getExchangeRate().doubleValue()) {
						BigDecimal integral = money.multiply(level.getExchangeRate());
						account.setCustomerIntegral(account.getCustomerIntegral().add(integral));
						cuCustomerAccountDao.save(account);
						doAddCustomerProfit(account.getId(), customerId, integral, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "兑换奖励");
						if (0 < level.getFlatRewardRate().doubleValue()) {
							rewardFlatCustomer(account.getId(), integral, level, account.getId());
						}
					}
				} else {
					if (floor <= level.getFloorNum() && count.getSalfNum().intValue() >= level.getSalfNum().intValue()) {
						BigDecimal rate = BigDecimal.ZERO;
						if (Constant.PROFIT_STATUS_1 == status.intValue()) {
							/*
							 * if (account.getCustomerIntegral().doubleValue() >=
							 * money.multiply(level.getTeamInRewardRate()).doubleValue()) { rate =
							 * money.multiply(level.getTeamInRewardRate()); } else { if (0 <
							 * account.getCustomerIntegral().doubleValue()) { rate =
							 * account.getCustomerIntegral(); } } boolean flag = IntegralToMoney(parentId,
							 * rate); if (flag) { doAddCustomerProfit(parentId, customerId, rate,
							 * Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "团队转入收益");
							 * doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_2,
							 * Constant.MONEY_TYPE_3, "团队转入收益"); }
							 */
						} else {
							BigDecimal reward = money.multiply(isMoneyToIntegral ? level.getRecastRewardRate() : level.getTeamOutRewardRate());
							if (account.getCustomerIntegral().doubleValue() >= reward.doubleValue()) {
								rate = reward;
							} else {
								if (0 < account.getCustomerIntegral().doubleValue()) {
									rate = account.getCustomerIntegral();
								}
							}
							boolean flag = IntegralToMoney(parentId, rate);
							if (flag) {
								doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, isMoneyToIntegral ? "复投收益" : "团队转出收益");
								doAddCustomerProfit(parentId, customerId, rate, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_3, isMoneyToIntegral ? "复投收益" : "团队转出收益");
							}
						}
					}
				}
			}
		}
		// 获取推荐人
		CuCustomerReferee referee = cuCustomerRefereeDao.get(parentId);
		if (null != referee && !"0".equals(referee.getRefereeId())) {
			floor = floor + 1;
			rewardParent(customerId, referee.getRefereeId(), money, status, floor, isMoneyToIntegral);
		}
	}

	/**
	 * 平级奖
	 *
	 * @param customerId
	 * @param rate
	 */
	public void rewardFlatCustomer(String customerId, BigDecimal rate, SysCustomerLevel level, String sourceId) {
		// 获取推荐人
		CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
		if (null != referee && !"0".equals(referee.getRefereeId())) {
			CuCustomerCount refereeCount = cuCustomerCountDao.get(referee.getRefereeId());
			if (level.getId().equals(refereeCount.getCustomerLevelId())) {
				BigDecimal integral = rate.multiply(level.getFlatRewardRate());
				if (0 < integral.doubleValue()) {
					doUpdateIntegral(referee.getRefereeId(), integral, true);
					doAddCustomerProfit(referee.getRefereeId(), sourceId, integral, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "平级奖励");
					return;
				}
			}
			rewardFlatCustomer(referee.getRefereeId(), rate, level, sourceId);
		}
	}

	/**
	 * 余额转账
	 */
	public void transferByMoney(String customerId, BigDecimal num, String sourceId) {
		if (customerId.equals(sourceId)) {
			CommonSupport.checkNotNull(null, "您不能给您自己转账");
		}
		// 判断会员余额是否足够
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "获取会员账户失败");
		CuCustomerAccount sourceUser = cuCustomerAccountDao.get(sourceId);
		CommonSupport.checkNotNull(sourceUser, "获取会员账户失败");
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "获取平台规则失败");
		if (account.getUseMoney().doubleValue() >= num.multiply(rule.getTradeRate()).add(num).doubleValue()) {
			account.setUseMoney(account.getUseMoney().subtract(num.multiply(rule.getTradeRate()).add(num)));
			account.setTotalMoney(account.getTotalMoney().subtract(num.multiply(rule.getTradeRate()).add(num)));
			account.setCustomerIntegral(account.getCustomerIntegral().add(rule.getMoneyOutRate().multiply(num)));
			cuCustomerAccountDao.save(account);
			checkCustomerLevel(account.getId(), account.getCustomerIntegral());
			doAddCustomerProfit(customerId, sourceId, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MC交易");
			doAddCustomerProfit(customerId, sourceId, rule.getMoneyOutRate().multiply(num), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "MC转账");
			String convertId = createAccountConvert(customerId, Constant.CONVERT_TYPE_3, num, num, sourceId, "MC交易");
			// 手续费
			addSysProfit(convertId, Constant.APPLY_STATUS_3, num.multiply(rule.getTradeRate()), "转账手续费");
			sourceUser.setUseMoney(sourceUser.getUseMoney().add(num.multiply(rule.getConvertRate())));
			sourceUser.setTotalMoney(sourceUser.getTotalMoney().add(num.multiply(rule.getConvertRate())));
			sourceUser.setCustomerIntegral(sourceUser.getCustomerIntegral().add(num.subtract(num.multiply(rule.getConvertRate()))));
			cuCustomerAccountDao.save(sourceUser);
			checkCustomerLevel(sourceUser.getId(), sourceUser.getCustomerIntegral());
			doAddCustomerProfit(sourceId, customerId, num.multiply(rule.getConvertRate()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "MC交易");
			doAddCustomerProfit(sourceId, customerId, num.subtract(num.multiply(rule.getConvertRate())), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "MC交易");
			// createAccountConvert(sourceId, Constant.CONVERT_TYPE_3, num,
			// num.multiply(rule.getConvertRate()), customerId, "余额转帐");
			// 判断其上级是否能获取奖励
			CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
			if (null != referee && !"0".equals(referee.getRefereeId())) {
				rewardParent(customerId, referee.getRefereeId(), num, Constant.PROFIT_STATUS_2, Constant.STATE_YES);
			}
			CuCustomerReferee sourceReferee = cuCustomerRefereeDao.get(sourceId);
			if (null != sourceReferee && !"0".equals(sourceReferee.getRefereeId())) {
				rewardParent(sourceId, sourceReferee.getRefereeId(), num, Constant.PROFIT_STATUS_1, Constant.STATE_YES);
			}
		}
	}

	/**
	 * 余额转积分
	 */
	public void dealMoneyToIntegral(String customerId, BigDecimal num) {
		// 判断会员余额是否足够
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "获取会员账户失败");
		if (account.getUseMoney().doubleValue() >= num.doubleValue()) {
			SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
			CommonSupport.checkNotNull(rule, "获取平台规则失败");
			account.setUseMoney(account.getUseMoney().subtract(num));
			account.setTotalMoney(account.getTotalMoney().subtract(num));
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("customerId", account.getId());
			paramMap.put("convertType", Constant.CONVERT_TYPE_1);
			long count = cuAccountConvertDao.count(paramMap);
			BigDecimal rateToIntegral = BigDecimal.ZERO;
			if (count > 0) {
				rateToIntegral = rule.getRateToIntegralMore().multiply(num);
			} else {
				rateToIntegral = rule.getRateToIntegralFirst().multiply(num);
			}
			account.setCustomerIntegral(account.getCustomerIntegral().add(rateToIntegral));
			cuCustomerAccountDao.save(account);
			checkCustomerLevel(account.getId(), account.getCustomerIntegral());
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MC转换");
			doAddCustomerProfit(customerId, null, rateToIntegral, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "MC转换");
			createAccountConvert(customerId, Constant.CONVERT_TYPE_1, num, rateToIntegral, "", "MC转换");
			// 判断是否有推荐人
			CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
			if (null != referee && !"0".equals(referee.getRefereeId())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", referee.getRefereeId());
				CustomerCountLevelVo level = cuCustomerCountDao.getExtend(map, "queryCustomerCountLevel");
				CommonSupport.checkNotNull(level, "获取会员账户失败");
				boolean flag = IntegralToMoney(referee.getRefereeId(), num.multiply(level.getSalfRewardRate()));
				if (flag) {
					doAddCustomerProfit(referee.getRefereeId(), customerId, num.multiply(level.getSalfRewardRate()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "分享收益");
					doAddCustomerProfit(referee.getRefereeId(), customerId, num.multiply(level.getSalfRewardRate()), Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_3, "分享释放");
				}
				rewardParent(customerId, referee.getRefereeId(), num, Constant.PROFIT_STATUS_2, Constant.STATE_YES, true);
			}
		}
	}

	/**
	 * 余额兑换链
	 */
	public void moneyToCoin(String customerId, BigDecimal num) {
		// 判断会员余额是否足够
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "获取会员账户失败");
		if (account.getUseMoney().doubleValue() >= num.doubleValue()) {
			// 获取今日市值
			SysMarketValue marketValue = sysMarketValueDao.queryOne(new QueryFilterBuilder().put("nowDate", new Date()).build());
			CommonSupport.checkNotNull(marketValue, "获取市值失败");
			BigDecimal coin = num.divide(marketValue.getRmbRate(), 5, BigDecimal.ROUND_HALF_UP);
			account.setUseCoin(account.getUseCoin().add(coin));
			account.setTotalCoin(account.getTotalCoin().add(coin));
			account.setTotalMoney(account.getTotalMoney().subtract(num));
			account.setUseMoney(account.getUseMoney().subtract(num));
			cuCustomerAccountDao.save(account);
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MNC兑换");
			doAddCustomerProfit(customerId, null, coin, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "MNC兑换");
			createAccountConvert(account.getId(), Constant.CONVERT_TYPE_4, num, coin, customerId, "MNC兑换");
			// 判断其上级是否能获取奖励
			CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
			if (null != referee && !"0".equals(referee.getRefereeId())) {
				rewardParent(customerId, referee.getRefereeId(), num, Constant.PROFIT_STATUS_2, Constant.STATE_YES);
			}
		}
	}

	/**
	 * 链兑换余额
	 */
	public void coinToMoney(String customerId, BigDecimal num) {
		// 判断会员余额是否足够
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "获取会员账户失败");
		if (account.getUseCoin().doubleValue() >= num.doubleValue()) {
			// 获取今日市值
			SysMarketValue marketValue = sysMarketValueDao.queryOne(new QueryFilterBuilder().put("nowDate", new Date()).build());
			CommonSupport.checkNotNull(marketValue, "获取市值失败");
			BigDecimal money = num.multiply(marketValue.getRmbRate());
			// 系统配置
			SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
			CommonSupport.checkNotNull(rule, "系统规则不存在");
			account.setUseMoney(account.getUseMoney().add(money.multiply(rule.getConvertRate())));
			account.setTotalMoney(account.getTotalMoney().add(money.multiply(rule.getConvertRate())));
			account.setCustomerIntegral(account.getCustomerIntegral().add(money.subtract(money.multiply(rule.getConvertRate()))));
			account.setTotalCoin(account.getTotalCoin().subtract(num));
			account.setUseCoin(account.getUseCoin().subtract(num));
			cuCustomerAccountDao.save(account);
			doAddCustomerProfit(customerId, null, money.multiply(rule.getConvertRate()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "货币转换");
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_2, "货币转换");
			doAddCustomerProfit(customerId, null, money.subtract(money.multiply(rule.getConvertRate())), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "货币转换");
			createAccountConvert(account.getId(), Constant.CONVERT_TYPE_5, num, money, customerId, "货币转换");
			// 判断其上级是否能获取奖励
			CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
			if (null != referee && !"0".equals(referee.getRefereeId())) {
				rewardParent(customerId, referee.getRefereeId(), num, Constant.PROFIT_STATUS_1, Constant.STATE_YES);
			}
		}
	}

	/**
	 * 判断用户是否有未处理完的交易
	 */
	public boolean checkCustomerTrade(String customerId, SysRule rule, int type) {
		long num = 0L;
		long buy = 0L;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LTtradeStatus", Constant.TRADE_STATUS_4);
		map.put("customerId", customerId);
		if (Constant.MONEY_TYPE_1 == type) {
			num = trTradeMoneyDao.count(map);
		} else if (Constant.MONEY_TYPE_2 == type) {
			num = trTradeCoinDao.count(map);
		} else if (Constant.MONEY_TYPE_3 == type) {
			num = trTradeConvertDao.count(map);
		}
		map.clear();
		map.put("LTtradeStatus", Constant.TRADE_STATUS_3);
		map.put("customerBuyId", customerId);
		if (Constant.MONEY_TYPE_1 == type) {
			buy = trTradeMoneyDao.count(map);
		} else if (Constant.MONEY_TYPE_2 == type) {
			buy = trTradeCoinDao.count(map);
		} else if (Constant.MONEY_TYPE_3 == type) {
			buy = trTradeConvertDao.count(map);
		}
		if (num + buy >= rule.getCustomerTradeNum()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断当前用户是否是一笔出售一笔求购
	 *
	 * @param customerId
	 * @param tradeType
	 * @param moneyType
	 * @return
	 */
	public boolean checkTradeTypeIsOk(String customerId, Integer tradeType, Integer moneyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LTtradeStatus", Constant.TRADE_STATUS_4);
		if (Constant.MONEY_TYPE_1 == moneyType) {
			if (Constant.TRADE_TYPE_1 == tradeType) {
				map.put("customerId", customerId);
				long num = trTradeMoneyDao.count(map);
				if (0 < num) {
					return true;
				}
			} else {
				map.put("customerBuyId", customerId);
				long num = trTradeMoneyDao.count(map);
				if (0 < num) {
					return true;
				}
			}
		} else if (Constant.MONEY_TYPE_2 == moneyType) {
			if (Constant.TRADE_TYPE_1 == tradeType) {
				map.put("customerId", customerId);
				long num = trTradeCoinDao.count(map);
				if (0 < num) {
					return true;
				}
			} else {
				map.put("customerBuyId", customerId);
				long num = trTradeCoinDao.count(map);
				if (0 < num) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 对交易规则的校验
	 *
	 * @param rule
	 *            系统规则
	 * @param num
	 *            数量
	 * @param money
	 *            金额
	 * @param now
	 *            当前时间
	 * @param tradeType
	 *            交易类型(1.售出 2.买入)
	 * @param moneyType
	 *            货币类型
	 * @param flag
	 *            是否对数量,金额进行判断 true是 false否
	 * @return
	 */
	public String checkTradeRule(SysRule rule, BigDecimal num, BigDecimal money, Date now, int tradeType, int moneyType, boolean flag) {
		// 交易时间
		if (DateUtils.getDateTimesByHours(rule.getTradeStartTime()) > now.getTime() || now.getTime() > DateUtils.getDateTimesByHours(rule.getTradeEndTime())) {
			return "温馨提示:交易中心开放时间，" + rule.getTradeStartTime() + "点至" + rule.getTradeEndTime() + "点，当前时间不能交易";
		}
		// 每笔数量判断
		if (flag) {
			if (num.doubleValue() < rule.getTradeMinNum().doubleValue() || num.doubleValue() > rule.getTradeMaxNum().doubleValue()) {
				return "您的数量不合法";
			}
			if (Constant.TRADE_TYPE_1 == tradeType) { // 出售
				if (Constant.MONEY_TYPE_2 == moneyType) {
					SysMarketValue marketValue = sysMarketValueDao.queryOne(new QueryFilterBuilder().put("nowDate", now).build());
					if (null == marketValue) {
						return "暂无今日市值";
					}
					if (money.doubleValue() < marketValue.getDollarRate().multiply(rule.getMallMinPrice()).doubleValue()
							|| money.doubleValue() > marketValue.getDollarRate().multiply(rule.getMallMaxPrice()).doubleValue()) {
						return "温馨提示:价格不符合平台规则";
					}
				}
			} else if (Constant.TRADE_TYPE_2 == tradeType) { // 购买
				if (Constant.MONEY_TYPE_2 == moneyType) {
					SysMarketValue marketValue = sysMarketValueDao.queryOne(new QueryFilterBuilder().put("nowDate", now).build());
					if (null == marketValue) {
						return "暂无今日市值";
					}
					if (money.doubleValue() < marketValue.getDollarRate().multiply(rule.getBuyMinPrice()).doubleValue()
							|| money.doubleValue() > marketValue.getDollarRate().multiply(rule.getBuyMaxPrice()).doubleValue()) {
						return "温馨提示:价格不符合平台规则";
					}
				}
			} else {
				return "交易类型不合法";
			}
		}
		return null;
	}

	/**
	 * 余额挂卖
	 */
	public void confirmTradeForMoney(String customerId, String sourceId) {
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("tradeIng", "tradeIng").build());
		CommonSupport.checkNotNull(trade, "该交易状态无法确认");
		// 卖方账号
		CuCustomerAccount mallAccount = cuCustomerAccountDao.get(trade.getCustomerId());
		CommonSupport.checkNotNull(mallAccount, "用户account错误, id:" + mallAccount);
		// 系统规则
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "获取平台规则失败");
		if (mallAccount.getLockMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
			CommonSupport.checkNotNull(null, "卖方账户数据错误");
		}
		mallAccount.setLockMoney(mallAccount.getLockMoney().subtract(trade.getLockMoneyNum()));
		mallAccount.setCustomerIntegral(mallAccount.getCustomerIntegral().add(trade.getMoneyNum().multiply(rule.getMoneyTradeRateSale())));
		cuCustomerAccountDao.save(mallAccount);
		checkCustomerLevel(mallAccount.getId(), mallAccount.getCustomerIntegral());
		doAddCustomerProfit(mallAccount.getId(), trade.getId(), trade.getMoneyNum().multiply(rule.getMoneyTradeRateSale()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "交易赠送");
		// 买方账号
		CuCustomerAccount buyAccount = cuCustomerAccountDao.get(trade.getCustomerBuyId());
		CommonSupport.checkNotNull(buyAccount, "获取买方账户错误");
		// 买方账号进行操作
		BigDecimal money = trade.getMoneyNum().multiply(rule.getConvertRate());
		BigDecimal integral = trade.getMoneyNum().multiply(BigDecimal.ONE.subtract(rule.getConvertRate()));
		BigDecimal rewardIntegral = trade.getMoneyNum().multiply(rule.getMoneyTradeRate());
		buyAccount.setUseMoney(buyAccount.getUseMoney().add(money));
		buyAccount.setTotalMoney(buyAccount.getTotalMoney().add(money));
		buyAccount.setCustomerIntegral(buyAccount.getCustomerIntegral().add(integral).add(rewardIntegral));
		cuCustomerAccountDao.save(buyAccount);
		checkCustomerLevel(buyAccount.getId(), buyAccount.getCustomerIntegral());
		doAddCustomerProfit(buyAccount.getId(), trade.getId(), money, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "交易收入");
		doAddCustomerProfit(buyAccount.getId(), trade.getId(), integral, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "交易收入");
		doAddCustomerProfit(buyAccount.getId(), trade.getId(), rewardIntegral, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "交易赠送");
		addSysProfit(trade.getId(), Constant.TRADE_STATUS_3, trade.getLockMoneyNum().subtract(trade.getMoneyNum()), "余额交易手续");
		trade.setTradeStatus(Constant.TRADE_STATUS_6);
		trade.setTradeFinishTime(new Date());
		trTradeMoneyDao.save(trade);
		// 判断其上级是否能获取奖励
		CuCustomerReferee referee = cuCustomerRefereeDao.get(trade.getCustomerId());
		if (null != referee && !"0".equals(referee.getRefereeId())) {
			rewardParent(trade.getCustomerId(), referee.getRefereeId(), trade.getMoneyNum(), Constant.PROFIT_STATUS_2, Constant.STATE_YES);
		}
		/*
		 * CuCustomerReferee sourceReferee =
		 * cuCustomerRefereeDao.get(trade.getCustomerBuyId()); if (null != sourceReferee
		 * && !"0".equals(sourceReferee.getRefereeId())) { rewardParent(sourceId,
		 * sourceReferee.getRefereeId(), trade.getMoneyNum(), Constant.PROFIT_STATUS_1,
		 * Constant.STATE_YES); }
		 */
	}

	/**
	 * 链确认
	 */
	public void confirmTradeForCoin(String customerId, String sourceId) {
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("tradeIng", "tradeIng").build());
		CommonSupport.checkNotNull(trade, "该交易状态无法确认");
		// 系统规则
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "获取平台规则失败");
		// 卖方账号
		CuCustomerAccount mallAccount = cuCustomerAccountDao.get(trade.getCustomerId());
		CommonSupport.checkNotNull(mallAccount, "用户account错误, id:" + mallAccount);
		if (mallAccount.getLockCoin().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
			CommonSupport.checkNotNull(null, "MNC交易数据错误");
		}
		mallAccount.setLockCoin(mallAccount.getLockCoin().subtract(trade.getLockMoneyNum()));
		cuCustomerAccountDao.save(mallAccount);
		checkCustomerLevel(mallAccount.getId(), mallAccount.getCustomerIntegral());
		// 买方账号
		CuCustomerAccount buyAccount = cuCustomerAccountDao.get(trade.getCustomerBuyId());
		CommonSupport.checkNotNull(buyAccount, "用户account错误, id:" + buyAccount);
		// 买方账号进行操作
		buyAccount.setUseCoin(buyAccount.getUseCoin().add(trade.getMoneyNum()));
		buyAccount.setTotalCoin(buyAccount.getTotalCoin().add(trade.getMoneyNum()));
		cuCustomerAccountDao.save(buyAccount);
		checkCustomerLevel(buyAccount.getId(), buyAccount.getCustomerIntegral());
		doAddCustomerProfit(buyAccount.getId(), trade.getId(), trade.getMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "MNC交易收入");
		addSysProfit(trade.getId(), Constant.TRADE_STATUS_3, trade.getLockMoneyNum().subtract(trade.getMoneyNum()), "MNC交易手续");
		trade.setTradeStatus(Constant.TRADE_STATUS_6);
		trade.setTradeFinishTime(new Date());
		trTradeCoinDao.save(trade);
	}

	/**
	 * 余额转账
	 */
	@Transactional
	public synchronized void confirmTradeForConvert(String customerId, String sourceId) {
		TrTradeConvert trade = trTradeConvertDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("tradeIng", "tradeIng").build());
		CommonSupport.checkNotNull(trade, "交易不存在或当前状态无法确认");
		// 系统规则
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "获取平台规则失败");
		CuCustomerAccount mallAccount = cuCustomerAccountDao.get(trade.getCustomerId());
		CommonSupport.checkNotNull(mallAccount, "用户account错误, id:" + mallAccount);
		if (mallAccount.getLockMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
			CommonSupport.checkNotNull(null, "会员交易数据错误");
		}
		// 买方账号
		CuCustomerAccount buyAccount = cuCustomerAccountDao.get(trade.getCustomerBuyId());
		CommonSupport.checkNotNull(buyAccount, "用户account错误, id:" + buyAccount);
		// 卖方账号进行操作
		BigDecimal reward = trade.getLockMoneyNum().multiply(rule.getMoneyOutRate());
		MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId", mallAccount.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());
		if (null == shop) {
			MallShopAdvert advert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", mallAccount.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());
			if (null == advert) {
				shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId", buyAccount.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());
				if (null == shop) {
					advert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", buyAccount.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());
					if (null == advert) {
						// 会员转账奖励
						SysConfig config = sysConfigDao.getConfigByCode("TRANSFER_MONEY_OUT_REWARD_RATE");
						reward = Constant.STATE_YES == config.getConfigFlag() ? config.getConfigValue() : BigDecimal.ZERO;
					}
				}
			}
		}
		mallAccount.setLockMoney(mallAccount.getLockMoney().subtract(trade.getLockMoneyNum()));
		mallAccount.setCustomerIntegral(mallAccount.getCustomerIntegral().add(reward));
		cuCustomerAccountDao.save(mallAccount);
		doAddCustomerProfit(mallAccount.getId(), sourceId, reward, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "余额定向转账");
		checkCustomerLevel(mallAccount.getId(), mallAccount.getCustomerIntegral());
		// 买方账号进行操作
		BigDecimal buyReward = trade.getLockMoneyNum().subtract(trade.getLockMoneyNum().multiply(rule.getConvertRate()));
		buyAccount.setUseMoney(buyAccount.getUseMoney().add(trade.getLockMoneyNum().multiply(rule.getConvertRate())));
		buyAccount.setTotalMoney(buyAccount.getTotalMoney().add(trade.getLockMoneyNum().multiply(rule.getConvertRate())));
		buyAccount.setCustomerIntegral(buyAccount.getCustomerIntegral().add(buyReward));
		cuCustomerAccountDao.save(buyAccount);
		checkCustomerLevel(buyAccount.getId(), buyAccount.getCustomerIntegral());
		doAddCustomerProfit(buyAccount.getId(), sourceId, trade.getLockMoneyNum().multiply(rule.getConvertRate()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "余额定向转账");
		doAddCustomerProfit(buyAccount.getId(), sourceId, buyReward, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "余额定向转账");
		trade.setTradeStatus(Constant.TRADE_STATUS_6);
		trade.setTradeFinishTime(new Date());
		trTradeConvertDao.update(trade);

		// 判断其上级是否能获取奖励
		CuCustomerReferee referee = cuCustomerRefereeDao.get(trade.getCustomerId());
		if (null != referee && !"0".equals(referee.getRefereeId())) {
			rewardParent(trade.getCustomerId(), referee.getRefereeId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_2, Constant.STATE_YES);
		}
		/*
		 * CuCustomerReferee sourceReferee =
		 * cuCustomerRefereeDao.get(trade.getCustomerBuyId()); if (null != sourceReferee
		 * && !"0".equals(sourceReferee.getRefereeId())) {
		 * rewardParent(trade.getCustomerBuyId(), sourceReferee.getRefereeId(),
		 * trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.STATE_YES); }
		 */
	}

	/**
	 * 判断parentId是否在customerId的团队中
	 */
	public boolean checkWithTeam(String customerId, String parentId) {
		if (!"0".equals(parentId)) {
			if (!customerId.equals(parentId)) {
				CuCustomerReferee referee = cuCustomerRefereeDao.get(parentId);
				CommonSupport.checkNotNull(referee, "获取推荐人错误");
				if (!referee.getRefereeId().equals(customerId)) {
					return checkWithTeam(customerId, referee.getRefereeId());
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 修改推荐人更新团队人数 flag为true增加，flag为false减少
	 */
	public void doUpdateTeamNumByReferee(String refereeId, int teamNum, boolean flagSalf, boolean flag) {
		if (org.apache.commons.lang3.StringUtils.isNotBlank(refereeId) && !"0".equals(refereeId)) {
			CuCustomerCount count = cuCustomerCountDao.get(refereeId);
			CommonSupport.checkNotNull(count, "推荐人count出错了, id:" + refereeId);
			if (flag) {
				if (flagSalf) {
					count.setSalfNum(count.getSalfNum() + 1);
				}
				count.setTeamNum(count.getTeamNum() + teamNum);
			} else {
				if (flagSalf) {
					if (count.getSalfNum() >= 1) {
						count.setSalfNum(count.getSalfNum() - 1);
					} else {
						count.setSalfNum(0);
					}

				}
				if (count.getTeamNum().intValue() >= teamNum) {
					count.setTeamNum(count.getTeamNum() - teamNum);
				} else {
					count.setTeamNum(1);
				}
			}
			cuCustomerCountDao.save(count);
			CuCustomerReferee referee = cuCustomerRefereeDao.get(refereeId);
			if (null != referee && !"0".equals(referee.getRefereeId())) {
				doUpdateTeamNumByReferee(referee.getRefereeId(), teamNum, false, flag);
			}
		}
	}

	/**
	 * 冻结用户
	 *
	 * @param customerId
	 *            用户id
	 * @param freezeMsg
	 *            冻结原因
	 */
	public void doFrozenCustomer(String customerId, String freezeMsg) {
		CuCustomerInfo info = cuCustomerInfoDao.get(customerId);
		CommonSupport.checkNotNull(info, "用户info的错误， id:" + customerId);
		CuCustomerLogin login = cuCustomerLoginDao.get(customerId);
		CommonSupport.checkNotNull(login, "用户login的错误， id:" + customerId);
		info.customerStatus(Constant.OPER_STATUS_FAIL).setCustomerFreezeContext(freezeMsg);
		login.customerStatus(Constant.OPER_STATUS_FAIL);
		cuCustomerInfoDao.save(info);
		cuCustomerLoginDao.save(login);
	}

	/**
	 * 更新会员余额
	 */
	public void doUpdateMoney(String customerId, BigDecimal num, Boolean flagReward) {
		if (num.doubleValue() <= 0) {
			CommonSupport.checkNotNull(null, "金额不合法");
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误，id:" + customerId);
		if (flagReward) {
			account.setUseMoney(account.getUseMoney().add(num));
			account.setTotalMoney(account.getTotalMoney().add(num));
		} else {
			if (account.getUseMoney().doubleValue() >= num.doubleValue()) {
				account.setUseMoney(account.getUseMoney().subtract(num));
				account.setTotalMoney(account.getTotalMoney().subtract(num));
			} else {
				CommonSupport.checkNotNull(null, "MC不足，无法扣除");
			}
		}
		cuCustomerAccountDao.save(account);
	}

	/**
	 * 更新会员交易链
	 */
	public void doUpdateTradeCoin(String customerId, BigDecimal num, Boolean flagReward) {
		if (num.doubleValue() <= 0) {
			CommonSupport.checkNotNull(null, "金额不合法");
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误，id:" + customerId);
		if (flagReward) {
			account.setTradeCoin(account.getTradeCoin().add(num));
		} else {
			if (account.getTradeCoin().doubleValue() >= num.doubleValue()) {
				account.setTradeCoin(account.getTradeCoin().subtract(num));
			} else {
				CommonSupport.checkNotNull(null, "交易链不足，无法扣除");
			}
		}
		cuCustomerAccountDao.save(account);
	}

	/**
	 * 更新会员积分
	 */
	public void doUpdateIntegral(String customerId, BigDecimal num, Boolean flagReward) {
		if (num.doubleValue() <= 0) {
			CommonSupport.checkNotNull(null, "金额不合法");
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误，id:" + customerId);
		if (flagReward) {
			account.setCustomerIntegral(account.getCustomerIntegral().add(num));
		} else {
			if (account.getCustomerIntegral().doubleValue() >= num.doubleValue()) {
				account.setCustomerIntegral(account.getCustomerIntegral().subtract(num));
			} else {
				CommonSupport.checkNotNull(null, "MP不足，无法扣除");
			}
		}
		cuCustomerAccountDao.save(account);
		checkCustomerLevel(account.getId(), account.getCustomerIntegral());// 根据积分更新等级
	}

	/**
	 * 更新会员链
	 */
	public void doUpdateCoin(String customerId, BigDecimal num, Boolean flagReward) {
		if (num.doubleValue() <= 0) {
			CommonSupport.checkNotNull(null, "金额不合法");
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误，id:" + customerId);
		if (flagReward) {
			account.setUseCoin(account.getUseCoin().add(num));
			account.setTotalCoin(account.getTotalCoin().add(num));
		} else {
			if (account.getUseCoin().doubleValue() >= num.doubleValue()) {
				account.setUseCoin(account.getUseCoin().subtract(num));
				account.setTotalCoin(account.getTotalCoin().subtract(num));
			} else {
				CommonSupport.checkNotNull("MNC不足，无法扣除");
			}
		}
		cuCustomerAccountDao.save(account);
	}

	/**
	 *
	 * @Title: createActiveMq @Description: 发送消息 @param @param customerId 会员ID @param @param type 类型 @param @param num 数量 @param @param sourceId 关联ID @param @param remark 参数 @return void 返回类型 @throws
	 */
	public void createActiveMq(String customerId, int activemqType, BigDecimal num, String sourceId, String remark, Object obj) {
		JSONObject json = new JSONObject();
		json.put("customerId", customerId);
		json.put("activemqType", activemqType);
		json.put("num", num);
		json.put("sourceId", sourceId);
		json.put("obj", JSONObject.toJSONString(obj));
		SysActivemqRequest activemqRequest = new SysActivemqRequest();
		activemqRequest.customerId(customerId).activemqType(activemqType + "").activemqContext(json.toJSONString()).createDate(new Date()).activemqRemark(remark);
		sysActivemqRequestDao.save(activemqRequest);
		json.put("activemqId", activemqRequest.getId());
		queueSender.sendMessage(queueDestination, json.toJSONString());
	}


	public void createActiveMq2(String customerId, int activemqType, GtyWallet wallet, Object obj) {
		JSONObject json = new JSONObject();
		json.put("customerId", customerId);
		json.put("activemqType", activemqType);
		json.put("sourceId", "121");
		json.put("obj", JSONObject.toJSONString(obj));

		gtyWalletDao.save(wallet);
		json.put("activemqId", customerId+System.currentTimeMillis()/1000);
		queueSender.sendMessage(queueDestination, json.toJSONString());
	}

	public void createSysActivemqResponse(String customerId, int activemqType, String activemqId, String activemqResponse) {
		SysActivemqResponse response = new SysActivemqResponse();
		response.setId(activemqId);
		response.setActivemqType(activemqType + "");
		response.setActivemqResponse(activemqResponse);
		response.setCustomerId(customerId);
		response.setCreateDate(new Date());
		sysActivemqResponseDao.save(response);
	}

	/**
	 * 根据手机号获取商家信息
	 */
	public MallShop selectMallShop(String phone) {
		CuCustomerLogin customer = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerPhone", phone).build());
		if (null != customer) {
			MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId", customer.getId()).build());
			return shop;
		}
		return null;
	}

	/**
	 * 添加用户消息
	 *
	 * @param customerId
	 * @param sourceId
	 * @param messageTitle
	 * @param messageContext
	 * @param messageType
	 */
	public void pushCustomerMessage(String customerId, String sourceId, String messageTitle, String messageContext, Integer flagRead, Integer messageType) {
		CuCustomerMessage message = new CuCustomerMessage();
		message.setCustomerId(customerId);
		if (StringUtils.isNotBlank(sourceId)) {
			message.setAppMessageId(sourceId);
		}
		message.setMessageTitle(messageTitle);
		message.setMessageContext(messageContext);
		message.setMessageType(messageType);
		message.setFlagRead(flagRead);
		message.setCreateTime(new Date());
		cuCustomerMessageDao.save(message);
	}

	/**
	 * 获取今日市值
	 *
	 * @return
	 */
	public SysMarketValue getMarketValueToday() {
		SysMarketValue value = sysMarketValueDao.queryOne(new QueryFilterBuilder().put("nowDate", new Date()).build());
		if (null == value) {
			SysMarketValue valueNow = new SysMarketValue();
			value = sysMarketValueDao.queryOne(new QueryFilterBuilder().orderBy("sysMarketValue.RATE_TIME DESC").build());
			valueNow.setDollarRate(null == value ? new BigDecimal(0.5) : value.getDollarRate());
			valueNow.setRmbRate(valueNow.getDollarRate().multiply(new BigDecimal(7)));
			valueNow.setRateTime(new Date());
			valueNow.setCreateTime(new Date());
			sysMarketValueDao.save(valueNow);
			return valueNow;
		}
		return value;
	}

	/**
	 * 删除用户及节点以下
	 *
	 * @param customerId
	 */
	public void deleteCustomers(String customerId) {
		List<CuCustomerReferee> list = cuCustomerRefereeDao.query(new QueryFilterBuilder().put("refereeId", customerId).build());
		if (CollectionUtils.isNotEmpty(list)) {
			for (CuCustomerReferee referee : list) {
				deleteCustomers(referee.getId());
			}
		}
		CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
		if (null != referee && !"-1".equals(referee.getRefereeId())) {
			deleteCustomer(customerId, referee);
		}
	}

	/**
	 * 删除用户
	 *
	 * @param customerId
	 */
	public void deleteCustomer(String customerId, CuCustomerReferee referee) {
		cuCustomerLoginDao.delete(customerId);
		cuCustomerInfoDao.delete(customerId);
		cuCustomerAccountDao.delete(customerId);
		cuCustomerCountDao.delete(customerId);
		cuCustomerRefereeDao.delete(referee);
		Map<String, Object> map = new HashMap<>();
		map.put("customerId", customerId);
		cuCustomerProfitDao.deleteAll(map);
		cuCustomerProfitAdminDao.deleteAll(map);
		cuCustomerMessageDao.deleteAll(map);
		cuAccountConvertDao.deleteAll(map);
	}

	/**
	 * 获取国际化配置文件中的数据
	 */
	public static String getMessage(HttpServletRequest request, String key) {
		RequestContext requestContext = new RequestContext(request);
		String value = requestContext.getMessage(key);
		return value;
	}

	/**
	 * 算出该用户的团队人数
	 */
	public int sumCustomerTeam(String customerId, int num) {
		List<CuCustomerReferee> list = cuCustomerRefereeDao.query(new QueryFilterBuilder().put("refereeId", customerId).build());
		if (CollectionUtils.isNotEmpty(list)) {
			num += list.size();
			for (CuCustomerReferee referee : list) {
				num = sumCustomerTeam(referee.getId(), num);
			}
		}
		return num;
	}

	/**
	 * 判断验证码
	 *
	 * @param phone
	 * @param codeType
	 * @param code
	 * @return
	 */
	public String checkSmsCode(String phone, Integer codeType, String code, String countryId, HttpServletRequest req) {
		if (StringUtils.isEmpty(phone)) {
			return getMessage(req, "smsRequest.phone");
		}
		if (StringUtils.isEmpty(code)) {
			return getMessage(req, "registerUserRequest.code");
		}
		if (null == codeType) {
			return getMessage(req, "emailRequest.emailType");
		}
		SysConfig config = sysConfigDao.getConfigByCode("SYS_OMNIPOTENT_SMS_CODE");
		if (null != config && Constant.STATE_YES == config.getConfigFlag()) {
			if (code.equals(config.getConfigValue().intValue() + "")) {
				return null;
			}
		}
		AppCode appCode = null;
		//手机号
		if (!phone.contains("@")) {
			String codeMobile = phone;
			if (!Constant.COUNTRY_CHINA.equals(countryId)) {
				SysCountry country = sysCountryDao.get(countryId);
				if (null == country) {
					return getMessage(req, "impl.doRegisterUser.country.nonexistent");
				}
				//codeMobile = country.getCountryNum() + phone;
			}
			appCode = appCodeDao
					.queryOne(new QueryFilterBuilder().put("codeMobile", codeMobile).put("codeState", Constant.STATE_YES).put("codeType", codeType).orderBy("CODE_CREATE_TIME DESC").build());
		}
		if (null == appCode || !code.equals(appCode.getCodeCode())) {
			return getMessage(req, "impl.doRegisterUser.sms.error");
		}
		appCode.setCodeState(Constant.STATE_NO);
		appCodeDao.save(appCode);
		return null;
	}

	/**
	 * 判断今日是否还可以出售
	 *
	 * @return
	 */
	public boolean judgeTradeTime(String customerId) {
		Map<String, Object> map = new HashMap<>();
		map.put("customerId", customerId);
		map.put("notTradeStatus", Constant.TRADE_STATUS_5);
		map.put("createTimeNow", new Date());
		long count = trTradeMoneyDao.count(map);
		SysConfig config = sysConfigDao.getConfigByCode("TRADE_MC_SELL_TIME");
		if (config.getConfigValue().intValue() > count) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否可以买链
	 *
	 * @return
	 */
	public String judgeLimitCoin(String customerId, BigDecimal num) {
		TrConvert convert = trConvertDao.queryOne(new QueryFilterBuilder().orderBy("trConvert.CREATE_TIME DESC").build());
		Map<String, Object> map = new HashMap<>();
		map.put("customerId", customerId);
		map.put("sourceId", convert.getConvertSerial());
		map.put("convertType", Constant.CONVERT_TYPE_4);
		BigDecimal total = cuAccountConvertDao.getExtend(map, "sumConvertNum");
		CuCustomerCount count = cuCustomerCountDao.get(customerId);
		SysCustomerLevel level = sysCustomerLevelDao.get(count.getCustomerLevelId());
		if (null == level) {
			return "等级错误";
		}
		if (total.add(num).doubleValue() > level.getLimitCoin().doubleValue()) {
			return "已达限购额度";
		}
		return null;
	}

}

