package com.guc2.business;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.SeedCalculator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;
import io.github.novacrypto.hashing.Sha256;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.web3j.crypto.WalletUtils.generateWalletFile;

@EnableAutoConfiguration
@RestController
public class GucController {
    @GetMapping(value ="/test")
    public String test(String a ){
        System.out.println("test");
            return a;
    }

    //http://bitin.io/api/v2/coins?id=1 兑换比例与行情

    @GetMapping(value ="/createMemories")
    public MemoriesBean createWallet(@RequestParam String password){

        File fileDir = new File("D:\\keystore");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        MemoriesBean memoriesBean = new MemoriesBean();
        try {
        StringBuilder sb = new StringBuilder();
        byte[] entropy = new byte[Words.TWELVE.byteLength()];
        new SecureRandom().nextBytes(entropy);
        new MnemonicGenerator(English.INSTANCE).createMnemonic(entropy, sb::append);
        String mnemonics = sb.toString();

        memoriesBean.setCode(0);
        memoriesBean.setData(mnemonics);
        memoriesBean.setMsg("success");
        //password为输入的钱包密码
        byte[] seed = MnemonicUtils.generateSeed(mnemonics, password);
        ECKeyPair privateKey = ECKeyPair.create(Sha256.sha256(seed));
        String walletFile = null;

            walletFile = generateWalletFile(password, privateKey, fileDir, false);
            Bip39Wallet bip39Wallet = new Bip39Wallet(walletFile, mnemonics);

            Credentials credentials = WalletUtils.loadBip39Credentials(password,bip39Wallet.getMnemonic());
            String msg = "\n助记词:\n" + bip39Wallet.getMnemonic()
                    +"\naddress:\n" + credentials.getAddress()
                    + "\nprivateKey:\n" + Numeric.encodeQuantity(credentials.getEcKeyPair().getPrivateKey())
                    + "\nPublicKey:\n" + Numeric.encodeQuantity(credentials.getEcKeyPair().getPublicKey());

            System.out.println(mnemonics);
            memoriesBean.setPrivateKey(credentials.getEcKeyPair().getPrivateKey()+"");
            memoriesBean.setAddress(credentials.getAddress());
            memoriesBean.setPublicKey(credentials.getEcKeyPair().getPublicKey()+"");
        }catch (Exception e) {
            memoriesBean.setCode(1);
            memoriesBean.setMsg("failed");
            memoriesBean.setData(null);
            e.printStackTrace();
        }

        return memoriesBean;
    }

    @GetMapping(value ="/importMemories")
    public MemoriesBean importMemories(@RequestParam String menmory,@RequestParam String passwd){
        MemoriesBean memoriesBean = new MemoriesBean();
        try {
        List mnemonicList = Arrays.asList(menmory.split(" "));
        byte[] seed = new SeedCalculator()
                .withWordsFromWordList(English.INSTANCE)
                .calculateSeed(mnemonicList, passwd);
        ECKeyPair ecKeyPair = ECKeyPair.create(Sha256.sha256(seed));
        String privateKey = ecKeyPair.getPrivateKey().toString(16);
        String publicKey = ecKeyPair.getPublicKey().toString(16);
        String address = "0x" + Keys.getAddress(publicKey);
        //创建钱包地址与密钥
        String fileName = null;
            fileName = WalletUtils.generateWalletFile(passwd, ecKeyPair, new File("D:\\keystore"), false);
            memoriesBean.setCode(0);
            memoriesBean.setData(menmory);
            memoriesBean.setMsg("success");
            memoriesBean.setPrivateKey(privateKey);
            memoriesBean.setAddress(address);
            memoriesBean.setPublicKey(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
            memoriesBean.setCode(1);
            memoriesBean.setMsg("failed");
            memoriesBean.setData(null);
        }

        return memoriesBean;
    }

    @GetMapping(value ="/getTradeInfo")
    public String getTradeInfo(){
        Map<String,String> map = new HashMap<>();
        map.put("id","1");
        String info = HttpClientUtils.invokeGet("http://bitin.io:8091/api/v2/coins", map);
//        String info = HttpClientUtils.invokeGet("http://bitin.io:8091/api/v1/coinList", map);
        return info;
    }

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

    private String netMode="https://ropsten";//ropsten//mainnet
    private String from="0xd70DA8FeED9C217D65319602654a32699B1e5921";
//    private String gucContactAddress="0x4a6544008aadfc35f7ab58394a9df174f44bc6ff";// 线上
          private String gucContactAddress="0x7903db2485c09b7b403fcc29a8a36063fbba6fd1";// 测试
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

}
