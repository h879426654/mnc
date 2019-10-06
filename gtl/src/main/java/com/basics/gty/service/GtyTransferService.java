package com.basics.gty.service;

import com.basics.common.AddressRequest;
import com.basics.common.DataResponse;
import com.basics.gty.controller.request.TokenTransferRequest;
import com.basics.gty.entity.GtyWallet;
import com.basics.support.GenericService;

import javax.servlet.http.HttpServletRequest;

public interface GtyTransferService  {
    public DataResponse transferMoney(TokenTransferRequest request, HttpServletRequest req);

    public DataResponse transfer(AddressRequest request, HttpServletRequest req);
}
