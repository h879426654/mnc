package com.basics.tr.service;

import com.basics.support.FormResultSupport;
import com.basics.support.GenericService;
import com.basics.tr.entity.TrTradeConvert;

public interface TrTradeConvertService extends GenericService<TrTradeConvert> {

	FormResultSupport doApplyTrade(TrTradeConvert entity);

	FormResultSupport doApplyTradeFail(TrTradeConvert entity);

}
