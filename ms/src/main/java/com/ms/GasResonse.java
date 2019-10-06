package com.ms;

public class GasResonse {

    /**
     * jsonrpc : 2.0
     * id : 1
     * result : 0x218711a00
     */

    private String jsonrpc;
    private String id;
    private String result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
