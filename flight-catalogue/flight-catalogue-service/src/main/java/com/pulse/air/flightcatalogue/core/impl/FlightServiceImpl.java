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
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.FlightService;
import com.pulse.air.flightcatalogue.core.mapper.FlightMapper;
import com.pulse.air.flightcatalogue.dao.FlightRepository;
import com.pulse.air.flightcatalogue.dao.FlightRepositoryCustom;
import com.pulse.air.flightcatalogue.dao.RouteRepository;
import com.pulse.air.flightcatalogue.dao.model.FlightEntity;
import com.pulse.air.flightcatalogue.dao.model.RouteEntity;
import com.pulse.air.flightcatalogue.model.flight.FindReturnFlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;
import com.pulse.air.flightcatalogue.model.flight.FlightSearchRequest;

@Service
public class FlightServiceImpl extends
		BaseCRUDServiceImpl<FlightEntity, FlightResponse, FlightRequest, FlightSearchRequest, FlightMapper, FlightRepository>
		implements FlightService {

	private FlightMapper mapper;
	private FlightRepositoryCustom repositoryCustom;
	private RouteRepository routeRepository;

	public FlightServiceImpl(final FlightMapper mapper, final FlightRepository repository,
			final FlightRepositoryCustom repositoryCustom, final RouteRepository routeRepository) {
		super(mapper, repository);
		this.mapper = mapper;
		this.repositoryCustom = repositoryCustom;
		this.routeRepository = routeRepository;
	}

	@Override
	public ApiListResponse<FlightResponse> findAll(final ApiRequest<FlightSearchRequest> request) {

		return new ApiListResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				mapper.entitesToDtos(repositoryCustom.searchFlights(request.getObject())));
	}

	@Override
	public Example<FlightEntity> getExample(final ApiRequest<FlightSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new FlightEntity();
		var exampleRoute = new RouteEntity();
		example.setRoute(exampleRoute);
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final FlightEntity entity, final ApiRequest<FlightRequest> request) throws ApiException {

		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final FlightEntity entity, final ApiUpdateRequest<FlightRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

	@Override
	public ApiListResponse<FlightResponse> findReturnFligtsByRouteId(final ApiRequest<FindReturnFlightRequest> request)
			throws ApiException {

		var route = routeRepository.findByArrivalAirportIdAndDepartureAirportId(
				request.getObject().getArrivalAirportId(), request.getObject().getDepartureAirportId());

		var searchRequest = new FlightSearchRequest();
		searchRequest.setRouteId(route.getId());
		searchRequest.setStatus(Status.ACTIVE.getValue());
		searchRequest.setDepartOn(request.getObject().getDepartOn());
		if (StringUtils.isEmpty(request.getObject().getDepartOn())) {
			searchRequest.setFlightAfter(request.getObject().getFlightAfter());
		}

		return new ApiListResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				mapper.entitesToDtos(repositoryCustom.searchFlights(searchRequest)));
	}

}
