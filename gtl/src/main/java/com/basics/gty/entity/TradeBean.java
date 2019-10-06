package com.basics.gty.entity;

import com.basics.cu.entity.BaseBean;

public class TradeBean extends BaseBean {

    /**
     * code : 200
     * data : {"high":"0","vol":"0","ydayClose":"0","last":"0","low":"0"}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * high : 0
         * vol : 0
         * ydayClose : 0
         * last : 0
         * low : 0
         */

        private String high;
        private String vol;
        private String ydayClose;
        private String last;
        private String low;

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }

        public String getYdayClose() {
            return ydayClose;
        }

        public void setYdayClose(String ydayClose) {
            this.ydayClose = ydayClose;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }
    }
}
