package com.pulse.air.commons.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.BaseSearchRequest;

public interface BaseService<TResponse, TSearch extends BaseSearchRequest> {

	public ApiListResponse<TResponse> findAll(final ApiRequest<TSearch> request) throws ApiException;

	public ApiResponse<TResponse> findById(final ApiRequest<Long> request) throws ApiException;
}
