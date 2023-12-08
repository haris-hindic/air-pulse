package com.pulse.air.employee.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.EmployeeService;
import com.pulse.air.employee.core.mapper.EmployeeMapper;
import com.pulse.air.employee.dao.EmployeeRepository;
import com.pulse.air.employee.dao.model.EmployeeEntity;
import com.pulse.air.employee.model.employee.EmployeeRequest;
import com.pulse.air.employee.model.employee.EmployeeResponse;

@Service
public class EmployeeServiceImpl extends
		BaseCRUDServiceImpl<EmployeeEntity, EmployeeResponse, EmployeeRequest, BaseSearchRequest, EmployeeMapper, EmployeeRepository>
		implements EmployeeService {

	public EmployeeServiceImpl(final EmployeeMapper mapper, final EmployeeRepository repository) {
		super(mapper, repository);
	}

	@Override
	public Example<EmployeeEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		BaseSearchRequest search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new EmployeeEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final EmployeeEntity entity, final ApiRequest<EmployeeRequest> request)
			throws ApiException {
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
