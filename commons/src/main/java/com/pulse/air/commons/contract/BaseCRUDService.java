package com.pulse.air.commons.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListRequest;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;

public interface BaseCRUDService<TResponse, TRequest, TSearch extends BaseSearchRequest>
		extends BaseService<TResponse, TSearch> {

	public ApiResponse<TResponse> create(final ApiRequest<TRequest> request) throws ApiException;

	public ApiResponse<TResponse> update(final ApiUpdateRequest<TRequest> request) throws ApiException;

	public ApiResponse<String> delete(final ApiRequest<Long> request) throws ApiException;

	public ApiResponse<String> bulkDelete(final ApiListRequest<Long> request) throws ApiException;
}
