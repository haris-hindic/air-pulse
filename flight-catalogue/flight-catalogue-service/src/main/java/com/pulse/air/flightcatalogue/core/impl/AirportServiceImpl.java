package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.AirportService;
import com.pulse.air.flightcatalogue.core.mapper.AirportMapper;
import com.pulse.air.flightcatalogue.dao.AirportRepository;
import com.pulse.air.flightcatalogue.dao.model.AirportEntity;
import com.pulse.air.flightcatalogue.model.airport.AirportRequest;
import com.pulse.air.flightcatalogue.model.airport.AirportResponse;

@Service
public class AirportServiceImpl extends
		BaseCRUDServiceImpl<AirportEntity, AirportResponse, AirportRequest, BaseSearchRequest, AirportMapper, AirportRepository>
		implements AirportService {

	public AirportServiceImpl(final AirportMapper mapper, final AirportRepository repository) {
		super(mapper, repository);
	}

	@Override
	public Example<AirportEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new AirportEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final AirportEntity entity, final ApiRequest<AirportRequest> request) throws ApiException {

		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final AirportEntity entity, final ApiUpdateRequest<AirportRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

}
