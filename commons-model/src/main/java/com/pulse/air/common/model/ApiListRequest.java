package com.pulse.air.common.model;

import java.util.List;

public class ApiListRequest<T> extends BaseRequest {

	private List<T> object;

	public ApiListRequest(final String username, final List<T> object) {
		super(username);
		this.object = object;
	}

	public List<T> getObject() {
		return object;
	}

	public void setObject(final List<T> object) {
		this.object = object;
	}

}