package com.basics.wallet.controller.api;

import com.alibaba.fastjson.JSON;
import com.basics.broswer.controller.response.BlockInfoResponse;
import com.basics.broswer.controller.response.BlockNetResponse;
import com.basics.common.*;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.entity.CuCustomerAddress;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.cu.service.CustomerApiService;
import com.basics.mall.controller.request.*;
import com.basics.mall.controller.response.MallProductDetailResponse;
import com.basics.mall.controller.response.MallProductResponsePlus;
import com.basics.mall.controller.response.MyCollectionResponse;
import com.basics.mall.entity.MallProductClassify;
import com.basics.mall.service.MallApiService;
import com.basics.mall.vo.CommentInfoVo;
import com.basics.support.auth.HttpClientUtils;
import com.basics.tr.service.TradeCoinApiService;
import com.basics.wallet.Web3JConfigure;
import com.basics.wallet.controller.request.TokenBalanceRequest;
import com.basics.wallet.controller.response.TransationResponse;
import com.basics.wallet.controller.response.TxInfoResponse;
import com.basics.wallet.controller.response.WalletResponse;
import com.basics.wallet.service.WalletService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.Contract;
import org.web3j.utils.Collection;
import org.web3j.utils.Numeric;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 钱包
 */
@RestController
@RequestMapping("/api/wallet/")
public class WalletController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Autowired
	private CustomerApiService customerApiService;

	@Autowired
	private WalletService walletService;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

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
			txInfoResponse.setTxTime(System.currentTimeMillis()/1000+"");
			transationResponse.setData(txInfoResponse);
			transationResponse.setMsg("转账成功");
			transationResponse.setStatus(200);
		} catch (Exception e) {
			transationResponse.setMsg("转账失败");
			transationResponse.setStatus(500);
			e.printStackTrace();
		}

		return transationResponse;
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

	private BigInteger requestCurrentGasPrice(){
		Map<String,Object> map1 = new HashMap<>();
		map1.put("jsonrpc","2.0");
		map1.put("method","eth_gasPrice");
		map1.put("id","73");
		JsonArray jsonArray = new JsonArray();
		map1.put("params",jsonArray);
		String gasInfo=HttpClientUtils.postJSON("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48",new Gson().toJson(map1));
		String number = new BigInteger(gasInfo.substring(2,gasInfo.length()), 16).toString(10);
		return BigInteger.valueOf(Long.parseLong(number));
	}

	// 获取余额
	@RequestMapping("getTokenBlance")
	public String getTokenBlance(HttpServletRequest req) {//TokenBalanceRequest request,
		Map<String, String> params = new HashMap<>();
		params.put("module","account");
		params.put("action","tokentx");
		params.put("address","0xfaa0529e5d47ece85e093f5936bfd781de32060d");
		params.put("contractaddress","0x2657bd0193a127c93152e043e29980bf16a1b446");
		params.put("tag","latest");
		params.put("apikey","6XY2BCUEKHGDC8CMDSY1M6VCFNJZSV9HJA");
		String pendingBlock=HttpClientUtils.invokeGet("https://api.etherscan.io/api",params);
		return pendingBlock;
	}

	// 获取钱包对应的用户信息
//	@RequestMapping("getWalletInfo")
//	public DataItemResponse<CustomerInfoResponse> getwalletInfo(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
//		DataItemResponse<CustomerInfoResponse> response = new DataItemResponse<>();
//		if (result.hasErrors()) {
//			response.onBindingError(result.getAllErrors());
//			return response;
//		}
//		try {
//			response = customerApiService.selectCustomerInfo(request, req);
//		} catch (Exception e) {
//			response.onException(e);
//		}
//		return response;
//	}

	// 获取钱包对应的用户信息
	@RequestMapping("getWalletInfo")
	public WalletResponse getwalletInfo(HttpServletRequest req) {
		TokenIdRequest tokenIdRequest = new TokenIdRequest();
		tokenIdRequest.setId("1");
		WalletResponse walletResponse=walletService.getWalletInfo(tokenIdRequest,null);
		return walletResponse;
	}

	//测试
	@RequestMapping("test")
	public void getTest(){
		walletService.getTest();
	}
}
