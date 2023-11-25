package com.pulse.air.commons.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.contract.BaseCRUDService;

import jakarta.validation.Valid;

@SuppressWarnings("unchecked")
public class BaseCRUDController<TResponse, TRequest> extends BaseController<TResponse> {

	private static final String SYSTEM = "system";

	public BaseCRUDController(final BaseCRUDService<TResponse, TRequest> service) {
		super(service);
	}

	@PostMapping
	public ApiResponse<TResponse> create(@Valid @RequestBody final TRequest request) throws ApiException {

		return ((BaseCRUDService<TResponse, TRequest>) service).create(new ApiRequest<>(SYSTEM, request));
	}

	@PutMapping(value = "{id}")
	public ApiResponse<TResponse> create(@PathVariable final Long id, @Valid @RequestBody final TRequest request)
			throws ApiException {
		return ((BaseCRUDService<TResponse, TRequest>) service).update(new ApiUpdateRequest<>(SYSTEM, request, id));
	}

	@DeleteMapping(value = "{id}")
	public ApiResponse<String> delete(@PathVariable final Long id) throws ApiException {
		return ((BaseCRUDService<TResponse, TRequest>) service).delete(new ApiRequest<>(SYSTEM, id));
	}
}
