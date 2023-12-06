package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.AirportService;
import com.pulse.air.flightcatalogue.core.mapper.AirportMapper;
import com.pulse.air.flightcatalogue.dao.AirportRepository;
import com.pulse.air.flightcatalogue.dao.model.AirportEntity;
import com.pulse.air.flightcatalogue.model.airport.AirportRequest;
import com.pulse.air.flightcatalogue.model.airport.AirportResponse;

@Service
public class AirportServiceImpl
		extends
		BaseCRUDServiceImpl<AirportEntity, AirportResponse, AirportRequest, BaseSearchRequest, AirportMapper, AirportRepository>
		implements AirportService {

	public AirportServiceImpl(final AirportMapper mapper, final AirportRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final AirportEntity entity, final ApiRequest<AirportRequest> request) throws ApiException {

		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final AirportEntity entity, final ApiUpdateRequest<AirportRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

}
