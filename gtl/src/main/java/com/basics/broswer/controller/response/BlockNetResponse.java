package com.basics.broswer.controller.response;

import com.basics.common.DataResponse;

import java.io.Serializable;
import java.math.BigDecimal;

public class BlockNetResponse extends DataResponse implements Serializable {

    private BlockInfoResponse data;

    public BlockInfoResponse getData() {
        return data;
    }

    public void setData(BlockInfoResponse data) {
        this.data = data;
    }
}
