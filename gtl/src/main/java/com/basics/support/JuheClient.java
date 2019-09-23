package com.basics.support;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.basics.common.DataItemResponse;

@Service
public class JuheClient extends RestTemplateSupport {

	public static final String SMS_KEY = "118dbab600605c3f0e7ec41868498fe2";
	public static final String AUTO_KEY = "e237ee2b15ddd804f564bb31cabee2c6";
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	public static String CHECK_BANK_NO_URL = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json";

	/**
	 * 发送短信
	 * @param phone
	 * @param message
	 * @return
	 */
	public DataItemResponse<JSONObject> pushSms(String phone, String message) {
		DataItemResponse<JSONObject> dataResponse = new DataItemResponse<JSONObject>();
		try {
			CommonSupport.checkNotNull(phone, "手机号码不能为空");
			CommonSupport.checkNotNull(message, "短信内容不能为空");
			Map<String, String> params = new HashMap<String, String>();
			params.put("mobile", phone);
			params.put("tpl_id", "61440");
			params.put("tpl_value", URLEncoder.encode("#code#=" + message, "UTF-8"));
			params.put("key", SMS_KEY);
			this.setBaseUrl("http://v.juhe.cn");
			return this.getItem("/sms/send", params, JSONObject.class);
		} catch (Throwable e) {
			dataResponse.onException(e);
		}
		return dataResponse;
	}

	/**
	 * 发送短信
	 */
	public DataItemResponse<JSONObject> pushSms2(String phone, String message) {
		DataItemResponse<JSONObject> dataResponse = new DataItemResponse<JSONObject>();
		try {
			CommonSupport.checkNotNull(phone, "手机号码不能为空");
			CommonSupport.checkNotNull(message, "短信内容不能为空");
			String url = "http://v.juhe.cn/sms/send";
			Map<String, Object> params = new HashMap<String, Object>();// 请求参数
			params.put("mobile", phone);// 接收短信的手机号码
			params.put("tpl_id", 61440);// 短信模板ID，请参考个人中心短信模板设置
			params.put("tpl_value", URLEncoder.encode("#code#=" + message, "UTF-8"));// 变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a
																						// href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
			params.put("key", SMS_KEY);// 应用APPKEY(应用详细页查询)
			dataResponse.onHandleSuccess();
			dataResponse.setItem(JSONObject.parseObject(net(url, params, "GET")));
			return dataResponse;
		} catch (Throwable e) {
			dataResponse.onException(e);
		}
		return dataResponse;
	}

	public DataItemResponse<JSONObject> pushSmsByOrder(String phone, String message) {
		DataItemResponse<JSONObject> dataResponse = new DataItemResponse<JSONObject>();
		try {
			CommonSupport.checkNotNull(phone, "手机号码不能为空");
			CommonSupport.checkNotNull(message, "短信内容不能为空");
			String url = "http://v.juhe.cn/sms/send";
			Map<String, Object> params = new HashMap<String, Object>();// 请求参数
			params.put("mobile", phone);// 接收短信的手机号码
			params.put("tpl_id", 67885);// 短信模板ID，请参考个人中心短信模板设置
			params.put("tpl_value", URLEncoder.encode("#trade#=" + message, "UTF-8"));
			params.put("key", SMS_KEY);// 应用APPKEY(应用详细页查询)
			dataResponse.onHandleSuccess();
			dataResponse.setItem(JSONObject.parseObject(net(url, params, "GET")));
			return dataResponse;
		} catch (Throwable e) {
			dataResponse.onException(e);
		}
		return dataResponse;
	}

	/**
	 * 实名认证
	 * @param phone
	 * @param message
	 * @return
	 */
	public DataItemResponse<JSONObject> pushAuth(String cardNo, String name) {
		DataItemResponse<JSONObject> dataResponse = new DataItemResponse<JSONObject>();
		try {
			CommonSupport.checkNotNull(cardNo, "身份证号码不能为空");
			CommonSupport.checkNotNull(name, "真实姓名不能为空");
			Map<String, String> params = new HashMap<String, String>();
			params.put("idcard", cardNo);
			params.put("realname", URLEncoder.encode(name, "UTF-8"));
			params.put("key", AUTO_KEY);
			this.setBaseUrl("http://op.juhe.cn");
			return this.getItem("/idcard/query", params, JSONObject.class);
		} catch (Throwable e) {
			dataResponse.onException(e);
		}
		return dataResponse;
	}

	public DataItemResponse<JSONObject> pushAuth2(String cardNo, String name) {
		DataItemResponse<JSONObject> dataResponse = new DataItemResponse<JSONObject>();
		try {
			CommonSupport.checkNotNull(cardNo, "身份证不能为空");
			CommonSupport.checkNotNull(name, "真实姓名不能为空");
			String url = "http://op.juhe.cn/idcard/query";
			Map<String, Object> params = new HashMap<String, Object>();// 请求参数
			params.put("idcard", cardNo);
			params.put("realname", name);
			params.put("key", AUTO_KEY);
			dataResponse.onHandleSuccess();
			dataResponse.setItem(JSONObject.parseObject(net(url, params, "POST")));
			return dataResponse;
		} catch (Throwable e) {
			dataResponse.onException(e);
		}
		return dataResponse;
	}

	/**
	*
	* @param strUrl 请求地址
	* @param params 请求参数
	* @param method 请求方法
	* @return  网络请求字符串
	* @throws Exception
	*/
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 校验银行卡正确性
	 * @throws Exception 
	 */
	public JSONObject checkBankNo(String bankNo) throws Exception {
		JSONObject dataResponse = new JSONObject();
		CommonSupport.checkNotNull(bankNo, "银行卡号不能为空");
		Map<String, String> params = new HashMap<String, String>();
		params.put("_input_charset", "utf-8");
		params.put("cardNo", bankNo);
		params.put("cardBinCheck", "true");
		return JSONObject.parseObject(net(CHECK_BANK_NO_URL, params, "GET"));
	}

}
