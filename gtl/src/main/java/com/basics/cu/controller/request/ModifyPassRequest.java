package com.basics.cu.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenRequest;

public class ModifyPassRequest extends TokenRequest {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{smsRequest.phone}")
	private String phone;
//	@NotBlank(message = "密码不能为空！")
	private String newpassword1;
	@NotBlank(message = "{modifyPassRequest.newPassword}")
	private String newPassword2;
//	@NotBlank(message = "短信验证码不能为空！")
	private String code;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOldPassword() {
		return newpassword1;
	}

	public void setOldPassword(String oldPassword) {
		this.newpassword1 = oldPassword;
	}

	public String getNewPassword() {
		return newPassword2;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword2 = newPassword;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
