package com.basics.wallet.controller.entity;
import com.basics.or.entity.BaseBean;

public class WalletEntity extends BaseBean {
    private String id;
    private String userId;
    private String moveNum;
    private String superNum;
    private String mTokenNum;
    private String scoreNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}