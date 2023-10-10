package com.pulse.air.commons.contract;

import com.pulse.air.commons.model.ApiListResponse;
import com.pulse.air.commons.model.ApiRequest;
import com.pulse.air.commons.model.ApiResponse;

public interface BaseService<TResponse> {

	public ApiListResponse<TResponse> findAll();

	public ApiResponse<TResponse> findById(final ApiRequest<Long> request);
}
