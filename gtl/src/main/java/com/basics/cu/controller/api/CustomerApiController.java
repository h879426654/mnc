package com.basics.cu.controller.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.basics.cu.entity.CuReatil1;
import com.basics.cu.service.CuReatil1Service;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppOption;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenPageRequest;
import com.basics.common.TokenRequest;
import com.basics.common.TokenTypePageRequest;
import com.basics.common.TokenTypeRequest;
import com.basics.cu.controller.request.CustomerFeedbackRequest;
import com.basics.cu.controller.request.ModifyCustomerInfoRequest;
import com.basics.cu.controller.response.BaseImgResponse;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.controller.response.DirectCustomerResponse;
import com.basics.cu.controller.response.IndexViewResponse;
import com.basics.cu.entity.CuCustomerFeedback;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerMessage;
import com.basics.cu.service.CustomerApiService;
import com.basics.sys.entity.SysNotice;
import com.basics.sys.entity.SysTurntableReward;

@RestController
@RequestMapping("/api/customer/")
public class CustomerApiController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Autowired
	private CustomerApiService customerApiService;

	@Autowired
    private CuReatil1Service cuReatil1Service;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 个人信息查询
	 */
	@RequestMapping("selectMyCustomerInfo")
	public DataItemResponse<CustomerInfoResponse> selectSybCustomer(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<CustomerInfoResponse> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectCustomerInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 修改个人信息
	 */
	@RequestMapping("modifyCustomerInfo")
	public DataResponse modifyCustomerInfo(@Valid ModifyCustomerInfoRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.doModifyCustomerInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 根据手机号查找用户
	 */
	@RequestMapping("selectCustomerByPhone")
	public DataItemResponse<CuCustomerInfo> selectCustomerByPhone(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<CuCustomerInfo> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectCustomerByPhone(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 我的圈友-用户列表
	 */
	@RequestMapping("selectDirectCustomerList")
	public DataPageComonResponse<DirectCustomerResponse> selectDirectCustomerList(@Valid TokenPageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<DirectCustomerResponse> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectDirectCustomerList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 首页信息
	 */
	@RequestMapping("selectIndexView")
	public DataItemResponse<IndexViewResponse> selectIndexView(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<IndexViewResponse> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectIndexView(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 用户未读的公告信息
	 */
	@RequestMapping("selectCustomerTopMsg")
	public DataItemResponse<SysNotice> selectCustomerTopMsg(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<SysNotice> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectCustomerTopMsg(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 用户消息查询
	 */
	@RequestMapping("selectCustomerMessageList")
	public DataPageComonResponse<CuCustomerMessage> selectCustomerMessage(@Valid TokenTypePageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<CuCustomerMessage> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectCustomerMessage(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 消息详情
	 */
	@RequestMapping("selectCustomerMessageInfo")
	public DataItemResponse<CuCustomerMessage> selectCustomerMessageInfo(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<CuCustomerMessage> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectCustomerMessageInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 删除/批量删除消息
	 */
	@RequestMapping("delCustomerMessageByIds")
	public DataResponse delCustomerMessageByIds(@Valid TokenIdsRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.delCustomerMessageByIds(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 一键已读
	 */
	@RequestMapping("messagetoRead")
	public DataResponse delCustomerMessageByIds(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.messagetoRead(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 未读信息条数
	 */
	@RequestMapping("selectUnReadNum")
	public  DataItemResponse<JSONObject> selectUnReadNum(@Valid TokenTypeRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<JSONObject> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectUnReadNum(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	

	/**
	 * 查询公告
	 */
	@RequestMapping("selectSysNotice")
	public DataPageComonResponse<SysNotice> selectSysNotice(@Valid TokenTypePageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<SysNotice> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectSysNotice(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 查询banner
	 */
	@RequestMapping("selectSysBanner")
	public DataItemResponse<List<BaseImgResponse>> selectSysBanner(@Valid TokenTypeRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<BaseImgResponse>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.selectSysBanner(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 会员签到
	 */
	@RequestMapping("customerSign")
	public DataResponse customerSign(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.doCustomerSign(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 提出反馈
	 */
	@RequestMapping("pushCustomerFeedback")
	public DataResponse pushCustomerFeedback(@Valid CustomerFeedbackRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.doPushCustomerFeedback(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 意见反馈
	 */
	@RequestMapping("getCustomerFeedbackList")
	public DataItemResponse<List<CuCustomerFeedback>> getCustomerFeedbackList(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<CuCustomerFeedback>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.getCustomerFeedbackList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 意见反馈类型
	 */
	@RequestMapping("getFeedbackType")
	public DataItemResponse<List<AppOption>> getFeedbackType(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<AppOption>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.getFeedbackType(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	
	/**
	 * 获取抽奖配置
	 */
	@RequestMapping("getRewardTurntable")
	public DataItemResponse<Map<String, Object>> getRewardTurntable(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Map<String, Object>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.doRewardTurntable(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 获取中奖数
	 */
	@RequestMapping("getRewardNum")
	public DataItemResponse<Integer> getRewardNum(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<Integer>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.doRewardNum(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 中奖列表
	 */
	@RequestMapping("getRewardList")
	public DataItemResponse<Map<String,Object>> getRewardList(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Map<String,Object>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.doGetRewardList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 我的中奖记录
	 */
	@RequestMapping("getCustomerReward")
	public DataPageComonResponse<SysTurntableReward> getCustomerReward(@Valid TokenPageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<SysTurntableReward> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = customerApiService.getCustomerReward(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

    /**
     * 查看下级分销
     * @param customerId
     * @return
     */
	@RequestMapping("searchReatil")
    public String searchReatil(@Valid String customerId) {
        CuReatil1 cuReatil1 = cuReatil1Service.searchByCustomerId(customerId);
        BigDecimal a = cuReatil1.getMoney();
	    return null;
    }
	
	
	

}
