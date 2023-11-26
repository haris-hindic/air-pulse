package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.AircraftSeatService;
import com.pulse.air.flightcatalogue.core.mapper.AircraftSeatMapper;
import com.pulse.air.flightcatalogue.dao.AircraftSeatRepository;
import com.pulse.air.flightcatalogue.dao.model.AircraftSeatEntity;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatRequest;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatResponse;

@Service
public class AircraftSeatServiceImpl
		extends BaseCRUDServiceImpl<AircraftSeatEntity, AircraftSeatResponse, AircraftSeatRequest, AircraftSeatMapper, AircraftSeatRepository>
		implements AircraftSeatService {

	public AircraftSeatServiceImpl(final AircraftSeatMapper mapper, final AircraftSeatRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final AircraftSeatEntity entity, final ApiRequest<AircraftSeatRequest> request) throws ApiException {

		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final AircraftSeatEntity entity, final ApiUpdateRequest<AircraftSeatRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

}
