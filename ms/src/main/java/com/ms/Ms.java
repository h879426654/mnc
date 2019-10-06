package com.ms;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
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
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@EnableAutoConfiguration
public class Ms {
    @RequestMapping(value ="/test",method = RequestMethod.GET)
    public void test(){
        Map<String, Object> map1 = new HashMap<>();
        map1.put("jsonrpc", "2.0");
        map1.put("method", "eth_gasPrice");
        map1.put("id", "1");
        JsonArray jsonArray = new JsonArray();
        map1.put("params", jsonArray);
        String gasInfo = HttpClientUtils.postJSON("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48", new Gson().toJson(map1));
        GasResonse jsonObject = new Gson().fromJson(gasInfo,GasResonse.class);
//        String number = new BigInteger(gasInfo.substring(2, gasInfo.length()), 16).toString(10);
        BigInteger bigInteger=Numeric.decodeQuantity((String)jsonObject.getResult());
        System.out.println("bb="+bigInteger+"||"+jsonObject.getResult());
    }

    private String gucContactAddress="0x4a6544008aadfc35f7ab58394a9df174f44bc6ff";// 线上
//      private String gucContactAddress="0x7903db2485c09b7b403fcc29a8a36063fbba6fd1";// 测试

    private String netMode="https://mainnet";//ropsten//mainnet

    private String from="0xd70DA8FeED9C217D65319602654a32699B1e5921";

    @RequestMapping(value ="/transferToken",method = RequestMethod.GET)
    public EthSendTransaction transferERC20Token(
                                                 String to,
                                                 String value) throws Exception {
        BigDecimal bigDecimal = new BigDecimal(100000);
        BigDecimal bigValue = new BigDecimal(value);
        BigInteger bigInteger = bigDecimal.multiply(bigValue).toBigInteger();

        EthSendTransaction ethSendTransaction =null;
                String hexValue = null;
        try {
            HttpService httpService = new HttpService(netMode+".infura.io/v3/926d9374c9ad4f91ad6185f68b935a48");
            Web3j web3j = Web3j.build(httpService);
            //加载转账所需的凭证，用私钥
            Credentials credentials = Credentials.create("89BA293E1F361B72B9348D8401C92DB370752FB6A0942617466277DE11086EFE");
            //获取nonce，交易笔数
            BigInteger nonce = getNonce(from,web3j);
            //get gasPrice
            BigInteger gasPrice = requestCurrentGasPrice();
            BigInteger gasLimit = Contract.GAS_LIMIT;

            //创建RawTransaction交易对象
            Function function = new Function(
                    "transfer",
                    Arrays.asList(new Address(to), new Uint256(bigInteger)),
                    Arrays.asList(new TypeReference<Type>() {
                    }));

            String encodedFunction = FunctionEncoder.encode(function);

            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce,
                    gasPrice,
                    gasLimit,
                    gucContactAddress, encodedFunction);

            //签名Transaction，这里要对交易做签名
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            hexValue = Numeric.toHexString(signMessage);
            ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            LogUtils.performance.info("LogUtils.info transfer "+ethSendTransaction.getResult()+" time "+System.currentTimeMillis());
            return ethSendTransaction;
        } catch (Exception e) {
            LogUtils.performance.error("LogUtils.error transfer "+e.getMessage()+" time "+System.currentTimeMillis());
            e.printStackTrace();
        }

        return ethSendTransaction;
    }

    /**
     * 获取nonce，交易笔数
     *
     * @param from
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private BigInteger getNonce(String from,Web3j web3j) throws ExecutionException, InterruptedException {

        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = transactionCount.getTransactionCount();
        return nonce;
    }

    private BigInteger requestCurrentGasPrice() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("jsonrpc", "2.0");
        map1.put("method", "eth_gasPrice");
        map1.put("id", "1");
        JsonArray jsonArray = new JsonArray();
        map1.put("params", jsonArray);
        String gasInfo = HttpClientUtils.postJSON(netMode+".infura.io/v3/926d9374c9ad4f91ad6185f68b935a48", new Gson().toJson(map1));
        GasResonse jsonObject = new Gson().fromJson(gasInfo,GasResonse.class);
//        String number = new BigInteger(gasInfo.substring(2, gasInfo.length()), 16).toString(10);
        return Numeric.decodeQuantity((String)jsonObject.getResult());
    }


    //http://bitin.io/api/v2/coins?id=1 兑换比例与行情
}
