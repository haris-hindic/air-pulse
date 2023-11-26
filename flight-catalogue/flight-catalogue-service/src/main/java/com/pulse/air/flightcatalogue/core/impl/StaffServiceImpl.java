package com.pulse.air.flightcatalogue.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.StaffService;
import com.pulse.air.flightcatalogue.core.mapper.StaffMapper;
import com.pulse.air.flightcatalogue.dao.StaffRepository;
import com.pulse.air.flightcatalogue.dao.model.StaffEntity;
import com.pulse.air.flightcatalogue.model.staff.StaffRequest;
import com.pulse.air.flightcatalogue.model.staff.StaffResponse;

@Service
public class StaffServiceImpl
		extends BaseCRUDServiceImpl<StaffEntity, StaffResponse, StaffRequest, StaffMapper, StaffRepository>
		implements StaffService {

	public StaffServiceImpl(final StaffMapper mapper, final StaffRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final StaffEntity entity, final ApiRequest<StaffRequest> request) throws ApiException {

		entity.setStatus(Status.ACTIVE.getValue());
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

}
