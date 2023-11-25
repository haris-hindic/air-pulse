package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.AbsenceService;
import com.pulse.air.employee.core.mapper.AbsenceMapper;
import com.pulse.air.employee.dao.AbsenceRepository;
import com.pulse.air.employee.dao.model.AbsenceEntity;
import com.pulse.air.employee.model.absence.AbsenceRequest;
import com.pulse.air.employee.model.absence.AbsenceResponse;

@Service
public class AbsenceServiceImpl
		extends BaseCRUDServiceImpl<AbsenceEntity, AbsenceResponse, AbsenceRequest, AbsenceMapper, AbsenceRepository>
		implements AbsenceService {

	public AbsenceServiceImpl(final AbsenceMapper mapper, final AbsenceRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final AbsenceEntity entity, final ApiRequest<AbsenceRequest> request) throws ApiException {
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final AbsenceEntity entity, final ApiUpdateRequest<AbsenceRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}
}
