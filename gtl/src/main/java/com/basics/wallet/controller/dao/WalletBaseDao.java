package com.basics.wallet.controller.dao;
import com.basics.or.entity.BaseBean;
import com.basics.or.entity.OrOrder;
import com.basics.support.GenericDao;

public class WalletBaseDao extends BaseBean {
    private String userId;
    private String moveWalletRecord;
    private String superWalletRecord;
    private String mTokenRecord;
    private String scoreRecord;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoveWalletRecord() {
        return moveWalletRecord;
    }

    public void setMoveWalletRecord(String moveWalletRecord) {
        this.moveWalletRecord = moveWalletRecord;
    }

    public String getSuperWalletRecord() {
        return superWalletRecord;
    }

    public void setSuperWalletRecord(String superWalletRecord) {
        this.superWalletRecord = superWalletRecord;
    }

    public String getmTokenRecord() {
        return mTokenRecord;
    }

    public void setmTokenRecord(String mTokenRecord) {
        this.mTokenRecord = mTokenRecord;
    }

    public String getScoreRecord() {
        return scoreRecord;
    }

    public void setScoreRecord(String scoreRecord) {
        this.scoreRecord = scoreRecord;
    }
}