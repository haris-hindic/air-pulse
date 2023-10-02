package com.pulse.air.commons.contract;

import com.pulse.air.commons.model.ApiResponse;

public interface BaseCRUDService<TResponse, TRequest> extends BaseService<TResponse> {

	public ApiResponse<TResponse> create(TRequest request);

	public ApiResponse<TResponse> update(final Long id, TRequest request);

	public ApiResponse<String> delete(final Long id);
}
