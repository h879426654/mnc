package com.basics.gty.entity;

import com.basics.cu.entity.BaseBean;

public class EthBean extends BaseBean {
    private String address;
    private String privateKey;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
