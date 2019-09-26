package com.basics.wallet.controller.dao;
import com.basics.or.entity.BaseBean;
import com.basics.or.entity.OrOrder;
import com.basics.support.GenericDao;

public class WalletBaseDao extends BaseBean {
    private String userId;
    private String moveNum;
    private String superNum;
    private String mTokenNum;
    private String scoreNum;

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