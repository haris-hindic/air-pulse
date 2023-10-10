package com.pulse.air.commons.model;

public abstract class BaseResponse {

	private Long code;
	private String status;

	protected BaseResponse() {
		super();
	}

	protected BaseResponse(final int code, final String status) {
		super();
		this.code = (long) code;
		this.status = status;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(final Long code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

}
