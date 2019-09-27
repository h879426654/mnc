package com.basics.wallet;

import okhttp3.OkHttpClient;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/3/11.
 */
public class Web3JConfigure {
    private static Web3j web3j;
    public static  OkHttpClient okHttpClient;
    public static Web3j getWeb3JInstance(){
        if(null==web3j){
            if(null== okHttpClient)
                 okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            HttpService httpService = new HttpService("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48");//"http://47.94.206.127:8545/",
            web3j = Web3j.build(httpService);
        }

        return web3j;
    }

    public static OkHttpClient getOkHttpClient(){
        if(null== okHttpClient){
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }
}
