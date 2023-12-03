package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.JobTypeService;
import com.pulse.air.employee.core.mapper.JobTypeMapper;
import com.pulse.air.employee.dao.JobTypeRepository;
import com.pulse.air.employee.dao.model.JobTypeEntity;
import com.pulse.air.employee.model.jobtype.JobTypeRequest;
import com.pulse.air.employee.model.jobtype.JobTypeResponse;

@Service
public class JobTypeServiceImpl
		extends BaseCRUDServiceImpl<JobTypeEntity, JobTypeResponse, JobTypeRequest, JobTypeMapper, JobTypeRepository>
		implements JobTypeService {

	public JobTypeServiceImpl(final JobTypeMapper mapper, final JobTypeRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final JobTypeEntity entity, final ApiRequest<JobTypeRequest> request) throws ApiException {
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final JobTypeEntity entity, final ApiUpdateRequest<JobTypeRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}
}
