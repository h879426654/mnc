package com.basics.gty.controller.api;

import com.basics.common.*;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.service.CuCustomerInfoService;
import com.basics.cu.service.CustomerApiService;
import com.basics.gty.controller.request.TokenTransferRequest;
import com.basics.gty.entity.GtyWallet;
import com.basics.gty.entity.GtyWalletHistory;
import com.basics.gty.service.GtyWalletHistory2Service;
import com.basics.gty.service.GtyWalletService;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.auth.HttpClientUtils;
import com.basics.wallet.Web3JConfigure;
import com.basics.wallet.controller.response.TransationResponse;
import com.basics.wallet.controller.response.TxInfoResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/gty/wallet/")
public class GtyWalletApiController implements ApplicationContextAware {

    @SuppressWarnings("unused")
    private ApplicationContext applicationContext;
    @Autowired
    private GtyWalletService gtySer;
	@Autowired
	private GtyWalletHistory2Service gtyWalletHistoryService;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("getWalletInfo")
    public GtyWallet query(TokenIdRequest request, HttpServletRequest req) {
        GtyWallet gtyWallet = new GtyWallet();
        if (StringUtils.isBlank(request.getId())) {
            gtyWallet.setMsg("未找到该用户");
            gtyWallet.setStatus(1);
            return gtyWallet;
        }

        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("USER_ID", request.getId());
        filter.setParams(map);
        gtyWallet = gtySer.queryOne(filter);
//		gtyWallet.setMncNum(new BigDecimal(getTokenBlance(gtyWallet.getWalletAddress())));

        BigDecimal mTokenRelease = gtyWallet.getmTokenNum().multiply(new BigDecimal(1 / 1000));
        gtyWallet.setReleasedTokenNum(mTokenRelease.setScale(5, BigDecimal.ROUND_HALF_UP));

        BigDecimal mSuperRelease = gtyWallet.getSuperNum().multiply(new BigDecimal(5 / 1000));
        gtyWallet.setReleasedSuperNum(mSuperRelease.setScale(5, BigDecimal.ROUND_HALF_UP));

        BigDecimal mScoreRelease = gtyWallet.getScoreNum().multiply(new BigDecimal(1 / 1000));
        gtyWallet.setReleasedScoreNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));

        gtyWallet.setMncPrice(new BigDecimal(10.5));
        gtyWallet.setPoint("+9.1%");

        gtyWallet.setMsg("成功");
        gtyWallet.setStatus(0);
        return gtyWallet;
    }

    private String getTokenBlance(String address) {//TokenBalanceRequest request,
        Map<String, String> params = new HashMap<>();
        params.put("module", "account");
        params.put("action", "tokentx");
        params.put("address", address);
        params.put("contractaddress", "0x2657bd0193a127c93152e043e29980bf16a1b446");
        params.put("tag", "latest");
        params.put("apikey", "6XY2BCUEKHGDC8CMDSY1M6VCFNJZSV9HJA");
        String pendingBlock = HttpClientUtils.invokeGet("https://api.etherscan.io/api", params);
        return pendingBlock;
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

    @RequestMapping("createWallet")
    public DataResponse createWallet(TokenRequest request, HttpServletRequest req){
        DataResponse dataResponse = new DataResponse();
        DataItemResponse<CustomerInfoResponse> response  = cuCustomerInfoService.selectCustomerInfo(request, req);
        CustomerInfoResponse customerInfoResponse;
        if(response.getItem()!=null){
            customerInfoResponse = response.getItem();
        }else {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }

        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("USER_ID", customerInfoResponse.getId());
        filter.setParams(map);
        GtyWallet gtyWallet1 = gtySer.queryOne(filter);
        if(StringUtils.isBlank(gtyWallet1.getWalletAddress())){
            dataResponse.setStatus(0);
            dataResponse.setMsg("成功");
            return dataResponse;
        }

//        String walletFileName = WalletUtils.generateNewWalletFile(password, new File(walletFilePath), false);
//        Credentials credentials = WalletUtils.loadCredentials(password, walletFilePath + "/" + walletFileName);
//        String address = credentials.getAddress();
//        GtyWallet gtyWallet = new GtyWallet();
//        gtyWallet.setUserId(customerInfoResponse.getId());
//        gtyWallet.setWalletAddress(address);
//        gtySer.save(gtyWallet);
        dataResponse.setStatus(0);
        dataResponse.setMsg("成功");
        return dataResponse;
    }


    @Autowired
    CustomerApiService cuCustomerInfoService;


    @RequestMapping("transferMoney")
    public DataResponse transfer(TokenTransferRequest request, HttpServletRequest req) {
        DataItemResponse<CustomerInfoResponse> response  = cuCustomerInfoService.selectCustomerInfo(request, req);
        DataResponse dataResponse = new DataResponse();
        if(response.getItem()!=null){

        }else {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }

        if (StringUtils.isBlank(request.getId())) {
            dataResponse.setMsg("未找到该用户");
            dataResponse.setStatus(1);
            return dataResponse;
        }
        if (StringUtils.isBlank(request.getNum())) {
            dataResponse.setMsg("请输入数量");
            dataResponse.setStatus(1);
            return dataResponse;
        }

        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("USER_ID", request.getId());
        filter.setParams(map);

        GtyWallet gtyWallet = gtySer.queryOne(filter);
        BigDecimal mncChain = BigDecimal.ZERO;
        String ss = getTokenBlance(gtyWallet.getWalletAddress());
        if (!StringUtils.isBlank(ss)) {
            mncChain = new BigDecimal(ss);
        }

        if(request.getFromType()==0){// mnc
			if (request.getToType() == 0) {// 转到交易所
				if (gtyWallet.getReleasedMnc().add(mncChain).compareTo(new BigDecimal(request.getNum())) == 1) {
//					transferERC20Token();
				} else {
					return transferError(dataResponse, "数量错误");
				}
			}else if(request.getToType() == 1){// 转到流通钱包
				if (gtyWallet.getReleasedMnc().add(mncChain).compareTo(new BigDecimal(request.getNum())) == 1) {

				}
			}
		}else if(request.getFromType()==1){// 流通钱包
			if (gtyWallet.getMoveNum().compareTo(new BigDecimal(request.getNum())) == -1) {
				return transferError(dataResponse, "数量错误");
			}else{
				GtyWallet gtyWallet1 = new GtyWallet();
				gtyWallet1.setUserId(gtyWallet.getUserId());
				gtyWallet1.setMoveNum(gtyWallet.getMoveNum().subtract(new BigDecimal(request.getNum())));
				if(request.getToType()==2){// 转到mtoken
					gtyWallet1.setmTokenNum(gtyWallet.getmTokenNum().add((new BigDecimal(request.getNum()))));
					gtyWallet1.setUserId(gtyWallet.getUserId());
					gtySer.save(gtyWallet1);
				}
				else if(request.getToType()==3) {// 转到记账钱包
					gtyWallet1.setRecordNum(gtyWallet.getRecordNum().add((new BigDecimal(request.getNum()))));
					gtyWallet1.setUserId(gtyWallet.getUserId());
					gtySer.save(gtyWallet1);
				}
			}
		}else if(request.getFromType()==2){// 转到super
			if (gtyWallet.getSuperNum().compareTo(new BigDecimal(request.getNum())) == -1) {
				return transferError(dataResponse, "数量错误");
			}else{
				GtyWallet gtyWallet1 = new GtyWallet();
				gtyWallet1.setUserId(gtyWallet.getUserId());
				gtyWallet1.setSuperNum(gtyWallet.getSuperNum().subtract(new BigDecimal(request.getNum())));
				gtyWallet1.setRecordNum(gtyWallet.getSuperNum().add(new BigDecimal(request.getNum())));
				gtySer.save(gtyWallet1);
			}
		}

		GtyWalletHistory gtyWalletHistory = new GtyWalletHistory();
		String uuid = UUID.randomUUID().toString().replace("-","");
		gtyWalletHistory.setId(uuid);
		gtyWalletHistory.setRecordNum(new BigDecimal(request.getNum()));
		gtyWalletHistory.setRecordType(request.getToType());
		gtyWalletHistory.setToAccount(request.getToAddress());
		gtyWalletHistory.setUserId(gtyWallet.getUserId());
		gtyWalletHistoryService.save(gtyWalletHistory);

		dataResponse.setMsg("转账成功");
		dataResponse.setStatus(0);
        return dataResponse;
    }

    @RequestMapping("transferTokenHistory")
    public DataItemResponse<List<GtyWalletHistory>> getWalletTransferHistory(IdRequest request){
        DataItemResponse dataItemResponse = new DataItemResponse();
        if (StringUtils.isBlank(request.getId())) {
            dataItemResponse.setMsg("未找到该用户");
            dataItemResponse.setStatus(1);
            return dataItemResponse;
        }
        QueryFilter filter = new QueryFilter();
        Map<String, Object> map = new HashMap<>();
        map.put("USER_ID", request.getId());
        filter.setParams(map);

        List<GtyWalletHistory> gtyWallet = gtyWalletHistoryService.query(filter);
        dataItemResponse.setItem(gtyWallet);
        dataItemResponse.setMsg("成功");
        dataItemResponse.setStatus(0);
        return dataItemResponse;
    }


    private DataResponse transferError(DataResponse dataResponse, String msg) {
        dataResponse.setMsg(msg);
        dataResponse.setStatus(1);
        return dataResponse;
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
