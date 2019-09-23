package com.basics.or.controller.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenRequest;

/**
 * @author cc
 */
public class PayOrderRequest extends TokenRequest{
	
	private static final long serialVersionUID = -1097480771245055133L;
	
	@NotNull(message = "{payOrderRequest.ids}")
	private List<String> ids = new ArrayList<>();
	
	@NotBlank(message = "{moneyToIntegralRequest.payPass}")
	private String payPass;
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getPayPass() {
		return payPass;
	}

	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}

	
	
}
