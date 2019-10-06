package com.basics.gty.entity;

import java.math.BigDecimal;

public class GtyLimitWallet {
    private String limitWithDraw;
    private BigDecimal limitDownSuperRelease;
    private String limitUpSuperRelease;
    private BigDecimal limitDownScoreRelease;
    private String limitUpScoreRelease;

    public String getLimitWithDraw() {
        return limitWithDraw;
    }

    public void setLimitWithDraw(String limitWithDraw) {
        this.limitWithDraw = limitWithDraw;
    }

    public BigDecimal getLimitDownSuperRelease() {
        return limitDownSuperRelease;
    }

    public void setLimitDownSuperRelease(BigDecimal limitDownSuperRelease) {
        this.limitDownSuperRelease = limitDownSuperRelease;
    }

    public String getLimitUpSuperRelease() {
        return limitUpSuperRelease;
    }

    public void setLimitUpSuperRelease(String limitUpSuperRelease) {
        this.limitUpSuperRelease = limitUpSuperRelease;
    }

    public BigDecimal getLimitDownScoreRelease() {
        return limitDownScoreRelease;
    }

    public void setLimitDownScoreRelease(BigDecimal limitDownScoreRelease) {
        this.limitDownScoreRelease = limitDownScoreRelease;
    }

    public String getLimitUpScoreRelease() {
        return limitUpScoreRelease;
    }

    public void setLimitUpScoreRelease(String limitUpScoreRelease) {
        this.limitUpScoreRelease = limitUpScoreRelease;
    }
}
