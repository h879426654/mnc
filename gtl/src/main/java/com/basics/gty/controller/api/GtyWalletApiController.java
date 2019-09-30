package com.basics.gty.controller.api;

import com.basics.common.*;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.service.CustomerApiService;
import com.basics.gty.controller.request.TokenTransferRequest;
import com.basics.gty.entity.GtyWallet;
import com.basics.gty.entity.GtyWalletHistory;
import com.basics.gty.service.GtyTransferService;
import com.basics.gty.service.GtyWalletHistory2Service;
import com.basics.gty.service.GtyWalletService;
import com.basics.support.QueryFilter;
import com.basics.support.auth.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/gty/wallet/")
public class GtyWalletApiController implements ApplicationContextAware {

    @SuppressWarnings("unused")
    private ApplicationContext applicationContext;
    @Autowired
    private GtyWalletService gtySer;
	@Autowired
	private GtyWalletHistory2Service gtyWalletHistoryService;

    @Autowired
    CustomerApiService cuCustomerInfoService;
    @Autowired
    GtyTransferService gtyTransferMybatisService;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("getWalletInfo")
    public DataItemResponse<GtyWallet> query(TokenIdRequest request, HttpServletRequest req) {

        DataItemResponse dataResponse = new DataItemResponse();
        if (StringUtils.isBlank(request.getId())) {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }

        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("USER_ID", request.getId());
        filter.setParams(map);
        GtyWallet gtyWallet = gtySer.queryOne(filter);
//		gtyWallet.setMncNum(new BigDecimal(getTokenBlance(gtyWallet.getWalletAddress())));

        gtyWallet.setMncNum(BigDecimal.ZERO);
        BigDecimal mTokenRelease = gtyWallet.getmTokenNum().multiply(new BigDecimal(1 / 1000));
        gtyWallet.setReleasedTokenNum(mTokenRelease.setScale(5, BigDecimal.ROUND_HALF_UP));

        BigDecimal mSuperRelease = gtyWallet.getSuperNum().multiply(new BigDecimal(5 / 1000));
        gtyWallet.setReleasedSuperNum(mSuperRelease.setScale(5, BigDecimal.ROUND_HALF_UP));

        BigDecimal mScoreRelease = gtyWallet.getScoreNum().multiply(new BigDecimal(1 / 1000));
        gtyWallet.setReleasedScoreNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));

        gtyWallet.setMncPrice(new BigDecimal(10.5));
        gtyWallet.setPoint("+9.1%");
        dataResponse.setItem(gtyWallet);
        dataResponse.setMsg("成功");
        dataResponse.setStatus(0);
        return dataResponse;
    }

    private String getTokenBlance(String address) {//TokenBalanceRequest request,
        Map<String, String> params = new HashMap<>();
        params.put("module", "account");
        params.put("action", "tokentx");
        params.put("address", address);
        params.put("contractaddress", "0x2657bd0193a127c93152e043e29980bf16a1b446");
        params.put("tag", "latest");
        params.put("apikey", "6XY2BCUEKHGDC8CMDSY1M6VCFNJZSV9HJA");
        String pendingBlock = HttpClientUtils.invokeGet("https://api.etherscan.io/api", params);
        return pendingBlock;
    }

    @RequestMapping("createWallet")
    public DataResponse createWallet(TokenRequest request, HttpServletRequest req){
        DataResponse dataResponse = new DataResponse();
        DataItemResponse<CustomerInfoResponse> response  = cuCustomerInfoService.selectCustomerInfo(request, req);
        CustomerInfoResponse customerInfoResponse;
        if(response.getItem()!=null){
            customerInfoResponse = response.getItem();
        }else {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }

        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("USER_ID", customerInfoResponse.getId());
        filter.setParams(map);
        GtyWallet gtyWallet1 = gtySer.queryOne(filter);
        if(StringUtils.isBlank(gtyWallet1.getWalletAddress())){
            dataResponse.setStatus(0);
            dataResponse.setMsg("成功");
            return dataResponse;
        }

//        String walletFileName = WalletUtils.generateNewWalletFile(password, new File(walletFilePath), false);
//        Credentials credentials = WalletUtils.loadCredentials(password, walletFilePath + "/" + walletFileName);
//        String address = credentials.getAddress();
//        GtyWallet gtyWallet = new GtyWallet();
//        gtyWallet.setUserId(customerInfoResponse.getId());
//        gtyWallet.setWalletAddress(address);
//        gtySer.save(gtyWallet);
        dataResponse.setStatus(0);
        dataResponse.setMsg("成功");
        return dataResponse;
    }

    @PostMapping("transferMoney")
    public DataResponse transfer(TokenTransferRequest request, HttpServletRequest req) {

        DataResponse response = new DataResponse();
        try {
            response = gtyTransferMybatisService.transferMoney(request,req);
        } catch (Exception e) {
            response.onException(e);
        }
        return response;

    }

    @RequestMapping("transferTokenHistory")
    public DataItemResponse<List<GtyWalletHistory>> getWalletTransferHistory(IdRequest request){
        DataItemResponse dataItemResponse = new DataItemResponse();
        if (StringUtils.isBlank(request.getId())) {
            dataItemResponse.setMsg("未找到该用户");
            dataItemResponse.setStatus(1);
            return dataItemResponse;
        }
        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("USER_ID", request.getId());
        filter.setParams(map);

        List<GtyWalletHistory> gtyWallet = gtyWalletHistoryService.query(filter);
        dataItemResponse.setItem(gtyWallet);
        dataItemResponse.setMsg("成功");
        dataItemResponse.setStatus(0);
        return dataItemResponse;
    }


    private DataResponse transferError(DataResponse dataResponse, String msg) {
        dataResponse.setMsg(msg);
        dataResponse.setStatus(1);
        return dataResponse;
    }

}
