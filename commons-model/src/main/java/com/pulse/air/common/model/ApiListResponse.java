package com.pulse.air.common.model;

import java.util.List;

public class ApiListResponse<T> extends BaseResponse {

	private List<T> data;

	public ApiListResponse(final int code, final String status, final List<T> data) {
		super(code, status);
		this.data = data;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(final List<T> data) {
		this.data = data;
	}

}
