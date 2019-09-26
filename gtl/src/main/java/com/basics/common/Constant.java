package com.basics.common;

import org.springframework.validation.BindingResult;

import com.basics.support.DataStatusException;

/**
 * 项目内常量定义.
 */
public class Constant {

	/**
	 * 成功.
	 */
	public static final int RETURN_CODE_SUCCESS = 0;

	/**
	 * 失败.
	 */
	public static final int RETURN_CODE_FAIL = 1;

	/**
	 * token过期错误代码.
	 */
	public static final int RETURN_CODE_TOKEN_EXPIRED = 1000;

	/**
	 * token过期时间.
	 */
	public static final int RETURN_CODE_TOKEN_TIMEOUT = 7200000;// 2小时

	/**
	 * token过期错误提示.
	 */
	public static final String RETURN_MSG_TOKEN_EXPIRED = "token过期";

	/**
	 * clientId无效.
	 */
	public static final int RETURN_CODE_CLIENT_ID_INVALID = 2000;

	/**
	 * clientId无效提示.
	 */
	public static final String RETURN_MSG_CLIENT_ID_INVALID = "clientId无效";

	/**
	 * yes
	 */
	public static final int STATE_YES = 1;

	/**
	 * no.
	 */
	public static final int STATE_NO = 0;

	public static final int PAGE_NUM = 1;

	public static final int PAGE_SIZE = 10;

	/**
	 * 抛出token过期异常.
	 */
	public static void throwTokenExpiredException() {
		throw new DataStatusException(RETURN_CODE_TOKEN_EXPIRED, RETURN_MSG_TOKEN_EXPIRED);
	}

	/**
	 * 抛出client无效异常.
	 */
	public static void throwClientIdInvalidException() {
		throw new DataStatusException(RETURN_CODE_CLIENT_ID_INVALID, RETURN_MSG_CLIENT_ID_INVALID);
	}

	/**
	 * 抛出绑定结果异常.
	 *
	 * @param result
	 */
	public static void checkBindingResult(BindingResult result) {
		if (result.hasErrors()) {
			throw new DataStatusException(Constant.RETURN_CODE_FAIL, ErrorMessageHelper.praseErrorMessage(result.getAllErrors()));
		}
	}

	/**
	 * 手机号码正则表达式
	 */
	public static final String MOBILE_REGX = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
	/**
	 * 国际手机号
	 */
	public static final String MOBILE_REGX_2 = "^(((\\+\\d{2}-)?0\\d{2,3}-\\d{7,8})|((\\+\\d{2}-)?(\\d{2,3}-)?([1][3,4,5,7,8][0-9]\\d{8})))$";

	/**
	 * 邮箱正则表达式
	 */
	public static final String EMAIL_REGX = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

	/**
	 * 身份证15位正则表达式
	 */
	public static final String AUTH_15_REGX = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";

	/**
	 * 身份证18位正则表达式
	 */
	public static final String AUTH_18_REGX = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

	public static final String LOWER_CUSTOMER_LEVEL = "-1"; // 平台会员等级最低

	public static final int OPER_STATUS_SUCCESS = 1;// 1正常
	public static final int OPER_STATUS_FAIL = 0;// 2异常

	/**
	 * 短信类型
	 */
	public static final int SMS_TYPE_1 = 1;// 注册
	public static final int SMS_TYPE_2 = 2;// 忘记密码
	public static final int SMS_TYPE_3 = 3;// 修改登录密码
	public static final int SMS_TYPE_4 = 4;// 修改支付密码
	public static final int SMS_TYPE_5 = 5;// 余额转链
	public static final int SMS_TYPE_6 = 6;// 商家修改密码

	/**
	 * 审核状态
	 */
	public static final int APPLY_STATUS_1 = 1; // 待审核
	public static final int APPLY_STATUS_2 = 2; // 审核通过
	public static final int APPLY_STATUS_3 = 3; // 审核不通过

	/**
	 * 交易状态
	 */
	public static final int TRADE_STATUS_1 = 1;// 待交易
	public static final int TRADE_STATUS_2 = 2;// 待支付
	public static final int TRADE_STATUS_3 = 3;// 支付完成
	public static final int TRADE_STATUS_4 = 4;// 交易投诉
	public static final int TRADE_STATUS_5 = 5;// 交易取消
	public static final int TRADE_STATUS_6 = 6;// 交易完成

	/**
	 * banner类型
	 */
	public static final int BANNER_TYPE_1 = 1; // 首页
	public static final int BANNER_TYPE_2 = 2; // 商城

	/**
	 * 公告类型
	 */
	public static final int BULLETIN_TYPE_1 = 1; // 首页公告
	public static final int BULLETIN_TYPE_2 = 2;// 2用户消息
	public static final int BULLETIN_TYPE_3 = 3;// 3新手入门
	public static final int BULLETIN_TYPE_4 = 4;// 4关于我们
	public static final int BULLETIN_TYPE_5 = 5;// 5首页滚动

	// public static final int BULLETIN_TYPE_2 = 2; // 用户公告

	// public static final int BULLETIN_TYPE_2 = 2; // 用户公告

	/**
	 * 流水状态
	 */
	public static final int PROFIT_STATUS_1 = 1;// 1收入
	public static final int PROFIT_STATUS_2 = 2;// 2支出

	/**
	 * 货币类型
	 */
	public static final int MONEY_TYPE_1 = 1; // 余额
	public static final int MONEY_TYPE_2 = 2; // 链
	public static final int MONEY_TYPE_3 = 3; // 积分
	public static final int MONEY_TYPE_4 = 4; // 交易链

	public static final int CONVERT_TYPE_1 = 1; // 余额转积分
	public static final int CONVERT_TYPE_2 = 2; // 余额出售
	public static final int CONVERT_TYPE_3 = 3; // 余额转账
	public static final int CONVERT_TYPE_4 = 4; // 余额转链
	public static final int CONVERT_TYPE_5 = 5; // 链转余额

	/**
	 * 交易类型
	 */
	public static final int TRADE_TYPE_1 = 1;// 1出售
	public static final int TRADE_TYPE_2 = 2;// 2购买

	/**
	 * 流水展示的最小收益值
	 */
	public static final double MIN_PROFIT_NUM_SHOW = 0.00001;

	/**
	 * 商品状态
	 */
	public static final int PRODUCT_STATUS_WAIT = 1;// 待上架
	public static final int PRODUCT_STATUS_SALE = 2;// 上架
	public static final int PRODUCT_STATUS_OFF = 3;// 已下架

	public static final int ORDER_STATUS_1 = 1;// 1待支付
	public static final int ORDER_STATUS_2 = 2;// 2待发货
	public static final int ORDER_STATUS_3 = 3;// 3待签收
	public static final int ORDER_STATUS_4 = 4;// 4待评价
	public static final int ORDER_STATUS_5 = 5;// 5订单完成
	public static final int ORDER_STATUS_6 = 6;// 6订单取消
	public static final int ORDER_STATUS_7 = 7;// 7订单退款

	public static final int SHOP_STATUS_1 = 1;// 营业
	public static final int SHOP_STATUS_2 = 2;// 停业

	/**
	 * 支付方式
	 */
	public static final int PAY_TYPE_1 = 1; // 余额
	public static final int PAY_TYPE_2 = 2; // 现金

	public static final int ORDER_PAY_STATUS_1 = 1;// 1待支付
	public static final int ORDER_PAY_STATUS_2 = 2;// 2支付失败
	public static final int ORDER_PAY_STATUS_3 = 3;// 3支付成功

	public static final Integer NUMBER_0 = 0;
	public static final Integer NUMBER_1 = 1;
	public static final Integer NUMBER_2 = 2;//
	public static final Integer NUMBER_3 = 3;//
	public static final Integer NUMBER_4 = 4;//
	public static final Integer NUMBER_5 = 5;//

	/**
	 * 未激活积分数值
	 */
	public static final double UN_ACTIVE_NUM = 800.0;
	public static final double UN_ACTIVE_TRADE_NUM = 1000.0;

	public static final int ACTIVEMQ_TYPE_1 = 1;// "后台余额发放";
	public static final int ACTIVEMQ_TYPE_2 = 2;// "后台余额扣除";
	public static final int ACTIVEMQ_TYPE_3 = 3;// "后台积分发放";
	public static final int ACTIVEMQ_TYPE_4 = 4;// "后台积分扣除";
	public static final int ACTIVEMQ_TYPE_5 = 5;// "后台链发放";
	public static final int ACTIVEMQ_TYPE_6 = 6;// "后台链扣除";
	public static final int ACTIVEMQ_TYPE_7 = 7;// "发布余额交易";
	public static final int ACTIVEMQ_TYPE_8 = 8;// "匹配余额交易";
	public static final int ACTIVEMQ_TYPE_9 = 9;// "完成余额交易";
	public static final int ACTIVEMQ_TYPE_10 = 10;// "取消余额交易";
	public static final int ACTIVEMQ_TYPE_11 = 11;// "余额定向转账";
	public static final int ACTIVEMQ_TYPE_12 = 12;// "完成余额定向转账";
	public static final int ACTIVEMQ_TYPE_13 = 13;// "取消余额定向转账";
	public static final int ACTIVEMQ_TYPE_14 = 14;// "发布链交易";
	public static final int ACTIVEMQ_TYPE_15 = 15;// "匹配链交易";
	public static final int ACTIVEMQ_TYPE_16 = 16;// "完成链交易";
	public static final int ACTIVEMQ_TYPE_17 = 17;// "取消链交易";
	public static final int ACTIVEMQ_TYPE_18 = 18;// "余额转积分";
	public static final int ACTIVEMQ_TYPE_19 = 19;// "余额转链";
	public static final int ACTIVEMQ_TYPE_20 = 20;// "链转余额";
	public static final int ACTIVEMQ_TYPE_21 = 21;// "后台完成余额交易";
	public static final int ACTIVEMQ_TYPE_22 = 22;// "后台取消余额交易";
	public static final int ACTIVEMQ_TYPE_23 = 23;// "后台完成定向交易";
	public static final int ACTIVEMQ_TYPE_24 = 24;// "后台取消定向交易";
	public static final int ACTIVEMQ_TYPE_25 = 25;// "后台完成链交易";
	public static final int ACTIVEMQ_TYPE_26 = 26;// "后台取消链交易";
	public static final int ACTIVEMQ_TYPE_27 = 27;// "静态释放";

	public static final int ACTIVEMQ_TYPE_28 = 28;// "商城支付";
	public static final int ACTIVEMQ_TYPE_29 = 29;// "确认收货";
	public static final int ACTIVEMQ_TYPE_30 = 30;// "商家申请";
	public static final int ACTIVEMQ_TYPE_31 = 31;// "商家通过";
	public static final int ACTIVEMQ_TYPE_32 = 32;// "商家拒绝";

	public static final int ACTIVEMQ_TYPE_33 = 33;// "大转盘抽奖";
	public static final int ACTIVEMQ_TYPE_34 = 34;// "商家取消订单";
	public static final int ACTIVEMQ_TYPE_35 = 35;// "二维码收付款";

	public static final int ACTIVEMQ_TYPE_36 = 36;// "规定时间未付款 冻结账号";

	public static final int ACTIVEMQ_TYPE_37 = 37;// "MC审核不通过, 取消交易";
	public static final int ACTIVEMQ_TYPE_38 = 38;// "MNC审核不通过, 取消交易";
	public static final int ACTIVEMQ_TYPE_39 = 39;// "签到";

	public static final int ACTIVEMQ_TYPE_40 = 40;// "后台交易链发放";
	public static final int ACTIVEMQ_TYPE_41 = 41;// "后台交易链扣除";

	public static final int ACTIVEMQ_TYPE_42 = 42;// "流通钱包释放";
	public static final int ACTIVEMQ_TYPE_43 = 43;// "流通钱包释放";

	/**
	 * 默认推荐人
	 */
	public static final String DEFAULT_REFEREE = "0";

	/**
	 * 消息类型
	 */
	public static final int Message_TYPE_1 = 1; //系统
	public static final int Message_TYPE_2 = 2; //交易
	public static final int Message_TYPE_3 = 3; //商城

	public static final String COUNTRY_CHINA = "e84a151ced294a04b45a8f2086fc7157";
	public static final String COUNTRY_KOREA = "6ddcd600225249e4984eeed742a5e35c";
	public static final String COUNTRY_JAPAN = "0e57b1a03e404b9ba9963aa3f4d940d9";

	public static final String AMCOIN_URL = "AMCOIN_URL";
	public static final String ML_URL = "ML_URL";

}
