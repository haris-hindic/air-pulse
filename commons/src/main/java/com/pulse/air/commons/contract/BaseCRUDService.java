package com.pulse.air.commons.contract;

public interface BaseCRUDService<TResponse, TRequest> extends BaseService<TResponse> {

	public TResponse create(TRequest request);

	public TResponse update(final Long id, TRequest request);

	public String delete(final Long id);
}
