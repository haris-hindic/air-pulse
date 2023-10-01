package com.pulse.air.commons.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pulse.air.commons.contract.BaseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseController<TResponse> {

	BaseService<TResponse> service;

	@GetMapping
	public List<TResponse> findAll() {
		return service.findAll();
	}

	@GetMapping(value = "{id}")
	public TResponse findById(@PathVariable final Long id) {

		return service.findById(id);
	}
}
