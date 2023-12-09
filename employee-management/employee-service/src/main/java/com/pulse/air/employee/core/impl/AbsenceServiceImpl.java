package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.AbsenceService;
import com.pulse.air.employee.core.mapper.AbsenceMapper;
import com.pulse.air.employee.dao.AbsenceRepository;
import com.pulse.air.employee.dao.EmployeeRepository;
import com.pulse.air.employee.dao.model.AbsenceEntity;
import com.pulse.air.employee.model.absence.AbsenceRequest;
import com.pulse.air.employee.model.absence.AbsenceResponse;

@Service
public class AbsenceServiceImpl extends
		BaseCRUDServiceImpl<AbsenceEntity, AbsenceResponse, AbsenceRequest, BaseSearchRequest, AbsenceMapper, AbsenceRepository>
		implements AbsenceService {

	private AbsenceRepository absenceRepository;
	private AbsenceMapper absenceMapper;
	private EmployeeRepository employeeRepository;

	public AbsenceServiceImpl(final AbsenceMapper mapper, final AbsenceRepository repository,
			final EmployeeRepository employeeRepository) {
		super(mapper, repository);
		this.absenceMapper = mapper;
		this.employeeRepository = employeeRepository;
		this.absenceRepository = repository;
	}

	@Override
	public Example<AbsenceEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new AbsenceEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final AbsenceEntity entity, final ApiRequest<AbsenceRequest> request) throws ApiException {
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final AbsenceEntity entity, final ApiUpdateRequest<AbsenceRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

	@Override
	public ApiListResponse<AbsenceResponse> findByEmployeeId(final ApiRequest<Long> request) throws ApiException {

		var employee = employeeRepository.findById(request.getObject());
		if (Boolean.FALSE.equals(employee.isPresent())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Employee does not exist!");
		}

		var entities = absenceRepository.findByEmployeeId(request.getObject());
		return new ApiListResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				absenceMapper.entitesToDtos(entities));
	}
}
