package com.basics.broswer.controller.response;

import java.io.Serializable;

public class BlockInfoResponse implements Serializable{
    // 刷新时间
    private String refreshTime;
    // 最新区块
    private String latestBlockNum;
    // 最新区块时间
    private String latestBlockTime;

    private String totalAssets;

    public String getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getLatestBlockNum() {
        return latestBlockNum;
    }

    public void setLatestBlockNum(String latestBlockNum) {
        this.latestBlockNum = latestBlockNum;
    }

    public String getLatestBlockTime() {
        return latestBlockTime;
    }

    public void setLatestBlockTime(String latestBlockTime) {
        this.latestBlockTime = latestBlockTime;
    }

}
