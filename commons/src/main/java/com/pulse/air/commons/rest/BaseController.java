package com.pulse.air.commons.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseController<TResponse, TSearch extends BaseSearchRequest> {

	BaseService<TResponse, TSearch> service;

	@GetMapping
	public ApiListResponse<TResponse> findAll(final TSearch search)
			throws ApiException {
		return service.findAll(new ApiRequest<>("system", search));
	}

	@GetMapping(value = "{id}")
	public ApiResponse<TResponse> findById(@PathVariable final Long id) throws ApiException {

		return service.findById(new ApiRequest<>("system", id));
	}
}
