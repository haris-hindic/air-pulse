package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.FlightBookingService;
import com.pulse.air.flightcatalogue.core.mapper.FlightBookingMapper;
import com.pulse.air.flightcatalogue.dao.FlightBookingRepository;
import com.pulse.air.flightcatalogue.dao.model.FlightBookingEntity;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingRequest;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingResponse;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingSearchRequest;

@Service
public class FlightBookignServiceImpl extends
		BaseCRUDServiceImpl<FlightBookingEntity, FlightBookingResponse, FlightBookingRequest, FlightBookingSearchRequest, FlightBookingMapper, FlightBookingRepository>
		implements FlightBookingService {
	private FlightBookingMapper mapper;
	private FlightBookingRepository repository;

	public FlightBookignServiceImpl(final FlightBookingMapper mapper, final FlightBookingRepository repository) {
		super(mapper, repository);
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Example<FlightBookingEntity> getExample(final ApiRequest<FlightBookingSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new FlightBookingEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		if (search.getUserId() != null) {
			example.setUserId(search.getUserId());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final FlightBookingEntity entity, final ApiRequest<FlightBookingRequest> request)
			throws ApiException {
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final FlightBookingEntity entity, final ApiUpdateRequest<FlightBookingRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

	@Override
	public ApiResponse<String> confirm(final ApiRequest<Long> request) throws ApiException {

		var booking = repository.findById(request.getObject());

		if (booking.isPresent()) {
			var entity = booking.get();
			entity.setModified(LocalDateTime.now());
			entity.setModifiedBy(request.getUsername());
			entity.setStatus("Confirmed");
			repository.save(entity);
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					String.format("Successfully confirmed booking with id -> %s", request.getObject()));
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND,
					String.format("There is no booking with id -> %s", request.getObject()));
		}
	}

}
