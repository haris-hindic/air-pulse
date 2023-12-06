package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.StaffService;
import com.pulse.air.flightcatalogue.core.mapper.StaffMapper;
import com.pulse.air.flightcatalogue.dao.AircraftRepository;
import com.pulse.air.flightcatalogue.dao.StaffRepository;
import com.pulse.air.flightcatalogue.dao.model.StaffEntity;
import com.pulse.air.flightcatalogue.model.staff.StaffRequest;
import com.pulse.air.flightcatalogue.model.staff.StaffResponse;

@Service
public class StaffServiceImpl
		extends
		BaseCRUDServiceImpl<StaffEntity, StaffResponse, StaffRequest, BaseSearchRequest, StaffMapper, StaffRepository>
		implements StaffService {

	private StaffMapper mapper;
	private StaffRepository repository;
	private AircraftRepository aircraftRepository;

	public StaffServiceImpl(final StaffMapper mapper, final StaffRepository repository,final AircraftRepository aircraftRepository) {
		super(mapper, repository);
		this.mapper = mapper;
		this.repository = repository;
		this.aircraftRepository = aircraftRepository;
	}

	@Override
	public void beforeInsert(final StaffEntity entity, final ApiRequest<StaffRequest> request) throws ApiException {
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final StaffEntity entity, final ApiUpdateRequest<StaffRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

	@Override
	public ApiListResponse<StaffResponse> findByAircraftId(final ApiRequest<Long> request) throws ApiException {

		var aircraft = aircraftRepository.findById(request.getObject());
		if (Boolean.FALSE.equals(aircraft.isPresent())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Aircraft does not exist!");
		}

		var entities = repository.findByAircraftId(request.getObject());
		return new ApiListResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				mapper.entitesToDtos(entities));
	}

}
