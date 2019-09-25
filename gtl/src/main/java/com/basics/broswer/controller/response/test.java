package com.basics.broswer.controller.response;

import com.basics.common.TokenIdRequest;
import com.basics.support.auth.HttpClientUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws Exception{
//        Map<String,String> map1 = new HashMap<>();
//        map1.put("jsonrpc","2.0");
//        map1.put("method","eth_blockNumber");
//        JsonArray jsonArray = new JsonArray();
//        jsonArray.add("latest");
//        jsonArray.add(false);
//        map1.put("params",jsonArray.toString());
//        map1.put("id","1");
//     System.out.println(new Gson().toJson(map1));

//        System.out.println(getHistory(null,null));
//        System.out.println( doGet("https://api.etherscan.io/api?contractaddress=0x2657bd0193a127c93152e043e29980bf16a1b446&endblock=8700000&address=0xfaa0529e5d47ece85e093f5936bfd781de32060d&offset=100&apikey=6XY2BCUEKHGDC8CMDSY1M6VCFNJZSV9HJA&module=account&action=tokentx&startblock=8000000&page=1&sort=asc"));
//        rere();

        mai1n(null);
    }

    public static String getHistory(TokenIdRequest request, HttpServletRequest req) {
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
        String pendingBlock= HttpClientUtils.invokeGet("https://api.etherscan.io/api",params);


//        Map<String,Object> map = new HashMap<>();
//        map.put("module","account");
//        map.put("action","tokentx");
//        map.put("contractaddress","0x2657bd0193a127c93152e043e29980bf16a1b446");
//        map.put("address","0xfaa0529e5d47ece85e093f5936bfd781de32060d");
//        map.put("apikey","6XY2BCUEKHGDC8CMDSY1M6VCFNJZSV9HJA");
//
//        String pendingBlock= HttpClientUtils.post("https://api.etherscan.io/api",map);

        return pendingBlock;
    }

        public static void rere()  {
        try {

            String result = "";
            BufferedReader in = null;
            //参数拼在后面
            String urlNameString = "https://api.etherscan.io/api?contractaddress=0x2657bd0193a127c93152e043e29980bf16a1b446&endblock=8700000&address=0xfaa0529e5d47ece85e093f5936bfd781de32060d&offset=100&apikey=6XY2BCUEKHGDC8CMDSY1M6VCFNJZSV9HJA&module=account&action=tokentx&startblock=8000000&page=1&sort=asc";
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //设置超时时间
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("access-control-allow-origin","*");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应，设置utf8防止中文乱码
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if (in != null) {
                in.close();
            }
            System.out.println(result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        }


    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

        public static void mai1n(String[] args) throws IOException{
            String url = "https://api.etherscan.io/api";
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //设置超时时间
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(15000);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param = "test={\"name\":\"whf\"}";
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if(out!=null){
                out.close();
            }
            if(in!=null){
                in.close();
            }
            System.out.println(result);
    }

}
