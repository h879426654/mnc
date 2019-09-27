package com.basics.wallet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppImage;
import com.basics.app.entity.AppOption;
import com.basics.common.*;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.cu.entity.CuCustomerProfit;
import com.basics.support.*;
import com.basics.support.auth.ModifyCoinUtil;
import com.basics.sys.entity.SysConfig;
import com.basics.sys.entity.SysRule;
import com.basics.tr.controller.request.*;
import com.basics.tr.controller.response.TradeResponse;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.service.TradeCoinApiService;
import com.basics.wallet.controller.response.WalletResponse;
import com.basics.wallet.entity.WalletEntity;
import com.basics.wallet.service.WalletService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class WalletServiceImpl extends BaseApiService implements WalletService {

	@Override
	public WalletResponse getWalletInfo(TokenIdRequest request, HttpServletRequest req) {
		WalletResponse walletResponse = new WalletResponse();
		walletResponse.setMncNum(walletEntityDao.get("MNC_NUM").toString());
		walletResponse.setMoveNum(walletEntityDao.get("MOVE_NUM").toString());
		walletResponse.setmTokenNum(walletEntityDao.get("MTOKEN_NUM").toString());
		walletResponse.setRecordNum(walletEntityDao.get("RECORD_NUM").toString());
		walletResponse.setScoreNum(walletEntityDao.get("SCORE_NUM").toString());
		walletResponse.setSuperNum(walletEntityDao.get("SUPER_NUM").toString());

		BigDecimal mTokenRelease = new BigDecimal(walletEntityDao.get("MTOKEN_NUM").toString()).multiply(new BigDecimal(1/1000));
		walletResponse.setmTokenRelease(mTokenRelease.toString());

		BigDecimal mSuperRelease = new BigDecimal(walletEntityDao.get("SUPER_NUM").toString()).multiply(new BigDecimal(5/1000));
		walletResponse.setmTokenRelease(mSuperRelease.toString());

		BigDecimal mScoreRelease = new BigDecimal(walletEntityDao.get("SCORE_NUM").toString()).multiply(new BigDecimal(1/1000));
		walletResponse.setmTokenRelease(mScoreRelease.toString());
		walletResponse.setPoint("9.1%");
		walletResponse.setMncPrice("20.1");
		return walletResponse;
	}

	@Override
	public void getTest() {
		System.out.println(">>>>>>>");
		List<Map<String,Object>> list = cuAccountConvertDao.queryExtend(new QueryFilterBuilder().build(),"getWalletInfo");
		System.out.println(">>>>>>");
	}

}
