package com.basics.cu.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.DataRequest;

public class RegisterUserRequest extends DataRequest {
private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{smsRequest.phone}")
	private String phone;
	@NotBlank(message = "{registerUserRequest.password}")
	private String password;
	@NotBlank(message = "{registerUserRequest.code}")
	private String code;
	@NotBlank(message = "{registerUserRequest.code}")
	private String parentPhone;
	@NotNull(message = "{emailRequest.emailType}")
	private Integer type ;  // 推荐人类型 0:用户账号 1：手机号
//		@NotBlank(message = "国家ID不能为空！")

	private String country = "e84a151ced294a04b45a8f2086fc7157";
	//	@NotBlank(message = "用户昵称不能为空！")
	private String userName ;
	//	@NotBlank(message = "二级密码不能为空！")
	private String payPass ;
	//	@NotBlank(message = "邮箱不能为空")
	private String email;

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
	public String getParentPhone() {
		return parentPhone;
	}
	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPayPass() {
		return payPass;
	}
	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	
}
