package com.ms;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TransferBean {
public static void main(String[] args){
    //100000
//    String a ="0.000021";
//    double d = Double.parseDouble(a)*100000;
//    BigInteger bigInteger = BigInteger.valueOf(Long.parseLong(d+""));

    BigDecimal bigDecimal = new BigDecimal(100000);
    BigDecimal bigValue = new BigDecimal("0.2");
    BigInteger bigInteger = bigDecimal.multiply(bigValue).toBigInteger();

    System.out.println(bigInteger);
}
}
