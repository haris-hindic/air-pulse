package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.FlightBookingService;
import com.pulse.air.flightcatalogue.core.mapper.FlightBookingMapper;
import com.pulse.air.flightcatalogue.dao.FlightBookingRepository;
import com.pulse.air.flightcatalogue.dao.model.FlightBookingEntity;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingRequest;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingResponse;

@Service
public class FlightBookignServiceImpl extends
		BaseCRUDServiceImpl<FlightBookingEntity, FlightBookingResponse, FlightBookingRequest, BaseSearchRequest, FlightBookingMapper, FlightBookingRepository>
		implements FlightBookingService {

	public FlightBookignServiceImpl(final FlightBookingMapper mapper, final FlightBookingRepository repository) {
		super(mapper, repository);
	}

	@Override
	public Example<FlightBookingEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new FlightBookingEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
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
}
