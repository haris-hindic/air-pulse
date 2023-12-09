package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.JobTypeService;
import com.pulse.air.employee.core.mapper.JobTypeMapper;
import com.pulse.air.employee.dao.JobTypeRepository;
import com.pulse.air.employee.dao.model.JobTypeEntity;
import com.pulse.air.employee.model.jobtype.JobTypeRequest;
import com.pulse.air.employee.model.jobtype.JobTypeResponse;

@Service
public class JobTypeServiceImpl extends
		BaseCRUDServiceImpl<JobTypeEntity, JobTypeResponse, JobTypeRequest, BaseSearchRequest, JobTypeMapper, JobTypeRepository>
		implements JobTypeService {

	public JobTypeServiceImpl(final JobTypeMapper mapper, final JobTypeRepository repository) {
		super(mapper, repository);
	}

	@Override
	public Example<JobTypeEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new JobTypeEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final JobTypeEntity entity, final ApiRequest<JobTypeRequest> request) throws ApiException {
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final JobTypeEntity entity, final ApiUpdateRequest<JobTypeRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}
}
