package com.pulse.air.common.model;

public abstract class BaseRequest {

	private String username;

	protected BaseRequest(final String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}
