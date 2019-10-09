package org.jeecg.common.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;

/**
 * TODO（描述类的职责）
 * @author hongyang.jiang
 * @date 2018年1月5日 上午9:15:57
 * @version <b>1.0.0</b>
 */
public class HttpUtil {
	public static CloseableHttpResponse post(String url, String outputEntity, String mch_id ) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		// 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.setEntity(new StringEntity(outputEntity, "UTF-8"));
		// 加载含有证书的http请求
		return HttpClients.custom().setSSLSocketFactory(initCert(mch_id)).build().execute(httpPost);

	}
	/**
	 * 加载证书
	 */
	public static SSLConnectionSocketFactory initCert(String mch_id) throws Exception {
		FileInputStream instream = null;
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		instream = new FileInputStream(new File("/www/wwwroot/admin.myloveqd.com/apiclient_cert.p12"));
		keyStore.load(instream, mch_id.toCharArray());

		if (null != instream) {
			instream.close();
		}

		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		return sslsf;
	}
}

