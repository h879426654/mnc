package com.basics.gty.entity;

import com.basics.cu.entity.BaseBean;

public class TradeTransferBean  {

    /**
     * success : true
     * result : null
     * error : null
     * code : null
     * total : null
     */

    private boolean success;
    private String result;
    private String error;
    private String code;
    private String total;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
