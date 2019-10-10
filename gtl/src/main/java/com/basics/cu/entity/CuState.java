package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CuState extends CuStateBase{

 public CuState state(String state){
  this.setState(state);
  return this;
 }
}