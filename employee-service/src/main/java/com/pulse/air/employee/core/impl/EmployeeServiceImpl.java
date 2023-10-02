package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.api.contract.EmployeeService;
import com.pulse.air.employee.api.model.employee.EmployeeRequest;
import com.pulse.air.employee.api.model.employee.EmployeeResponse;
import com.pulse.air.employee.core.mapper.EmployeeMapper;
import com.pulse.air.employee.dao.EmployeeRepository;
import com.pulse.air.employee.dao.model.EmployeeEntity;

@Service
public class EmployeeServiceImpl extends
		BaseCRUDServiceImpl<EmployeeEntity, EmployeeResponse, EmployeeRequest, EmployeeMapper, EmployeeRepository>
		implements EmployeeService {

	public EmployeeServiceImpl(final EmployeeMapper mapper, final EmployeeRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final EmployeeEntity entity) {
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy("System");
		super.beforeInsert(entity);
	}

	@Override
	public void beforeUpdate(final EmployeeEntity entity) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy("System");
		super.beforeUpdate(entity);
	}
}
