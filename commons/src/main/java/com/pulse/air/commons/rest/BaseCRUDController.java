package com.pulse.air.commons.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pulse.air.commons.contract.BaseCRUDService;

import jakarta.validation.Valid;

@SuppressWarnings("unchecked")
public class BaseCRUDController<TResponse, TRequest> extends BaseController<TResponse> {

	public BaseCRUDController(final BaseCRUDService<TResponse, TRequest> service) {
		super(service);
	}

	@PostMapping
	public TResponse create(@Valid @RequestBody final TRequest request) {

		return ((BaseCRUDService<TResponse, TRequest>) service).create(request);
	}

	@PutMapping(value = "{id}")
	public TResponse create(@PathVariable final Long id, @Valid @RequestBody final TRequest request) {
		return ((BaseCRUDService<TResponse, TRequest>) service).update(id, request);
	}

	@DeleteMapping(value = "{id}")
	public String delete(@PathVariable final Long id) {
		return ((BaseCRUDService<TResponse, TRequest>) service).delete(id);
	}
}
