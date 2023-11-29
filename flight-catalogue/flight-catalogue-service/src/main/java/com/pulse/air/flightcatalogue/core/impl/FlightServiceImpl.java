package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.FlightService;
import com.pulse.air.flightcatalogue.core.mapper.FlightMapper;
import com.pulse.air.flightcatalogue.dao.FlightRepository;
import com.pulse.air.flightcatalogue.dao.model.FlightEntity;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;

@Service
public class FlightServiceImpl
		extends BaseCRUDServiceImpl<FlightEntity, FlightResponse, FlightRequest, FlightMapper, FlightRepository>
		implements FlightService {

	public FlightServiceImpl(final FlightMapper mapper, final FlightRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final FlightEntity entity, final ApiRequest<FlightRequest> request) throws ApiException {

		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final FlightEntity entity, final ApiUpdateRequest<FlightRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

}