package com.basics.gty.service.impl;

import com.basics.common.*;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.entity.*;
import com.basics.cu.service.CuCustomerCollectService;
import com.basics.gty.controller.request.TokenTransferRequest;
import com.basics.gty.entity.*;
import com.basics.gty.service.GtyTransferService;
import com.basics.gty.service.GtyWalletService;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.support.GenericMybatisService;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.auth.HttpClientUtils;
import com.basics.support.auth.HttpRequestUtils2;
import com.basics.wallet.Web3JConfigure;
import com.basics.wallet.controller.response.TransationResponse;
import com.basics.wallet.controller.response.TxInfoResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class GtyTransferMybatisService extends BaseApiService implements GtyTransferService {

    @Autowired
    private CuCustomerCollectService cuCustomerCollectService;

    @Override
    public DataResponse transferMoney(TokenTransferRequest request, HttpServletRequest req) {
        DataResponse response = new DataResponse();
        CuCustomerLogin user = checkToken(request.getToken());
        if (null == user) {
            response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
            response.setStatus(2);
            return response;
        }
        if (!checkCustomerStatus(user)) {
            response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
            return response;
        }
        String userId = user.getId();

        List<GtyWallet> gtyWallet111 = gtyWalletDao.queryExtend(new QueryFilterBuilder().put("userId", userId).build(),
                "queryInfo");
        if (gtyWallet111 == null || gtyWallet111.size() == 0) {
            response.setMsg("转账失败");
            response.setStatus(1);
            return response;
        }
        try {
            BigDecimal toMone = new BigDecimal(request.getNum());
            if (toMone.compareTo(BigDecimal.ZERO) <= 0) {
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("数量错误");
            return response;
        }

        GtyWallet gtyWallet = gtyWallet111.get(0);

//        GtyWallet gtyWallet = gtyWalletDao.queryOne(filter);
        BigDecimal mncChain = BigDecimal.ZERO;

        if (request.getType() == 1) {// mnc转交易所
            if (gtyWallet.getMncNum().compareTo(new BigDecimal(request.getNum())) == -1) {

                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {

                Map<String, String> params = new HashMap<>();
                params.put("wallterKey", request.getToAddress());
                QueryFilter filter = new QueryFilter();
                Map<String, Object> maps = new HashMap<>();
                maps.put("id", "1");
                filter.setParams(maps);
                GtyLimitWallet gtyLimitWallet = gtyWalletLimitDao.queryOne(filter);
                BigDecimal realm = BigDecimal.ZERO;
                BigDecimal fee = BigDecimal.ZERO;
                if (gtyLimitWallet != null && gtyLimitWallet.getMncTradePoint() != null) {
                    fee = new BigDecimal(request.getNum()).multiply(gtyLimitWallet.getMncTradePoint());
                    realm = new BigDecimal(request.getNum()).subtract(fee);
                } else {
                    realm = new BigDecimal(request.getNum());
                }
                params.put("amount", realm + "");
                String info = HttpClientUtils.invokeGet("http://47.56.169.214:8085/api/increase", params);
                TradeTransferBean tradeTransferBean = new Gson().fromJson(info, TradeTransferBean.class);
                if (tradeTransferBean.isSuccess()) {
                    GtyWallet gtyWallet1 = new GtyWallet();
                    gtyWallet1.setUserId(gtyWallet.getUserId());
                    addFee(fee);
                    gtyWallet1.setMncNum(gtyWallet.getMncNum().subtract(new BigDecimal(request.getNum())));
                    gtyWalletDao.update(gtyWallet1);
                } else {
                    response.setStatus(1);
                    response.setMsg("转账失败,请验证交易所地址");
                    return response;
                }

            }
        } else if (request.getType() == 2) {// mnc转流通钱包
            if (gtyWallet.getMncNum().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {
                GtyWallet gtyWallet1 = new GtyWallet();
                gtyWallet1.setUserId(gtyWallet.getUserId());
                gtyWallet1.setMncNum(gtyWallet.getMncNum().subtract(new BigDecimal(request.getNum())));
                gtyWallet1.setMoveNum(gtyWallet.getMoveNum().add((new BigDecimal(request.getNum()))));
                gtyWalletDao.update(gtyWallet1);
            }
        } else if (request.getType() == 3) {// 流通转记账钱包
            if (gtyWallet.getMoveNum().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {
                GtyWallet gtyWallet1 = new GtyWallet();
                gtyWallet1.setUserId(gtyWallet.getUserId());
                gtyWallet1.setMoveNum(gtyWallet.getMoveNum().subtract(new BigDecimal(request.getNum())));
                // gtyWallet1.setRecordNum(gtyWallet.getRecordNum().add(new BigDecimal(request.getNum())));
                gtyWallet1.setRecordNum(new BigDecimal(request.getNum()).multiply(new BigDecimal("13.32")).add(gtyWallet.getRecordNum()));

                gtyWalletDao.update(gtyWallet1);
            }
        } else if (request.getType() == 4) {// 流通转mtoken
            if (gtyWallet.getMoveNum().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {//todo 获取币价
                GtyWallet gtyWallet1 = new GtyWallet();
                gtyWallet1.setUserId(gtyWallet.getUserId());
                Map<String, String> maps = new HashMap<>();
                maps.put("symbol", "3");
                String tradeBean = HttpClientUtils.invokeGet("http://bitin.io:8090/api/v1/ticker", maps);
                TradeBean tradeBean1 = new Gson().fromJson(tradeBean, TradeBean.class);
                String price;
                if (!StringUtils.isBlank(tradeBean1.getData().getLast())) {
                    if (tradeBean1.getData().getLast().equals("0")) {
                        price = "1";
                    } else {
                        price = tradeBean1.getData().getLast();
                    }
                } else {
                    price = "1";
                }
                if (gtyWallet.getMoveNum().compareTo(new BigDecimal("300").divide(new BigDecimal(price))) == 0 ||
                        gtyWallet.getMncNum().compareTo(new BigDecimal("300").divide(new BigDecimal(price))) == 1) {
                    gtyWallet1.setWalletFrozen(1);
                }

                gtyWallet1.setMoveNum(gtyWallet.getMoveNum().subtract(new BigDecimal(request.getNum())));
                gtyWallet1.setmTokenNum(new BigDecimal(request.getNum()).multiply(new BigDecimal("13.32")).add(gtyWallet.getmTokenNum()));
                cuCustomerCollectService.addMp(new BigDecimal(request.getNum()).multiply(new BigDecimal("13.32")), gtyWallet.getUserId());
                //gtyWallet.getmTokenNum()
                gtyWalletDao.update(gtyWallet1);
            }
        } else if (request.getType() == 5) {// 流通点对点
            if (gtyWallet.getMoveNum().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {//
                GtyWallet gtyWallet1 = new GtyWallet();
                gtyWallet1.setUserId(gtyWallet.getUserId());
                gtyWallet1.setMoveNum(gtyWallet.getMoveNum().subtract(new BigDecimal(request.getNum())));

                List<GtyWallet> gtyWallet22 = gtyWalletDao.queryExtend(new QueryFilterBuilder().put("walletAddress", request.getToAddress()).build(),
                        "queryInfoByAddress");
                GtyWallet gtyWallet2 = new GtyWallet();
                if (gtyWallet22.size() != 0) {
                    gtyWallet2 = gtyWallet22.get(0);
                }
                if (gtyWallet2 == null || StringUtils.isBlank(gtyWallet2.getWalletAddress())) {
                    response.setStatus(1);
                    response.setMsg("地址未找到");
                    return response;
                } else {
                    QueryFilter filter = new QueryFilter();
                    Map<String, Object> maps = new HashMap<>();
                    maps.put("id", "1");
                    filter.setParams(maps);
                    GtyLimitWallet gtyLimitWallet = gtyWalletLimitDao.queryOne(filter);
                    BigDecimal realm = BigDecimal.ZERO;
                    BigDecimal fee = BigDecimal.ZERO;
                    if (gtyLimitWallet != null && gtyLimitWallet.getP2pPoint() != null) {
                        fee = new BigDecimal(request.getNum()).multiply(gtyLimitWallet.getP2pPoint());
                        realm = new BigDecimal(request.getNum()).subtract(fee);
                        addFee(fee);
                    } else {
                        realm = new BigDecimal(request.getNum());
                    }
                    gtyWallet2.setMoveNum(gtyWallet2.getMoveNum().add(realm));
                    gtyWalletDao.update(gtyWallet2);
                    TokenTransferRequest request2 = new TokenTransferRequest();
                    request2.setNum(realm+"");
                    request2.setToAddress(gtyWallet.getWalletAddress());
                    request2.setType(request.getType());

                    addHistory2(gtyWallet2.getUserId(), request2, "流通钱包点对点转入", "+");
                }
                gtyWalletDao.update(gtyWallet1);
            }
        } else if (request.getType() == 6) {// 超级钱包转流通钱包
            if (gtyWallet.getReleasedMnc().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {
                GtyWallet gtyWallet1 = new GtyWallet();
                gtyWallet1.setUserId(gtyWallet.getUserId());
                gtyWallet1.setReleasedMnc(gtyWallet.getReleasedMnc().subtract(new BigDecimal(request.getNum())));
                gtyWallet1.setMoveNum(gtyWallet.getMoveNum().add(new BigDecimal(request.getNum())));
                gtyWalletDao.update(gtyWallet1);
            }
        } else if (request.getType() == 7) {// 超级钱包转交易所；
            if (gtyWallet.getReleasedMnc().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {
                Map<String, String> params = new HashMap<>();
                params.put("wallterKey", request.getToAddress());
                QueryFilter filter = new QueryFilter();
                Map<String, Object> maps = new HashMap<>();
                maps.put("id", "1");
                filter.setParams(maps);
                GtyLimitWallet gtyLimitWallet = gtyWalletLimitDao.queryOne(filter);
                BigDecimal realm = BigDecimal.ZERO;
                BigDecimal fee = BigDecimal.ZERO;
                if (gtyLimitWallet != null && gtyLimitWallet.getMncTradePoint() != null) {
                    fee = new BigDecimal(request.getNum()).multiply(gtyLimitWallet.getMncTradePoint());
                    realm = new BigDecimal(request.getNum()).subtract(fee);
                } else {
                    realm = new BigDecimal(request.getNum());
                }
                params.put("amount", realm + "");
                String info = HttpClientUtils.invokeGet("http://47.56.169.214:8085/api/increase", params);
                TradeTransferBean tradeTransferBean = new Gson().fromJson(info, TradeTransferBean.class);
                if (tradeTransferBean.isSuccess()) {
                    GtyWallet gtyWallet1 = new GtyWallet();
                    gtyWallet1.setUserId(gtyWallet.getUserId());
                    addFee(fee);
                    gtyWallet1.setReleasedMnc(gtyWallet.getReleasedMnc().subtract(new BigDecimal(request.getNum())));
                    gtyWalletDao.update(gtyWallet1);
                } else {
                    response.setStatus(1);
                    response.setMsg("转账失败,请验证交易所地址");
                    return response;
                }
            }

        }

        if (request.getType() == 1) {// mnc转交易所
            addHistory(gtyWallet, request, "MNC出到转交易所", "-");
        } else if (request.getType() == 2) {// mnc转流通钱包
//           addHistory(gtyWallet,request,"MNC转出到流通钱包","-");
            addHistory(gtyWallet, request, "MNC转入到流通钱包", "+");
        } else if (request.getType() == 3) {// 流通转记账钱包
//           addHistory(gtyWallet,request,"流通钱包转出到记账钱包","-");
            addHistory(gtyWallet, request, "流通钱包转入到记账钱包", "+");
        } else if (request.getType() == 4) {// 流通转mtoken
//           addHistory(gtyWallet,request,"流通钱包转出到MTOKEN","-");
            addHistory(gtyWallet, request, "流通钱包转入到MTOKEN", "+");
        } else if (request.getType() == 5) {// 流通点对点
            addHistory(gtyWallet, request, "流通钱包转账", "-");
        } else if (request.getType() == 6) {// 超级钱包转流通钱包
//           addHistory(gtyWallet,request,"超级钱包转出到流通钱包","-");
            addHistory(gtyWallet, request, "超级钱包转入到可提转", "+");
        } else if (request.getType() == 7) {// 超级钱包转交易所；
            addHistory(gtyWallet, request, "超级钱包转入到可提转", "-");
        }
        response.setMsg("转账成功");
        response.setStatus(0);
        return response;
    }


    private void addFee(BigDecimal fee){
        try {
            List<GtyWallet> gtyWallet_fee_list = gtyWalletDao.queryExtend(new QueryFilterBuilder().put("userId", "4710e04c34b84c058efae67256d8cedc").build(),
                    "queryInfo");
            GtyWallet gtyWallet_fee =  gtyWallet_fee_list.get(0);
            gtyWallet_fee.setMncNum(fee.add(gtyWallet_fee.getMncNum()));
            gtyWalletDao.update(gtyWallet_fee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addHistory(GtyWallet gtyWallet, TokenTransferRequest request, String recordName, String mark) {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        GtyWalletHistory gtyWalletHistory = new GtyWalletHistory();
        gtyWalletHistory.setId(uuid);
        gtyWalletHistory.setRecordNum(new BigDecimal(request.getNum()));
        gtyWalletHistory.setRecordType(request.getType());
        gtyWalletHistory.setToAccount(request.getToAddress());
        gtyWalletHistory.setUserId(gtyWallet.getUserId());
        gtyWalletHistory.setRecordName(recordName);
        gtyWalletHistory.setMark(mark);
        geyWallethistoryDao.save(gtyWalletHistory);

    }

    private void addHistory2(String mUid, TokenTransferRequest request, String recordName, String mark) {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        GtyWalletHistory gtyWalletHistory = new GtyWalletHistory();
        gtyWalletHistory.setId(uuid);
        gtyWalletHistory.setRecordNum(new BigDecimal(request.getNum()));
        gtyWalletHistory.setRecordType(request.getType());
        gtyWalletHistory.setToAccount(request.getToAddress());
        gtyWalletHistory.setUserId(mUid);
        gtyWalletHistory.setRecordName(recordName);
        gtyWalletHistory.setMark(mark);
        geyWallethistoryDao.save(gtyWalletHistory);

    }


    @Override
    public DataResponse transfer(AddressRequest request, HttpServletRequest req) {
        DataResponse dataResponse = new DataResponse();
        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("WALLET_ADDRESS", request.getAddress());
        filter.setParams(map);
        GtyWallet gtyWallet1 = gtyWalletDao.queryOne(filter);
        if (gtyWallet1 == null || StringUtils.isBlank(gtyWallet1.getUserId())) {
            dataResponse.setMsg("未找到对应地址");
            dataResponse.setStatus(1);
            return dataResponse;
        }
        gtyWallet1.setMncNum(gtyWallet1.getMncNum().add(request.getNum()));
        gtyWalletDao.update(gtyWallet1);
        return dataResponse;
    }

    @Override
    public DataItemResponse queryTransation(IdRequest request) {
        DataItemResponse dataResponse = new DataItemResponse();
        List<GtyWalletHistory> gtyWallet = geyWallethistoryDao.queryExtend(new QueryFilterBuilder().put("userId", request.getId()).build(),
                "queryTransation");
        dataResponse.setItem(gtyWallet);
        dataResponse.setMsg("成功");
        dataResponse.setStatus(0);
        return dataResponse;
    }

    @Override
    public DataItemResponse queryWalletInfo(TokenIdRequest request) {

        DataItemResponse dataResponse = new DataItemResponse();
        if (StringUtils.isBlank(request.getId())) {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }

//        QueryFilter filter = new QueryFilter();
//        Map<String, Object> map = new HashMap<>();
//        map.put("USER_ID", request.getId());
//        filter.setParams(map);
//        GtyWallet gtyWallet = gtySer.queryOne(filter);
//		gtyWallet.setMncNum(new BigDecimal(getTokenBlance(gtyWallet.getWalletAddress())));
        Map<String, Object> map = new HashMap<>();
        map.put("id", request.getId());
        CustomerInfoResponse userData = cuCustomerInfoDao.getExtend(map, "queryCustomerInfo");

        List<GtyWallet> gtyWallet1 = gtyWalletDao.queryExtend(new QueryFilterBuilder().put("userId", request.getId()).build(),
                "queryInfo");

        QueryFilter filter = new QueryFilter();
        Map<String, Object> map11 = new HashMap<>();
        map11.put("customerId", request.getId());
        filter.setParams(map11);
        MallShopAdvert advert = mallShopAdvertDao.queryOne(filter);

        GtyWallet gtyWallet = new GtyWallet();
        if (gtyWallet1.size() != 0) {
            gtyWallet = gtyWallet1.get(0);
        }
        if (advert != null) {
            gtyWallet.setMerchant(true);
        }

        Map<String, String> maps1 = new HashMap<>();
        maps1.put("symbol", "3");
        String tradeBean = HttpClientUtils.invokeGet("http://bitin.io:8090/api/v1/ticker", maps1);
        TradeBean tradeBean1 = new Gson().fromJson(tradeBean, TradeBean.class);
        if (tradeBean1 != null && tradeBean1.getData() != null && !StringUtils.isBlank(tradeBean1.getData().getLast())) {
            gtyWallet.setMncPrice(new BigDecimal(tradeBean1.getData().getLast()));
            double a = Double.parseDouble(tradeBean1.getData().getLast());
            double b = Double.parseDouble(tradeBean1.getData().getYdayClose());
            if (tradeBean1.getData().getYdayClose().equals("0")) {
                b = 1;
            }
            if (a > b) {
                double c = (a - b) / b * 100;
                BigDecimal cc = new BigDecimal(c);
                gtyWallet.setPoint("+" + cc.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%");
            } else {
                double c = (b - a) / b * 100;
                BigDecimal cc = new BigDecimal(c);
                gtyWallet.setPoint("-" + cc.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%");
            }
        } else {
            gtyWallet.setMncPrice(new BigDecimal("1"));
            gtyWallet.setPoint("+9.1%");
        }

        if (gtyWallet != null) {

            if (gtyWallet.getWalletFrozen() == 0) {
                gtyWallet.setReleasedTokenNum(new BigDecimal("0.00000"));
                gtyWallet.setReleasedScoreNum(new BigDecimal("0.00000"));
                gtyWallet.setReleasedSuperNum(new BigDecimal("0.00000"));
            } else {
                BigDecimal price = gtyWallet.getMncPrice();

                if (price.compareTo(BigDecimal.ZERO) == 0) {
                    price = BigDecimal.ONE;
                }

                QueryFilter filter1 = new QueryFilter();
                Map<String, Object> maps = new HashMap<>();
                maps.put("id", "1");
                filter.setParams(maps);
                GtyLimitWallet gtyLimitWallet = gtyWalletLimitDao.queryOne(filter1);

                String sMtokenPoint = gtyLimitWallet.getLimitMtokenReleasePoint() + "";
                String sSuperPoint = gtyLimitWallet.getLimitMncReleasePoint() + "";
                String sScorePoint = gtyLimitWallet.getLimitScoreReleasePoint() + "";

                if (gtyWallet.getScoreNum().compareTo(BigDecimal.ZERO) == 1) {// 等于0不处理
                    if (gtyLimitWallet.getLimitUpScoreRelease().compareTo(gtyWallet.getScoreNum()) == 1) {// 上限大于score
                        BigDecimal bigDecimal = gtyLimitWallet.getLimitUpScoreRelease();
                        if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {// 有上限
                            if (gtyWallet.getScoreNum().compareTo(bigDecimal) >= 0) {//大于上限
                                BigDecimal mScoreRelease = bigDecimal.multiply(new BigDecimal(sScorePoint)).divide(price);
                                gtyWallet.setReleasedScoreNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                            } else {
                                BigDecimal mScoreRelease = gtyWallet.getScoreNum().multiply(new BigDecimal(sScorePoint)).divide(price);
                                gtyWallet.setReleasedScoreNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                            }
                        } else {// 上限为0则无限制
                            BigDecimal mScoreRelease = gtyWallet.getScoreNum().multiply(new BigDecimal(sScorePoint)).divide(price);
                            gtyWallet.setReleasedScoreNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                        }

                    } else {// 判断是否小于下限
                        if (gtyWallet.getScoreNum().compareTo(gtyLimitWallet.getLimitDownScoreRelease()) >= 0) {// 实际金额大于下限
                            BigDecimal mScoreRelease = gtyWallet.getScoreNum().multiply(new BigDecimal(sScorePoint)).divide(price);
                            gtyWallet.setReleasedScoreNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                        } else {
                            gtyWallet.setReleasedScoreNum(new BigDecimal("0.0000"));
                        }
                    }
                } else {
                    gtyWallet.setReleasedScoreNum(new BigDecimal("0.0000"));
                }


                if (gtyWallet.getSuperNum().compareTo(BigDecimal.ZERO) == 1) {// 等于0不处理
                    if (gtyLimitWallet.getLimitUpSuperRelease().compareTo(gtyWallet.getSuperNum()) == 1) {// 上限大于score
                        BigDecimal bigDecimal = gtyLimitWallet.getLimitUpSuperRelease();
                        if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {// 有上限
                            if (gtyWallet.getSuperNum().compareTo(bigDecimal) >= 0) {//大于上限
                                BigDecimal mScoreRelease = bigDecimal.multiply(new BigDecimal(sSuperPoint)).divide(price);
                                gtyWallet.setReleasedSuperNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                            } else {
                                BigDecimal mScoreRelease = gtyWallet.getSuperNum().multiply(new BigDecimal(sSuperPoint)).divide(price);
                                gtyWallet.setReleasedSuperNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                            }
                        } else {// 上限为0则无限制
                            BigDecimal mScoreRelease = gtyWallet.getSuperNum().multiply(new BigDecimal(sSuperPoint)).divide(price);
                            gtyWallet.setReleasedSuperNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                        }

                    } else {// 判断是否小于下限
                        if (gtyWallet.getSuperNum().compareTo(gtyLimitWallet.getLimitDownScoreRelease()) >= 0) {// 实际金额大于下限
                            BigDecimal mScoreRelease = gtyWallet.getSuperNum().multiply(new BigDecimal(sSuperPoint)).divide(price);
                            gtyWallet.setReleasedSuperNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                        } else {
                            gtyWallet.setReleasedSuperNum(new BigDecimal("0.0000"));
                        }
                    }
                } else {
                    gtyWallet.setReleasedSuperNum(new BigDecimal("0.0000"));
                }


                if (gtyWallet.getmTokenNum().compareTo(BigDecimal.ZERO) == 1) {// 等于0不处理
                    if (gtyLimitWallet.getLimitUpMtokenRelease().compareTo(gtyWallet.getmTokenNum()) == 1) {// 上限大于score
                        BigDecimal bigDecimal = gtyLimitWallet.getLimitUpMtokenRelease();
                        if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {// 有上限
                            if (gtyWallet.getmTokenNum().compareTo(bigDecimal) >= 0) {//大于上限
                                BigDecimal mScoreRelease = bigDecimal.multiply(new BigDecimal(sMtokenPoint)).divide(price);
                                gtyWallet.setReleasedTokenNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                            } else {
                                BigDecimal mScoreRelease = gtyWallet.getmTokenNum().multiply(new BigDecimal(sMtokenPoint)).divide(price);
                                gtyWallet.setReleasedTokenNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                            }
                        } else {// 上限为0则无限制
                            BigDecimal mScoreRelease = gtyWallet.getmTokenNum().multiply(new BigDecimal(sMtokenPoint)).divide(price);
                            gtyWallet.setReleasedTokenNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                        }

                    } else {// 判断是否小于下限
                        if (gtyWallet.getmTokenNum().compareTo(gtyLimitWallet.getLimitDownScoreRelease()) >= 0) {// 实际金额大于下限
                            BigDecimal mScoreRelease = gtyWallet.getmTokenNum().multiply(new BigDecimal(sMtokenPoint)).divide(price);
                            gtyWallet.setReleasedTokenNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
                        } else {
                            gtyWallet.setReleasedTokenNum(new BigDecimal("0.0000"));
                        }
                    }
                } else {
                    gtyWallet.setReleasedTokenNum(new BigDecimal("0.0000"));
                }
            }
        } else {
            gtyWallet = new GtyWallet();
            gtyWallet.setReleasedTokenNum(new BigDecimal("0.00000"));
            gtyWallet.setReleasedScoreNum(new BigDecimal("0.00000"));
            gtyWallet.setReleasedSuperNum(new BigDecimal("0.00000"));
        }
        gtyWallet.setSuperNum(gtyWallet.getSuperNum().add(gtyWallet.getReleasedMnc()));
        gtyWallet.setNickName(userData.getCustomerName());
        dataResponse.setItem(gtyWallet);
        dataResponse.setMsg("成功");
        dataResponse.setStatus(0);
        return dataResponse;
    }

    @Override
    public DataResponse setLimit(IdNumRequest request, HttpServletResponse res) {
        DataResponse dataResponse = new DataResponse();
        if (StringUtils.isBlank(request.getId())) {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }
        GtyLimitWallet gtyLimitWallet = new GtyLimitWallet();
        if (request.getType() == 1) {//1,释放mnc上下线；2，释放mtoken上下限；3，释放积分上下限
            gtyLimitWallet.setLimitUpSuperRelease(new BigDecimal(request.getUpNum()));
            gtyLimitWallet.setLimitDownSuperRelease(new BigDecimal(request.getDownNum()));
        } else if (request.getType() == 2) {//1,释放mnc上下线；2，释放mtoken上下限；3，释放积分上下限
            gtyLimitWallet.setLimitDownMtokenRelease(new BigDecimal(request.getDownNum()));
            gtyLimitWallet.setLimitUpMtokenRelease(new BigDecimal(request.getUpNum()));
        } else if (request.getType() == 3) {//1,释放mnc上下线；2，释放mtoken上下限；3，释放积分上下限
            gtyLimitWallet.setLimitDownScoreRelease(new BigDecimal(request.getDownNum()));
            gtyLimitWallet.setLimitUpScoreRelease(new BigDecimal(request.getUpNum()));
        } else if (request.getType() == 4) {//1,释放mnc上下线；2，释放mtoken上下限；3，释放积分上下限,4设置mnc提现下限
            gtyLimitWallet.setLimitWithDraw(request.getDownNum());
        }

        gtyLimitWallet.setId(request.getId());
        gtyWalletLimitDao.update(gtyLimitWallet);
        dataResponse.setStatus(0);
        dataResponse.setMsg("成功");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/json;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");//表明服务器支持的所有头信息字段
        res.setHeader("Access-Control-Allow-Credentials", "true"); //如果要把Cookie发到服务器，需要指定Access-Control-Allow-Credentials字段为true;
        res.setHeader("XDomainRequestAllowed", "1");
        return dataResponse;
    }

    @Override
    public DataResponse setPointLimit(IdPointRequest request) {
        DataResponse dataResponse = new DataResponse();
        if (StringUtils.isBlank(request.getId())) {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }
        try {
            BigDecimal toMone = new BigDecimal(request.getNum());
            if (toMone.compareTo(BigDecimal.ZERO) <= 0) {
                dataResponse.setStatus(1);
                dataResponse.setMsg("数量错误");
                return dataResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            dataResponse.setStatus(1);
            dataResponse.setMsg("数量错误");
            return dataResponse;
        }
        GtyLimitWallet gtyLimitWallet = new GtyLimitWallet();
        if (request.getType() == 1) {//1,释放mnc比例；2，释放mtoken比例；3，释放积分比例;4,点对点手续费比例;5,mnc转交易所手续费
            gtyLimitWallet.setLimitMncReleasePoint(new BigDecimal(request.getNum()));
        } else if (request.getType() == 2) {
            gtyLimitWallet.setLimitMtokenReleasePoint(new BigDecimal(request.getNum()));
        } else if (request.getType() == 3) {
            gtyLimitWallet.setLimitScoreReleasePoint(new BigDecimal(request.getNum()));
        } else if (request.getType() == 4) {
            gtyLimitWallet.setP2pPoint(new BigDecimal(request.getNum()));
        } else if (request.getType() == 5) {
            gtyLimitWallet.setMncTradePoint(new BigDecimal(request.getNum()));
        }
        gtyLimitWallet.setId(request.getId());
        gtyWalletLimitDao.update(gtyLimitWallet);
        dataResponse.setStatus(0);
        dataResponse.setMsg("成功");

        return dataResponse;
    }

    @Override
    public DataResponse modifyMncNum(IdNumsRequest request) {
        DataResponse dataResponse = new DataResponse();
        if (StringUtils.isBlank(request.getId())) {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }
        List<GtyWallet> gtyWallet1 = gtyWalletDao.queryExtend(new QueryFilterBuilder().put("userId", request.getId()).build(),
                "queryInfo");
        if (gtyWallet1.size() != 0) {
            GtyWallet gtyWallet = gtyWallet1.get(0);
            gtyWallet.setMncNum(new BigDecimal(request.getNum()));
            gtyWallet.setUserId(request.getId());
            gtyWalletDao.update(gtyWallet);
            dataResponse.setMsg("成功");
            dataResponse.setStatus(0);
        }
        return dataResponse;
    }

    @Override
    public DataItemResponse getLimitInfo() {
        DataItemResponse dataItemResponse = new DataItemResponse();
        QueryFilter filter = new QueryFilter();
        Map<String, Object> maps = new HashMap<>();
        maps.put("id", "1");
        filter.setParams(maps);
        GtyLimitWallet gtyLimitWallet = gtyWalletLimitDao.queryOne(filter);
        dataItemResponse.setItem(gtyLimitWallet);
        dataItemResponse.setStatus(0);
        dataItemResponse.setMsg("成功");
        return dataItemResponse;
    }

    @Override
    public DataItemResponse getUserId(TokenRequest request) {
        DataItemResponse dataResponse = new DataItemResponse();
        CuCustomerLogin user = checkToken(request.getToken());
        if (user != null) {
            GtyId gtyId = new GtyId();
            gtyId.setUserId(user.getId());
            dataResponse.setItem(gtyId);
            dataResponse.setMsg("成功");
            dataResponse.setStatus(0);
        } else {
            dataResponse.setMsg("失败");
            dataResponse.setStatus(1);
        }

        return dataResponse;
    }

    /**
     * ERC-20Token交易
     *
     * @param from
     * @param to
     * @param value
     * @param privateKey
     * @return
     * @throws Exception
     */
    @RequestMapping("transferToken")
    public TransationResponse transferERC20Token(String from,
                                                 String to,
                                                 BigInteger value,
                                                 String privateKey,
                                                 String contractAddress) throws Exception {
        TransationResponse transationResponse = null;
        String hexValue = null;
        try {
            transationResponse = new TransationResponse();
            //加载转账所需的凭证，用私钥
            Credentials credentials = Credentials.create(privateKey);
            //获取nonce，交易笔数
            BigInteger nonce = getNonce(from);
            //get gasPrice
            BigInteger gasPrice = requestCurrentGasPrice();
            BigInteger gasLimit = Contract.GAS_LIMIT;

            //创建RawTransaction交易对象
            Function function = new Function(
                    "transfer",
                    Arrays.asList(new Address(to), new Uint256(value)),
                    Arrays.asList(new TypeReference<Type>() {
                    }));

            String encodedFunction = FunctionEncoder.encode(function);

            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce,
                    gasPrice,
                    gasLimit,
                    contractAddress, encodedFunction);

            //签名Transaction，这里要对交易做签名
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            hexValue = Numeric.toHexString(signMessage);
            EthSendTransaction ethSendTransaction = Web3JConfigure.getWeb3JInstance().ethSendRawTransaction(hexValue).sendAsync().get();
            TxInfoResponse txInfoResponse = new TxInfoResponse();
            txInfoResponse.setTxHash(ethSendTransaction.getTransactionHash());
            txInfoResponse.setTxTime(System.currentTimeMillis() / 1000 + "");
            transationResponse.setData(txInfoResponse);
            transationResponse.setMsg("转账成功");
            transationResponse.setStatus(0);
        } catch (Exception e) {
            transationResponse.setMsg("转账失败");
            transationResponse.setStatus(1);
            e.printStackTrace();
        }

        return transationResponse;
    }


    private boolean transferEth(String toAddress, String amount) {

        List<EthBean> records = ethDao.query(new QueryFilterBuilder().build());
        EthBean ethBean = null;
        if (records.size() != 0) {
            ethBean = records.get(0);
        }
        if (ethBean == null) {
            return false;
        }
        try {
            HttpService httpService = new HttpService("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48");
            Web3j web3j = Web3j.build(httpService);

            Credentials credentials = Credentials.create("privateKey");

            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                    ethBean.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                    nonce, Convert.toWei("18", Convert.Unit.GWEI).toBigInteger(),
                    Convert.toWei("45000", Convert.Unit.WEI).toBigInteger(), toAddress, new BigInteger(amount));
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);

            EthSendTransaction ethSendTransaction = null;

            ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            if (ethSendTransaction.hasError()) {
                LogUtils.performance.info("transfer error", ethSendTransaction.getError().getMessage(), new Date());
                return false;

            } else {
                LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
                String transactionHash = ethSendTransaction.getTransactionHash();
                LogUtils.performance.info("Transfer transactionHash:", transactionHash, new Date());
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 获取nonce，交易笔数
     *
     * @param from
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private BigInteger getNonce(String from) throws ExecutionException, InterruptedException {
        EthGetTransactionCount transactionCount = Web3JConfigure.getWeb3JInstance().ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = transactionCount.getTransactionCount();
        return nonce;
    }

    private BigInteger requestCurrentGasPrice() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("jsonrpc", "2.0");
        map1.put("method", "eth_gasPrice");
        map1.put("id", "73");
        JsonArray jsonArray = new JsonArray();
        map1.put("params", jsonArray);
        String gasInfo = HttpClientUtils.postJSON("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48", new Gson().toJson(map1));
        String number = new BigInteger(gasInfo.substring(2, gasInfo.length()), 16).toString(10);
        return BigInteger.valueOf(Long.parseLong(number));
    }
}
