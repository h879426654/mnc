package com.basics.cu.controller.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.basics.cu.controller.request.*;
import com.basics.cu.dao.CuCustomerCollectDao;
import com.basics.cu.entity.*;
import com.basics.cu.service.CuCustomerCollectService;
import com.basics.cu.service.CuReatil1Service;
import net.sf.json.JSONArray;
import org.nutz.lang.random.R;
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
import com.basics.cu.controller.response.BaseImgResponse;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.controller.response.DirectCustomerResponse;
import com.basics.cu.controller.response.IndexViewResponse;
import com.basics.cu.service.CustomerApiService;
import com.basics.sys.entity.SysNotice;
import com.basics.sys.entity.SysTurntableReward;
import org.web3j.abi.datatypes.Int;

@RestController
@RequestMapping("/api/customer/")
public class CustomerApiController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Autowired
	private CustomerApiService customerApiService;

	@Autowired
    private CuReatil1Service cuReatil1Service;

	@Autowired
	private CuCustomerCollectService cuCustomerCollectService;

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
     * @param token
     * @return
     */
	@RequestMapping("searchReatil")
    public DataResponse searchReatil(@Valid String token, Integer pageNum, Integer pageSize) {
		return cuReatil1Service.searchReatil(token, pageNum, pageSize);
    }

	/**
	 * 代理数据和收益
	 * @param customerId
	 * @return
	 */
	@RequestMapping("searchReatilandIncome")
	public String searchReatilandIncome(@Valid String customerId) {
		CuReatil1 cuReatil1 = cuReatil1Service.searchReatilandIncome(customerId);
		JSONObject json = (JSONObject) JSONObject.toJSON(cuReatil1);
		return json.toString();
	}

	/**
	 * 收藏列表
	 * @param token
	 * @return
	 */
	@RequestMapping("searchCollect")
	public String searchCollect(@Valid String token, Integer page, Integer rows) {
		List<CuCustomerCollect> list = cuCustomerCollectService.searchCollect(token, page, rows);
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	/**
	 * 添加收藏
	 * @param collectRequest
	 * @return
	 */
	@RequestMapping("insertCollect")
	public String insertCollect(@Valid CollectRequest collectRequest) {
		return cuCustomerCollectService.insertCollect(collectRequest);
	}

	/**
	 * 取消收藏
	 * @param id
	 * @return
	 */
	@RequestMapping("updateCollect")
	public String updateCollect(@Valid String id, String state) {
		return cuCustomerCollectService.updateCollect(id, state);
	}

	/**
	 * 我的页面数据
	 * @param token
	 * @return
	 */
	@RequestMapping("searchMy")
	public String searchMy(@Valid String token) {
		return cuCustomerCollectService.searchMy(token);
	}

	/**
	 * 我的消费
	 * @param state
	 * @param token
	 * @param type
	 * @return
	 */
	@RequestMapping("searchConConsume")
	public String searchConConsume(@Valid String state, String token, String type, Integer page, Integer rows) {
		return cuCustomerCollectService.searchConConsume(state, token, type, page, rows);
	}

	/**
	 * 添加的消费
	 * @param request
	 * @return
	 */
	@RequestMapping("insertConConsume")
	public String insertConConsume(@Valid ConsumeRequest request) {
		return cuCustomerCollectService.insertConConsume(request);
	}
	/**
	 * 更改的消费
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping("udpateConConsume")
	public String udpateConConsume(@Valid String token, String id, String state, String mp) {
		return cuCustomerCollectService.updateConConsume(token, id, state, mp);
	}
	/**
	 * 添加历史数据
	 * @param historyRequest
	 * @return
	 */
	@RequestMapping("insertHistory")
	public String insertHistory(@Valid HistoryRequest historyRequest) {
		return cuCustomerCollectService.insertHistory(historyRequest);
	}
	/**
	 * 查看历史数据
	 * @param token
	 * @return
	 */
	@RequestMapping("searchHistory")
	public String searchHistory(@Valid String token) {
		return cuCustomerCollectService.searchHistory(token);
	}
	/**
	 * 是否收藏
	 * @param token
	 * @return
	 */
	@RequestMapping("searchIsCollect")
	public String searchIsCollect(@Valid String token, String shopId) {
		return cuCustomerCollectService.searchIsCollect(token, shopId);
	}

	/**
	 * 获取图片还有名称
	 * @param shopId
	 * @return
	 */
	@RequestMapping("getImageAndName")
	public String getImageAndName(@Valid String shopId, String token) {
		return cuCustomerCollectService.getImageAndName(shopId, token);
	}

	/**
	 * 评论
	 * @param token
	 * @param shopId
	 * @return
	 */
	@RequestMapping("insertDiscuss")
	public String insertDiscuss(@Valid String token, String shopId, String remark, String id) {
		return cuCustomerCollectService.insertDiscuss(token, shopId, remark, id);
	}

	/**
	 * 查询评论
	 * @param shopId
	 * @return
	 */
	@RequestMapping("searchDiscuss")
	public String searchDiscuss(@Valid String shopId, Integer pageNum, Integer pageSize) {
		return cuCustomerCollectService.searchDiscuss(shopId, pageNum, pageSize);
	}

	/**
	 * 获取手机号
	 * @param personToken
	 * @return
	 */
	@RequestMapping("getPhone")
	public String getPhone(@Valid String personToken) {
		String phone = cuCustomerCollectService.getPhone(personToken);
		return phone;
	}
	/**
	 * 轮播图、背景图
	 */
	@RequestMapping("searchPicture")
	public String searchPicture() {
		return cuCustomerCollectService.searchPicture();
	}

	/**
	 * 判断Json
	 * @return
	 */
	@RequestMapping("searchToken")
	public String searchToken(String token) {
		return cuCustomerCollectService.searchToken(token);
	}
}
