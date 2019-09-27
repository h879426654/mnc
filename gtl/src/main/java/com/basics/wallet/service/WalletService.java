package com.basics.wallet.service;

import com.basics.common.*;
import com.basics.tr.controller.request.*;
import com.basics.tr.controller.response.TradeResponse;
import com.basics.wallet.controller.response.WalletResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WalletService {


    /**
     * 钱包信息
     */
    WalletResponse getWalletInfo(TokenIdRequest request, HttpServletRequest req);

    void getTest();

}
