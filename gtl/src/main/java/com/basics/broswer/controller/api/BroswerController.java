package com.basics.broswer.controller.api;

import com.alibaba.fastjson.JSON;
import com.basics.app.entity.AppToken;
import com.basics.broswer.controller.response.BlockInfoResponse;
import com.basics.broswer.controller.response.BlockNetResponse;
import com.basics.broswer.service.CuConcerService;
import com.basics.common.*;
import com.basics.cu.entity.CuConsume;
import com.basics.cu.entity.CuCustomerAddress;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.cu.service.CuCustomerInfoService;
import com.basics.gty.entity.GtyWallet;
import com.basics.gty.service.GtyWalletService;
import com.basics.mall.controller.request.*;
import com.basics.mall.controller.response.MallProductDetailResponse;
import com.basics.mall.controller.response.MallProductResponsePlus;
import com.basics.mall.controller.response.MyCollectionResponse;
import com.basics.mall.entity.MallProductClassify;
import com.basics.mall.service.MallApiService;
import com.basics.mall.vo.CommentInfoVo;
import com.basics.support.DateUtils;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.auth.HttpClientUtils;
import com.basics.support.auth.HttpRequestUtils2;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/block/")
public class BroswerController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Autowired
	private GtyWalletService gtySer;

	@Autowired
	CuConcerService cuConcerService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	@RequestMapping("getTransationRecord")
	public DataItemResponse<List<CuConsume>> getConsemeList(HttpServletRequest req){
		DataItemResponse<List<CuConsume>> response = new DataItemResponse<>();
		response.setItem(cuConcerService.getConSumeList());
		response.setStatus(0);
		response.setMsg("成功");
		return response;
	}

	/**
	 * 查询区块信息
	 */
	@RequestMapping("getBlockInfo")
	public BlockNetResponse selectProductClassifyList(HttpServletRequest req) {
		BlockNetResponse response = new BlockNetResponse();
		Map<String,Object> map1 = new HashMap<>();
		map1.put("jsonrpc","2.0");
		map1.put("method","eth_getBlockByNumber");
		JsonArray jsonArray = new JsonArray();
		jsonArray.add("latest");
		jsonArray.add(false);
		map1.put("params",jsonArray);
		map1.put("id","1");
		String preBlock=HttpClientUtils.postJSON("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48",new Gson().toJson(map1));
		JSONObject preObject =  JSONObject.fromObject(preBlock);
		JSONObject prepbject=preObject.getJSONObject("result");
		String preBlockTime = prepbject.getString("timestamp");

		JsonArray jsonArray2 = new JsonArray();
		jsonArray2.add("pending");
		jsonArray2.add(false);
		map1.put("params",jsonArray2);
		String pendingBlock=HttpClientUtils.postJSON("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48",new Gson().toJson(map1));
		JSONObject pendingObject =  JSONObject.fromObject(pendingBlock);
		String pendingBlockTime = pendingObject.getJSONObject("result").getString("timestamp");

		String a = new BigInteger(preBlockTime.substring(2,preBlockTime.length()), 16).toString(10);
		String b = new BigInteger(pendingBlockTime.substring(2,pendingBlockTime.length()), 16).toString(10);

		Long current=Long.parseLong(b)-Long.parseLong(a);

		String nowNumber = prepbject.getString("number");
		String number = new BigInteger(nowNumber.substring(2,nowNumber.length()), 16).toString(10);

		String latestBlockNum = prepbject.getString("number");
		String latestBlockNum1 = new BigInteger(latestBlockNum.substring(2,latestBlockNum.length()), 16).toString(10);

		response.setData(new BlockInfoResponse());
		response.getData().setLatestBlockNum(latestBlockNum1);
		response.getData().setLatestBlockTime(a);
		response.getData().setRefreshTime(current+"");
		response.getData().setTotalAssets("320000000");
		response.getData().setHistoryRecord("0x00312");
		response.getData().setCoreVersion("0x002");
		response.getData().setNewestPrice("40.3");
		response.getData().setNewestVersion("113");

		return response;
	}

//	@RequestMapping("getTransationRecord1")
//	public DataResponse getHistory1(TokenIdRequest request, HttpServletRequest req) {
//			DataResponse dataResponse = new DataResponse();
//			dataResponse.setMsg("id不能为空");
//			dataResponse.setStatus(1);
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("msg","id不能为空");
//			jsonObject.put("status",1);
//			return dataResponse;
//	}

	// 获取记录
	@RequestMapping("getTransationRecord1")
	public JSONObject getHistory(TokenIdRequest request, HttpServletRequest req) {
		if(StringUtils.isBlank(request.getId())){
			DataResponse dataResponse = new DataResponse();
			dataResponse.setMsg("id不能为空");
			dataResponse.setStatus(1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","id不能为空");
            jsonObject.put("status",1);
			return jsonObject;
		}
		QueryFilter filter = new QueryFilter();
		Map<String, Object> map = new HashMap<>();
		map.put("USER_ID", request.getId());
		filter.setParams(map);
		GtyWallet gtyWallet = gtySer.queryOne(filter);

		Map<String, String> params = new HashMap<>();
		params.put("module","account");
		params.put("action","tokentx");
		params.put("address",gtyWallet.getWalletAddress());
		params.put("contractaddress","0x2657bd0193a127c93152e043e29980bf16a1b446");
		params.put("startblock","8000000");
		params.put("endblock","8700000");
		params.put("page","1");
		params.put("offset","100");
		params.put("sort","asc");
		params.put("apikey","6XY2BCUEKHGDC8CMDSY1M6VCFNJZSV9HJA");
//		String pendingBlock=HttpClientUtils.invokeGet("https://api.etherscan.io/api",params);
		JSONObject jsonobject=HttpRequestUtils2.httpGet("https://api.etherscan.io/api",params);
//        JSONObject jsonobject = JSONObject.fromObject(pendingBlock);
		System.out.println(jsonobject);
		return jsonobject;
	}

}
