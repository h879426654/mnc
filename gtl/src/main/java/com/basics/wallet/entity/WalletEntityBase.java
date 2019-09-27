package com.basics.wallet.entity;

import com.basics.cu.entity.BaseBean;

import java.math.BigDecimal;

public class WalletEntityBase extends BaseBean {
    private String UserId;
    private BigDecimal MoveNum;
    private BigDecimal SuperNum;
    private BigDecimal MTokenNum;
    private BigDecimal ScoreNum;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public BigDecimal getMoveNum() {
        return MoveNum;
    }

    public void setMoveNum(BigDecimal moveNum) {
        MoveNum = moveNum;
    }

    public BigDecimal getSuperNum() {
        return SuperNum;
    }

    public void setSuperNum(BigDecimal superNum) {
        SuperNum = superNum;
    }

    public BigDecimal getMTokenNum() {
        return MTokenNum;
    }

    public void setMTokenNum(BigDecimal MTokenNum) {
        this.MTokenNum = MTokenNum;
    }

    public BigDecimal getScoreNum() {
        return ScoreNum;
    }

    public void setScoreNum(BigDecimal scoreNum) {
        ScoreNum = scoreNum;
    }
}