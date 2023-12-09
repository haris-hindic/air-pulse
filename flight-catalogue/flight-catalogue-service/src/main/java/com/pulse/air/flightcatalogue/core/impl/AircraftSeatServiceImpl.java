package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.AircraftSeatService;
import com.pulse.air.flightcatalogue.core.mapper.AircraftSeatMapper;
import com.pulse.air.flightcatalogue.dao.AircraftRepository;
import com.pulse.air.flightcatalogue.dao.AircraftSeatRepository;
import com.pulse.air.flightcatalogue.dao.model.AircraftSeatEntity;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatRequest;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatResponse;

@Service
public class AircraftSeatServiceImpl extends
		BaseCRUDServiceImpl<AircraftSeatEntity, AircraftSeatResponse, AircraftSeatRequest, BaseSearchRequest, AircraftSeatMapper, AircraftSeatRepository>
		implements AircraftSeatService {

	private AircraftSeatMapper mapper;
	private AircraftSeatRepository aircraftSeatrepository;
	private AircraftRepository aircraftRepository;

	public AircraftSeatServiceImpl(final AircraftSeatMapper mapper, final AircraftSeatRepository repository,
			final AircraftRepository aircraftRepository) {
		super(mapper, repository);
		this.mapper = mapper;
		this.aircraftSeatrepository = repository;
		this.aircraftRepository = aircraftRepository;
	}

	@Override
	public Example<AircraftSeatEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new AircraftSeatEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final AircraftSeatEntity entity, final ApiRequest<AircraftSeatRequest> request)
			throws ApiException {

		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final AircraftSeatEntity entity, final ApiUpdateRequest<AircraftSeatRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

	@Override
	public ApiListResponse<AircraftSeatResponse> findByAircraftId(final ApiRequest<Long> request) throws ApiException {

		var aircraft = aircraftRepository.findById(request.getObject());
		if (Boolean.FALSE.equals(aircraft.isPresent())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Aircraft does not exist!");
		}

		var entities = aircraftSeatrepository.findByAircraftId(request.getObject());
		return new ApiListResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				mapper.entitesToDtos(entities));
	}

}
