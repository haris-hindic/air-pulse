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
import com.pulse.air.flightcatalogue.contract.AircraftService;
import com.pulse.air.flightcatalogue.core.mapper.AircraftMapper;
import com.pulse.air.flightcatalogue.dao.AircraftRepository;
import com.pulse.air.flightcatalogue.dao.model.AircraftEntity;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftRequest;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftResponse;

@Service
public class AircraftServiceImpl
		extends
		BaseCRUDServiceImpl<AircraftEntity, AircraftResponse, AircraftRequest, BaseSearchRequest, AircraftMapper, AircraftRepository>
		implements AircraftService {

	public AircraftServiceImpl(final AircraftMapper mapper, final AircraftRepository repository) {
		super(mapper, repository);
	}

	@Override
	public Example<AircraftEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		BaseSearchRequest search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new AircraftEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final AircraftEntity entity, final ApiRequest<AircraftRequest> request) throws ApiException {
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final AircraftEntity entity, final ApiUpdateRequest<AircraftRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

}
