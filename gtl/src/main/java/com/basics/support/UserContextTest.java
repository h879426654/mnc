package com.basics.support;

import java.util.List;

public abstract class UserContextTest {
	public abstract UserContext getUserContext();

	public UserSession createSession(String username) {
		return this.getUserContext().createSession(username);
	}

	public <T> T getService(Class<T> key) {
		return this.getUserContext().getService(key);
	}

	public <T> List<T> getServices(Class<T> key) {
		return this.getUserContext().getServices(key);
	}
}
