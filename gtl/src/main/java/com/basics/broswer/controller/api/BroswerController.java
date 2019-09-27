package com.basics.broswer.controller.api;

import com.alibaba.fastjson.JSON;
import com.basics.broswer.controller.response.BlockInfoResponse;
import com.basics.broswer.controller.response.BlockNetResponse;
import com.basics.common.*;
import com.basics.cu.entity.CuCustomerAddress;
import com.basics.mall.controller.request.*;
import com.basics.mall.controller.response.MallProductDetailResponse;
import com.basics.mall.controller.response.MallProductResponsePlus;
import com.basics.mall.controller.response.MyCollectionResponse;
import com.basics.mall.entity.MallProductClassify;
import com.basics.mall.service.MallApiService;
import com.basics.mall.vo.CommentInfoVo;
import com.basics.support.auth.HttpClientUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/block/")
public class BroswerController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	/**
	 * 查询区块信息
	 */
	@RequestMapping("getBlockInfo")
	public BlockNetResponse selectProductClassifyList(TokenIdRequest request, HttpServletRequest req) {
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

	// 获取记录
	@RequestMapping("getTransationRecord")
	public String getHistory(TokenIdRequest request, HttpServletRequest req) {
		Map<String, String> params = new HashMap<>();
		params.put("module","account");
		params.put("action","tokentx");
		params.put("address","0xfaa0529e5d47ece85e093f5936bfd781de32060d");
		params.put("contractaddress","0x2657bd0193a127c93152e043e29980bf16a1b446");
		params.put("startblock","8000000");
		params.put("endblock","8700000");
		params.put("page","1");
		params.put("offset","100");
		params.put("sort","asc");
		params.put("apikey","6XY2BCUEKHGDC8CMDSY1M6VCFNJZSV9HJA");
		String pendingBlock=HttpClientUtils.invokeGet("https://api.etherscan.io/api",params);
		return pendingBlock;
	}

}
