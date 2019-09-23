package com.basics.common;

import java.io.Serializable;

public class TokenResponse implements Serializable {
	
	private static final long serialVersionUID = -1108489557428018446L;

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
