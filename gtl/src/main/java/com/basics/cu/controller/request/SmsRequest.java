package com.basics.cu.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.DataRequest;

public class SmsRequest extends DataRequest {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{smsRequest.phone}")
//	@Pattern(regexp = Constant.MOBILE_REGX_2, message = "手机号错误")
	private String phone;
	@NotNull(message = "{smsRequest.smsType}")
	private Integer smsType;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSmsType() {
		return smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

}
