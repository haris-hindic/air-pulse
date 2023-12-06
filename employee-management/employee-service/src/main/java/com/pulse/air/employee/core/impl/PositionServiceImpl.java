package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.PositionService;
import com.pulse.air.employee.core.mapper.PositionMapper;
import com.pulse.air.employee.dao.EmployeeRepository;
import com.pulse.air.employee.dao.JobTypeRepository;
import com.pulse.air.employee.dao.PositionRepository;
import com.pulse.air.employee.dao.model.PositionEntity;
import com.pulse.air.employee.model.position.PositionRequest;
import com.pulse.air.employee.model.position.PositionResponse;

@Service
public class PositionServiceImpl extends
		BaseCRUDServiceImpl<PositionEntity, PositionResponse, PositionRequest, BaseSearchRequest, PositionMapper, PositionRepository>
		implements PositionService {

	private EmployeeRepository employeeRepository;
	private JobTypeRepository jobTypeRepository;
	private PositionRepository positionRepository;
	private PositionMapper positionMapper;

	public PositionServiceImpl(final PositionMapper mapper, final PositionRepository repository,
			final EmployeeRepository employeeRepository, final JobTypeRepository jobTypeRepository) {
		super(mapper, repository);
		this.employeeRepository = employeeRepository;
		this.jobTypeRepository = jobTypeRepository;
		this.positionRepository = repository;
		this.positionMapper = mapper;
	}

	@Override
	public void beforeInsert(final PositionEntity entity, final ApiRequest<PositionRequest> request)
			throws ApiException {

		var employee = employeeRepository.findById(request.getObject().getEmployeeId());
		if (Boolean.FALSE.equals(employee.isPresent())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Employee does not exist!");
		}

		var jobType = jobTypeRepository.findById(request.getObject().getJobTypeId());
		if (Boolean.FALSE.equals(jobType.isPresent())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Job Type does not exist!");
		}

		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final PositionEntity entity, final ApiUpdateRequest<PositionRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

	@Override
	public ApiListResponse<PositionResponse> findByEmployeeId(final ApiRequest<Long> request) throws ApiException {

		var employee = employeeRepository.findById(request.getObject());
		if (Boolean.FALSE.equals(employee.isPresent())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Employee does not exist!");
		}

		var entities = positionRepository.findByEmployeeId(request.getObject());
		return new ApiListResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				positionMapper.entitesToDtos(entities));
	}

}
