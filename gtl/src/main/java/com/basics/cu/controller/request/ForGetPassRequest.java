package com.basics.cu.controller.request;

import com.basics.common.DataRequest;
import org.hibernate.validator.constraints.NotBlank;

public class ForGetPassRequest extends DataRequest {
	
	private static final long serialVersionUID = 742680166365561238L;

	@NotBlank(message = "手机号不能为空")
	private String phone;

	@NotBlank(message = "密码不能为空")
	private String password;

	@NotBlank(message = "确认密码不能为空")
	private String repeatPassword;

	@NotBlank(message = "验证码不能为空")
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
