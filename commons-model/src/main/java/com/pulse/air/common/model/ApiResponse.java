package com.pulse.air.common.model;

public class ApiResponse<T> extends BaseResponse {

	private T data;

	public ApiResponse() {
		super();
	}

	public ApiResponse(final int code, final String status, final T data) {
		super(code, status);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(final T data) {
		this.data = data;
	}

}