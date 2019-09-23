package com.basics.tr.service;

import com.basics.support.FormResultSupport;
import com.basics.support.GenericService;
import com.basics.tr.entity.TrTradeCoin;

public interface TrTradeCoinService extends GenericService<TrTradeCoin> {

	FormResultSupport doApplyTrade(TrTradeCoin entity);

	FormResultSupport doApplyTradeFail(TrTradeCoin entity);

}
