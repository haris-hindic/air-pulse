package com.pulse.air.auth.model.nofitication;

import com.pulse.air.common.model.BaseSearchRequest;

public class NotificationSearchRequest extends BaseSearchRequest {
	private static final long serialVersionUID = 1L;

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

}
