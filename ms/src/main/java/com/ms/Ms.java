package com.ms;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import net.sf.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@EnableAutoConfiguration
public class Ms {
    @RequestMapping(value ="/test",method = RequestMethod.GET)
    public void test(){
        System.out.println("testtesttest");
    }

    @RequestMapping(value ="/transferToken",method = RequestMethod.POST)
    public TxInfoResponse transferERC20Token(String from,
                                                 String to,
                                                 BigInteger value) throws Exception {
        TxInfoResponse txInfoResponse = new TxInfoResponse();
        String hexValue = null;
        try {
            HttpService httpService = new HttpService("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48");
            Web3j web3j = Web3j.build(httpService);
            //加载转账所需的凭证，用私钥
            Credentials credentials = Credentials.create("ED2DEC64CDF8B0693ED8EE9F846DEB3C8C7614B02E46186AA90B6FD1CAE4CB8D");
            //获取nonce，交易笔数
            BigInteger nonce = getNonce(from,web3j);
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
                    "0x2657bd0193a127c93152e043e29980bf16a1b446", encodedFunction);

            //签名Transaction，这里要对交易做签名
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            hexValue = Numeric.toHexString(signMessage);
            EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();

            txInfoResponse.setTxHash(ethSendTransaction.getTransactionHash());
            txInfoResponse.setTxTime(System.currentTimeMillis() / 1000 + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return txInfoResponse;
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
        map1.put("id", "73");
        JsonArray jsonArray = new JsonArray();
        map1.put("params", jsonArray);
        String gasInfo = HttpClientUtils.postJSON("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48", new Gson().toJson(map1));
        String number = new BigInteger(gasInfo.substring(2, gasInfo.length()), 16).toString(10);
        return BigInteger.valueOf(Long.parseLong(number));
    }

}
