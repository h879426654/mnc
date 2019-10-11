package com.basics.gty.service;

import com.basics.common.*;
import com.basics.gty.controller.request.TokenTransferRequest;
import com.basics.gty.entity.GtyWallet;
import com.basics.support.GenericService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GtyTransferService  {
    public DataResponse transferMoney(TokenTransferRequest request, HttpServletRequest req);

    public DataResponse transfer(AddressRequest request, HttpServletRequest req);

    public DataItemResponse queryTransation(IdRequest request );

    public DataItemResponse queryWalletInfo(TokenIdRequest request );

    public DataResponse setLimit(IdNumRequest request, HttpServletResponse response );

    public DataResponse setPointLimit(IdPointRequest request );

    public DataResponse modifyMncNum(IdNumsRequest request );

    public DataItemResponse getLimitInfo();

    public DataItemResponse getUserId(TokenRequest request );
}
