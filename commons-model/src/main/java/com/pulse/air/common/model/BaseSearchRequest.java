package com.pulse.air.common.model;

import java.io.Serializable;

public class BaseSearchRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}
}
