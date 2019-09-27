package com.basics.wallet.controller.response;

import com.basics.common.DataResponse;

import java.io.Serializable;

public class WalletResponse extends DataResponse implements Serializable {

    private String mncNum;
    private String moveNum;
    private String superNum;
    private String recordNum;
    private String mTokenNum;
    private String rewardNum;
    private String scoreNum;

    private String superRelease;
    private String mTokenRelease;
    private String scoreRelease;

    private String mncPrice;
    private String point;

    public String getRewardNum() {
        return rewardNum;
    }

    public void setRewardNum(String rewardNum) {
        this.rewardNum = rewardNum;
    }

    public String getMncNum() {
        return mncNum;
    }

    public void setMncNum(String mncNum) {
        this.mncNum = mncNum;
    }

    public String getMoveNum() {
        return moveNum;
    }

    public void setMoveNum(String moveNum) {
        this.moveNum = moveNum;
    }

    public String getSuperNum() {
        return superNum;
    }

    public void setSuperNum(String superNum) {
        this.superNum = superNum;
    }

    public String getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum = recordNum;
    }

    public String getmTokenNum() {
        return mTokenNum;
    }

    public void setmTokenNum(String mTokenNum) {
        this.mTokenNum = mTokenNum;
    }

    public String getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(String scoreNum) {
        this.scoreNum = scoreNum;
    }

    public String getSuperRelease() {
        return superRelease;
    }

    public void setSuperRelease(String superRelease) {
        this.superRelease = superRelease;
    }

    public String getmTokenRelease() {
        return mTokenRelease;
    }

    public void setmTokenRelease(String mTokenRelease) {
        this.mTokenRelease = mTokenRelease;
    }

    public String getScoreRelease() {
        return scoreRelease;
    }

    public void setScoreRelease(String scoreRelease) {
        this.scoreRelease = scoreRelease;
    }

    public String getMncPrice() {
        return mncPrice;
    }

    public void setMncPrice(String mncPrice) {
        this.mncPrice = mncPrice;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
