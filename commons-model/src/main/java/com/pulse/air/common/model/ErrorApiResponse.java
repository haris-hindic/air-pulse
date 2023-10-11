package com.pulse.air.common.model;

import java.util.List;

public class ErrorApiResponse extends BaseResponse {

	private String message;
	private List<String> errors;

	public ErrorApiResponse(final int code, final String status, final String message, final List<String> errors) {
		super(code, status);
		this.message = message;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(final List<String> errors) {
		this.errors = errors;
	}

}
