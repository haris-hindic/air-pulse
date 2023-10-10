package com.pulse.air.commons.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pulse.air.commons.contract.BaseService;
import com.pulse.air.commons.model.ApiListResponse;
import com.pulse.air.commons.model.ApiRequest;
import com.pulse.air.commons.model.ApiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseController<TResponse> {

	BaseService<TResponse> service;

	@GetMapping
	public ApiListResponse<TResponse> findAll() {
		return service.findAll();
	}

	@GetMapping(value = "{id}")
	public ApiResponse<TResponse> findById(@PathVariable final Long id) {

		return service.findById(new ApiRequest<>("system", id));
	}
}
