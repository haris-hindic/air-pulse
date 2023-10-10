package com.pulse.air.commons.model;

public class ApiRequest<T> extends BaseRequest {

	private T object;

	public ApiRequest(final String username, final T object) {
		super(username);
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	public void setObject(final T object) {
		this.object = object;
	}

}
