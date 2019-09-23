package com.basics.tr.controller.response;

import java.util.ArrayList;
import java.util.List;

import com.basics.tr.entity.TrTradeMoney;

public class TradeResponse extends TrTradeMoney {

	private static final long serialVersionUID = 3828476477675318428L;

	private String mallCustomerName;
	private String mallCustomerPhone;
	private String mallRealName;
	private String mallCustomerAlipay;
	private String mallCustomerHead;
	private String mallBankCard;
	private String mallBankName;
	
	
	private String buyCustomerName;
	private String buyCustomerPhone;
	private String buyRealName;
	private String buyCustomerAlipay;
	private String buyCustomerHead;
	private String buyBankCard;
	private String buyBankName;
	
	private Integer flagMerchant;// 是否为卖家（1是 0否）
	private List<String> voucherImg = new ArrayList<String>();

	
	
	

	public String getMallBankName() {
		return mallBankName;
	}

	public void setMallBankName(String mallBankName) {
		this.mallBankName = mallBankName;
	}

	public String getBuyBankName() {
		return buyBankName;
	}

	public void setBuyBankName(String buyBankName) {
		this.buyBankName = buyBankName;
	}

	public String getMallCustomerHead() {
		return mallCustomerHead;
	}

	public void setMallCustomerHead(String mallCustomerHead) {
		this.mallCustomerHead = mallCustomerHead;
	}

	public String getMallBankCard() {
		return mallBankCard;
	}

	public void setMallBankCard(String mallBankCard) {
		this.mallBankCard = mallBankCard;
	}

	public String getBuyCustomerHead() {
		return buyCustomerHead;
	}

	public void setBuyCustomerHead(String buyCustomerHead) {
		this.buyCustomerHead = buyCustomerHead;
	}

	public String getBuyBankCard() {
		return buyBankCard;
	}

	public void setBuyBankCard(String buyBankCard) {
		this.buyBankCard = buyBankCard;
	}

	public String getMallCustomerName() {
		return mallCustomerName;
	}

	public void setMallCustomerName(String mallCustomerName) {
		this.mallCustomerName = mallCustomerName;
	}

	public String getMallCustomerPhone() {
		return mallCustomerPhone;
	}

	public void setMallCustomerPhone(String mallCustomerPhone) {
		this.mallCustomerPhone = mallCustomerPhone;
	}

	public String getMallCustomerAlipay() {
		return mallCustomerAlipay;
	}

	public void setMallCustomerAlipay(String mallCustomerAlipay) {
		this.mallCustomerAlipay = mallCustomerAlipay;
	}

	public String getBuyCustomerName() {
		return buyCustomerName;
	}

	public void setBuyCustomerName(String buyCustomerName) {
		this.buyCustomerName = buyCustomerName;
	}

	public String getBuyCustomerPhone() {
		return buyCustomerPhone;
	}

	public void setBuyCustomerPhone(String buyCustomerPhone) {
		this.buyCustomerPhone = buyCustomerPhone;
	}

	public String getBuyCustomerAlipay() {
		return buyCustomerAlipay;
	}

	public void setBuyCustomerAlipay(String buyCustomerAlipay) {
		this.buyCustomerAlipay = buyCustomerAlipay;
	}

	public Integer getFlagMerchant() {
		return flagMerchant;
	}

	public void setFlagMerchant(Integer flagMerchant) {
		this.flagMerchant = flagMerchant;
	}

	public List<String> getVoucherImg() {
		return voucherImg;
	}

	public void setVoucherImg(List<String> voucherImg) {
		this.voucherImg = voucherImg;
	}

	public String getMallRealName() {
		return mallRealName;
	}

	public void setMallRealName(String mallRealName) {
		this.mallRealName = mallRealName;
	}

	public String getBuyRealName() {
		return buyRealName;
	}

	public void setBuyRealName(String buyRealName) {
		this.buyRealName = buyRealName;
	}
	
	



}
