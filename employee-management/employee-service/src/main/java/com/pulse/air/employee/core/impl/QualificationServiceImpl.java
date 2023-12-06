package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.QualificationService;
import com.pulse.air.employee.core.mapper.QualificationMapper;
import com.pulse.air.employee.dao.EmployeeRepository;
import com.pulse.air.employee.dao.QualificationRepository;
import com.pulse.air.employee.dao.model.QualificationEntity;
import com.pulse.air.employee.model.qualification.QualificationRequest;
import com.pulse.air.employee.model.qualification.QualificationResponse;

@Service
public class QualificationServiceImpl extends
		BaseCRUDServiceImpl<QualificationEntity, QualificationResponse, QualificationRequest, BaseSearchRequest, QualificationMapper, QualificationRepository>
		implements QualificationService {

	private EmployeeRepository employeeRepository;

	public QualificationServiceImpl(final QualificationMapper mapper, final QualificationRepository repository,
			final EmployeeRepository employeeRepository) {
		super(mapper, repository);
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void beforeInsert(final QualificationEntity entity, final ApiRequest<QualificationRequest> request)
			throws ApiException {

		var employee = employeeRepository.findById(request.getObject().getEmployeeId());
		if (Boolean.FALSE.equals(employee.isPresent())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Employee does not exist!");
		}

		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final QualificationEntity entity, final ApiUpdateRequest<QualificationRequest> request) {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

}
