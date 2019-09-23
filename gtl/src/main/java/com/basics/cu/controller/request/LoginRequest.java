package com.basics.cu.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.DataRequest;

public class LoginRequest extends DataRequest {
	
	private static final long serialVersionUID = 742680166365561238L;

	@NotBlank(message = "{smsRequest.phone}")
	private String phone;
	@NotBlank(message = "{registerUserRequest.password}")
	private String password;
	
//	@NotBlank(message = "国家不能为空！")
	private String country;
	
	private String code; 
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

}
