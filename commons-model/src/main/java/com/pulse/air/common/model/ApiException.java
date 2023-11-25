package com.pulse.air.common.model;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {
	private static final long serialVersionUID = 1L;

	private HttpStatus status;

	public ApiException(final HttpStatus status, final String message) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(final HttpStatus status) {
		this.status = status;
	}
}
