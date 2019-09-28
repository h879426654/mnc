package com.basics.support.auth;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Logger;

public class HttpRequestUtils2 {


    public static JSONObject httpPost(String url,JSONObject jsonParam){

        return httpPost(url, jsonParam, false);
    }

    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){

        DefaultHttpClient httpClient = new DefaultHttpClient();

        JSONObject jsonResult = null;

        HttpPost method = new HttpPost(url);

        try {


            if (null != jsonParam) {

                //解决中文乱码问题

                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");



                entity.setContentEncoding("UTF-8");



                entity.setContentType("application/json");



                method.setEntity(entity);



            }



            HttpResponse result = httpClient.execute(method);

            url = URLDecoder.decode(url, "UTF-8");


            /**请求发送成功，并得到响应**/

            if (result.getStatusLine().getStatusCode() == 200) {

                String str = "";
                try {

                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());

                    if (noNeedResponse) {

                        return null;

                    }

                    /**把json字符串转换成json对象**/

                    jsonResult = JSONObject.fromObject(str);

                } catch (Exception e) {


                }

            }


        } catch (IOException e) {

        }

        return jsonResult;

    }




    public static JSONObject httpGet(String url,Map<String, String> params){

        //get请求返回结果

        JSONObject jsonResult = null;

        try {

            DefaultHttpClient client = new DefaultHttpClient();
            String requestUrl;
            try {
                requestUrl = buildRequestUrl(url, params);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
            //发送get请求
            HttpGet request = new HttpGet(requestUrl);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                /**读取服务器返回过来的json字符串数据**/

                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/

                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {

            }

        } catch (IOException e) {

        }

        return jsonResult;

    }

    private static String buildRequestUrl(String url, Map<String, String> params) throws UnsupportedEncodingException {
        if (CollectionUtils.isEmpty(params)) {
            return url;
        }
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(url);
        int i = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (i == 0) {
                requestUrl.append("?");
            }
            requestUrl.append(entry.getKey());
            requestUrl.append("=");
            String value = entry.getValue();
            requestUrl.append(URLEncoder.encode(value, "UTF-8"));
            requestUrl.append("&");
            i++;
        }
        requestUrl.deleteCharAt(requestUrl.length() - 1);
        return requestUrl.toString();
    }

}
