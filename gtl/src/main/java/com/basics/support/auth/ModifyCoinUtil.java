package com.basics.support.auth;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.basics.support.DesUtil;

public class ModifyCoinUtil {
	// 接口调用地址
	private static String url = "https://www.amcoinio.com/mnc/api/receivecoin";

	public static void main(String[] args) throws Exception {
		String resultStr = modifyCoin("cico1028", "10.05");
		JSONObject result = JSONObject.parseObject(resultStr);
		if ("0".equals(result.getString("code"))) {
			System.out.println(result.toJSONString());
		}
		System.out.println(result.toJSONString());
	}

	public static String modifyCoin(String member, String number) throws Exception {
		JSONObject param = new JSONObject();
		param.put("member", member);
		param.put("number", number);
		Map<String, String> params = new HashMap<String, String>();
		params.put("param", DesUtil.encrypt(param.toJSONString()));
		System.out.println("参数信息:" + params.toString());
		// 调用接口
		return HttpUtils.sendPostMap(url, params);
	}

}
