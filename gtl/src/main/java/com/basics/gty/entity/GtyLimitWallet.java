package com.basics.gty.entity;

import java.math.BigDecimal;

public class GtyLimitWallet {
    private String limitWithDraw;
    private BigDecimal limitDownSuperRelease;
    private BigDecimal limitUpSuperRelease;
    private BigDecimal limitDownScoreRelease;
    private BigDecimal limitUpScoreRelease;

    private BigDecimal limitDownMtokenRelease;
    private BigDecimal limitUpMtokenRelease;

    private BigDecimal limitMtokenReleasePoint;
    private BigDecimal limitMncReleasePoint;
    private BigDecimal limitScoreReleasePoint;
    private BigDecimal p2pPoint;
    private BigDecimal mncTradePoint;

    public BigDecimal getMncTradePoint() {
        return mncTradePoint;
    }

    public void setMncTradePoint(BigDecimal mncTradePoint) {
        this.mncTradePoint = mncTradePoint;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getP2pPoint() {
        return p2pPoint;
    }

    public void setP2pPoint(BigDecimal p2pPoint) {
        this.p2pPoint = p2pPoint;
    }

    public BigDecimal getLimitMtokenReleasePoint() {
        return limitMtokenReleasePoint;
    }

    public void setLimitMtokenReleasePoint(BigDecimal limitMtokenReleasePoint) {
        this.limitMtokenReleasePoint = limitMtokenReleasePoint;
    }

    public BigDecimal getLimitMncReleasePoint() {
        return limitMncReleasePoint;
    }

    public void setLimitMncReleasePoint(BigDecimal limitMncReleasePoint) {
        this.limitMncReleasePoint = limitMncReleasePoint;
    }

    public BigDecimal getLimitScoreReleasePoint() {
        return limitScoreReleasePoint;
    }

    public void setLimitScoreReleasePoint(BigDecimal limitScoreReleasePoint) {
        this.limitScoreReleasePoint = limitScoreReleasePoint;
    }

    public BigDecimal getLimitDownMtokenRelease() {
        return limitDownMtokenRelease;
    }

    public void setLimitDownMtokenRelease(BigDecimal limitDownMtokenRelease) {
        this.limitDownMtokenRelease = limitDownMtokenRelease;
    }

    public BigDecimal getLimitUpMtokenRelease() {
        return limitUpMtokenRelease;
    }

    public void setLimitUpMtokenRelease(BigDecimal limitUpMtokenRelease) {
        this.limitUpMtokenRelease = limitUpMtokenRelease;
    }

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

    public BigDecimal getLimitUpSuperRelease() {
        return limitUpSuperRelease;
    }

    public void setLimitUpSuperRelease(BigDecimal limitUpSuperRelease) {
        this.limitUpSuperRelease = limitUpSuperRelease;
    }

    public BigDecimal getLimitUpScoreRelease() {
        return limitUpScoreRelease;
    }

    public void setLimitUpScoreRelease(BigDecimal limitUpScoreRelease) {
        this.limitUpScoreRelease = limitUpScoreRelease;
    }

    public BigDecimal getLimitDownScoreRelease() {
        return limitDownScoreRelease;
    }

    public void setLimitDownScoreRelease(BigDecimal limitDownScoreRelease) {
        this.limitDownScoreRelease = limitDownScoreRelease;
    }

}
