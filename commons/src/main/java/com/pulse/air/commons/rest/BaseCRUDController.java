package com.pulse.air.commons.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListRequest;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.contract.BaseCRUDService;

import jakarta.validation.Valid;

@SuppressWarnings("unchecked")
public class BaseCRUDController<TResponse, TRequest> extends BaseController<TResponse> {

	public BaseCRUDController(final BaseCRUDService<TResponse, TRequest> service) {
		super(service);
	}

	@PostMapping
	public ApiResponse<TResponse> create(@Valid @RequestBody final TRequest request,
			@RequestHeader("AP_USER") final String user) throws ApiException {

		return ((BaseCRUDService<TResponse, TRequest>) service).create(new ApiRequest<>(user, request));
	}

	@PutMapping(value = "{id}")
	public ApiResponse<TResponse> create(@PathVariable final Long id, @Valid @RequestBody final TRequest request,
			@RequestHeader("AP_USER") final String user)
			throws ApiException {
		return ((BaseCRUDService<TResponse, TRequest>) service).update(new ApiUpdateRequest<>(user, request, id));
	}

	@DeleteMapping(value = "{id}")
	public ApiResponse<String> delete(@PathVariable final Long id, @RequestHeader("AP_USER") final String user)
			throws ApiException {
		return ((BaseCRUDService<TResponse, TRequest>) service).delete(new ApiRequest<>(user, id));
	}

	@DeleteMapping("/bulk-delete")
	public ApiResponse<String> bulkDelete(@RequestParam final List<Long> ids,
			@RequestHeader("AP_USER") final String user) throws ApiException {
		return ((BaseCRUDService<TResponse, TRequest>) service).bulkDelete(new ApiListRequest<>(user, ids));
	}
}
