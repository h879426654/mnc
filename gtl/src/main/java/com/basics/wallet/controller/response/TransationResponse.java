package com.basics.wallet.controller.response;

import com.basics.broswer.controller.response.BlockInfoResponse;
import com.basics.common.DataResponse;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransationResponse extends DataResponse implements Serializable {

    private TxInfoResponse data;

    public TxInfoResponse getData() {
        return data;
    }

    public void setData(TxInfoResponse data) {
        this.data = data;
    }
}
