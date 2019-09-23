package com.basics.support.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用http发送方法
 * 
 * @author basics
 */
public class HttpUtils {
	private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 向指定 URL 发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		StringBuilder result = new StringBuilder();
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			log.info("sendGet - {}", urlNameString);
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			log.info("recv - {}", result);
		} catch (ConnectException e) {
			log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
		} catch (SocketTimeoutException e) {
			log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
		} catch (IOException e) {
			log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
		} catch (Exception e) {
			log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
			}
		}
		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			String urlNameString = url + "?" + param;
			log.info("sendPost - {}", urlNameString);
			URL realUrl = new URL(urlNameString);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			log.info("recv - {}", result);
		} catch (ConnectException e) {
			log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
		} catch (SocketTimeoutException e) {
			log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
		} catch (IOException e) {
			log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
		} catch (Exception e) {
			log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
			}
		}
		return result.toString();
	}

	public static String sendSSLPost(String url, String param) {
		StringBuilder result = new StringBuilder();
		String urlNameString = url + "?" + param;
		try {
			log.info("sendSSLPost - {}", urlNameString);
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL console = new URL(urlNameString);
			HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String ret = "";
			while ((ret = br.readLine()) != null) {
				if (ret != null && !ret.trim().equals("")) {
					result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
				}
			}
			log.info("recv - {}", result);
			conn.disconnect();
			br.close();
		} catch (ConnectException e) {
			log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
		} catch (SocketTimeoutException e) {
			log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
		} catch (IOException e) {
			log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
		} catch (Exception e) {
			log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
		}
		return result.toString();
	}

	private static class TrustAnyTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	public static String sendPostMap(String url, Map<String, String> parameters) {
		String result = "";// 返回的结果  
		BufferedReader in = null;// 读取响应输入流  
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();// 处理请求参数  
		String params = "";// 编码之后的参数  
		try {
			// 编码请求参数  
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
				}
				params = sb.toString();
			} else {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			// 创建URL对象  
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接  
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
			// 设置通用属性  
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 设置POST方式  
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			// 获取HttpURLConnection对象对应的输出流  
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数  
			out.write(params);
			// flush输出流的缓冲  
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式  
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容  
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}