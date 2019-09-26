package com.basics.cu.service;

import com.basics.cu.controller.request.CollectRequest;
import com.basics.cu.controller.request.ConsumeRequest;
import com.basics.cu.controller.request.HistoryRequest;
import com.basics.cu.entity.CuCustomerCollect;

import java.util.List;

public interface CuCustomerCollectService {
    List<CuCustomerCollect> searchCollect(String phone, String token);

    String insertCollect(CollectRequest collectRequest);

    String updateCollect(String id, String state);

    String searchMy(String phone, String token);

    String searchConConsume(String phone, String state, String token, String type);

    String insertConConsume(ConsumeRequest request);

    String updateConConsume(String id, String state);

    String insertHistory(HistoryRequest historyRequest);

    String searchHistory(String phone, String token);

    String searchIsCollect(String phone, String token, String shopId);
}
