package com.basics.support.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class SmsWhUtil {

	private static final String BaseUrl = "http://123.58.1.121:7862/sms";

	private static final String BaseInternationalUrl = "http://intapi.253.com/send/json";

	public static final String SMS_MODE = "【MNC】";

	public static void main(String[] args) throws UnsupportedEncodingException {
		String result = sendInternational("821039051437", "4563457", SMS_MODE);
		System.out.println(JSONObject.parseObject(result));
	}

	public static String send(String mobile, String code, final String smsMode) throws UnsupportedEncodingException {
		String content = URLEncoder.encode("您的短信验证码为" + code + "，有效时间5分钟，请不要把验证码泄漏给其他人，如非本人请勿操作！" + smsMode, "utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append("action=send");
		sb.append("&account=110010");
		sb.append("&password=W3B7Dk");
		sb.append("&mobile=");
		sb.append(mobile.trim());
		sb.append("&content=");
		sb.append(content);
		sb.append("&extno=106901054");
		sb.append("&rt=json");
		return sendGet(BaseUrl, sb.toString());
	}

	/*
	 * 国际短信(手机号码需要国家编号)
	 */
	public static String sendInternational(String mobile, String code, final String smsMode) throws UnsupportedEncodingException {
		String content = URLEncoder.encode("Your validation code is " + code + ", The validity period of the validation code is 10 minutes. Please retrieve it after expiration!" + smsMode, "utf-8");
		JSONObject json = new JSONObject();
		json.put("account", "I6210702");
		json.put("password", "aJUTByOSXQ9d1e");
		json.put("msg", content);
		json.put("mobile", mobile);
		return doPostJson(BaseInternationalUrl, json.toJSONString());
	}

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Content-Type", "application/json;charset=utf-8"); // 设置发送数据的格式
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultString;
	}

}
