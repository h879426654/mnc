package org.jeecg.modules.walletInfo.service.impl;

import org.jeecg.modules.walletInfo.entity.WalletInfo;
import org.jeecg.modules.walletInfo.mapper.WalletInfoMapper;
import org.jeecg.modules.walletInfo.service.IWalletInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description: mnc管理
 * @author： jeecg-boot
 * @date：   2019-10-11
 * @version： V1.0
 */
@Service
public class WalletInfoServiceImpl extends ServiceImpl<WalletInfoMapper, WalletInfo> implements IWalletInfoService {
    @Override
    public String httpClient(String url1) {
        try {
            HttpURLConnection connection = null;
            InputStream is = null;
            BufferedReader br = null;
            String result = null;//
            URL url = new URL(url1);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
            return result;
        } catch (Exception e) {
            return "请求失败";
        }
    }
}
