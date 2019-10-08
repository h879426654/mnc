package com.basics.gty.service.impl;

import com.basics.common.AddressRequest;
import com.basics.common.BaseApiService;
import com.basics.common.DataResponse;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.gty.controller.request.TokenTransferRequest;
import com.basics.gty.entity.*;
import com.basics.gty.service.GtyTransferService;
import com.basics.gty.service.GtyWalletService;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class GtyTransferMybatisService extends BaseApiService implements GtyTransferService {

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
         String userId =   user.getId();
//        String userId = request.getId();
        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("USER_ID", userId);
        filter.setParams(map);

        GtyWallet gtyWallet = gtyWalletDao.queryOne(filter);
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
                params.put("amount", request.getNum());
                String info = HttpClientUtils.invokeGet("http://47.56.169.214:8085/api/increase", params);
                TradeTransferBean tradeTransferBean = new Gson().fromJson(info,TradeTransferBean.class);
                if(tradeTransferBean.isSuccess()){
                    GtyWallet gtyWallet1 = new GtyWallet();
                    gtyWallet1.setUserId(gtyWallet.getUserId());
                    gtyWallet1.setMncNum(gtyWallet.getMncNum().subtract(new BigDecimal(request.getNum())));
                    gtyWalletDao.update(gtyWallet1);
                }else{
                    response.setStatus(0);
                    response.setMsg("转账失败");
                    return response;
                }
//                transferERC20Token(gtyWallet.getWalletAddress(),);

            }
        } else if (request.getType() == 2) {// mnc转流通钱包
            if (gtyWallet.getMoveNum().compareTo(new BigDecimal(request.getNum())) == -1) {
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
            if (gtyWallet.getSuperNum().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {
                GtyWallet gtyWallet1 = new GtyWallet();
                gtyWallet1.setUserId(gtyWallet.getUserId());
                gtyWallet1.setMoveNum(gtyWallet.getMoveNum().subtract(new BigDecimal(request.getNum())));
                gtyWallet1.setRecordNum(gtyWallet.getRecordNum().add(new BigDecimal(request.getNum())));
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
                gtyWallet1.setMoveNum(gtyWallet.getMoveNum().subtract(new BigDecimal(request.getNum())));
                gtyWallet1.setmTokenNum(new BigDecimal(request.getNum()).multiply(new BigDecimal("13.32")).add(gtyWallet1.getmTokenNum()));
                Map<String,String> maps = new HashMap<>();
                maps.put("symbol","3");
                String tradeBean = HttpClientUtils.invokeGet("http://bitin.io:8090/api/v1/ticker",maps);
                TradeBean tradeBean1= new Gson().fromJson(tradeBean,TradeBean.class);
                String price;
                if(!StringUtils.isBlank(tradeBean1.getData().getLast())){
                    if(tradeBean1.getData().getLast().equals("0")){
                        price = "1";
                    }else{
                        price = tradeBean1.getData().getLast();
                    }
                }else{
                    price = "1";
                }
                if(gtyWallet1.getmTokenNum().compareTo(new BigDecimal("300").divide(new BigDecimal(price)))==1){
                    gtyWallet1.setWalletFrozen(1);
                }
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

                QueryFilter filter1 = new QueryFilter();
                Map<String, Object> map2 = new HashMap<>();
                map2.put("WALLET_ADDRESS", request.getToAddress());
                filter1.setParams(map2);
                GtyWallet gtyWallet2 = gtyWalletDao.queryOne(filter1);
                if (gtyWallet2 == null || StringUtils.isBlank(gtyWallet2.getWalletAddress())) {
                    gtyWallet2 = new GtyWallet();
                    gtyWallet2.setUserId(request.getToAddress());
                    gtyWallet2.setMoveNum(new BigDecimal(request.getNum()));
                    gtyWalletDao.save(gtyWallet2);
                } else {
                    gtyWallet2.setUserId(request.getToAddress());
                    gtyWallet2.setMoveNum(gtyWallet2.getMoveNum().add(new BigDecimal(request.getNum())));
                    gtyWalletDao.update(gtyWallet2);
                }
                gtyWalletDao.update(gtyWallet1);
            }
        } else if (request.getType() == 6) {// 超级钱包转流通钱包
            if (gtyWallet.getSuperNum().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {
                GtyWallet gtyWallet1 = new GtyWallet();
                gtyWallet1.setUserId(gtyWallet.getUserId());
                gtyWallet1.setSuperNum(gtyWallet.getSuperNum().subtract(new BigDecimal(request.getNum())));
                gtyWallet1.setMoveNum(gtyWallet.getMoveNum().add(new BigDecimal(request.getNum())));
                gtyWalletDao.update(gtyWallet1);
            }
        } else if (request.getType() == 7) {// 超级钱包转交易所；
            if (gtyWallet.getSuperNum().compareTo(new BigDecimal(request.getNum())) == -1) {
                response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
                response.setStatus(1);
                response.setMsg("数量错误");
                return response;
            } else {//todo 转账
                Map<String, String> params = new HashMap<>();
                params.put("wallterKey", request.getToAddress());
                params.put("amount", request.getNum());
                String info = HttpClientUtils.invokeGet("http://47.56.169.214:8085/api/increase", params);
                TradeTransferBean tradeTransferBean = new Gson().fromJson(info,TradeTransferBean.class);
                if(tradeTransferBean.isSuccess()){
                    GtyWallet gtyWallet1 = new GtyWallet();
                    gtyWallet1.setUserId(gtyWallet.getUserId());
                    gtyWallet1.setSuperNum(gtyWallet.getSuperNum().subtract(new BigDecimal(request.getNum())));
                    gtyWalletDao.update(gtyWallet1);
                }else{
                    response.setStatus(0);
                    response.setMsg("转账失败");
                    return response;
                }
            }
        }

       if(request.getType() == 1){// mnc转交易所
           addHistory(gtyWallet,request,"MNC出到转交易所","-");
        }else if(request.getType() == 2){// mnc转流通钱包
//           addHistory(gtyWallet,request,"MNC转出到流通钱包","-");
           addHistory(gtyWallet,request,"MNC转入到流通钱包","+");
        }else if(request.getType() == 3){// 流通转记账钱包
//           addHistory(gtyWallet,request,"流通钱包转出到记账钱包","-");
           addHistory(gtyWallet,request,"流通钱包转入到记账钱包","+");
        }else if(request.getType() == 4){// 流通转mtoken
//           addHistory(gtyWallet,request,"流通钱包转出到MTOKEN","-");
           addHistory(gtyWallet,request,"流通钱包转入到MTOKEN","+");
        }else if(request.getType() == 5){// 流通点对点
           addHistory(gtyWallet,request,"流通钱包转账","-");
        }else if(request.getType() == 6){// 超级钱包转流通钱包
//           addHistory(gtyWallet,request,"超级钱包转出到流通钱包","-");
           addHistory(gtyWallet,request,"超级钱包转入到流通钱包","+");
        }else if(request.getType() == 7){// 超级钱包转交易所；
           addHistory(gtyWallet,request,"超级钱包转出到交易所","-");
       }
        response.setMsg("转账成功");
        response.setStatus(0);
        return response;
    }

    private void addHistory( GtyWallet gtyWallet,TokenTransferRequest request,String recordName ,String mark ){

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


    @Override
    public DataResponse transfer(AddressRequest request, HttpServletRequest req) {
        DataResponse dataResponse = new DataResponse();
        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("WALLET_ADDRESS", request.getAddress());
        filter.setParams(map);
        GtyWallet gtyWallet1 = gtyWalletDao.queryOne(filter);
        if(gtyWallet1==null || StringUtils.isBlank(gtyWallet1.getUserId())){
            dataResponse.setMsg("未找到对应地址");
            dataResponse.setStatus(1);
            return dataResponse;
        }
        gtyWallet1.setMncNum(gtyWallet1.getMncNum().add(request.getNum()));
        gtyWalletDao.update(gtyWallet1);
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

    private boolean transferEth(String toAddress,String amount){

        List<EthBean> records = ethDao.query(new QueryFilterBuilder().build());
        EthBean ethBean = null;
        if(records.size()!=0){
            ethBean = records.get(0);
        }
        if(ethBean==null){
            return false;
        }
        try {
        HttpService httpService = new HttpService("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48");
        Web3j  web3j = Web3j.build(httpService);

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
