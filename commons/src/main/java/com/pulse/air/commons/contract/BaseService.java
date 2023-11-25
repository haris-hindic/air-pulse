package com.pulse.air.commons.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;

public interface BaseService<TResponse> {

	public ApiListResponse<TResponse> findAll() throws ApiException;

	public ApiResponse<TResponse> findById(final ApiRequest<Long> request) throws ApiException;
}
