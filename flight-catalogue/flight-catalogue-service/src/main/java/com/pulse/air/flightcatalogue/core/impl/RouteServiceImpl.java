package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.RouteService;
import com.pulse.air.flightcatalogue.core.mapper.RouteMapper;
import com.pulse.air.flightcatalogue.dao.RouteRepository;
import com.pulse.air.flightcatalogue.dao.model.RouteEntity;
import com.pulse.air.flightcatalogue.model.route.RouteRequest;
import com.pulse.air.flightcatalogue.model.route.RouteResponse;

@Service
public class RouteServiceImpl extends
		BaseCRUDServiceImpl<RouteEntity, RouteResponse, RouteRequest, BaseSearchRequest, RouteMapper, RouteRepository>
		implements RouteService {

	public RouteServiceImpl(final RouteMapper mapper, final RouteRepository repository) {
		super(mapper, repository);
	}

	@Override
	public Example<RouteEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new RouteEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public Sort getSort(final ApiRequest<BaseSearchRequest> request) {
		return Sort.by(Sort.Direction.ASC, "departureAirportId");
	}

	@Override
	public void beforeInsert(final RouteEntity entity, final ApiRequest<RouteRequest> request) throws ApiException {
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final RouteEntity entity, final ApiUpdateRequest<RouteRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

}
