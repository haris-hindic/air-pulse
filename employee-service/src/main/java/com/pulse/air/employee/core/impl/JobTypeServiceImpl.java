package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.api.contract.JobTypeService;
import com.pulse.air.employee.api.model.jobtype.JobTypeRequest;
import com.pulse.air.employee.api.model.jobtype.JobTypeResponse;
import com.pulse.air.employee.core.mapper.JobTypeMapper;
import com.pulse.air.employee.dao.JobTypeRepository;
import com.pulse.air.employee.dao.model.JobTypeEntity;

@Service
public class JobTypeServiceImpl
		extends BaseCRUDServiceImpl<JobTypeEntity, JobTypeResponse, JobTypeRequest, JobTypeMapper, JobTypeRepository>
		implements JobTypeService {

	public JobTypeServiceImpl(final JobTypeMapper mapper, final JobTypeRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final JobTypeEntity entity) {
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy("System");
		super.beforeInsert(entity);
	}

	@Override
	public void beforeUpdate(final JobTypeEntity entity) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy("System");
		super.beforeUpdate(entity);
	}
}
