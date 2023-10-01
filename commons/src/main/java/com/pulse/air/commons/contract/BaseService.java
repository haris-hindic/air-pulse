package com.pulse.air.commons.contract;

import java.util.List;

public interface BaseService<TResponse> {

	public List<TResponse> findAll();

	public TResponse findById(final Long id);
}
