package com.basics.wallet.controller;

import com.basics.support.auth.HttpClientUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class test {
    public static void main(String args[]){
        try {
            transferERC20Token("0xfAa0529E5d47Ece85E093F5936bFD781de32060d","0xd70DA8FeED9C217D65319602654a32699B1e5921",BigInteger.ONE,
                    "ED2DEC64CDF8B0693ED8EE9F846DEB3C8C7614B02E46186AA90B6FD1CAE4CB8D",

                    "0x2657bd0193a127c93152e043e29980bf16a1b446");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static EthSendTransaction transferERC20Token(String from,
                                                 String to,
                                                 BigInteger value,
                                                 String privateKey,
                                                 String contractAddress) throws Exception {
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
        String hexValue = Numeric.toHexString(signMessage);
        //发送交易
        EthSendTransaction ethSendTransaction = Web3JConfigure.getWeb3JInstance().ethSendRawTransaction(hexValue).sendAsync().get();
        System.out.println(ethSendTransaction.getTransactionHash());
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
    private static BigInteger getNonce(String from) throws ExecutionException, InterruptedException {
        EthGetTransactionCount transactionCount = Web3JConfigure.getWeb3JInstance().ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = transactionCount.getTransactionCount();
        return nonce;
    }

    private static BigInteger requestCurrentGasPrice(){
        Map<String,Object> map1 = new HashMap<>();
        map1.put("jsonrpc","2.0");
        map1.put("method","eth_gasPrice");
        map1.put("id","73");
        JsonArray jsonArray = new JsonArray();
        map1.put("params",jsonArray);
        String gasInfo= HttpClientUtils.postJSON("https://mainnet.infura.io/v3/926d9374c9ad4f91ad6185f68b935a48",new Gson().toJson(map1));
        String number = new BigInteger(gasInfo.substring(2,gasInfo.length()), 16).toString(10);
        return BigInteger.valueOf(Long.parseLong(number));
    }


}
