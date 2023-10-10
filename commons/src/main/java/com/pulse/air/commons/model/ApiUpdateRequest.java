package com.pulse.air.commons.model;

public class ApiUpdateRequest<T> extends ApiRequest<T> {

	private Long id;

	public ApiUpdateRequest(final String username, final T object, final Long id) {
		super(username, object);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

}
