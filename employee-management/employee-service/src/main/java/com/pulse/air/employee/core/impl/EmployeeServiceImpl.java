package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.model.ApiRequest;
import com.pulse.air.commons.model.ApiUpdateRequest;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.EmployeeService;
import com.pulse.air.employee.core.mapper.EmployeeMapper;
import com.pulse.air.employee.dao.EmployeeRepository;
import com.pulse.air.employee.dao.model.EmployeeEntity;
import com.pulse.air.employee.model.employee.EmployeeRequest;
import com.pulse.air.employee.model.employee.EmployeeResponse;

@Service
public class EmployeeServiceImpl extends
		BaseCRUDServiceImpl<EmployeeEntity, EmployeeResponse, EmployeeRequest, EmployeeMapper, EmployeeRepository>
		implements EmployeeService {

	public EmployeeServiceImpl(final EmployeeMapper mapper, final EmployeeRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final EmployeeEntity entity, final ApiRequest<EmployeeRequest> request) {
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final EmployeeEntity entity, final ApiUpdateRequest<EmployeeRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}
}
