package com.basics.gty.entity;

import java.math.BigDecimal;

public class test {
    public static void main(String[] args){
        BigDecimal bigDecimal = new BigDecimal("1");
        System.out.println(bigDecimal.subtract(new BigDecimal("0.1")));
    }
}
