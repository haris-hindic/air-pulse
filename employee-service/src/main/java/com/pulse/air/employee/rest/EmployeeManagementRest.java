package com.pulse.air.employee.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.employee.api.model.EmployeeRequest;
import com.pulse.air.employee.api.model.EmployeeResponse;

@RestController
@RequestMapping("employee")
public class EmployeeManagementRest extends BaseCRUDController<EmployeeResponse, EmployeeRequest> {

	public EmployeeManagementRest(final BaseCRUDService<EmployeeResponse, EmployeeRequest> service) {
		super(service);
	}

}
