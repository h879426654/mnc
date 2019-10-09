package com.basics.cu.service;

import com.basics.cu.controller.request.CollectRequest;
import com.basics.cu.controller.request.ConsumeRequest;
import com.basics.cu.controller.request.HistoryRequest;
import com.basics.cu.entity.CuCustomerCollect;
import jnr.ffi.annotations.In;
import org.web3j.abi.datatypes.Int;

import java.util.List;

public interface CuCustomerCollectService {
    List<CuCustomerCollect> searchCollect(String token, Integer page, Integer rows);

    String insertCollect(CollectRequest collectRequest);

    String updateCollect(String id, String state);

    String searchMy(String token);

    String searchConConsume(String state, String token, String type, Integer pageNum, Integer rows);

    String insertConConsume(ConsumeRequest request);

    String updateConConsume(String token, String id, String state, String mp);

    String insertHistory(HistoryRequest historyRequest);

    String searchHistory(String token);

    String searchIsCollect(String token, String shopId);

    String getImageAndName(String shopId, String token);

    String insertDiscuss(String token, String shopId, String remark, String id);

    String searchDiscuss(String shopId, Integer pageNum, Integer pageSize);

    String getPhone(String personToken);

    String searchPicture();

    String searchToken(String token);
}
