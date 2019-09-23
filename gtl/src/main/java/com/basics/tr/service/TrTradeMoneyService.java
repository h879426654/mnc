package com.basics.tr.service;

import com.basics.support.FormResultSupport;
import com.basics.support.GenericService;
import com.basics.tr.entity.TrTradeMoney;

public interface TrTradeMoneyService extends GenericService<TrTradeMoney> {

	FormResultSupport doApplyTrade(TrTradeMoney entity);

	FormResultSupport doApplyTradeFail(TrTradeMoney entity);

}
