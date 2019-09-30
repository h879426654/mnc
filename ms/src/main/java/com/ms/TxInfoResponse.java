package com.ms;

import java.io.Serializable;

public class TxInfoResponse implements Serializable{

    // 交易hash
    private String txHash;

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    private String txTime;

    public String getTxTime() {
        return txTime;
    }

    public void setTxTime(String txTime) {
        this.txTime = txTime;
    }
}
