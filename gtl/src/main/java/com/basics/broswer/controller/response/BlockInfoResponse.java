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

    private String historyRecord;
    private String coreVersion;
    private String newestPrice;
    private String newestVersion;
    private String blockNUm;


    public String getHistoryRecord() {
        return historyRecord;
    }

    public void setHistoryRecord(String historyRecord) {
        this.historyRecord = historyRecord;
    }

    public String getCoreVersion() {
        return coreVersion;
    }

    public void setCoreVersion(String coreVersion) {
        this.coreVersion = coreVersion;
    }

    public String getNewestPrice() {
        return newestPrice;
    }

    public void setNewestPrice(String newestPrice) {
        this.newestPrice = newestPrice;
    }

    public String getNewestVersion() {
        return newestVersion;
    }

    public void setNewestVersion(String newestVersion) {
        this.newestVersion = newestVersion;
    }

    public String getBlockNUm() {
        return blockNUm;
    }

    public void setBlockNUm(String blockNUm) {
        this.blockNUm = blockNUm;
    }

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
