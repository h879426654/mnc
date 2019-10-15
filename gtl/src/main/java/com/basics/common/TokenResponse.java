package com.basics.common;

import java.io.Serializable;

public class TokenResponse implements Serializable {
	
	private static final long serialVersionUID = -1108489557428018446L;

	private String token;

	//推荐人 y:有 n:没有
	private String isMan;

	//激活 y:激活 n:未激活
	private String act;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsMan() {
		return isMan;
	}

	public void setIsMan(String isMan) {
		this.isMan = isMan;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}
}
