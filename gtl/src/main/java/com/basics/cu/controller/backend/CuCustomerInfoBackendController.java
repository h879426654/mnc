package com.basics.cu.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerCount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.service.BaseAccountApiService;
import com.basics.cu.service.CuCustomerInfoService;
import com.basics.cu.service.CuCustomerProfitService;
import com.basics.cu.service.CuCustomerRefereeService;
import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;

@Controller
@RequestMapping("/backend/cu/cuCustomerInfo/")
public class CuCustomerInfoBackendController extends BaseBackendController<CuCustomerInfo> {

	@Autowired
	private CuCustomerInfoService cuCustomerInfoService;
	@Autowired
	private CuCustomerRefereeService cuCustomerRefereeService;
	@Autowired
	private CuCustomerProfitService cuCustomerProfitService;
	@Autowired
	private BaseAccountApiService baseAccountApiService;

	@RequestMapping(value = "/reward")
	public String reward() {
		return getView("reward");
	}

	@Override
	public void save(CuCustomerInfo entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = cuCustomerInfoService.updateCustomerInfo(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 奖励余额
	 */
	@RequestMapping(value = "/granRewardMoney")
	public void granRewardMoney(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doUpdateMoneyByAdmin(entity, true);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 奖励积分
	 */
	@RequestMapping(value = "/granRewardIntegral")
	public void granRewardIntegral(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doUpdateIntegralByAdmin(entity, true);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 奖励链
	 */
	@RequestMapping(value = "/granRewardCoin")
	public void granRewardCoin(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doUpdateCoinByAdmin(entity, true);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 奖励交易链
	 */
	@RequestMapping(value = "/granRewardTradeCoin")
	public void granRewardTradeCoin(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doUpdateTradeCoinByAdmin(entity, true);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 扣除余额
	 */
	@RequestMapping(value = "/granDeductMoney")
	public void granDeductMoney(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doUpdateMoneyByAdmin(entity, false);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 扣除积分
	 */
	@RequestMapping(value = "/granDeductIntegral")
	public void granDeductIntegral(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doUpdateIntegralByAdmin(entity, false);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 扣除链
	 */
	@RequestMapping(value = "/granDeductCoin")
	public void granDeductCoin(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doUpdateCoinByAdmin(entity, false);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 扣除交易链
	 */
	@RequestMapping(value = "/granDeductTradeCoin")
	public void granDeductTradeCoin(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doUpdateTradeCoinByAdmin(entity, false);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 修改推荐人
	 */

	@RequestMapping(value = "dealChangeReferee")
	public void dealChangeReferee(CuCustomerInfo entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.dealChangeReferee(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 删除用户
	 */
	@RequestMapping(value = "deleteCustomer")
	public void deleteCustomer(CuCustomerInfo entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doDeleteCustomer(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 会员列表
	 */
	@RequestMapping("/selectCustomerInfo")
	public void selectCustomerInfo(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.orderBy("cuCustomerInfo.REGISTER_TIME DESC");
		swgrid(gridPager, builder, cuCustomerInfoService, "queryCustomerInfo", "count", request, response);
	}

	/**
	 * 冻结/解冻会员
	 */
	@RequestMapping("updateCustomerStatus")
	public void updateCustomerStatus(CuCustomerInfo customerInfo, HttpServletResponse response) {
		cuCustomerInfoService.updateCustomerStatus(customerInfo);
		ServletUtils.renderJsonAsText(response, "success");
	}

	/**
	 * 直推列表
	 */
	@RequestMapping(value = "selectDirectCustomer", method = RequestMethod.POST)
	public void selectDirectCustomer(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		this.swgrid(gridPager, builder, cuCustomerRefereeService, "queryDirectCustomer", "count", request, response);
	}

	/**
	 * 收支流水
	 */
	@RequestMapping(value = "selectProfit", method = RequestMethod.POST)
	public void selectProfit(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.orderBy("cuCustomerProfit.PROFIT_HAVED_TIME DESC");
		this.swgrid(gridPager, builder, cuCustomerProfitService, "queryCustomerProfitInfo", "count", request, response);
	}

	/**
	 * 禁止交易
	 */
	@RequestMapping(value = "banCustomerFlagTrade", method = RequestMethod.POST)
	public String banCustomerFlagTrade(String id) {
		return cuCustomerInfoService.banCustomerFlagTrade(id);
	}

	/**
	 * 恢复等级
	 */
	@RequestMapping(value = "recoveryLevel", method = RequestMethod.POST)
	public String recoveryLevel(String id) {
		return baseAccountApiService.recoveryLevel(id);
	}

	/**
	 * 设置等级
	 */
	@RequestMapping(value = "/setLevel")
	public void setLevel(CuCustomerCount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = cuCustomerInfoService.doSetLevel(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 设置释放比例
	 */
	@RequestMapping(value = "/granReleaseRate")
	public void granReleaseRate(CuCustomerAccount entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doGranReleaseRate(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}
}
